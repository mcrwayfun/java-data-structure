# jdk1.8 ArrayList源码解析

## 1. 概括
```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
```

ArrayList继承父类以及实现的接口

```java
    /**
     * 默认初始化容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 被用于创建空实例的共享空数组
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 被用于创建默认大小的空实例的共享数组实例。
     * 其与EMPTY_ELEMENTDATA的区别是：当我们向数组中添加第一个元素时，便知道数组该扩充多少。
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * Object[]类型的数组，保存了添加到ArrayList中的元素。ArrayList的容量是该Object[]类型数组的长度
     * 当第一个元素被添加时，任何空ArrayList中的elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA将会被
     * 扩充到DEFAULT_CAPACITY（默认容量）。
     */
    transient Object[] elementData; // non-private to simplify nested class access

    /**
     * 表中拥有的元素个数
     */
    private int size;
```

## 2. 序列化
ArrayList是基于数组实现的，其具有动态扩容的特性（在需要的时候才扩容），因此保存元素的数组并不一定全被使用，那么便没有必要对整个数组进行序列化，只需要序列化部分数组。

> transient Object[] elementData; // non-private to simplify nested class access

使用transient来修饰保存元素的数组elementData，该关键字声明数组默认不会被序列化。当然ArrayList重写了writeObject() 和 readObject()来控制只序列化数组中有元素填充那部分内容。

## 3. 扩容

讲扩容先，先了解一下两个成员变量
```java
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    transient Object[] elementData; // non-private to simplify nested class access	
```
elementData是我们用来存储数据的数组，而DEFAULTCAPACITY_EMPTY_ELEMENTDATA是一个空数组，在无参构造函数中用到

```java
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }	
```
**当我们用ArrayList()来获取list对象时，此时elementData是一个空的数组对象，容量为0，这时候并没有分配内存空间。那么什么时候进行分配呢？**

```java
    private void ensureCapacityInternal(int minCapacity) {
        // 没有经过add操作扩容,elementData是一个空数组
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            // 获取扩容的期望值
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        // 期望扩容值大于当前elementData数组长度才需要执行扩容
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    /**
     * 要分配的数组大小
     * 有些VM会在数组中保留标题字
     * 尝试给数组分配更大的长度可能会导致OutOfMemoryError
     * 请求的数组大小超过了限制
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 增加ArrayList的容量，确保它至少能够容纳期望容量的元素
     *
     * @param minCapacity 期望容量
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        // 原先的数组长度
        int oldCapacity = elementData.length;
        // oldCapacity >> 1，左移1位，相当于除2
        // newCapacity = oldCapacity + 0.5oldCapacity = 1.5oldCapacity
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // newCapacity小于期望容量minCapacity，则按照期望容量来扩容
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        // newCapacity超过数组长度最大值
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * 超出数组最大容量
     * @param minCapacity
     * @return
     */
    private static int hugeCapacity(int minCapacity) {
        // 溢出情况下存在OOM
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }
```
**当我们第一次添加元素时，默认使用`DEFAULT_CAPACITY`来为elementData分配存储空间**，此后则会根据期望值**size + 1**来判断是否需要扩容。

而后，每当向数组中添加元素时，都要去检查添加元素后的个数是否会超出当前数组的长度，如果超出，数组将会调用grow()方法进行扩容，以满足添加数据的需求。新增的容量为 `oldCapacity + (oldCapacity >> 1)`，也就是旧容量的1.5倍。

扩容操作需要调用 Arrays.copyOf() 把原数组整个复制到新数组中，这个操作代价高，因此最好在创建 ArrayList 对象时就指定大概的容量大小，减少扩容操作的次数。

值得注意的是，jdk1.6中的扩容方法和1.8的不同，其扩容的长度为**原来的1.5倍+1**
```java
    // 若ArrayList的容量不足以容纳当前的全部元素，设置 新的容量=“(原始容量x3)/2 + 1”
    public void ensureCapacity(int minCapacity) {
        modCount++;  
        int oldCapacity = elementData.length;
        if (minCapacity > oldCapacity) {
            Object oldData[] = elementData;
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }	
```

## 4. 添加和删除

add(E e)操作若不涉及扩容，时间的复杂度为常数，而一旦涉及到扩容，就涉及到数组的移动，消耗就会变成线性时间了。

add(int index, E element)操作需要调用System.arraycopy()将index+1后面的元素向后移动一位，操作的时间复杂度为O(n)，代价非常高

```java
    
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);

        // 扩容
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }
```
无论是哪种remove方法，都需要调用System.arraycopy()方法将index+1后的元素复制到index开始的位置上，操作的时间复杂度为O(1)，操作的代价也是十分高昂的
```java
    public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        // numMoved = 0 时，移除元素为末尾元素
        int numMoved = size - index - 1;
        // 移除末尾元素，数组就不用移动
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    public boolean remove(Object o) {
        // o为null值
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            // o为数值
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }
```
## 5. 查找
因为ArrayList是基于数组来实现的，所以查找所花费的时间为常数时间

## 6. Fail-Fast
ArrayList继承了AbstractList类，在该类中有一个变量：
> protected transient int modCount = 0;

这个变量用来记录ArrayList结构上的变化次数。结构上的变化包括新增或删除至少一个元素，或者调整数组的大小（扩容），如果仅仅设置数组值是不算结构变化的，比如调用set()方法。

当进行序列化或者利用迭代器进行操作时，需要比较modCount数值是否发生改变，若改变了则会抛出ConcurrentModificationException