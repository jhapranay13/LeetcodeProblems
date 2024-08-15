package leetcode.Strings;

import java.util.HashSet;

/**
 *
 *
 * Return the number of distinct non-empty substrings of text that can be written as the concatenation of some string with itself (i.e. it can be written as a + a where a is some string).
 *
 *
 *
 * Example 1:
 *
 * Input: text = "abcabcabc"
 * Output: 3
 * Explanation: The 3 substrings are "abcabc", "bcabca" and "cabcab".
 * Example 2:
 *
 * Input: text = "leetcodeleetcode"
 * Output: 2
 * Explanation: The 2 substrings are "ee" and "leetcodeleetcode".
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 2000
 * text has only lowercase English letters.
 *
 */

public class _1316_Distinct_Echo_Substrings {
    // Rolling hash
    public int distinctEchoSubstrings(String text) {
        long[] pow = new long[text.length()];
        long[] hash = new long[text.length()];
        long prime = 26;
        pow[0] = 1;

        for (int i = 0; i < text.length(); i++) {
            hash[i] = (text.charAt(i) * pow[i] + (i - 1 >= 0 ? hash[i - 1] : 0)) % mod;

            if (i + 1 < text.length()) {
                pow[i + 1] = pow[i] * prime % mod;
            }
        }
        HashSet<String> holder = new HashSet<>();

        for (int len = 1; len <= text.length() / 2; len++) {

            for (int i = 0; i + len * 2 <= text.length(); i++) {
                long hash1 = getHash(hash, pow, i + len - 1, i);
                long hash2 = getHash(hash, pow, i + len * 2 - 1, i + len);

                if (hash1 == hash2) {
                    holder.add(text.substring(i, i + len));
                }
            }
        }
        return holder.size();
    }
    private long mod = 1000000007;

    private long getHash(long[] hash, long[] pow, int hi, int lo) {

        if (lo == 0 || hi == 0) {
            return hash[hi];
        }
        return (hash[hi] - hash[lo - 1] * pow[hi - (lo - 1)] % mod + mod) %  mod;
    }
}
