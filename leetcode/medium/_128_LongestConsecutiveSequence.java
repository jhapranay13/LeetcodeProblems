package leetcode.medium;

import java.util.Arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
 

Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
 */

public class _128_LongestConsecutiveSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//O(nlog n)
	public int longestConsecutive(int[] nums) {

		if (nums.length == 0) {
			return 0;
		}
		Arrays.sort(nums);
		int count = 1;
		int maxCount = 1;

		for (int i = 1; i < nums.length; i++) {

			if (nums[i] != nums[i - 1]) {

				if (nums[i] == nums[i - 1] + 1) {
					count++;
				} else {
					maxCount = Math.max(maxCount, count);
					count = 1;
				}
			}
		}
		return Math.max(maxCount, count);
	}
	//O(n)
	public int longestConsecutive1(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        
        for (int num : nums) {
            seen.add(num);
        }
        int maxCount = 0;
        
        for (int num : nums) {
            
            if (!seen.contains(num - 1)) {
                int count = 1;
                int currNum = num;
                
                while (seen.contains(currNum + 1)) {
                    count++;
                    currNum++;
                }
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount;
    }
}
