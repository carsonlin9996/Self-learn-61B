import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/

    @Test
    public void testEqualChars(){

        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('y', 'z'));
        assertTrue(offByOne.equalChars('%', '&'));
        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('a', 'z'));
        assertFalse(offByOne.equalChars('a', 'A'));
        assertFalse(offByOne.equalChars('B', 'b'));
        assertTrue(offByOne.equalChars('Y', 'Z'));
        assertTrue(offByOne.equalChars('A', 'B'));
    }

    @Test
    public void testEqualsCharUpper() {
        assertFalse(offByOne.equalChars('A', 'A'));
        assertTrue(offByOne.equalChars('A', 'B'));
        assertTrue(offByOne.equalChars('B', 'A'));
        assertFalse(offByOne.equalChars('A', 'C'));
        assertFalse(offByOne.equalChars('C', 'A'));
    }
}
