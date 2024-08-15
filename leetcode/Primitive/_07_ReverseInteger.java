package leetcode.Primitive;

/**
 * 
 * @author Pranay Jha
 *
 *Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).



Example 1:

Input: x = 123
Output: 321

Example 2:

Input: x = -123
Output: -321

Example 3:

Input: x = 120
Output: 21

Example 4:

Input: x = 0
Output: 0


Constraints:

-231 <= x <= 231 - 1
 *
 */

public class _07_ReverseInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int reverse(int number) {
		int powerOfTen = 10;
		int result = 0;

		while (number != 0) {
			int pop = number % 10;
			
			//Checking for the max and Min value of the Integer range to 
			//avoid overflow.
			if (result > Integer.MAX_VALUE / 10 || 
					(result == Integer.MAX_VALUE / 10 && pop > 7)) {
				return 0;
			}

			if (result < Integer.MIN_VALUE / 10 || 
					(result == Integer.MIN_VALUE / 10 && pop < -8)) {
				return 0;
			}
			result *= powerOfTen;
			result += number % powerOfTen;
			number = number / powerOfTen;
		}
		return result;
	}
}
