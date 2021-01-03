public class LinkedListDeque<T> {


    private class IntNode {
        T item;
        IntNode previous;
        IntNode next;
        //constructor for each IntNode
        IntNode(T t, IntNode p, IntNode n) {
            item = t;
            previous = p;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque() {
        /*Creates an empty node where front and back points back to the sentinel */
        sentinel = new IntNode(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;

    }
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void addFirst(T item) {
        //                                      Original sentinel.next ref
        sentinel.next = new IntNode(item, sentinel, sentinel.next );
        size += 1;
        sentinel.next.next.previous = sentinel.next;

    }

    public void addLast(T item) {
        sentinel.previous = new IntNode(item, sentinel.previous, sentinel);
        size += 1;
        sentinel.previous.previous.next = sentinel.previous;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode ptr = sentinel;

        while(ptr.next != sentinel) {
            ptr = ptr.next;
            System.out.print(ptr.item + " ");

        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.next.item; //returns the item getting removed

        sentinel.next = sentinel.next.next; //sentinel.next now points to the next.next node.
        sentinel.next.previous = sentinel; //points back to the original sentinel

        size -= 1;
        return res;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.previous.item; //returns the item getting removed

        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;

        size -= 1;
        return res;
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }

        IntNode p = sentinel.next;

        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {

        if (index >= size) {
            return null;
        }

        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, IntNode p){
        if (index == 0) {
          return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }


    //Used for LinkedListDeque testing, comment out for at submission
   /*public static void main(String[] args){
        LinkedListDeque<Integer> test = new LinkedListDeque<>();
        test.addLast(88);


        int k = test.getRecursive(0);

        System.out.println(k);

    }*/
} //class

