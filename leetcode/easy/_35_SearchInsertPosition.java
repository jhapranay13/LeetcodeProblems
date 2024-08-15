package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2

Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1

Example 3:

Input: nums = [1,3,5,6], target = 7
Output: 4

Example 4:

Input: nums = [1,3,5,6], target = 0
Output: 0

Example 5:

Input: nums = [1], target = 0
Output: 0
 

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums contains distinct values sorted in ascending order.
-104 <= target <= 104
 *
 */

public class _35_SearchInsertPosition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int searchInsert(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        
        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            
            if (nums[pivot] == target) {
                return pivot;
            } else if (nums[pivot] > target){
                hi = pivot - 1;
            } else {
                lo = pivot + 1;
            }
        }
        return lo;
    }
//=========================================================================================	
	
	private int search1( int[] nums, int t ) {
        int lo = 0;
        int hi = nums.length - 1;
        int ans = -1;
        
        while( lo <= hi ) {
            int p = lo + ( hi - lo ) / 2;
            
            if( nums[ p ] == t ) {
                return p;
            } else if( nums[ p ] < t ) {
                lo = p + 1;
            } else {
                ans = p;
                hi = p - 1;
            }
        }
        return ans;
    }
//=============================================================================================	
	
	private int search2( int[] nums, int t ) {
        int lo = 0;
        int hi = nums.length - 1;
        
        while( lo < hi ) {
            
            if( hi - lo == 1 ) {
                
                if( nums[ lo ] >= t ) {
                    break;                
                } else {
                    return hi;
                }
            }
            int p = lo + ( hi - lo ) / 2;
            
            if( nums[ p ] == t ) {
                return p;
            } else if( nums[ p ] < t ) {
                lo = p + 1;
            } else {
                hi = p;
            }
        }
        return lo;
    }
}
