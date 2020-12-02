public class LinkedListDeque<Item> implements Deque<Item>{
    public class LinkNode{
        private Item Item;
        private LinkNode next;
        private LinkNode pre;

        public LinkNode(LinkNode p, Item i, LinkNode n){
            Item = i;
            next = n;
            pre = p;
        }

        public LinkNode(){
            Item = null;
            next = null;
            pre = null;
        }
    }

    public LinkNode sentinel;
    private int size;

    public LinkedListDeque(Item N) {
        sentinel = new LinkNode();
        LinkNode node = new LinkNode(null,N,null);
        sentinel.next = node;
        node.pre  = node;
        node.next = node;
        size = 1;
    }

    public LinkedListDeque() {
        sentinel = new LinkNode(null, null, null);
        size = 0;
    }

    @Override
    public void addFirst(Item Item){
        LinkNode N = new LinkNode();
        N.Item = Item;
        N.pre = N;
        N.next = N;
        if(size == 0) {
            sentinel.next = N;
        } else {
            N.next = sentinel.next;
            N.pre = sentinel.next.pre;
            sentinel.next.pre.next = N;
            sentinel.next.pre = N;
            sentinel.next = N;
        }
        size++;
    }

    @Override
    public void addLast(Item Item) {
        LinkNode N = new LinkNode();
        N.Item = Item;
        N.pre = N;
        N.next = N;
        if(size == 0) {
            sentinel.next = N;
        } else {
            sentinel.next.pre.next = N;
            N.pre = sentinel.next.pre;
            N.next = sentinel.next;
            sentinel.next.pre = N;
        }

        size++;
    }

    @Override
    public boolean isEmpty(){
        return sentinel.next == null;
    }


    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        int length = size;
        LinkNode ptr = sentinel.next;
        System.out.println("printDeque:");
        while(length != 0) {
            System.out.print(ptr.Item+" ");
            ptr = ptr.next;
            length--;
        }
    }

    @Override
    public Item removeFirst() {
        if(size == 0) {
            return null;
        }
        LinkNode ptr = sentinel.next;
        sentinel.next = sentinel.next.next;
        size--;
        return ptr.Item;
    }

    @Override
    public Item removeLast(){
        if(size == 0) {
            return null;
        }
        LinkNode ptr = sentinel.next;
        sentinel.next.pre = sentinel.next.pre.pre;
        sentinel.next.pre.next = sentinel.next;
        size--;
        return ptr.Item;
    }

    @Override
    public Item get(int index){
        if(index > size) {
            return null;
        }
        LinkNode head = sentinel.next;
        while(index != 0) {
            head = head.next;
            index--;
        }
        return head.Item;
    }
    
    public static void main(String[] args) {
        LinkedListDeque D = new LinkedListDeque("karry");
        D.addFirst("cat");
        D.addLast("pig");
        D.addLast("bird");
        D.addFirst("mouse");

        D.printDeque();

        System.out.println("\n" + D.get(1));
    }
}