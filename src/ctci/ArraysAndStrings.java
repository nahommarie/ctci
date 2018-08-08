package ctci;

import java.util.HashMap;
import java.util.HashSet;

public class ArraysAndStrings {
    // TODO clean up da code
    
    public static void main(String[] args) {
        // sandbox
        byte x = 3;
        System.out.println(x);
    }
    
    /**
     * Check if a string has only unique letters
     * @param s String to check uniqueness
     * @param additional Decide if an additional data structure can be used
     * @return true/false
     */
    public static boolean isUnique(String s, boolean additional) {
        // TODO requirements
        if (additional) {
            // O(n)
            // with additonal data structure
            // init a set
            HashSet set = new HashSet(); // not optimal to use generic set, better to use int[] (exercise 2)
            // handle empty string
            if (s.length() == 0) return true;
            for (int i=0; i<s.length(); i++) {
                if (set.contains(s.charAt(i))) return false;
                set.add(s.charAt(i));
            }
            return true;
        } else {
            // O(n^2)
            // without additional data structure
            // dont need to anchor on all letters
            if (s.length() == 0) return true;
            for (int i=0; i<s.length()-1; i++) {
                char anchor = s.charAt(i);
                for (int j=i+1; j<s.length(); j++) {
                    char test = s.charAt(j);
                    if (anchor == test) return false;
                }
            }
            return true;
        }
    }
    
    /**
     * Given two strings, decide if one is a permutation of the other
     * @param a first string
     * @param b second string
     * @return true/false
     */
    public static boolean isPermutation(String a, String b) {
        // TODO time complexity. requirements
        // make int array w/ a and chip away w/ b letters until end
        if (a.length() != b.length()) return false;
        if (a.length() == 0) return true;
        
        int[] count = new int[128];
        for (int i=0; i<a.length(); i++) {
            int val = a.charAt(i);
            count[val] += 1;
        } // check b only has a's letters
        for (int i=0; i<b.length(); i++) {
            int val = b.charAt(i);
            if (count[val] == 0) return false;
            count[val] -= 1;
        }
        return true;
    }
    
    /**
     * URL-ify a character array in-place by replacing spaces with '%20'. Assume trailing whitespaces give
     * just enough space to URL-ify input
     * 
     * @param c input char array
     * @param trueLen "true length" of input characters before whitespaces
     * @return String URL version of input char array
     */
    public static char[] urlify(char[] c, int trueLen) {
        // TODO time complexity
        /** REQUIRED: using a char array to perform operations in place **/
        // init output, in/out indices
        // TODO add counting white spaces to get correct initial out[] length
        int numSpaces = 0;
        for (int i=0; i < trueLen; i++) {
            if (c[i] == ' ') numSpaces++;
        }
        char[] out = new char[trueLen + numSpaces * 2];
        // char[] out = new char[trueLen];
        int inIndex = trueLen-1;
        int outIndex = out.length-1;
        // 2-finger technique iterate backwards
        while (inIndex >= 0) {
            char currInp = c[inIndex];
            if (currInp == ' ') {
                out[outIndex-2] = '%';
                out[outIndex-1] = '2';
                out[outIndex] =   '0';
                outIndex -= 3;
            } else {
                out[outIndex] = currInp;
                outIndex -= 1;
            }
            inIndex -= 1;
        }
        System.out.println(out);
        return out;
        
    }
    
    /**
     * Find if a string is a permutation of a palindrome. A palindrome
     * is any word that is the same frontwards and backwards (not 
     * case sensitive). 
     * 
     * @param input The input string
     * @return true/false if input is a permutation of a palindrome
     */
    public static boolean isPermutationPalindrome(String input) {
        // TODO runtime, requirements
        // check # of odd frequencies of any letter doesn't exceed 1
        
        HashMap<String, Integer> map = new HashMap<>();
        for (int i=0; i<input.length(); i++) {
            String curr = input.substring(i, i+1).toLowerCase();
            if (curr.equals(" ")) continue;
            if (map.keySet().contains(curr)) {
                int val = map.get(curr);
                map.put(curr, val+1);
            } else {
                map.put(curr, 1);
            }
        }
            int numOdds = 0;
            for (int i : map.values()) {
                if (i%2==1) numOdds++;
                if (numOdds > 1) return false;
        }
           return true;
    }
    
