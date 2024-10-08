package leetcode.Primitive;

/**
 * 
 * @author Pranay Jha
 *
 *Given a non-empty array of decimal digits representing a non-negative integer, increment one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contains a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.

Example 2:

Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.

Example 3:

Input: digits = [0]
Output: [1]
 
Constraints:

1 <= digits.length <= 100
0 <= digits[i] <= 9
 *
 */

public class _66_PlusOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] plusOne(int[] digits) {

		if (digits == null || digits.length == 0) {
			return digits;
		}
		int carry = 1;

		for (int i = digits.length - 1; i >= 0; i--) {
			digits[i] += carry;
			carry = digits[i] / 10;
			digits[i] %= 10;
		}

		if (carry > 0) {
			int[] ans = new int[digits.length + 1];
			ans[0] = carry;

			for (int i = 0; i < digits.length; i++) {
				ans[i + 1] = digits[i];
			}
			digits = ans;
		}
		return digits;
	}
}
