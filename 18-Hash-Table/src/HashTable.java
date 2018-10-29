import java.util.TreeMap;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/29
 * @description 哈希表（本身是无序的，但这里底层使用TreeMap来实现，所以K需要继承Comparable）
 */
public class HashTable<K extends Comparable<K>, V> {

    // 最适素数表
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    // hash碰撞上限
    private static final int upperTol = 10;
    // hash碰撞下限
    private static final int lowerTol = 2;
    // 对应capacity数组的下标
    private int capacityIndex = 0;

    private TreeMap<K, V>[] hashtable;

    private int size;
    // 链表数组的长度
    private int M;

    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++)
            hashtable[i] = new TreeMap<>();
    }

    // hash算法
    private int hash(K key) {
        // 0x7fffffff表示31个1
        // 与运算，1&1=1、其余均为0
        // key & 0x7fffffff 表示将key哈希值变为正数，若原来为正数则不变
        // 最高位为符号位，0正1负（哈希后可正可负）
        return (key.hashCode() & 0x7fffffff) & M;
    }

    // 返回hash表的元素个数
    public int getSize() {
        return size;
    }

    // 添加元素
    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key))
            map.put(key, value);
        else {
            map.put(key, value);
            size++;

            // 动态扩容
            if (size >= upperTol * M && capacityIndex + 1 < capacity.length)
                resize(capacity[++capacityIndex]);

        }
    }

    // 移除指定key的元素
    public V remove(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            // 缩容
            if (size <= lowerTol * M && capacityIndex - 1 >= 0)
                resize(capacity[--capacityIndex]);
        }

        return ret;
    }

    public void set(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        if(!map.containsKey(key))
            throw new IllegalArgumentException(key + " doesn't exist!");

        map.put(key, value);
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashtable[hash(key)].get(key);
    }

    // 根据newM改变数组长度
    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++)
            newHashTable[i] = new TreeMap<>();

        // 用于循环
        int oldM = M;
        // hash中使用了变量M，所以需要改变this.M的值
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key : map.keySet())
                newHashTable[hash(key)].put(key, map.get(key));
        }

        this.hashtable = newHashTable;
    }
}
