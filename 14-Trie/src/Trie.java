import java.util.TreeMap;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/10/24
 */
public class Trie {

    private class Node {
        // 标识是否为单词
        private boolean isWord;
        private TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = null;
        }

        public Node() {
            this(false);
        }
    }

    private Node root;

    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    // 返回Trie中有多少个单词
    public int getSize() {
        return size;
    }

    // 向Trie中添加一个单词
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 当前字母c不存在
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }

        // 此时cur已经是单词的末尾
        // 若其不是单词，则标记为单词
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    // 判断Trie是否包含单词word
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 当前字母c不存在
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        // 遍历完word，根据isWord判断是不是单词
        return cur.isWord;
    }

    // 查询Trie中是否有单词以prefix为前缀
    public boolean startsWith(String prefix){
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            // 当前字母c不存在
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        // 遍历完word，根据isWord判断是不是单词
        return true;
    }
}
