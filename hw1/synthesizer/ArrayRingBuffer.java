package synthesizer; // TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */

    @Override
    public void enqueue(Object x) {
        try{
            fillCount++;
            rb[last] = (T)x;
            last = (last + 1) % capacity;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        try{
            fillCount--;
            T temp = rb[first];
            first = (first + 1) % capacity;
            return temp;
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
       try{
           T temp = rb[(first % capacity)];
           return temp;
       }catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.


    @Override
    public boolean isEmpty() {
        return first == last && fillCount == 0;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int length;
        private int pos;

        public ArrayRingBufferIterator() {
            length = fillCount;
            pos = first;

        }

        public boolean hasNext() {
            return length != 0;
        }

        public T next() {
            T returnItem = rb[pos];
            length--;
            pos = (pos + 1) % capacity;
            return returnItem;
        }
    }

    public static void main(String[] args) {
        BoundedQueue x = new ArrayRingBuffer(4);
        x.enqueue(33.1); // 33.1 null null  null
        x.enqueue(44.8); // 33.1 44.8 null  null
        x.enqueue(62.3); // 33.1 44.8 62.3  null
        x.enqueue(-3.4); // 33.1 44.8 62.3 -3.4

        Iterator<Double> seer = x.iterator();
        while(seer.hasNext()){
            System.out.println(seer.next());
    }
    }
}
