package leetcode.medium;

/**
 *
 * You are given a 0-indexed binary string s which represents the types of buildings along a street where:
 *
 * s[i] = '0' denotes that the ith building is an office and
 * s[i] = '1' denotes that the ith building is a restaurant.
 * As a city official, you would like to select 3 buildings for random inspection. However, to ensure variety, no two consecutive buildings out of the selected buildings can be of the same type.
 *
 * For example, given s = "001101", we cannot select the 1st, 3rd, and 5th buildings as that would form "011" which is not allowed due to having two consecutive buildings of the same type.
 * Return the number of valid ways to select 3 buildings.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "001101"
 * Output: 6
 * Explanation:
 * The following sets of indices selected are valid:
 * - [0,2,4] from "001101" forms "010"
 * - [0,3,4] from "001101" forms "010"
 * - [1,2,4] from "001101" forms "010"
 * - [1,3,4] from "001101" forms "010"
 * - [2,4,5] from "001101" forms "101"
 * - [3,4,5] from "001101" forms "101"
 * No other selection is valid. Thus, there are 6 total ways.
 * Example 2:
 *
 * Input: s = "11100"
 * Output: 0
 * Explanation: It can be shown that there are no valid selections.
 *
 *
 * Constraints:
 *
 * 3 <= s.length <= 10^5
 * s[i] is either '0' or '1'.
 *
 */

public class _2222_Number_of_Ways_to_Select_Buildings {
    //Number of 101 or 010 patterns formed
    public long numberOfWays(String s) {
        int zeroCnt = 0;
        int oneCnt = 0;
        int[] leftZero = new int[s.length()];
        int[] leftOne = new int[s.length()];

        if (s.charAt(0) == '0') {
            leftZero[0] = 1;
        } else {
            leftOne[0] = 1;
        }

        for (int i = 1; i < s.length(); i++) {
            leftZero[i] = leftZero[i - 1];
            leftOne[i] = leftOne[i - 1];

            if (s.charAt(i) == '0') {
                leftZero[i] += 1;
            } else {
                leftOne[i] += 1;
            }
        }
        int[] rightZero = new int[s.length()];
        int[] rightOne = new int[s.length()];

        if (s.charAt(s.length() - 1) == '0') {
            rightZero[s.length() - 1] = 1;
        } else {
            rightOne[s.length() - 1] = 1;
        }

        for (int i = s.length() - 2; i >= 0; i--) {
            rightZero[i] = rightZero[i + 1];
            rightOne[i] = rightOne[i + 1];

            if (s.charAt(i) == '0') {
                rightZero[i] += 1;
            } else {
                rightOne[i] += 1;
            }
        }
        long ans = 0;

        for (int i = 1; i < s.length() - 1; i++) {

            if (s.charAt(i) == '0') {
                ans += (leftOne[i] * rightOne[i]);
            } else {
                ans += (leftZero[i] * rightZero[i]);
            }
        }
        return ans;
    }
}
