public class LinkedListDeque<T> {
    private class IntNode {
        T item;
        IntNode pre;
        IntNode next;
        //constructor for each IntNode
        IntNode(T t, IntNode p, IntNode n) {
            item = t;
            pre = p;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque() {
        /*Creates an empty node where front and back points back to the sentinel */
        sentinel = new IntNode(null, null, null);
        sentinel.pre = sentinel;
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
        //Original sentinel.next ref
        sentinel.next = new IntNode(item, sentinel, sentinel.next);
        size += 1;
        sentinel.next.next.pre = sentinel.next;

    }

    public void addLast(T item) {
        sentinel.pre = new IntNode(item, sentinel.pre, sentinel);
        size += 1;
        sentinel.pre.pre.next = sentinel.pre;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode ptr = sentinel;

        while (ptr.next != sentinel) {
            ptr = ptr.next;
            System.out.print(ptr.item + " ");

        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.next.item; //returns the item getting removed

        //sentinel.next now points to the next.next node.
        sentinel.next = sentinel.next.next;
        //points back to the original sentinel
        sentinel.next.pre = sentinel;


        size -= 1;
        return res;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.pre.item; //returns the item getting removed

        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;

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

    private T getRecursiveHelper(int index, IntNode p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }
}

    //Used for LinkedListDeque testing, comment out for at submission
   /*public static void main(String[] args){
        LinkedListDeque<Integer> test = new LinkedListDeque<>();
        test.addLast(88);


        int k = test.getRecursive(0);

        System.out.println(k);

    }*/


