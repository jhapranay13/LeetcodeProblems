package LeetcodeDiscuss;


import java.util.Arrays;

/**
 *
 * Follow up for plaindrome by removing 1 character:
 * String palindrome by removing at most k character
 *
 */
public class MetaOAPalindromeRemovingUptoK {

    public static void main(String[] args) {

        System.out.println("Test Cases (Top-Down LCS):");

        // Example 1: "abcba", k = 0 (already a palindrome)
        System.out.println("abcba, k=0 -> " + canBePalindrome("abcba", 0)); // Expected: true

        // Example 2: "abca", k = 1 (remove 'c')
        System.out.println("abca, k=1 -> " + canBePalindrome("abca", 1));   // Expected: true

        // Example 3: "abcde", k = 1
        System.out.println("abcde, k=1 -> " + canBePalindrome("abcde", 1)); // Expected: false

        // Example 4: "racecar", k = 0
        System.out.println("racecar, k=0 -> " + canBePalindrome("racecar", 0)); // Expected: true

        // Example 5: "foobar", k = 2
        System.out.println("foobar, k=2 -> " + canBePalindrome("foobar", 2)); // Expected: false

        // Example 6: "aebcbda", k = 2
        System.out.println("aebcbda, k=2 -> " + canBePalindrome("aebcbda", 2)); // Expected: true

        // Example 7: Empty string
        System.out.println("empty, k=0 -> " + canBePalindrome("", 0)); // Expected: true

        // Example 8: Single character string
        System.out.println("a, k=0 -> " + canBePalindrome("a", 0)); // Expected: true
    }
    
    // The whole idea is to find the longest palindrome subsequece
    // if length - LCS <= k, then it can be true
    private static boolean canBePalindrome(String str, int k) {

        if (str == null || str.length() == 0) {
            return true; // Empty string is a palindrome, no removals needed.
        }
        StringBuilder revStr = new StringBuilder(str).reverse();
        int[][] memo = new int[str.length()][str.length()];

        for (int[] mem : memo) {
            Arrays.fill(mem, -1); // Initialize memoization table with -1
        }
        return str.length() - longestPlaindromeSubsequence(str, revStr.toString(), 0, 0, memo) <= k;
    }

    private static int longestPlaindromeSubsequence(String str, String rev, int i, int j, int[][] memo) {

        if (i == str.length() ||j == rev.length()) {
            return 0; // Base case: reached the end of either string
        }

        if (memo[i][j] != -1) {
            return memo[i][j]; // Return the cached result
        }
        int ans = 0;

        if (str.charAt(i) == rev.charAt(j)) {
            // Characters match, include this character in the LCS
            ans = 1 + longestPlaindromeSubsequence(str, rev, i + 1, j + 1, memo);
        } else {
            // Characters do not match, explore both possibilities
            int skipStr = longestPlaindromeSubsequence(str, rev, i + 1, j, memo);
            int skipRev = longestPlaindromeSubsequence(str, rev, i, j + 1, memo);
            ans =  Math.max(skipStr, skipRev); // Return the maximum of both options
        }
        return memo[i][j] = ans;
    }
}
