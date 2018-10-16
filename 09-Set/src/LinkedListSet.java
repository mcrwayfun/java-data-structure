import java.util.ArrayList;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/17
 * @description
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> linkedList;

    public LinkedListSet(){
        linkedList = new LinkedList<>();
    }

    // 返回集合是否为空
    @Override
    public boolean isEmpty(){
        return linkedList.isEmpty();
    }

    // 返回集合中元素的个数
    @Override
    public int getSize(){
        return linkedList.getSize();
    }

    // 添加元素
    @Override
    public void add(E e){

        if(!linkedList.contains(e))
            linkedList.addFirst(e);
    }

    // 移除元素
    @Override
    public void remove(E e){
        linkedList.removeElement(e);
    }

    // 返回集合中是否包含该元素
    @Override
    public boolean contains(E e){
        return linkedList.contains(e);
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            LinkedListSet<String> set1 = new LinkedListSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();

        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        if(FileOperation.readFile("a-tale-of-two-cities.txt", words2)){
            System.out.println("Total words: " + words2.size());

            LinkedListSet<String> set2 = new LinkedListSet<>();
            for(String word: words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }
    }
}
