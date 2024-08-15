package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a binary array nums, return the maximum length of a contiguous
 *         subarray with an equal number of 0 and 1.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [0,1] Output: 2 Explanation: [0, 1] is the longest
 *         contiguous subarray with an equal number of 0 and 1.
 * 
 *         Example 2:
 * 
 *         Input: nums = [0,1,0] Output: 2 Explanation: [0, 1] (or [1, 0]) is a
 *         longest contiguous subarray with equal number of 0 and 1.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 105 nums[i] is either 0 or 1.
 *
 */

public class _525_ContiguousArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int findMaxLength(int[] nums) {
		/*
		 * The countcount variable is incremented by one for every \text{1}1 encountered
		 * and the same is decremented by one for every \text{0}0 encountered.
		 * 
		 * We start traversing the array from the beginning. If at any moment, the
		 * countcount becomes zero, it implies that we've encountered equal number of
		 * zeros and ones from the beginning till the current index of the array(ii).
		 * Not only this, another point to be noted is that if we encounter the same
		 * countcount twice while traversing the array, it means that the number of
		 * zeros and ones are equal between the indices corresponding to the equal
		 * countcount values.
		 *
		 * The idea is simple just treat all the zeroes as -1 and treat 1 as it is .
		 * now iterate over the array and store the prefix sum in hashmap with index .
		 * now if you encounter the same sum again that means there is a subarray between the last
		 * time we encountered the sum and this time and so on meanwhile we also calculate the len of
		 * longest subarray present.
		 *
		 */
		Map<Integer, Integer> sumPosMap = new HashMap<>();
		sumPosMap.put(0, -1);//Initializing with 0 sum
		int ans = 0;
		int sum = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i] == 0 ? -1 : 1;

			if (sumPosMap.containsKey(sum)) {
				ans = Math.max(ans, i - sumPosMap.get(sum));
			} else {
				sumPosMap.put(sum, i);
			}
		}
		return ans;
	}
}
