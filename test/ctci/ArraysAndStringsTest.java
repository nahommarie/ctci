package ctci;

import static org.junit.Assert.*;
import org.junit.Test;

public class ArraysAndStringsTest {

    @Test(expected = AssertionError.class) public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    // Exercise 1: isUnique()
    // input: unique, not unique, empty string, unique with capitals, not unique
    // with capitals
    // output: true, false

    /*
     * input: not unique
     * output: false
     */
    @Test public void testIsUniqueBasicFalse() {
        String s = "hello";
        boolean additional = false;
        assertFalse(ArraysAndStrings.isUnique(s, additional));
    }

    /*
     * input: unique
     * output: true
     */
    @Test
    public void testIsUniqueBasicTrue() {
        String s = "world";
        boolean additional = false;
        assertTrue(ArraysAndStrings.isUnique(s, additional));
    }

    /*
     * input: empty string
     * output: true
     */
    @Test
    public void testIsUniqueEmptyString() {
        String s = "";
        boolean additional = false;
        assertTrue(ArraysAndStrings.isUnique(s, additional));
    }

    /*
     * input: unique with capitals
     * output: true
     */
    @Test
    public void testIsUniqueCapitalsTrue() {
        String s = "Strings";
        boolean additional = false;
        assertTrue(ArraysAndStrings.isUnique(s, additional));
    }
    
    /*
     * input: not unique with capitals
     * output: false
     */
    @Test
    public void testIsUniqueCapitalsFalse() {
        String s = "StringS";
        boolean additional = false;
        assertFalse(ArraysAndStrings.isUnique(s, additional));
    }
    
    // Exercise 2: isPermutation()
    // input:    empty string, nonempty string
    // has caps: true, false
    // output:   true, false
    
    /*
     * input: empty string
     * has caps: false
     * output: true
     */
    @Test
    public void testIsPermutationEmptyString() {
        String a = "";
        String b = "";
        assertTrue(ArraysAndStrings.isPermutation(a, b));
    }
    
    /*
     * input: nonempty string
     * has caps: false
     * output: true
     */
    @Test
    public void testIsPermutationBasicTrue() {
        String a = "hello world"; 
        String b = "world hello";
        assertTrue(ArraysAndStrings.isPermutation(a, b));
    }
    
    /*
     * input: nonempty string
     * has caps: true
     * output: true
     */
    @Test 
    public void testIsPermutationWithCapsTrue() {
        String a = "Hello World";
        String b = "World Hello";
        assertTrue(ArraysAndStrings.isPermutation(a, b));
    }
    
    /*
     * input: nonempty string
     * has caps: false
     * output: false
     */
    @Test
    public void testIsPermutationBasicFalse() {
        String a = "hello world"; 
        String b = "worldhello";
        assertFalse(ArraysAndStrings.isPermutation(a, b));
    }
    
    /*
     * input: nonempty string
     * has caps: true
     * output: false
     */
    @Test 
    public void testIsPermutationWithCapsFalse() {
        String a = "HELLO WORLD";
        String b = "World Hello";
        assertFalse(ArraysAndStrings.isPermutation(a, b));
    }
    
    // Exercise 3: urlify()
    // input: empty, nonempty
    
    /*
     * input: empty
     */
    @Test
    public void testUrlifyEmpty() {
        char[] c = new char[0];
        int trueLen = 0;
        char[] out = ArraysAndStrings.urlify(c, trueLen);
        assertEquals(0, out.length);
    }
    
    /*
     * input: nonempty
     */
    @Test
    public void testUrlifyBasic() {
        String input =    "Mr John Smith    ";
        String expected = "Mr%20John%20Smith";
        char[] c = input.toCharArray();
        int trueLen = 13;
        char[] out = ArraysAndStrings.urlify(c, trueLen);
        assertEquals(expected, new String(out));
    }
    
    /*
     * input: nonempty
     */
    
    @Test
    public void testUrlifyComplex() {
        String input =    "Mr John Smith       ";
        String expected = "Mr%20John%20Smith";
        char[] c = input.toCharArray();
        int trueLen = 13;
        char[] out = ArraysAndStrings.urlify(c, trueLen);
        assertEquals(expected.length(), out.length);
        assertEquals(expected, new String(out));
    }
    
    // isPermutationPalindrome()
    // input: empty string, nonempty string
    // spaces: yes, no
    // caps: yes, no
    // output: true, false
    
    /*
     * input: empty string
     * spaces: no
     * caps: no
     * output: true
     */
    @Test
    public void testIsPermutationPalindromeEmptyString() {
        String in = "";
        assertTrue(ArraysAndStrings.isPermutationPalindrome(in));
    }
    
    /*
     * input: nonempty string
     * spaces: no
     * caps: no
     * output: true
     */
    @Test
    public void testIsPermutationPalindromeSimple() {
        String in = "abba";
        assertTrue(ArraysAndStrings.isPermutationPalindrome(in));
    }
    
    /*
     * input: nonempty string
     * spaces: yes
     * caps: no
     * output: true
     */
    @Test
    public void testIsPermutationPalindromeSpaces() {
        String in = "ab a b";
        assertTrue(ArraysAndStrings.isPermutationPalindrome(in));
    }
    
