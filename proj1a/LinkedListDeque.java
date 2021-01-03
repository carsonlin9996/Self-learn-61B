public class LinkedListDeque<T> {


    private class IntNode{
        public T item;
        IntNode previous;
        IntNode next;

        //constructor for each IntNode
        IntNode(T t, IntNode p, IntNode n){
            item = t;
            previous = p;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque(){
        /*Creates an empty node where front and back points back to the sentinel */
        sentinel = new IntNode(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;

    }
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public void addFirst(T item){
        //                                      Original sentinel.next ref
        sentinel.next = new IntNode(item, sentinel, sentinel.next );
        size += 1;
        sentinel.next.next.previous = sentinel.next;

    }

    public void addLast(T item){
        sentinel.previous = new IntNode(item, sentinel.previous, sentinel);
        size +=1;
        sentinel.previous.previous.next = sentinel.previous;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        IntNode ptr = sentinel;

        while(ptr.next != sentinel){
            ptr = ptr.next;
            System.out.print(ptr.item + " ");

        }
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T res = sentinel.next.item; //returns the item getting removed

        sentinel.next = sentinel.next.next; //sentinel.next now points to the next.next node.
        sentinel.next.previous = sentinel; //points back to the original sentinel

        size -=1;
        return res;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T res = sentinel.previous.item; //returns the item getting removed

        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;

        size -=1;
        return res;
    }

    public T get(int index){
        if(isEmpty()){
            return null;
        }

        IntNode p = sentinel.next;

        for(int i = 0; i < index; i++){
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index){
        return getRecursiveHelper(index-1, sentinel.next);
    }

    public T getRecursiveHelper(int index, IntNode p){
      if(index == 0){
          return p.next.item;
      }
      return getRecursiveHelper(index - 1, p.next);
    }


    //Used for LinkedListDeque testing, comment out for at submission
    public static void main(String[] args){
        LinkedListDeque<Integer> test = new LinkedListDeque<>();
        test.addFirst(88);
        test.addFirst(5);
        //test.addFirst(10);
        //System.out.println(test.size());
        test.addFirst(99);
        test.addFirst(11);
        test.addLast(7);
        int x = test.removeLast();
        //test.removeLast();

        test.printDeque();
        System.out.println(" ");
        //System.out.println("The ith item is " + test.get(1));
        //System.out.print("The ith item is " + test.getRecursive(1));
        System.out.println("The first item removed is " + x);

    }
} //class

