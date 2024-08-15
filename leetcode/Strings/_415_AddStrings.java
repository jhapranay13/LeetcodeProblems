package leetcode.Strings;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two non-negative integers, num1 and num2 represented as string,
 *         return the sum of num1 and num2 as a string.
 * 
 *         You must solve the problem without using any built-in library for
 *         handling large integers (such as BigInteger). You must also not
 *         convert the inputs to integers directly.
 * 
 *         Example 1:
 * 
 *         Input: num1 = "11", num2 = "123" Output: "134" 
 *         
 *         Example 2:
 * 
 *         Input: num1 = "456", num2 = "77" Output: "533" 
 *         
 *         Example 3:
 * 
 *         Input: num1 = "0", num2 = "0" Output: "0"
 * 
 * 
 *         Constraints:
 * 
 *         1 <= num1.length, num2.length <= 104 num1 and num2 consist of only
 *         digits. num1 and num2 don't have any leading zeros except for the
 *         zero itself.
 *
 */

public class _415_AddStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String addStrings(String num1, String num2) {

		if (num1 == null || num1.trim().length() == 0) {
			return num2;
		}

		if (num2 == null || num2.trim().length() == 0) {
			return num1;
		}
		int index1 = num1.length() - 1;
		int index2 = num2.length() - 1;
		int carry = 0;
		StringBuilder ans = new StringBuilder();

		while (index1 >= 0 || index2 >= 0) {
			int n1 = index1 >= 0 ? num1.charAt(index1--) - '0' : 0;
			int n2 = index2 >= 0 ? num2.charAt(index2--) - '0' : 0;
			int sum = n1 + n2 + carry;
			ans.insert(0, sum % 10);
			carry = sum / 10;
		}

		if (carry != 0) {
			ans.insert(0, carry);
		}
		return ans.toString();
	}
}
