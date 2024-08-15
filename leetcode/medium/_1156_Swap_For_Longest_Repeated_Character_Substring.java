package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given a string text. You can swap two of the characters in the text.
 *
 * Return the length of the longest substring with repeated characters.
 *
 *
 *
 * Example 1:
 *
 * Input: text = "ababa"
 * Output: 3
 * Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa" with length 3.
 * Example 2:
 *
 * Input: text = "aaabaaa"
 * Output: 6
 * Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa" with length 6.
 * Example 3:
 *
 * Input: text = "aaaaa"
 * Output: 5
 * Explanation: No need to swap, longest repeated character substring is "aaaaa" with length is 5.
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 2 * 10^4
 * text consist of lowercase English characters only.
 *
 */

public class _1156_Swap_For_Longest_Repeated_Character_Substring {
    public int maxRepOpt1(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();

        for (char ch : text.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        int leftToRight[] = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {

            if (i == 0 || text.charAt(i) != text.charAt(i - 1)) {
                leftToRight[i] = 1;
            } else {
                leftToRight[i] = 1 + leftToRight[i - 1];
            }
        }
        int rightToLeft[] = new int[text.length()];

        for (int i = text.length() - 1; i >= 0; i--) {

            if (i == text.length() - 1 || text.charAt(i) != text.charAt(i + 1)) {
                rightToLeft[i] = 1;
            } else {
                rightToLeft[i] = 1 + rightToLeft[i + 1];
            }
        }
        int ans = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if ( i > 0 && i < text.length() - 1 && text.charAt(i - 1) != ch && text.charAt(i + 1) != ch &&
                    text.charAt(i - 1) == text.charAt(i + 1)) {
                int countLeft = leftToRight[i - 1];
                int countRight = rightToLeft[i + 1];

                if (countLeft + countRight == freqMap.get(text.charAt(i - 1))) {
                    ans = Math.max(ans, countLeft + countRight);
                } else if (countLeft + countRight < freqMap.get(text.charAt(i - 1))) {
                    ans = Math.max(ans, countLeft + countRight + 1);
                }
            } else {

                if (i > 0 && text.charAt(i - 1) != ch) {
                    int countLeft = leftToRight[i - 1];
                    int countCurr = rightToLeft[i];

                    if (countLeft == freqMap.get(text.charAt(i - 1))) {
                        ans = Math.max(ans, countLeft);
                    } else if (countLeft < freqMap.get(text.charAt(i - 1))) {
                        ans = Math.max(ans, countLeft + 1);
                    }

                    if (countCurr == freqMap.get(ch)) {
                        ans = Math.max(ans, countCurr);
                    } else if (countCurr < freqMap.get(ch)) {
                        ans = Math.max(ans, countCurr + 1);
                    }
                }

                if (i < text.length() - 1 && text.charAt(i + 1) != ch) {
                    int countRight = rightToLeft[i + 1];
                    int countCurr = leftToRight[i];

                    if (countRight == freqMap.get(text.charAt(i + 1))) {
                        ans = Math.max(ans, countRight);
                    } else if (countRight < freqMap.get(text.charAt(i + 1))) {
                        ans = Math.max(ans, countRight + 1);
                    }

                    if (countCurr == freqMap.get(ch)) {
                        ans = Math.max(ans, countCurr);
                    } else if (countCurr < freqMap.get(ch)) {
                        ans = Math.max(ans, countCurr + 1);
                    }
                }
            }
        }
        return ans == 0 ? text.length() : ans;
    }

}
