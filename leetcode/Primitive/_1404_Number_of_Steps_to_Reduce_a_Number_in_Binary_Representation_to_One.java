package leetcode.Primitive;

/**
 *
 * Given the binary representation of an integer as a string s, return the number of steps to reduce it to 1 under the following rules:
 *
 * If the current number is even, you have to divide it by 2.
 *
 * If the current number is odd, you have to add 1 to it.
 *
 * It is guaranteed that you can always reach one for all test cases.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1101"
 * Output: 6
 * Explanation: "1101" corressponds to number 13 in their decimal representation.
 * Step 1) 13 is odd, add 1 and obtain 14.
 * Step 2) 14 is even, divide by 2 and obtain 7.
 * Step 3) 7 is odd, add 1 and obtain 8.
 * Step 4) 8 is even, divide by 2 and obtain 4.
 * Step 5) 4 is even, divide by 2 and obtain 2.
 * Step 6) 2 is even, divide by 2 and obtain 1.
 * Example 2:
 *
 * Input: s = "10"
 * Output: 1
 * Explanation: "10" corressponds to number 2 in their decimal representation.
 * Step 1) 2 is even, divide by 2 and obtain 1.
 * Example 3:
 *
 * Input: s = "1"
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of characters '0' or '1'
 * s[0] == '1'
 *
 */

public class _1404_Number_of_Steps_to_Reduce_a_Number_in_Binary_Representation_to_One {
    public int numSteps(String s) {
        int countSteps = 0;
        int carry = 0;
        //If last bit is one and carry is also one two steps will be required. So adding carry in the end
        for(int i = s.length()-1;i>=1;i--) {
            int rightMostBit = s.charAt(i)-'0';

            if((rightMostBit+carry) == 1) {
                carry=1;
                countSteps += 2;
            } else {
                countSteps++;
            }
        }
        return countSteps+carry;
    }
}
