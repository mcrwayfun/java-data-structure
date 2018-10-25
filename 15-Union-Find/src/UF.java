/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/10/25
 */
public interface UF {
    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
