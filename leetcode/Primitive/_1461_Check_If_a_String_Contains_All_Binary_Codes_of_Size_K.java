package leetcode.Primitive;

import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * Given a binary string s and an integer k, return true if every binary code of length k is a substring of s. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00110110", k = 2
 * Output: true
 * Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indices 0, 1, 3 and 2 respectively.
 * Example 2:
 *
 * Input: s = "0110", k = 1
 * Output: true
 * Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring.
 * Example 3:
 *
 * Input: s = "0110", k = 2
 * Output: false
 * Explanation: The binary code "00" is of length 2 and does not exist in the array.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 10^5
 * s[i] is either '0' or '1'.
 * 1 <= k <= 20
 *
 */

public class _1461_Check_If_a_String_Contains_All_Binary_Codes_of_Size_K {
    public boolean hasAllCodes(String s, int k) {
        int need = 1 << k; // 2 ^ k is required;
        Set<String> v = new HashSet<>();

        for (int i = 0; i + k <= s.length(); i++) {
            String str = s.substring(i, i + k);

            if (v.add(str)) {
                need--;

                if (need == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
