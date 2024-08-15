package leetcode.StackAndQueues;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array nums sorted in non-decreasing order, return an
 *         array of the squares of each number sorted in non-decreasing order.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [-4,-1,0,3,10] Output: [0,1,9,16,100] Explanation:
 *         After squaring, the array becomes [16,1,0,9,100]. After sorting, it
 *         becomes [0,1,9,16,100]. Example 2:
 * 
 *         Input: nums = [-7,-3,2,3,11] Output: [4,9,9,49,121]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 104 -104 <= nums[i] <= 104 nums is sorted in
 *         non-decreasing order.
 * 
 * 
 *         Follow up: Squaring each element and sorting the new array is very
 *         trivial, could you find an O(n) solution using a different approach?
 *
 */

public class _977_SquareOfSortedArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] sortedSquares(int[] nums) {
		int lo = 0;
		int hi = nums.length - 1;
		int ans[] = new int[nums.length];
		int index = nums.length - 1;

		while (lo <= hi) {
			int loProd = nums[lo] * nums[lo];
			int hiProd = nums[hi] * nums[hi];

			if (loProd > hiProd) {
				ans[index] = loProd;
				lo++;
			} else {
				ans[index] = hiProd;
				hi--;
			}
			index--;
		}
		return ans;
	}

	public int[] sortedSquares1(int[] nums) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<>() {
			public int compare(Integer a, Integer b) {
				return a - b;
			}
		});
		int[] ans = new int[nums.length];

		for (int num : nums) {
			pq.offer(num * num);
		}

		for (int i = 0; i < ans.length; i++) {
			ans[i] = pq.poll();
		}
		return ans;
	}
}
