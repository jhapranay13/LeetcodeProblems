package leetcode.hard;

/**
 *
 * You are given a string s consisting only of lowercase English letters.
 *
 * In one move, you can select any two adjacent characters of s and swap them.
 *
 * Return the minimum number of moves needed to make s a palindrome.
 *
 * Note that the input will be generated such that s can always be converted to a palindrome.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aabb"
 * Output: 2
 * Explanation:
 * We can obtain two palindromes from s, "abba" and "baab".
 * - We can obtain "abba" from s in 2 moves: "aabb" -> "abab" -> "abba".
 * - We can obtain "baab" from s in 2 moves: "aabb" -> "abab" -> "baab".
 * Thus, the minimum number of moves needed to make s a palindrome is 2.
 *
 * Example 2:
 *
 * Input: s = "letelt"
 * Output: 2
 * Explanation:
 * One of the palindromes we can obtain from s in 2 moves is "lettel".
 * One of the ways we can obtain it is "letelt" -> "letetl" -> "lettel".
 * Other palindromes such as "tleelt" can also be obtained in 2 moves.
 * It can be shown that it is not possible to obtain a palindrome in less than 2 moves.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 2000
 * s consists only of lowercase English letters.
 * s can be converted to a palindrome using a finite number of moves.
 *
 */

public class _2193_Minimum_Number_of_Moves_to_Make_Palindrome {
    public int minMovesToMakePalindrome(String s) {
        int lo = 0;
        int hi = s.length() - 1;
        int ans = 0;
        StringBuilder sb = new StringBuilder(s);

        while (lo < hi) {

            if (sb.charAt(lo) == sb.charAt(hi)) {
                lo++;
                hi--;
                continue;
            }
            int l = lo + 1;
            int h = hi - 1;

            while (sb.charAt(l) != sb.charAt(hi)) {
                l++;
            }

            while (sb.charAt(h) != sb.charAt(lo)) {
                h--;
            }
            //Can also be done using Arrays while bubbling the value to the position
            if (l - lo < hi - h) {
                sb.insert(lo, sb.charAt(l));
                sb.deleteCharAt(l + 1);
                ans += l - lo;
            } else {
                sb.insert(hi + 1, sb.charAt(h));
                sb.deleteCharAt(h);
                ans += hi - h;
            }
            lo++;
            hi--;
        }
        return ans;
    }
}
