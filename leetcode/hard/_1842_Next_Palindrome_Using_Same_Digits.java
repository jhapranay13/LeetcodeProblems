package leetcode.hard;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * You are given a numeric string num, representing a very large palindrome.
 *
 * Return the smallest palindrome larger than num that can be created by rearranging its digits. If no such palindrome exists, return an empty string "".
 *
 * A palindrome is a number that reads the same backward as forward.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "1221"
 * Output: "2112"
 * Explanation: The next palindrome larger than "1221" is "2112".
 * Example 2:
 *
 * Input: num = "32123"
 * Output: ""
 * Explanation: No palindromes larger than "32123" can be made by rearranging the digits.
 * Example 3:
 *
 * Input: num = "45544554"
 * Output: "54455445"
 * Explanation: The next palindrome larger than "45544554" is "54455445".
 *
 *
 * Constraints:
 *
 * 1 <= num.length <= 10^5
 * num is a palindrome.
 *
 */

public class _1842_Next_Palindrome_Using_Same_Digits {
    // Same pattern as 556. Next Greater Element III
    // Binary Search based
    public String nextPalindrome(String num) {
        int index = num.length() / 2 - 1;
        char[] holder = num.toCharArray();

        for (int i = index; i > 0; i--) {

            if (holder[i] > holder[i - 1]) {
                int idx = i - 1;
                int lo = idx + 1;
                int hi = index;
                int indexToSwap = lo;
                // Finding out just greater than
                while (lo <= hi) {
                    int pivot = lo + (hi - lo) / 2;

                    if (holder[pivot] > holder[idx]) {
                        indexToSwap = pivot;
                        lo = pivot + 1;
                    } else {
                        hi = pivot - 1;
                    }
                }
                swap(holder, idx, indexToSwap);
                lo = idx + 1;
                hi = index;

                while (lo < hi) {
                    swap(holder, lo++, hi--);
                }
                break;
            }
        }
        StringBuilder ans = new StringBuilder();
        int idx = 0;

        while (idx <= index) {
            ans.append(holder[idx++]);
        }
        // Making a palindrome
        if (holder.length % 2 != 0) {
            ans.append(holder[idx]);
        }
        idx--;

        while (idx >= 0) {
            ans.append(holder[idx--]);
        }
        return ans.toString().equals(num) ? "" : ans.toString();
    }

    private void swap(char[] holder, int x, int y) {
        char temp = holder[x];
        holder[x] = holder[y];
        holder[y] = temp;
    }
    //=============================================================================================
    // TreeMap based
    public String nextPalindrome1(String num) {
        int index = num.length() / 2 - 1;
        char[] holder = num.toCharArray();
        TreeMap<Character, Integer> charIndex = new TreeMap<>();

        for (int i = index; i > 0; i--) {
            // Taking Right most so that number can be next max
            if (!charIndex.containsKey(holder[i])) {
                charIndex.put(holder[i], i);
            }

            if (holder[i] > holder[i - 1]) {
                int idx = i - 1;
                Map.Entry<Character, Integer> entry = charIndex.higherEntry(holder[idx]);

                if (entry == null) {
                    break;
                }
                swap(holder, idx, entry.getValue());
                int lo = idx + 1;
                int hi = index;

                while (lo < hi) {
                    swap(holder, lo++, hi--);
                }
                break;
            }
        }
        StringBuilder ans = new StringBuilder();
        int idx = 0;

        while (idx <= index) {
            ans.append(holder[idx++]);
        }
        // Making a palindrome
        if (holder.length % 2 != 0) {
            ans.append(holder[idx]);
        }
        idx--;

        while (idx >= 0) {
            ans.append(holder[idx--]);
        }
        return ans.toString().equals(num) ? "" : ans.toString();
    }

}
