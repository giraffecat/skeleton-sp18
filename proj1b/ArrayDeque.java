public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] Ts;
    private int Maxsize = 8;
    private int front = 0;
    private int rear = 0;
    //tag is false mean out, tag is true mean in
    private boolean tag = false;

    public ArrayDeque(){
        Ts = (Item[]) new Object[Maxsize];
    }

    private void  resize(int capacity){
        Item[] a =(Item[]) new Object[capacity];
//        遍历复制？？？是不是有点蠢？？ 诶 想不到其他方法
//        那缩小的时候也可以用遍历复制？？
        int length = size();
        for(int i=length-1;i>=0;i--){
            a[length-1-i] = get(i);
        }
        Ts = a;
        front = length;
        rear=0;
        Maxsize = capacity;
    }
    //约定 队头指针与队尾指针的位置相同 且 tag是true 作为队满的标志
    @Override
    public void addFirst(Item Item){
        if(size() == Maxsize) resize(Maxsize*2);
        Ts[front] = Item;
        front = (front+1)%Maxsize;
        tag = true;

    }
    @Override
    public void addLast(Item Item){
        if(size() == Maxsize) resize(Maxsize*2);
        rear = (rear+Maxsize-1)%Maxsize;
        Ts[rear] = Item;
        tag = true;

    };
    @Override
    public boolean isEmpty(){return  front==rear && !tag ;}

    @Override
    public int size(){
        if(tag && (front == rear)) return Maxsize;
        return (front-rear+Maxsize)%Maxsize;
    }

    @Override
    public void printDeque(){
        int length = size();
        int start = front-1;
        System.out.print("\n数组：");
        while(length != 0){
            int pos = (start+Maxsize) % Maxsize;
            System.out.print(Ts[pos]);
            start--;
            length--;
        }
    }

    @Override
    public Item removeFirst(){
        if(size() == 0) return null;
        if(size()<(Maxsize/4)) resize(Maxsize/2);
        front = (front+Maxsize-1)%Maxsize;
        Item Item = Ts[front];
        tag = false;
        return  Item;
    }

    @Override
    public Item removeLast(){
        if(size() == 0) return null;
        if(size()<(Maxsize/4)) resize(Maxsize/2);
        Item Item = Ts[rear];
        rear = (rear+1)%Maxsize;
        tag = false;
        return  Item;
    }

    @Override
    public Item get(int index){
        int length = size();
        int start = front-1;
        int pos = (start+Maxsize) % Maxsize;
        while(index != 0){
            start--;
            index--;
            pos = (start+Maxsize) % Maxsize;
        }
        return  Ts[pos];
    }

    public static void main(String[] args) {
        ArrayDeque A = new ArrayDeque();
        A.addFirst(2);
        A.addFirst(3);
        A.addLast(4);
        A.addLast(5);
        A.addFirst(6);
        A.addFirst(7);
        A.addFirst(8);
        A.addFirst(9);
        A.addFirst(10);
        A.addFirst(7);
        A.addFirst(8);
        A.addFirst(9);
        A.addFirst(10);
        A.addFirst(7);
        A.addFirst(8);
        A.addFirst(9);
        A.addFirst(10);



        System.out.print("第1个数："+A.get(2)+"\nMaxSize:"+A.Maxsize);
        A.printDeque();
    }

}
