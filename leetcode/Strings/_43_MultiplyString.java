package leetcode.Strings;

/**
 * 
 * @author Pranay Jha
 *
 *Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
 
Constraints:

1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 *
 */

public class _43_MultiplyString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String multiply(String num1, String num2) {

		if (num1.equals("0") || num2.equals("0")) {
			return "0";
		}
		int[] cache = new int[num1.length() + num2.length()];
		int cIndex = cache.length - 1;

		for (int j = num2.length() - 1; j >= 0; j--) {
			int decrement = num2.length() - 1 - j;
			cIndex = cache.length - 1 - decrement;
			int two = (num2.charAt( j ) - '0');
			int carry  = 0;

			for (int i = num1.length() - 1; i >= 0; i--) {
				int one = (num1.charAt( i ) - '0');
				int res = one * two;
				res += carry;
				res += cache[cIndex];
				carry = res / 10;
				cache[cIndex--] = res % 10;
			}
			cache[cIndex] = carry;
		}
		int index = 0;

		while (index < cache.length && cache[index] == 0 ) {
			index++;
		}
		StringBuilder ans = new StringBuilder();

		for (; index < cache.length; index++) {
			ans.append( cache[ index ] );
		}
		return ans.toString();
	}
}
