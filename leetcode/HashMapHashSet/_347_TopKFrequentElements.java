package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array nums and an integer k, return the k most
 *         frequent elements. You may return the answer in any order.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,1,1,2,2,3], k = 2 Output: [1,2] 
 *         
 *         Example 2:
 * 
 *         Input: nums = [1], k = 1 Output: [1]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 105 k is in the range [1, the number of unique
 *         elements in the array]. It is guaranteed that the answer is unique.
 * 
 * 
 *         Follow up: Your algorithm's time complexity must be better than O(n
 *         log n), where n is the array's size.
 *
 */

public class _347_TopKFrequentElements {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> numFreqMap = new HashMap<>();

		for (int num : nums) {
			int freq = numFreqMap.getOrDefault(num, 0);
			numFreqMap.put(num, ++freq);
		}
		PriorityQueue<Integer> queue = new PriorityQueue<>(
				(Integer num1, Integer num2) -> numFreqMap.get(num1) - numFreqMap.get(num2));

		for (int num : numFreqMap.keySet()) {
			queue.offer(num);

			while(queue.size() > k) {
				queue.poll();
			}
		}
		int[] ans = new int[k];
		int index = 0;

		while (!queue.isEmpty()){
			ans[index++] = queue.poll();
		}
		return ans;
	}
}