    /**
     * Find whether two strings are one edit apart. Possible edits:
     * Replace: 'pail' -> 'bail'
     * Delete: 'pail' -> 'pal'
     * Insert: 'pail' -> 'pails'
     * @param a first string
     * @param b second string
     * @return whether <= one edit will turn a-> b or b->a 
     */
    public static boolean maxOneEdit(String a, String b) {
        // use int[] to count char freq's and lastly check <= 1 differences
        int lengthDifference = a.length() - b.length();
        if (lengthDifference > 1 || lengthDifference < -1) return false;
        if (a.length() == b.length()) {
            boolean hasDifference = false;
            for (int i=0; i < a.length(); i++) {
                if (!a.substring(i, i+1).equals( 
                     b.substring(i, i+1))) {
                    if (hasDifference) return false;
                    hasDifference = true;
                }
            }
            return true;
        } else {
            String shorter = a.length() < b.length() ? a : b;
            String longer = a.length() > b.length() ? a : b;
            int[] counts = new int[128]; // assumes ASCII
            int[] cnts = new int[128];
            // build frequency table for shorter string
            for (int i=0; i<shorter.length(); i++) {
                int key = shorter.charAt(i);
                counts[key]++;
            }
            // // build freq table for longer
            for (int i=0; i<longer.length(); i++) {
                int key = longer.charAt(i);
                cnts[key]++;
            }
            // compare # discrepancies between freq. tables
            // could have simply implemented a check against the strings themselves
            // would have taken less time with less overhead 
            boolean hasDiff = false;
            for (int i=0; i<128; i++) {
                if (counts[i] != cnts[i]) {
                    if (hasDiff) return false;
                    hasDiff = true;
                }
            }
            return true;
        }
    }
    
    /**
     * Compress the string s by adding letter frequencies. Returns the 
     * original string if the output would be longer than the input
     * @param s Input string
     * @return shorter of compressed input and original input strings
     */
    public static String compress(String s) {
        if (s.length() == 0) return "";
        int count = 0;
        StringBuilder out = new StringBuilder();
        String last = s.substring(0, 1);
        for (int i=0; i<s.length(); i++) {
            String curr = s.substring(i, i+1);
            if (curr.equals(last)) {
                count++;
            } else {
                out.append(last);
                out.append(count);
                count = 1;
                last = curr;
            }
        } 
        out.append(last);
        out.append(count);
        if (s.length() < out.length()) {
            return s;
        } else {
            return out.toString();
        }
    }
    
    public static void rotate(byte[][] matrix) {
     // TODO
        // what exactly are they asking here..?
    }
    
    public static int[][] zeroMatrix(int[][] matrix) {
        // TODO
        int n = matrix.length;
        if (n == 0) return new int[0][0];
        int[][] out = new int[n][n];
        HashSet<Integer> rows = new HashSet<>();
        HashSet<Integer> cols = new HashSet<>();
        for (int r=0; r<matrix.length; r++) {
            int[] currRow = matrix[r];
            for (int c=0; c<currRow.length; c++) {
                if (currRow[c] == 0) {
                    rows.add(r);
                    cols.add(c);
                }
            }
        }
        
        for (int r=0; r<matrix.length; r++) {
            int[] currRow = new int[n];
            for (int c=0; c<currRow.length; c++) {
                if (rows.contains(r) || cols.contains(c)) {
                    currRow[c] = 0;
                } else {
                    currRow[c] = matrix[r][c];
                }
            }
            out[r] = currRow;
        }
        
        return out;
        
    }

}
