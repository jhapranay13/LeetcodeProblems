package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of integers nums and an integer k, return the total
 *         number of continuous subarrays whose sum equals to k.
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,1,1], k = 2 Output: 2 Example 2:
 * 
 *         Input: nums = [1,2,3], k = 3 Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 2 * 104 -1000 <= nums[i] <= 1000 -107 <= k <= 107
 *
 */

public class _560_SubarraySumEqualsK {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//PreSum Concept. N^ 2 solution
	public int subarraySum(int[] nums, int k) {
		int[] preSum = new int[nums.length];
		preSum[0] = nums[0];

		for (int i = 1; i < nums.length; i++) {
			preSum[i] = preSum[i - 1] + nums[i];
		}
		int ans = 0;

		for (int i = 0; i < nums.length; i++) {

			for (int j = i; j < nums.length; j++) {
				int sum = 0;

				if (i == 0) {
					sum = preSum[j];
				} else {
					sum = preSum[j] - preSum[i - 1];
				}

				if (sum == k) {
					ans++;
				}
			}
		}
		return ans;
	}
	//==============================================================================
	//using HashMap
	public int subarraySum1(int[] nums, int k) {
		int sum = 0;
		int ans = 0;
		Map<Integer, Integer> sumCountMap = new HashMap<>();
		sumCountMap.put(0, 1);

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			if (sumCountMap.containsKey(sum - k)) {
				ans += sumCountMap.get(sum - k);
			}
			sumCountMap.put(sum, sumCountMap.getOrDefault(sum, 0) + 1);
		}
		return ans;
	}


}
