package leetcode.SlidingWindow;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.

Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0

Constraints:

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 105

Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 *
 */

public class _209_MinimumSizeSubArraySum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_209_MinimumSizeSubArraySum obj = new _209_MinimumSizeSubArraySum();
		System.out.println(obj.minSubArrayLen1(11, new int[] {1,2,3,4,5}));
	}
	//=============================================================================
	//Sliding window Approach O(n)
	public int minSubArrayLen(int target, int[] nums) {
		int fast = 0;
		int slow = 0;
		int minSubArray = Integer.MAX_VALUE;
		int sum = 0;

		while (fast < nums.length) {
			sum += nums[fast];

			while (sum >= target) {
				minSubArray = Math.min(minSubArray, fast - slow + 1);
				sum -= nums[slow++];
			}
			fast++;
		}
		return minSubArray == Integer.MAX_VALUE ? 0 : minSubArray;    
	}
	//=============================================================================
	//Binary Search Approach O(n log n)
	public int minSubArrayLen1(int target, int[] nums) {
        int[] preSum = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            
            if (i == 0) {
                preSum[i] = nums[i];
            } else {
                preSum[i] = preSum[i - 1] + nums[i];
            }
        }
        
        if (target > preSum[preSum.length - 1]) {
            return 0;
        }
        int minLength = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int lo = i;
            int hi = nums.length - 1;
            
            while (lo <= hi) {
				int pivot = lo + (hi - lo) / 2;
				int tempSum = 0;

				if (i == 0) {
					tempSum = preSum[pivot];
				} else {
					tempSum = preSum[pivot] - preSum[i - 1];
				}

				if(tempSum == target) {
						lo = pivot;
						break;
					
				} else if (tempSum < target) {
					lo = pivot + 1;
				} else {
                    hi = pivot - 1;
                }
			}
			
			if (lo >= nums.length) {
				break;
			}
			minLength = Math.min(minLength, lo - i + 1);
		}
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
