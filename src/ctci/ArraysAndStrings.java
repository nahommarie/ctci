package ctci;

import java.util.HashSet;

public class ArraysAndStrings {
    public static void main(String[] args) {
        // exercise 1
        // System.out.println(isUnique("Strintgs", false));
        
        // exercise 2
        System.out.println(isPermutation("heelo", "eeloH"));
    }
    
    /**
     * Check if a string has only unique letters
     * @param s String to check uniqueness
     * @param additional Decide if an additional data strucrture can be used
     * @return true/false
     */
    public static boolean isUnique(String s, boolean additional) {
        if (additional) {
            // O(n)?
            // with additonal data structure
            // init a set
            HashSet set = new HashSet();
            // handle empty string
            if (s.length() == 0) return true;
            for (int i=0; i<s.length(); i++) {
                if (set.contains(s.charAt(i))) return false;
                set.add(s.charAt(i));
            }
            return true;
        } else {
            // O(n^2)?
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

}
