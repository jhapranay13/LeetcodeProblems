package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a positive integer, check whether it has alternating bits:
 *         namely, if two adjacent bits will always have different values.
 * 
 *         Example 1:
 * 
 *         Input: n = 5 Output: true Explanation: The binary representation of 5
 *         is: 101
 * 
 *         Example 2:
 * 
 *         Input: n = 7 Output: false Explanation: The binary representation of
 *         7 is: 111.
 * 
 *         Example 3:
 * 
 *         Input: n = 11 Output: false Explanation: The binary representation of
 *         11 is: 1011.
 * 
 *         Example 4:
 * 
 *         Input: n = 10 Output: true Explanation: The binary representation of
 *         10 is: 1010.
 * 
 *         Example 5:
 * 
 *         Input: n = 3 Output: false
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 231 - 1
 *
 */

public class _693_AlternatingBits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean hasAlternatingBits(int n) {
		boolean flag = true;
		int previous = 0;
		int current = 0;
		int inputHolder = n;

		while (inputHolder > 0) {
			current = inputHolder & 1;

			if (inputHolder == n) {
				previous = current;
			} else {

				if ((previous > 0 && current > 0) ||
						(previous == 0 && current == 0)) {
					return false;
				}
				previous = current;
			}
			inputHolder >>= 1;
		}
		return flag;
	}
}
