package leetcode.TreeSetTreeMap;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an array of integers nums, there is a sliding window of
 *         size k which is moving from the very left of the array to the very
 *         right. You can only see the k numbers in the window. Each time the
 *         sliding window moves right by one position.
 * 
 *         Return the max sliding window.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,3,-1,-3,5,3,6,7], k = 3 Output: [3,3,5,5,6,7]
 *         Explanation: Window position Max --------------- ----- [1 3 -1] -3 5
 *         3 6 7 3 1 [3 -1 -3] 5 3 6 7 3 1 3 [-1 -3 5] 3 6 7 5 1 3 -1 [-3 5 3] 6
 *         7 5 1 3 -1 -3 [5 3 6] 7 6 1 3 -1 -3 5 [3 6 7] 7
 * 
 *         Example 2:
 * 
 *         Input: nums = [1], k = 1 Output: [1]
 * 
 *         Example 3:
 * 
 *         Input: nums = [1,-1], k = 1 Output: [1,-1]
 * 
 *         Example 4:
 * 
 *         Input: nums = [9,11], k = 2 Output: [11]
 * 
 *         Example 5:
 * 
 *         Input: nums = [4,-2], k = 2 Output: [4]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 105 -104 <= nums[i] <= 104 1 <= k <= nums.length
 *
 */

public class _239_SlidingWindowMaximum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] maxSlidingWindow(int[] nums, int k) {
		Deque<Integer> monoQueue = new LinkedList<>();
		int[] ans = new int[nums.length - k + 1];
		int index = 0;

		for (int i = 0; i < nums.length; i++) {
			while (!monoQueue.isEmpty() && nums[monoQueue.getLast()] <= nums[i]) {
				monoQueue.pollLast();
			}

			while (!monoQueue.isEmpty() && monoQueue.getFirst() <= i - k) {
				monoQueue.pollFirst();
			}
			monoQueue.offer(i);

			if (i >= k - 1) {
				ans[index++] = nums[monoQueue.getFirst()];
			}
		}
		return ans;
	}
	//=============================================================================================
	//TreeMap solution
	public int[] maxSlidingWindow1(int[] nums, int k) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int[] ans = new int[nums.length - k + 1];
		int index = 0;

		for (int i = 0; i < k; i++) {
			int count = map.getOrDefault(nums[i], 0);
			map.put(nums[i], count + 1);
		}

		for (int i = 0; i < ans.length; i++) {
			ans[index++] = map.lastKey();

			if (map.get(nums[i]) == 1) {
				map.remove(nums[i]);
			} else {
				map.put(nums[i], map.get(nums[i]) - 1);
			}

			if (i + k < nums.length) {
				map.put(nums[i + k], map.getOrDefault(nums[i + k], 0) + 1);
			}
		}
		return ans;
	}
}
