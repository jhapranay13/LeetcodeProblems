package leetcode.BinarySearch;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array nums which is sorted in ascending order and
 *         all of its elements are unique and given also an integer k, return
 *         the kth missing number starting from the leftmost number of the
 *         array.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [4,7,9,10], k = 1 Output: 5 Explanation: The first
 *         missing number is 5. Example 2:
 * 
 *         Input: nums = [4,7,9,10], k = 3 Output: 8 Explanation: The missing
 *         numbers are [5,6,8,...], hence the third missing number is 8. Example
 *         3:
 * 
 *         Input: nums = [1,2,4], k = 3 Output: 6 Explanation: The missing
 *         numbers are [3,5,6,7,...], hence the third missing number is 6.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 5 * 104 1 <= nums[i] <= 107 nums is sorted in
 *         ascending order, and all the elements are unique. 1 <= k <= 108
 * 
 * 
 *         Follow up: Can you find a logarithmic time complexity (i.e.,
 *         O(log(n))) solution?
 *
 */

public class _1060_MissingElementInASortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int missingElement(int[] nums, int k) {

		if (nums.length == 1) {
			return nums[0] + k;
		}

		for (int i = 1; i < nums.length; i++) {
			int count = nums[i] - nums[i - 1] - 1;

			if (count >= k) {
				return nums[i - 1] + k;
			}
			k -= count;
		}
		return nums[nums.length - 1] + k;
	}

	// =============================================================================
	// Binary Search Approach
	public int missingElement1(int[] nums, int k) {

		if (nums.length == 1) {
			return nums[0] + k;
		}
		// Binary Search for justLess than
		int index = binarySearch(nums, k);
		// This is finding out the value of K just before the index and te rest can be
		// added
		// to the index
		k -= nums[index] - nums[0] - index;
		return nums[index] + k;
	}

	private int binarySearch(int[] nums, int k) {
		int lo = 0;
		int hi = nums.length - 1;
		int ans = 0;
		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (nums[pivot] - nums[0] - pivot < k) {
				lo = pivot + 1;
				ans = pivot;
			} else {
				hi = pivot - 1;
			}
		}
		return ans;
	}
}
