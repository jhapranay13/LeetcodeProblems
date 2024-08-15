package leetcode.StackAndQueues;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4

Constraints:

1 <= k <= nums.length <= 104
-104 <= nums[i] <= 104
Accepted
939,657
Submissions
1,570,586
 *
 */

public class _215_KthLargestElementInArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_215_KthLargestElementInArray obj = new _215_KthLargestElementInArray();
		obj.findKthLargest1(new int[] {3,2,1,5,6,4}, 2);
	}
	//=============================================================================
	//Using Priority Queue. Sorting can also be used.
	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer i, Integer j) {
				return j - i;
			}
		});

		for (int num : nums) {
			pq.offer(num);
		}

		while (k-- > 1) {
			pq.poll();
		}
		return pq.poll();
	}
	//=============================================================================
	//Using quickSelect Algo.
	public int findKthLargest1(int[] nums, int k) {
		int pos = nums.length - k;
		return quickSelect(nums, pos, 0, nums.length - 1);
	}

	private int quickSelect(int[] nums, int pos, int lo, int hi) {
		int index = find(nums, lo, hi);

		if (index == pos) {
			return nums[index];
		} else if (index < pos){
			return quickSelect(nums, pos, index + 1, hi);
		} else {
			return quickSelect(nums, pos, lo, index - 1);
		}
	}

	private int find(int[] nums, int lo, int hi) {
		int pivotVal = nums[hi];
		int index = lo;

		for (int i = lo; i < hi; i++) {

			if (nums[i] < pivotVal) {
				int temp = nums[index];
				nums[index++] = nums[i];
				nums[i] = temp;
			}
		}
		int temp = nums[index];
		nums[index] = nums[hi];
		nums[hi] = temp;
		return index;
	}
}
