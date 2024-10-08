package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *Given an integer x, return true if x is palindrome integer.

An integer is a palindrome when it reads the same backward as forward. For example, 121 is palindrome while 123 is not.

 

Example 1:

Input: x = 121
Output: true

Example 2:

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Example 4:

Input: x = -101
Output: false
 

Constraints:

-231 <= x <= 231 - 1
 

Follow up: Could you solve it without converting the integer to a string?
 *
 */
public class _09_PalindromeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean isPalindrome(int x) {
        int num = Math.abs(x);
        int powOfTen = 10;
        int reverseNum = 0;
        
        while (num != 0) {
            int val = num % 10;
            num = num / 10;
            reverseNum = val += reverseNum *= 10;
            /*
             * equivalent to 
             * reverseNum *= 10;
             * reverseNum += val;
             */
        }
        return x == reverseNum;
    }
}
