public class LinkedListDeque<T>{
    public class LinkNode{
        public T T;
        public LinkNode next;
        public LinkNode pre;

        public LinkNode(LinkNode p,T i, LinkNode n){
            T = i;
            next = n;
            pre = p;
        }

        public LinkNode(){
            T = null;
            next = null;
            pre = null;
        }
    }

    public LinkNode sentinel;
    private int size;

    public LinkedListDeque(T N){
        sentinel = new LinkNode();
        LinkNode node = new LinkNode(null,N,null);
        sentinel.next = node;
        node.pre  = node;
        node.next = node;
        size = 1;
    }

    public LinkedListDeque(){
        sentinel = new LinkNode(null,null,null);
        size = 0;
    }

    public void addFirst(T T){
        LinkNode N = new LinkNode();
        N.T = T;
        N.pre = N;
        N.next = N;
        if(size == 0) {
            sentinel.next = N;
        }else{
            N.next = sentinel.next;
            N.pre = sentinel.next.pre;
            sentinel.next.pre.next = N;
            sentinel.next.pre = N;
            sentinel.next = N;
        }
        size++;
    }

    public void addLast(T T){
        LinkNode N = new LinkNode();
        N.T = T;
        N.pre = N;
        N.next = N;
        if(size == 0) {
            sentinel.next = N;
        }else{
           sentinel.next.pre.next = N;
           N.pre = sentinel.next.pre;
           N.next = sentinel.next;
           sentinel.next.pre = N;
        }

        size++;
    }

    public boolean isEmpty(){
       return sentinel.next ==null;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int length = size;
        LinkNode ptr = sentinel.next;
        System.out.println("printDeque:");
        while(length !=0) {
            System.out.print(ptr.T+" ");
            ptr = ptr.next;
            length--;
        }
    }
    public T removeFirst(){
        if(size == 0) return null;
        LinkNode ptr = sentinel.next;
        sentinel.next = sentinel.next.next;
        size--;
        return ptr.T;
    }
    public T removeLast(){
        if(size == 0) return null;
        LinkNode ptr = sentinel.next;
        sentinel.next.pre = sentinel.next.pre.pre;
        sentinel.next.pre.next = sentinel.next;
        size--;
        return ptr.T;
    }
    public T get(int index){
        if(index>size) return null;
        LinkNode head = sentinel.next;
        while(index != 0){
            head = head.next;
            index--;
        };
        return head.T;
    }
    
    public static void main(String[] args) {
        LinkedListDeque D = new LinkedListDeque("karry");
        D.addFirst("cat");
        D.addLast("pig");
        D.addLast("bird");
        D.addFirst("mouse");
        D.removeFirst();
        D.removeLast();
        D.printDeque();
    }
}