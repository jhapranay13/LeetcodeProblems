package leetcode.hard;

/**
 *
 * Given two strings low and high that represent two integers low and high where low <= high, return the number of strobogrammatic numbers in the range [low, high].
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 *
 *
 * Example 1:
 *
 * Input: low = "50", high = "100"
 * Output: 3
 * Example 2:
 *
 * Input: low = "0", high = "0"
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= low.length, high.length <= 15
 * low and high consist of only digits.
 * low <= high
 * low and high do not contain any leading zeros except for zero itself.
 *
 */

public class _248_Strobogrammatic_Number_III {
    public int strobogrammaticInRange(String low, String high) {

        for (int i = low.length(); i <= high.length(); i++) {
            recur(low, high, new char[i], 0, i - 1);
        }
        return ans;
    }
    private char[][] pairs = {{'0', '0'}, {'1', '1'}, {'8', '8'}, {'9', '6'}, {'6', '9'}};
    private int ans = 0;

    private void recur(String low, String high, char[] holder, int lo,  int hi) {

        if (lo > hi) {
            String res = new String(holder);

            if (!(res.length() == low.length() && res.compareTo(low) < 0) &&
                    !(res.length() == high.length() && res.compareTo(high) > 0)) {
                ans++;
            }
            return;
        }

        for (char[] pair : pairs) {
            holder[lo] = pair[0];
            holder[hi] = pair[1];

            if (holder.length != 1 && holder[0] == '0') {
                continue;
            }
            // if lo == hi then it should be equal as it has to be read the same 180 degree
            if (lo == hi && pair[0] != pair[1]) {
                continue;
            }
            recur(low, high, holder, lo + 1, hi - 1);
        }
    }
}
