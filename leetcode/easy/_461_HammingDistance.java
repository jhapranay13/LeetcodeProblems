package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *         The Hamming distance between two integers is the number of positions
 *         at which the corresponding bits are different.
 * 
 *         Given two integers x and y, return the Hamming distance between them.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: x = 1, y = 4 Output: 2 Explanation: 1 (0 0 0 1) 
 *         											  4 (0 1 0 0)
 *   													   ^   ^
 *         The above arrows point to positions where the corresponding bits
 *         are different. 
 *         
 *         Example 2:
 * 
 *         Input: x = 3, y = 1 Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         0 <= x, y <= 231 - 1
 *
 */

public class _461_HammingDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int hammingDistance(int x, int y) {
		int mask = 1;
		int count = 0;

		while (x > 0 || y > 0) {
			int xMask = x & mask;
			int yMask = y & mask;

			if (xMask != yMask) {
				count++;
			} 
			x >>= 1;
			y >>= 1;
		}
		return count;
	}
}
