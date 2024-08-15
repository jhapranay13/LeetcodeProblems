package leetcode.ForBiginners.BinarySearch.SimpleBinarySearch;

/**
 * 
 * @author Pranay Jha
 *
 *Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:

Input: nums = [], target = 0
Output: [-1,-1]


Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
 *
 */


public class _34_FirstAndLastPositionInSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] searchRange(int[] nums, int target) {
		int[] ans = {-1, -1};

		if (nums != null || nums.length != 0) {
			int index = binarySearch(nums, 0, nums.length - 1, target);
			ans[0] = index;
			ans[1] = index;
			int left = index - 1;
			int right = index + 1;

			while (left != -1) {
				left = binarySearch(nums, 0, left, target);

				if (left != -1) {
					ans[0] = left;
					left = left - 1;
				}
			}

			while (right != -1) {
				right = binarySearch(nums, right, nums.length - 1, target);

				if (right != -1) {
					ans[1] = right;
					right = right + 1;
				}
			}
		}
		return ans;
	}

	private int binarySearch(int[] nums, int lo, int hi, int target) {

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (nums[pivot] == target) {
				return pivot;
			} else if (nums[pivot] < target) {
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}
		return -1;
	}
//===================================================================================================	

	public int[] searchRange1(int[] nums, int target) {
        int[] result = new int[ 2 ];
        result[ 0 ] = -1;
        result[ 1 ] = -1;
        
        if( nums == null || nums.length == 0 ) {
            return result;
        }
        
        if( nums.length == 1 && nums[ 0 ] == target ) {
            return new int[ 2 ];
        }
        
        int index = binarySearch1( nums, 0, nums.length - 1, target );
       // System.out.println( index );
        if( index != -1 ) {
            int left = index;
            
            while( left - 1 >= 0 && nums[ left - 1 ] == target ) {
                left = binarySearch1( nums, 0, left - 1, target );
            }
            int right = index;
            
            while( right + 1 < nums.length && nums[ right + 1 ] == target ) {
                right = binarySearch1( nums, right + 1, nums.length - 1, target );
            }
            result[ 0 ] = left;
            result[ 1 ] = right;
        }
        return result;
    }
    
    private int binarySearch1( int[] nums, int lo, int hi, int target ) {
        
        if( lo <= hi ) {
            int pivot = lo + ( hi - lo ) / 2;

            if( nums[ pivot ] == target ) {
                return pivot;
            } else if( nums[ pivot ] > target ) {
                return binarySearch1( nums, lo, pivot - 1, target );
            } else {
                return binarySearch1( nums, pivot + 1, hi, target );
            }
        }
        return -1;
    }
}
