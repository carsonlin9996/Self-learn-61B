
import static org.junit.Assert.*;
import org.junit.Test;


public class FlikTest {

    @Test
    public void testFlik(){
        int a = 500;
        int b = 501;
        int c = 500;

        boolean equal = Flik.isSameNumber(a, c);
        boolean nonEqual = Flik.isSameNumber(a, b);
        assertTrue(equal);
        assertFalse(nonEqual);
    }

}
