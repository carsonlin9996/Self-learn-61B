// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>{
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
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;

        rb = (T[]) new Object[capacity];
    }



    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    //@Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if(isFull()) {
            throw new RuntimeException("Ring buffer overflow!");
        }
        rb[last] = x;
        last = onePlus(last);
        fillCount += 1;
    }

    //increase array index by 1 or start from 0.
    private int onePlus(int index){
        if( index == rb.length - 1) {
            return 0;
        }
        return index + 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    //@Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow!");
        }

        T removed = rb[first];
        first = onePlus(first);
        fillCount -= 1;

        return removed;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    //@Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow!");
        }
        return rb[first];
    }

    private class QueIterator implements Iterator<T>{

        int current;
        int filled;

        public QueIterator(){
            current = first;
            filled = fillCount();
        }

        @Override
        public boolean hasNext(){
            return filled > 0;
        }

        @Override
        public T next(){
            T item = rb[current];
            current = onePlus(current);
            filled -= 1;

            return item;

        }

    }

    public Iterator<T> iterator(){

        return new QueIterator();
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
