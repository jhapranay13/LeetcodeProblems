package leetcode.BinarySearch;

/**
 * 
 * @author Pranay Jha
 *
 *         Suppose an array of length n sorted in ascending order is rotated
 *         between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7]
 *         might become:
 * 
 *         [4,5,6,7,0,1,4] if it was rotated 4 times. [0,1,4,4,5,6,7] if it was
 *         rotated 7 times. Notice that rotating an array [a[0], a[1], a[2],
 *         ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2],
 *         ..., a[n-2]].
 * 
 *         Given the sorted rotated array nums that may contain duplicates,
 *         return the minimum element of this array.
 * 
 *         You must decrease the overall operation steps as much as possible.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,3,5] Output: 1 
 *         
 *         Example 2:
 * 
 *         Input: nums = [2,2,2,0,1] Output: 0
 * 
 * 
 *         Constraints:
 * 
 *         n == nums.length 1 <= n <= 5000 -5000 <= nums[i] <= 5000 nums is
 *         sorted and rotated between 1 and n times.
 * 
 * 
 *         Follow up: This problem is similar to Find Minimum in Rotated Sorted
 *         Array, but nums may contain duplicates. Would this affect the runtime
 *         complexity? How and why?
 *
 */

public class _154_FindMinimumInRotatedSortedArray2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int findMin(int[] nums) {
		int lo = 0;
		int hi = nums.length - 1;

		while (lo < hi) {
			int pivot = lo + (hi - lo) / 2;

			if (nums[pivot] > nums[hi]) {
				lo = pivot + 1;
			} else if (nums[pivot] < nums[hi]) {
				hi = pivot;
			} else {
				hi--;
			}
		}
		return nums[hi];
	}
	//==============================================================================================
	//Another Approach
	public int findMin1(int[] nums) {
		int ans = binarySearch(nums, 0, nums.length - 1);
		return ans;
	}

	private int binarySearch(int[] nums, int lo, int hi) {

		while (lo < hi) {
			int pivot = lo + (hi - lo) / 2;

			if (nums[pivot] == nums[hi]) {
				int left = binarySearch(nums, lo, pivot);
				int right = binarySearch(nums, pivot + 1, hi);
				return Math.min(left, right);
			}

			if (pivot > 0 && nums[pivot] < nums[pivot - 1]) {
				return nums[pivot];
			}

			if (nums[pivot] < nums[hi]) {
				hi = pivot;
			} else {
				lo = pivot + 1;
			}
		}
		return nums[hi];
	}
}
