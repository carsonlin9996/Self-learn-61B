public class ArrayDeque<T> {

    private static int Initial_capacity = 8;
    private T[] item;
    private int size = 0;
    private int nextLast;
    private int nextFirst;
    private int newCapacity = 8;

    public ArrayDeque(){
        item =(T[]) new Object[Initial_capacity];
        nextFirst = 4;
        nextLast = 5;
    }
    public void addFirst(T x){
        resize();
        item[nextFirst] = x;
        nextFirst = oneMinus(nextFirst);
        size +=1;
    }

    public void addLast(T x){
        resize();
        item[nextLast] = x;
        nextLast = onePlus(nextLast);
        size +=1;


        /*if(size == item.length){
            //expand the array
        }*/
    }

    public T removeFirst(){
        int actualFirst = onePlus(nextFirst);
        nextFirst = actualFirst; //nextFirst index becomes the actualFirst.
        T removedItem = item[actualFirst]; //removed the currentFirst item.
        size -=1;
        resize();
        return removedItem;

    }

    public T removeLast(){
        int actualLast = oneMinus(nextLast);
        nextLast = actualLast;
        T removedItem = item[actualLast];
        size -=1;
        resize();
        return removedItem;

    }

    public void resize(){
        if(size == item.length){
            expand();
        }
        if((size < item.length/4) && item.length > 8){
            shrink();
        }
    }
    private void expand(){
        newCapacity = 2 * newCapacity;
        resizeArray(item.length * 2);

    }

    private void shrink(){
        newCapacity = newCapacity/2;
        resizeArray(item.length/2);

    }

    private void resizeArray(int newSize){
        T[] newArray = (T[]) new Object[newSize];
        T[] temp = item;
        int first = onePlus(nextFirst);
        int last = oneMinus(nextLast);
        nextFirst = 4;
        nextLast = 5;

        while(first != last){
            newArray[nextLast] = temp[first];
            nextLast = onePlus2(nextLast, newCapacity);
            first =  onePlus(first);
        }
        newArray[nextLast] = temp[last];
        nextLast = onePlus2(nextLast, newCapacity);
        item = newArray;
    }

    public void printDeque(){
        int currentFirst = onePlus(nextFirst);
        while(currentFirst != nextLast){
            System.out.print(item[currentFirst]+ " ");
            currentFirst = onePlus(currentFirst); //updates currentFirst index to the next one.
        }
    }

    public T get(int index){
        if(isEmpty()){
            return null;
        }
        if(index >= size){
            return null;
        }
        int finalIndex = onePlus(nextFirst);
        for(int i = 0; i < index; i++){
            finalIndex = onePlus(finalIndex);
        }
        return item[finalIndex];
    }

    /*input index -1, if index at 0, roll back to item.length 1 (last index of the array)*/
    private int oneMinus(int index){
        if(index == 0){
            return item.length - 1;
        }
        return index - 1;
    }
    /*input index +1, if index at the item.length - 1, return 0. else, return index + 1; */
    private int onePlus(int index){
        if(index == item.length - 1) {
            return 0;
        }
        return index + 1;
    }

    private int onePlus2(int index, int newCapacity){ //use for new array
        if(index == newCapacity - 1) {
            return 0;
        }
        return index + 1;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(1);
        test.addFirst(3);
        test.addFirst(4);
        test.addFirst(5);
        test.addFirst(6);
        test.addFirst(7);
        test.addFirst(8);
        test.addFirst(9);
        test.addFirst(999);


        //int items_removed = test.removeLast();
        //System.out.println("items removed last "+ items_removed);
        //System.out.println(test.size());
        //int get = test.get(4);
        //System.out.print(get);
    }
}
