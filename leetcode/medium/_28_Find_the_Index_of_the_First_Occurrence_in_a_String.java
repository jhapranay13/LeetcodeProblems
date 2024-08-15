package leetcode.medium;

/**
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 *
 *
 * Example 1:
 *
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 * Example 2:
 *
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 *
 *
 * Constraints:
 *
 * 1 <= haystack.length, needle.length <= 10^4
 * haystack and needle consist of only lowercase English characters.
 *
 */

public class _28_Find_the_Index_of_the_First_Occurrence_in_a_String {
    //Rabin Karp Implementation
    public int strStr(String haystack, String needle) {
        int size = needle.length();
        double[] pow = new double[size];
        double prime = 11;
        double hashValNeedle = 0;

        for (int i = 0; i < size; i++) {

            if (i == 0) {
                pow[i] = 1;
            } else {
                pow[i] = Math.pow(prime, i);
            }
            hashValNeedle += pow[i] * (needle.charAt(i) - 'a');
        }
        int slow = 0;
        int fast = 0;
        double hashValStack = 0;

        while (fast < haystack.length()) {
            int powerIndex = fast - slow;
            hashValStack += pow[powerIndex] * (haystack.charAt(fast) - 'a');

            if (fast - slow + 1 == size) {

                if (hashValNeedle == hashValStack) {
                    return slow;
                }
                hashValStack -= haystack.charAt(slow++) - 'a';
                hashValStack /= prime;
            }
            fast++;
        }
        return -1;
    }
}
