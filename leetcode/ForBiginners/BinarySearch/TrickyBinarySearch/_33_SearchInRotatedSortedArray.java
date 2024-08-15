package leetcode.ForBiginners.BinarySearch.TrickyBinarySearch;


/**
 * 
 * @author Pranay Jha
 *
 *There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:

Input: nums = [1], target = 0
Output: -1

Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
All values of nums are unique.
nums is guaranteed to be rotated at some pivot.
-104 <= target <= 104
 */


public class _33_SearchInRotatedSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int search(int[] nums, int target) {
		int minIndex = findMinimumIndex(nums);
		int index = -1;

		if (target > nums[nums.length - 1]) {
			index = binarySearch(nums, 0, minIndex - 1, target);  
		} else {
			index = binarySearch(nums, minIndex, nums.length - 1, target);
		}
		return index;
	}

	private int binarySearch(int[] nums, int lo, int hi, int target) {

		while(lo <= hi) {
			int pivot = lo + (hi - lo) /2;

			if(nums[pivot] == target) {
				return pivot;
			} else if (nums[pivot] < target) {
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		return -1;
	}                      

	private int findMinimumIndex(int[] nums) {
		int hi = nums.length - 1;
		int lo = 0;
		//Variation of binarySearch
		while (lo < hi) {
			int pivot = lo + (hi - lo) / 2;

			if (nums[pivot] <= nums[hi]) {
				hi = pivot;
			} else {
				lo = pivot + 1;
			}
		}
		return hi;
	}

	//===============================================================================================
	public int search1(int[] nums, int target) {

		if( nums == null || nums.length == 0 ) {
			return -1;
		}

		if( nums.length == 1 ) {
			return nums[ 0 ] == target ? 0 : -1;
		}
		int index = find( nums );
		int ans = -1;

		if( index == 0 ) {
			ans = bs( target, nums, 0, nums.length - 1 );
		} else if( target >= nums[ index ] && target <= nums[ nums.length - 1 ] ) {
			ans = bs( target, nums, index, nums.length - 1 );
		} else if( target >= nums[ 0 ] && target <= nums[ index - 1 ] ){
			ans = bs( target, nums, 0, index - 1 );
		} else {
			ans = -1;
		}
		return ans;
	}

	private int bs( int t, int[] nums, int lo, int hi ) {

		while( lo <= hi ) {
			int pivot = lo + ( hi - lo ) / 2;

			if( nums[ pivot ] == t ) {
				return pivot;
			} else if( nums[ pivot ] < t ) {
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		return lo > hi ? -1 : lo;
	}

	private int find( int[] nums ) {
		int lo = 0;
		int hi = nums.length - 1;

		while( lo < hi ) {
			int p = lo + ( hi - lo ) / 2;

			if( nums[ p ] > nums[ p + 1 ] ) {
				return p + 1;
			} else if( nums[ p ] < nums[ hi ] ) {
				hi = p;
			} else {
				lo = p;
			}
		}
		return lo;
	}
}