    /*
     * input: nonempty string
     * spaces: no
     * caps: yes
     * output: true
     */
    @Test
    public void testIsPermutationPalindromeCaps() {
        String in = "Abab";
        assertTrue(ArraysAndStrings.isPermutationPalindrome(in));
    }
    
    /*
     * input: empty string
     * spaces: yes
     * caps: yes
     * output: true
     */
    @Test
    public void testIsPermutationPalindromeSpacesCaps() {
        String in = "A  B A B";
        assertTrue(ArraysAndStrings.isPermutationPalindrome(in));
    }
   
    // maxOneEdit()
    // input: equal lengths
    // output: true, false
    
    /*
     * input: equal lengths
     * output: true
     */
    @Test
    public void testMaxOneEditSimpleTrue() {
        String a = "apple";
        String b = "applo";
        assertTrue(ArraysAndStrings.maxOneEdit(a, b));
    }
    
    /*
     * input: equal lengths
     * output: false
     */
    @Test
    public void testMaxOneEditSimpleFalse() {
        String a = "apple";
        String b = "opplo";
        assertFalse(ArraysAndStrings.maxOneEdit(a, b));
    }
    
    /*
     * input: unequal lengths
     * output: true
     */
    @Test
    public void testMaxOneEditInsertion() {
        String a = "pail";
        String b = "pails";
        assertTrue(ArraysAndStrings.maxOneEdit(a, b));
    }
    
    /*
     * input: unequal lengths
     * output: true
     */
    @Test
    public void testMaxOneEditDeletion() {
        String a = "pail";
        String b = "ail";
        assertTrue(ArraysAndStrings.maxOneEdit(a, b));
    }
    
    /*
     * input: unequal lengths
     * output: true
     */
    @Test
    public void testMaxOneEditReplace() {
        String a = "pail";
        String b = "tail";
        assertTrue(ArraysAndStrings.maxOneEdit(a, b));
    }
    
    /*
     * input: unequal lengths
     * output: false
     */
    @Test
    public void testMaxOneEditUnequalLengthsFalse() {
        String a = "pail";
        String b = "ails";
        assertFalse(ArraysAndStrings.maxOneEdit(a, b));
    }
    
    // compress()
    // input: empty, nonempty
    // out==in: true, false
    
    /*
     * input: empty
     * out==in: true
     */
    @Test
    public void testCompressEmptyString() {
        String s = "";
        assertEquals("", ArraysAndStrings.compress(s));
    }
    
    /*
     * input: nonempty
     * out==in: false
     */
    @Test
    public void testCompressSimple() {
        String s = "aaabbb";
        assertEquals("a3b3", ArraysAndStrings.compress(s));
    }
    
    /*
     * input: nonempty
     * out==in: true
     */
    @Test
    public void testCompressOutputInput() {
        String s = "abb";
        assertEquals(s, ArraysAndStrings.compress(s));
    }
    
    /*
     * input: nonempty
     * out==in: false
     */
    @Test
    public void testCompressComplexEndMultiple() {
        String s = "aaaccdddeebb";
        assertEquals("a3c2d3e2b2", ArraysAndStrings.compress(s));
    }
    
    /*
     * input: nonempty
     * out==in: false
     */
    @Test
    public void testCompressComplexEndSingle() {
        String s = "accddddddeeb";
        assertEquals("a1c2d6e2b1", ArraysAndStrings.compress(s));
    }
    
    // zeroMatrix()
    // N: 0, >0
    // numZeros: 0, 1, >1
    
    /*
     * N: 0
     * numZeros: 0
     */
    @Test
    public void testZeroMatrixEmpty() {
        int[][] in = new int[0][0];
        assertArrayEquals(new int[0][0], ArraysAndStrings.zeroMatrix(in));
    }
    
    /*
     * N: >0
     * numZeros: 0
     */
    @Test
    public void testZeroMatrixNoZeros() {
        int[][] in = {{1, 2}, {3, 4}};
        assertArrayEquals(new int[][] {{1, 2}, {3, 4}},
                ArraysAndStrings.zeroMatrix(in));
        
    }
    
    /*
     * N: >0
     * numZeros: 1
     */
    @Test 
    public void testZeroMatrixOneZero() {
        int[][] in = {{0, 2}, {3, 4}};
        assertArrayEquals(new int[][] {{0, 0}, {0, 4}},
                ArraysAndStrings.zeroMatrix(in));
    }
    
    /*
     * N: >0
     * numZeros: >1
     */
    @Test 
    public void testZeroMatrixCornerZeros() {
        int[][] in = {{0, 2}, {3, 0}};
        assertArrayEquals(new int[][] {{0, 0}, {0, 0}},
                ArraysAndStrings.zeroMatrix(in));
    }
    
    /*
     * N: >0
     * numZeros: >1
     */
    @Test 
    public void testZeroMatrixMultipleZeros() {
        int[][] in = {{0, 2, 2}, {3, 0, 1}, {6, 4, 5}};
        assertArrayEquals(new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 5}},
                ArraysAndStrings.zeroMatrix(in));
    }
    
}
