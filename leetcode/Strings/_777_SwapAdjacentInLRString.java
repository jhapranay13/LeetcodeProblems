package leetcode.Strings;

/**
 * n a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.
 *
 *
 *
 * Example 1:
 *
 * Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * Output: true
 * Explanation: We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 * Example 2:
 *
 * Input: start = "X", end = "L"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= start.length <= 104
 * start.length == end.length
 * Both start and end will only consist of characters in 'L', 'R', and 'X'.
 *
 */
//Think of the L and R's as people on a horizontal line, where X is a space. The people can't
//        cross each other, and also you can't go from XRX to RXX.

public class _777_SwapAdjacentInLRString {
    /**
     Without 'X', 'L' and 'R' has the same relative position in start and end
     For any corresponding 'R' in start and end, say start[i] and end[j], i <= j, and for any corresponding 'L', i >= j.
     */
    public boolean canTransform(String start, String end) {
        int i = 0;
        int j = 0;

        while (i < start.length() && j < end.length()) {

            if (start.charAt(i) == 'X') {
                i++;
                continue;
            }

            if (j < end.length() && end.charAt(j) == 'X') {
                j++;
                continue;
            }

            if (start.charAt(i) == end.charAt(j)) {
                if (start.charAt(i) == 'R' && i > j) { // Since i < j R cannot move left so false;
                    return false;
                } else if (start.charAt(i) == 'L' && i < j) { //since i < j L cannot move right so false
                    return false;
                }
            } else {
                return false;
            }
            i++;
            j++;
        }

        while (i < start.length()) {

            if (start.charAt(i++) != 'X') {
                return false;
            }
        }


        while (j < end.length()) {

            if (end.charAt(j++) != 'X') {
                return false;
            }
        }
        return true;
    }
}
