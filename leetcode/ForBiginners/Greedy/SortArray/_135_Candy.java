package leetcode.ForBiginners.Greedy.SortArray;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         There are n children standing in a line. Each child is assigned a
 *         rating value given in the integer array ratings.
 * 
 *         You are giving candies to these children subjected to the following
 *         requirements:
 * 
 *         Each child must have at least one candy. Children with a higher
 *         rating get more candies than their neighbors. Return the minimum
 *         number of candies you need to have to distribute the candies to the
 *         children.
 * 
 *         Example 1:
 * 
 *         Input: ratings = [1,0,2] Output: 5 Explanation: You can allocate to
 *         the first, second and third child with 2, 1, 2 candies respectively.
 * 
 *         Example 2:
 * 
 *         Input: ratings = [1,2,2] Output: 4 Explanation: You can allocate to
 *         the first, second and third child with 1, 2, 1 candies respectively.
 *         The third child gets 1 candy because it satisfies the above two
 *         conditions.
 * 
 * 
 *         Constraints:
 * 
 *         n == ratings.length 1 <= n <= 2 * 104 0 <= ratings[i] <= 2 * 104
 *
 */

public class _135_Candy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int candy(int[] ratings) {
		int[] leftToRight = new int[ratings.length];
		int[] rightToLeft = new int[ratings.length];
		Arrays.fill(leftToRight, 1); // Since everyone will have atleast one candy

		for (int i = 1; i < ratings.length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				leftToRight[i] = leftToRight[i - 1] + 1;
			}
		}
		Arrays.fill(rightToLeft, 1); // Since everyone will have atleast one candy

		for (int i = ratings.length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1]) {
				rightToLeft[i] = rightToLeft[i + 1] + 1;
			}
		}
		int ans = 0;

		for (int i = 0; i < ratings.length; i++) {
			ans += Math.max(leftToRight[i], rightToLeft[i]);
		}
		return ans;
	}
}
