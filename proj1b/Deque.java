public interface Deque<Item> {

    void addFirst(Item T);
    void addLast(Item T);
    boolean isEmpty();
    void printDeque();
    Item removeFirst();
    Item removeLast();
    Item get(int index);
    int size();
}
