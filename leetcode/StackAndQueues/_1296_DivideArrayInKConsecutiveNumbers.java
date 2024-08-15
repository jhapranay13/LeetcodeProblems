package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of integers nums and a positive integer k, find
 *         whether it is possible to divide this array into sets of k
 *         consecutive numbers.
 * 
 *         Return true if it is possible. Otherwise, return false.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,2,3,3,4,4,5,6], k = 4 Output: true Explanation:
 *         Array can be divided into [1,2,3,4] and [3,4,5,6]. Example 2:
 * 
 *         Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3 Output: true
 *         Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5]
 *         and [9,10,11]. Example 3:
 * 
 *         Input: nums = [3,3,2,2,1,1], k = 3 Output: true Example 4:
 * 
 *         Input: nums = [1,2,3,4], k = 3 Output: false Explanation: Each array
 *         should be divided in subarrays of size 3.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= k <= nums.length <= 105 1 <= nums[i] <= 109
 * 
 * 
 *         Note: This question is the same as 846:
 *         https://leetcode.com/problems/hand-of-straights/
 *
 */

public class _1296_DivideArrayInKConsecutiveNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean isPossibleDivide(int[] nums, int k) {
		Map<Integer, Integer> numFreqMap = new HashMap<>();

		for (int num : nums) {
			numFreqMap.put(num, numFreqMap.getOrDefault(num, 0) + 1);
		}
		Deque<Integer> holder = new LinkedList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.addAll(numFreqMap.keySet());
		int count = k;

		while (!pq.isEmpty()) {
			int num = pq.poll();
			int freq = numFreqMap.get(num);

			if (!holder.isEmpty() && holder.getLast() + 1 != num) {
				return false;
			}

			if (freq == 1) {
				numFreqMap.remove(num);
			} else {
				numFreqMap.put(num, --freq);
			}
			holder.offer(num);

			if (--count == 0) {

				while (!holder.isEmpty()) {
					int holderVal = holder.poll();

					if (numFreqMap.containsKey(holderVal)) {
						pq.offer(holderVal);
					}
				}
				count = k;
			}
		}
		return holder.isEmpty();
	}
}
