package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of n integers nums, a 132 pattern is a subsequence of
 *         three integers nums[i], nums[j] and nums[k] such that i < j < k and
 *         nums[i] < nums[k] < nums[j].
 * 
 *         Return true if there is a 132 pattern in nums, otherwise, return
 *         false.
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,2,3,4] Output: false Explanation: There is no 132
 *         pattern in the sequence. 
 *         
 *         Example 2:
 * 
 *         Input: nums = [3,1,4,2] Output: true Explanation: There is a 132
 *         pattern in the sequence: [1, 4, 2]. 
 *         
 *         Example 3:
 * 
 *         Input: nums = [-1,3,2,0] Output: true Explanation: There are three
 *         132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 * 
 * 
 *         Constraints:
 * 
 *         n == nums.length 1 <= n <= 2 * 105 -109 <= nums[i] <= 109
 *
 */
public class _456_132Pattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//==============================================================================
	//Brute Force n^2 Solution
	public boolean find132pattern(int[] nums) {
		int[] min = new int[nums.length];
		min[0] = nums[0];

		for (int i = 1; i < nums.length; i++) {
			min[i] = Math.min(min[i - 1], nums[i]);
		} 

		for (int i = 0; i < nums.length; i++) {

			for (int j = i + 1; j < nums.length; j++) {

				if (nums[j] > min[i] && nums[i] > nums[j]) {
					return true;
				}
			}
		}
		return false;
	}
	//==============================================================================
	//Stack Based N solution
	public boolean find132pattern1(int[] nums) {
        int[] min = new int[nums.length];
        min[0] = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        } 
        Deque<Integer> monoStack = new LinkedList<>();
        
        for (int i = nums.length - 1; i >= 0; i--) {
            
            while (!monoStack.isEmpty() && monoStack.peek() <= min[i]) {
                monoStack.pop();
            }
            
            if(!monoStack.isEmpty() && monoStack.peek() < nums[i]) {
                return true;
            }
            monoStack.push(nums[i]);
        }
        return false;
    }
}
