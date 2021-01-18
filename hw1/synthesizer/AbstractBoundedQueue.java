package synthesizer;


//If an implementing class fails to implement any abstract
//methods inherited from an interface then that class
//must be declared abstract as in:
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{

    protected int fillCount;
    protected int capacity;


}
