package leetcode.DP;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a string s, encode the string such that its encoded length is the shortest.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. k should be a positive integer.
 *
 * If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaa"
 * Output: "aaa"
 * Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
 * Example 2:
 *
 * Input: s = "aaaaa"
 * Output: "5[a]"
 * Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
 * Example 3:
 *
 * Input: s = "aaaaaaaaaa"
 * Output: "10[a]"
 * Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 150
 * s consists of only lowercase English letters.
 *
 */

public class _471_Encode_String_with_Shortest_Length {
    // Top Down approach
    //"aabcaabcd"
    //"2[aabc]d"
    public String encode(String s) {
        String[][] memo = new String[s.length()][s.length()];
        return recur(s, 0, s.length() - 1, memo);
    }

    private String recur(String s, int lo, int hi, String[][] memo) {

        if (lo > hi) {
            return "";
        }

        if (hi - lo + 1 < 5) {
            return s.substring(lo, hi + 1);
        }

        if (memo[lo][hi] != null) {
            return memo[lo][hi];
        }
        String ans = s.substring(lo, hi + 1);
        int ansLength = ans.length();

        for (int i = lo; i < hi; i++) {
            String left = s.substring(lo, i + 1);
            String right = s.substring(i + 1, hi + 1);
            int count = getCount(left, right);
            String encode1 = s.substring(lo, hi + 1);

            if (count > 0) {
                // Finding out where the pattern ends
                int newLo = i + count * (i - lo + 1);
                encode1 = (count + 1) + "[" + recur(s, lo, i, memo) +"]" + recur(s, newLo + 1, hi, memo);
            }
            String encode2 = recur(s, lo, i, memo) + recur(s, i + 1, hi, memo);

            if (encode2.length() < encode1.length()) {
                encode1 = encode2;
            }

            if (encode1.length() < ansLength) {
                ansLength = encode1.length();
                ans = encode1;
            }
        }

        return memo[lo][hi] = ans;
    }
    //=============================================================================================
    // Top Down Different approach
    //"aabcaabcd"
    //"2[aabc]d"
    public String encode1(String s) {
        Map<String, String> memo = new HashMap<>();
        return recur(s, memo);
    }

    private String recur(String s, Map<String, String> memo) {

        if (s == null || s.length() < 5) {
            return s;
        }

        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        int ansLength = s.length();
        String ans = s;

        for (int i = 1; i <= s.length() / 2; i++) {
            String prefix = s.substring(0, i);
            String suffix = s.substring(i);
            int count = getCount(prefix, suffix);
            String encode1 = s;

            if (count > 0) {
                encode1 = (count + 1) + "[" + recur(prefix, memo) + "]" + recur(suffix.substring(count * prefix.length()), memo);
            }
            String encode2 = recur(prefix, memo) + recur(suffix, memo);

            if (encode2.length() < encode1.length()) {
                encode1 = encode2;
            }

            if (encode1.length() < s.length())  {

                if (encode1.length() < ansLength) {
                    ansLength = encode1.length();
                    ans = encode1;
                }
            }
        }
        memo.put(s, ans);
        return ans;
    }

    private int getCount(String pattern, String mainString) {
        int mainStart = 0;
        int count = 0;

        do {
            int index = mainString.indexOf(pattern, mainStart);

            if (index != mainStart) {
                // Since it wont form a pattern if it does not start with the prefix
                break;
            }
            count++;
            mainStart += pattern.length();
        } while (mainStart < mainString.length());
        return count;
    }
}
