package leetcode.Greedy;

/**
 *
 * A decimal number is called deci-binary if each of its digits is either 0 or 1 without any leading zeros. For example, 101 and 1100 are deci-binary, while 112 and 3001 are not.
 *
 * Given a string n that represents a positive decimal integer, return the minimum number of positive deci-binary numbers needed so that they sum up to n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = "32"
 * Output: 3
 * Explanation: 10 + 11 + 11 = 32
 * Example 2:
 *
 * Input: n = "82734"
 * Output: 8
 * Example 3:
 *
 * Input: n = "27346209830709182346"
 * Output: 9
 *
 *
 * Constraints:
 *
 * 1 <= n.length <= 10^5
 * n consists of only digits.
 * n does not contain any leading zeros and represents a positive integer.
 *
 */

public class _1689_Partitioning_Into_Minimum_Number_Of_Deci_Binary_Numbers {
    /**
     Number suppose is 28764

     2  8. 7. 6. 4
     1. 1. 1. 1. 1
     1. 1. 1. 1. 1
     0. 1. 1. 1. 1
     0. 1. 1. 1. 1
     0. 1. 1. 1. 0
     0. 1. 1. 1. 0
     0. 1. 1. 0. 0
     0  1. 0. 0. 0

     So the numbers are 11111 11111 1111 1111 1110 1110 1100 1000 So the number is 8. which is like the
     heighest digit present in the number.
     */
    public int minPartitions(String n) {
        int max = 0;

        for (char ch : n.toCharArray()) {
            max = Math.max(max, ch - '0');
        }
        return max;
    }
}
