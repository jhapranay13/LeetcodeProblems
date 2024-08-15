package leetcode.HashMapHashSet;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of integers nums and an integer k, return the number
 *         of unique k-diff pairs in the array.
 * 
 *         A k-diff pair is an integer pair (nums[i], nums[j]), where the
 *         following are true:
 * 
 *         0 <= i < j < nums.length |nums[i] - nums[j]| == k Notice that |val|
 *         denotes the absolute value of val.
 * 
 *         Example 1:
 * 
 *         Input: nums = [3,1,4,1,5], k = 2 Output: 2 Explanation: There are two
 *         2-diff pairs in the array, (1, 3) and (3, 5). Although we have two 1s
 *         in the input, we should only return the number of unique pairs.
 *         
 *         Example 2:
 * 
 *         Input: nums = [1,2,3,4,5], k = 1 Output: 4 Explanation: There are
 *         four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 *         
 *         Example 3:
 * 
 *         Input: nums = [1,3,1,5,4], k = 0 Output: 1 Explanation: There is one
 *         0-diff pair in the array, (1, 1). Example 4:
 * 
 *         Input: nums = [1,2,4,4,3,3,0,9,2,3], k = 3 Output: 2 Example 5:
 * 
 *         Input: nums = [-1,-2,-3], k = 1 Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 104 -107 <= nums[i] <= 107 0 <= k <= 107
 *
 */

public class _532_KDiffPairsInAnArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int findPairs(int[] nums, int k) {
		Set<Integer> numSet = new HashSet<>();
		Set<String> visited = new HashSet<>();
		int count = 0;

		for (int  i = 0; i < nums.length; i++) {
			int num = nums[i];
			int sum = num + k;
			int diff = num - k;

			if (numSet.contains(sum)) {
				String key = sum + "," + num;
				String reverseKey = num + "," + sum;

				if (!visited.contains(key) && !visited.contains(reverseKey)) {
					visited.add(key);
					visited.add(reverseKey);
					count++;
				}
			}

			if (numSet.contains(diff)) {
				String key = diff + "," + num;
				String reverseKey = num + "," + diff;

				if (!visited.contains(key) && !visited.contains(reverseKey)) {
					visited.add(key);
					visited.add(reverseKey);
					count++;
				}
			}
			numSet.add(num);
		}
		return count;
	}
}
