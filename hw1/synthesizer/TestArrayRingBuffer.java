package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(4);
        arb.enqueue(11.2);
        arb.enqueue(55.6);
        arb.enqueue(12.3);

        assertEquals(new Double(11.2), arb.peek());
        assertEquals(new Double(11.2), arb.dequeue());

        arb.enqueue(33.3);
        assertEquals(new Double(55.6), arb.dequeue());
        assertEquals(new Double(12.3), arb.peek());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
