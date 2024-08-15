package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible.



Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false


Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
nums is guaranteed to be rotated at some pivot.
-104 <= target <= 104


Follow up: This problem is similar to Search in Rotated Sorted Array, but nums may contain duplicates. Would this affect the runtime complexity? How and why?
 *
 */

public class _81_SearchInRotatedSortedArray2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean search(int[] nums, int target) {

		if (nums == null || nums.length == 0) {
			return false;
		}

		if (nums.length == 1) {
			return nums[0] == target;
		}
		int minIndex = findLeftMostMinIndex(nums);

		if (minIndex == 0) {
			return binarySearch(nums, target, minIndex, nums.length - 1);
		} else if (target >= nums[minIndex] && target <= nums[nums.length - 1]) {
			return binarySearch(nums, target, minIndex, nums.length - 1);
		} else if (target >= nums[0] && target <= nums[minIndex - 1]){
			return binarySearch(nums, target, 0, minIndex - 1);
		}
		return false;
	}

	private boolean binarySearch(int[] nums, int target, int lo, int hi) {


		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (nums[pivot] == target) {
				return true;
			} else if (nums[pivot] > target) {
				hi = pivot - 1;
			} else {
				lo = pivot + 1;
			}
		}
		return false;
	}

	private int findLeftMostMinIndex(int[] nums) {
		int lo = 0;
		int hi = nums.length - 1;

		while (lo < hi) {
			int pivot = lo + (hi - lo) / 2;

			if (hi < nums.length - 1 && nums[hi] > nums[hi + 1]) {
				return hi + 1;
			}

			if (nums[pivot] == nums[hi]) {
				hi--;
			} else if (nums[pivot] > nums[hi]) {
				lo = pivot + 1;
			} else {
				hi = pivot;
			}
		}
		return hi;
	}

	//===================================================================================================
	//Another Version with Linear Search
	public boolean search1(int[] nums, int target) {

		if( nums == null || nums.length == 0 ) {
			return false;
		}

		if( nums.length == 1 ) {
			return nums[ 0 ] == target;
		}
		int minIndex = getMinIndex( nums );
		System.out.println( minIndex );

		if( minIndex == 0 ) {
			return bs( nums, target, 0, nums.length - 1 );
		} else {

			if( target >= nums[ minIndex ] && target <= nums[ nums.length - 1 ] ) {
				return bs( nums, target, minIndex, nums.length - 1 );
			} else {
				return bs( nums, target, 0, minIndex - 1 );
			}
		} 
	}

	public boolean bs( int[] n, int t, int lo, int hi ) {

		while( lo <= hi ) {
			int pivot = lo + ( hi - lo ) / 2;

			if( n[ pivot ] == t ) {
				return true;
			} else if( n[ pivot ] < t ) {
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		return false;
	}

	private int getMinIndex( int[] n ) {
		int hi = n.length - 1;

		while( hi > 0 && n[ hi ] >= n[ hi - 1 ] ) {
			hi--;
		}
		return hi;
	}
	//=====================================================================
	//Another way
	 public boolean search2(int[] nums, int target) {
	        
	        if (nums.length == 1) {
	            return nums[0] == target;
	        }
	        int minIndex = binarySearchMin(nums);
	        int lo = 0;
	        int hi = nums.length - 1;
	        
	        if (minIndex == 0) {
	            return binarySearch2(nums, target, 0, hi);
	        } else if (target >= nums[0] && target <= nums[minIndex - 1]) {
	            return binarySearch2(nums, target, 0, minIndex - 1);
	        } else if(target >= nums[minIndex] && target <= nums[hi]) {
	            return binarySearch2(nums, target, minIndex, hi);
	        }
	        return false;
	    }
	    
	    private boolean binarySearch2(int[] nums, int target, int lo, int hi) {
	        
	        while (lo <= hi) {
	            int pivot = lo + (hi - lo) / 2;
	            
	            if (nums[pivot] == target) {
	                return true;
	            } else if (nums[pivot] < target) {
	                lo = pivot + 1;
	            } else {
	                hi = pivot - 1;
	            }
	        }
	        return false;
	    }
	    
	    private int binarySearchMin(int[] nums) {
	        int lo = 0;
	        int hi = nums.length - 1;
	        
	        while (lo < hi) {
	            
	            while (lo < hi && nums[lo] == nums[lo + 1]) {
	                lo++;
	            }
	            
	            while (hi > lo && nums[hi] == nums[hi - 1]) {
	                hi--;
	            }
	            
	            if (lo >= hi) {
	                break;
	            }
	            int pivot = lo + (hi - lo) / 2;
	            
	            if (nums[pivot] > nums[pivot + 1]) {
	                return pivot + 1;
	            } else if (nums[pivot] <= nums[hi]) {
	                hi = pivot;
	            } else {
	                lo = pivot + 1;
	            }
	        }
	        return hi;
	    }
}
