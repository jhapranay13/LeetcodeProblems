package leetcode.easy;

/**
 *
 *Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
 *
 * Substrings that occur multiple times are counted the number of times they occur.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 * Example 2:
 *
 * Input: s = "10101"
 * Output: 4
 * Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either '0' or '1'.
 *
 *
 */

public class _696_CountBinarySubstrings {

    public int countBinarySubstrings(String s) {
        int prevSeq = -1;
        int currSeq = 0;
        int ans = 0;
        int prevCh = '\u0000';

        for (char ch : s.toCharArray()) {
            if (ch == prevCh) {
                currSeq++;
            } else {
                prevSeq = currSeq;
                currSeq = 1;
                prevCh = ch;
            }

            if (currSeq <= prevSeq) {
                ans++;
            }
        }
        return ans;
    }
    //=============================================================================================
    //Sligtly different
    public int countBinarySubstrings1(String s) {
        int currSeq = 0;
        int prevSeq = 0;
        char prev = '\u0000';
        char curr = prev;
        int ans = 0;

        for (char ch : s.toCharArray()) {

            if (prev != ch) {
                prev = ch;
                prevSeq = currSeq;
                currSeq = 1;
            } else {
                currSeq++;
            }

            if (currSeq <= prevSeq) {
                ans++;
            }
        }
        return ans;
    }
}
