package leetcode.DP.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".
 *
 * Notice that in this problem, we are not adding '1' after single characters.
 *
 * Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.
 *
 * Find the minimum length of the run-length encoded version of s after deleting at most k characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabcccd", k = 2
 * Output: 4
 * Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6. Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5, for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d. Therefore, the optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.
 * Example 2:
 *
 * Input: s = "aabbaa", k = 2
 * Output: 2
 * Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.
 * Example 3:
 *
 * Input: s = "aaaaaaaaaaa", k = 0
 * Output: 3
 * Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * 0 <= k <= s.length
 * s contains only lowercase English letters.
 *
 */

public class _1531_StringCompressionII {
    //Top Down Approach
    public int getLengthOfOptimalCompression(String s, int k) {
        Map<String, Integer> memo = new HashMap<>();
        return recur(s, '\u0000', 0, k, 0, memo);
    }

    private int recur(String s, char prevChar, int prevCharCount, int k, int index, Map<String, Integer> memo) {

        if (index == s.length()) {
            return 0;
        }
        String key = prevChar + ", " + prevCharCount + ", " + k + ", " + index;
        Integer keyVal = memo.get(key);

        if (keyVal != null) {
            return keyVal;
        }
        char ch = s.charAt(index);
        int count = 1;
        int nextIndex = index + 1;

        for (int i = index + 1; i < s.length(); i++) {

            if (s.charAt(i) == ch) {
                count++;
                nextIndex = i + 1;
            } else {
                nextIndex = i;
                break;
            }
        }
        int totalCount = count;
        int prevCountRepresentation = 0;
        //if prev char is equal to current char that means we have removed middle element
        //So we have to subtract the previous representation length and add the new encoding
        //representation length
        if (ch == prevChar) {
            totalCount += prevCharCount;
            prevCountRepresentation = getLength(prevCharCount);
        }

        int representaionLength = getLength(totalCount);
        int ans = representaionLength + recur(s, ch, totalCount, k, nextIndex, memo) - prevCountRepresentation;

        if (k > 0) {

            for (int i = 1; i <= k && i <= count; i++) {
                int currentCount = totalCount - i;
                int length = getLength(currentCount);
                //checking if we have to send current char and current char count or previous char
                //and previous char count
                int holder = length + recur(s, currentCount == 0 ? prevChar : ch,
                        currentCount == 0 ? prevCharCount : currentCount, k - i, nextIndex, memo) -
                        prevCountRepresentation;
                ans = Math.min(ans, holder);
            }
        }
        memo.put(key, ans);
        return ans;
    }
    //Since length for aaaaa will be a5(2) aaaaaaaaaa a10(3) etc.
    private int getLength(int n) {

        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n < 10) {
            return 2;
        } else if (n < 100) {
            return 3;
        } else  {
            return 4;
        }
    }
}
