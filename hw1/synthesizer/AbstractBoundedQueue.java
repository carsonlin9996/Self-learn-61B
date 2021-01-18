package synthesizer;


//If an implementing class fails to implement any abstract
//methods inherited from an interface then that class
//must be declared abstract as in:
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{

    protected int fillCount;
    protected int capacity;

    @Override
    public int capacity(){
        return capacity;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }

    public abstract void enqueue(T x);
    public abstract T dequeue();
    public abstract T peek();
}
