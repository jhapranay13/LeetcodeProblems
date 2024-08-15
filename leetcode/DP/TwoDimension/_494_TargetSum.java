package leetcode.DP.TwoDimension;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an integer array nums and an integer target.
 * 
 *         You want to build an expression out of nums by adding one of the
 *         symbols '+' and '-' before each integer in nums and then concatenate
 *         all the integers.
 * 
 *         For example, if nums = [2, 1], you can add a '+' before 2 and a '-'
 *         before 1 and concatenate them to build the expression "+2-1". Return
 *         the number of different expressions that you can build, which
 *         evaluates to target.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,1,1,1,1], target = 3 Output: 5 Explanation: There
 *         are 5 ways to assign symbols to make the sum of nums be target 3. -1
 *         + 1 + 1 + 1 + 1 = 3 +1 - 1 + 1 + 1 + 1 = 3 +1 + 1 - 1 + 1 + 1 = 3 +1
 *         + 1 + 1 - 1 + 1 = 3 +1 + 1 + 1 + 1 - 1 = 3 
 *         
 *         Example 2:
 * 
 *         Input: nums = [1], target = 1 Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 20 0 <= nums[i] <= 1000 0 <= sum(nums[i]) <= 1000
 *         -1000 <= target <= 1000
 *
 */

public class _494_TargetSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top Down Approach
	public int findTargetSumWays(int[] nums, int target) {
		// as per the constraint to represent +ve and -ve sum
		int[][] memo = new int[nums.length][2001];

		for (int[] mem : memo) {
			Arrays.fill(mem, Integer.MIN_VALUE);
		}
		return recur(nums, target, 0, 0, memo);
	}

	private int recur(int[] nums, int target, int sum, int index, int[][] memo) {

		if (index == nums.length) {

			if (sum == target) {
				return 1;
			} else {
				return 0;
			}
		}

		if (memo[index][sum + 1000] != Integer.MIN_VALUE) {
			return memo[index][sum + 1000];
		}
		int ans = recur(nums, target, sum + nums[index], index + 1, memo);
		ans += recur(nums, target, sum - nums[index], index + 1, memo);
		return memo[index][sum + 1000] = ans;
	}
	//=============================================================================
	//Bottom up approach
	public int findTargetSumWays1(int[] nums, int target) {
		// as per the constraint to represent +ve and -ve sum
		int[][] memo = new int[nums.length + 1][2001];
		memo[nums.length][target + 1000] = 1;

		for (int index = nums.length - 1; index >= 0; index--) {

			for (int sum = 1000; sum >= -1000; sum--) {

				int ans = 0;
				if (sum + nums[index] <= 1000) {
					ans = memo[index + 1][sum + nums[index] + 1000];
				}

				if (sum - nums[index] >= -1000) {
					ans += memo[index + 1][sum - nums[index] + 1000];
				}
				memo[index][sum + 1000] = ans;
			}
		}
		return memo[0][1000];
	}
	//=============================================================================
	//instead of 1000 limit of constraint we can use sum of all indexes of num
	public int findTargetSumWays2(int[] nums, int target) {
        // as per the constraint to represent +ve and -ve sum
        int[][] memo = new int[nums.length + 1][2001];
        memo[nums.length][target + 1000] = 1;
        int t = 0;
        
        for (int num : nums) { 
            t += num; 
        }
        for (int index = nums.length - 1; index >= 0; index--) {
            
            for (int sum = t; sum >= -t; sum--) {
                
                int ans = 0;
                if (sum + nums[index] <= 1000) {
                    ans = memo[index + 1][sum + nums[index] + 1000];
                }
                
                if (sum - nums[index] >= -1000) {
                    ans += memo[index + 1][sum - nums[index] + 1000];
                }
                memo[index][sum + 1000] = ans;
            }
        }
        return memo[0][1000];
    }
}
