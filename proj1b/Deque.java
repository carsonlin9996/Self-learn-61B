public interface Deque<T> {

    /*checks if the Deque is empty
    if size == 0, return true*/
    boolean isEmpty();

    /*returns the size of the Deque*/
    int size();

    /*add x to the first position of the Deque*/
    void addFirst(T x);

    //adds x to the last position of the Deque
    void addLast(T x);

    /*Remove the first item of the Deque, and return the first item.
    Checks if the Deque is empty, if true, return null. */
    T removeFirst();

    /*Remove the last item of the Deque, and return the first item.
    Checks if the Deque is empty, if true, return null. */
    T removeLast();

    //Prints the whole Deque
    void printDeque();

    /*Returns the index item of the Deque
    Returns null if the Deque is empty or get() index is > size */
    T get(int index);


}
