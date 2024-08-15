package leetcode.HashMapHashSet;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         In a array nums of size 2 * n, there are n + 1 unique elements, and
 *         exactly one of these elements is repeated n times.
 * 
 *         Return the element repeated n times.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums[1,2,3,3] Output: 3
 * 
 *         Example 2:
 * 
 *         Input: nums[2,1,2,5,3,2] Output: 2
 * 
 *         Example 3:
 * 
 *         Input: nums[5,1,5,2,5,3,5,4] Output: 5
 * 
 * 
 *         Note:
 * 
 *         4 <= nums.length <= 10000 0 <= nums[i] < 10000 nums.length is even
 *
 */

public class _961_NRepeatedElementInSize2NArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int repeatedNTimes(int[] nums) {
		Set<Integer> seen = new HashSet<>();
		int ans = -1;

		for (int num : nums) {

			if (!seen.add(num)) {
				ans = num;
				break;
			}
		}
		return ans;
	}
}
