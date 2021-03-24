import edu.princeton.cs.algs4.Queue;
import org.junit.Test;


public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {

        Queue<Queue<Item>> queueOfQueue = new Queue<>();
        for(Item i : items) {
            Queue<Item> item = new Queue<>();
            item.enqueue(i);
            queueOfQueue.enqueue(item);
        }
        return queueOfQueue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> merged = new Queue<>();
        int totalSize = q1.size() + q2.size();
        /*while(!(q1.isEmpty() && q2.isEmpty())) {
            merged.enqueue(getMin(q1, q2));
        }*/
        for(int i = 0; i < totalSize; i++) {
            merged.enqueue(getMin(q1,q2));
        }
        // Your code here!
        return merged;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {

        //Queue<Queue<Item>> splitQueue = makeSingleItemQueues(items);
        Queue<Item> newQueue = new Queue<>();
        for(Item i : items) {
            newQueue.enqueue(i);
        }

        if(items.size() > 1) {
            Queue<Item> leftQueue = new Queue<>();
            Queue<Item> rightQueue = new Queue<>();

            int sizeHalf = items.size() / 2;
            for (int i = 0; i < sizeHalf; i++) {
                //Queue<Item> item = splitQueue.dequeue();
                leftQueue.enqueue(newQueue.dequeue());
            }
            for (int i = 0; i < sizeHalf; i++) {
                //Queue<Item> item = splitQueue.dequeue();
                rightQueue.enqueue(newQueue.dequeue());
            }

            Queue<Item> sortLeft = mergeSort(leftQueue);
            Queue<Item> sortRight = mergeSort(rightQueue);
            Queue<Item> results = mergeSortedQueues(sortLeft, sortRight);
            return results;
        }
        return items;

    }


    public static void main(String[] args) {
        Queue<String> students = new Queue<>();

        students.enqueue("C");
        students.enqueue("C");
        students.enqueue("A");
        students.enqueue("E");
        students.enqueue("V");
        students.enqueue("Z");
        students.enqueue("Z");
        students.enqueue("K");

        System.out.println(students);
        System.out.println(MergeSort.mergeSort(students));
        System.out.println(students);

    }
}
