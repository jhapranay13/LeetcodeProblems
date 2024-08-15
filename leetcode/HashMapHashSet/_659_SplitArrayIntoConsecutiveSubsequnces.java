package leetcode.HashMapHashSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an integer array nums that is sorted in non-decreasing
 *         order.
 * 
 *         Determine if it is possible to split nums into one or more
 *         subsequences such that both of the following conditions are true:
 * 
 *         Each subsequence is a consecutive increasing sequence (i.e. each
 *         integer is exactly one more than the previous integer). All
 *         subsequences have a length of 3 or more. Return true if you can split
 *         nums according to the above conditions, or false otherwise.
 * 
 *         A subsequence of an array is a new array that is formed from the
 *         original array by deleting some (can be none) of the elements without
 *         disturbing the relative positions of the remaining elements. (i.e.,
 *         [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,2,3,3,4,5] Output: true Explanation: nums can be
 *         split into the following subsequences: [1,2,3,3,4,5] --> 1, 2, 3
 *         [1,2,3,3,4,5] --> 3, 4, 5 Example 2:
 * 
 *         Input: nums = [1,2,3,3,4,4,5,5] Output: true Explanation: nums can be
 *         split into the following subsequences: [1,2,3,3,4,4,5,5] --> 1, 2, 3,
 *         4, 5 [1,2,3,3,4,4,5,5] --> 3, 4, 5 Example 3:
 * 
 *         Input: nums = [1,2,3,4,4,5] Output: false Explanation: It is
 *         impossible to split nums into consecutive increasing subsequences of
 *         length 3 or more.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 104 -1000 <= nums[i] <= 1000 nums is sorted in
 *         non-decreasing order.
 *
 */

public class _659_SplitArrayIntoConsecutiveSubsequnces {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	// =============================================================================
	// My version Of solution
	public boolean isPossible(int[] nums) {
        List<List<Integer>> holder = new ArrayList<>();
        holder.add(new ArrayList<>());
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);    
        }
        int currIndex = 0;
        
        for (int i = 0; i < nums.length;) {
            int freq = freqMap.get(nums[i]);
            int num = nums[i];
            i += freq;
            
            while (currIndex >= 0 && freq > 0) {
                List<Integer> list = holder.get(currIndex);
                
                if (list.isEmpty() || list.get(list.size() - 1) + 1 == num) {
                    list.add(num);
                    currIndex--;
                    freq--;
                } else {
                    break;
                }
            }
            
            while (freq-- > 0) {
                List<Integer> list = new ArrayList<>();
                list.add(num);
                holder.add(list);
            }
            currIndex = holder.size() - 1;
        }
        
        for (List<Integer> list : holder) {
            if (list.size() < 3) {
                return false;
            }
        }
        return true;
    }
	// =============================================================================
	// Solution to understand
	/*
	 * for each element we have 2 options: 
	 * append it to a previously constructed consecutive sequence 
	 * become a start of a new consecutive sequence
	 * return false if both options are not valid
	 */
	public boolean isPossible1(int[] nums) {
		HashMap<Integer, Integer> count = new HashMap<>();
		HashMap<Integer, Integer> tails = new HashMap<>();
		for (int num : nums) {
			count.put(num, count.getOrDefault(num, 0) + 1);
		}

		for (int num : nums) {
			if (count.get(num) == 0)
				continue;
			else if (tails.getOrDefault(num, 0) > 0) {
				tails.put(num, tails.get(num) - 1);
				tails.put(num + 1, tails.getOrDefault(num + 1, 0) + 1);
			} else if (count.getOrDefault(num + 1, 0) > 0 && count.getOrDefault(num + 2, 0) > 0) {
				count.put(num + 1, count.get(num + 1) - 1);
				count.put(num + 2, count.get(num + 2) - 1);
				tails.put(num + 3, tails.getOrDefault(num + 3, 0) + 1);
			} else
				return false;

			count.put(num, count.get(num) - 1);
		}
		return true;
	}

}
