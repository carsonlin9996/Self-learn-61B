import org.junit.Test;
import static org.junit.Assert.*;



public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(4);

    // Your tests go here.
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/

    @Test
    public void testEqualChars(){

        assertTrue(offByN.equalChars('a', 'e'));
        assertTrue(offByN.equalChars('1', '5'));
        //assertTrue(offByN.equalChars('%', '&'));
        assertFalse(offByN.equalChars('a', 'k'));
        assertFalse(offByN.equalChars('a', 'b'));
    }
}
