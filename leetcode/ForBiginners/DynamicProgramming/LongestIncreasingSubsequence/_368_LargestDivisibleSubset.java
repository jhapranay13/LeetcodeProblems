package leetcode.ForBiginners.DynamicProgramming.LongestIncreasingSubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a set of distinct positive integers nums, return the largest
 *         subset answer such that every pair (answer[i], answer[j]) of elements
 *         in this subset satisfies:
 * 
 *         answer[i] % answer[j] == 0, or answer[j] % answer[i] == 0 If there
 *         are multiple solutions, return any of them.
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,2,3] Output: [1,2] Explanation: [1,3] is also
 *         accepted.
 * 
 *         Example 2:
 * 
 *         Input: nums = [1,2,4,8] Output: [1,2,4,8]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 1000 1 <= nums[i] <= 2 * 109 All the integers in
 *         nums are unique.
 *
 */

public class _368_LargestDivisibleSubset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ===========================================================================
	// Top Down Approach
	public List<Integer> largestDivisibleSubset(int[] nums) {
		List<Integer> ans = new ArrayList<>();
		Arrays.sort(nums);
		Map<Integer, List<Integer>> memo = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			List<Integer> temp = recur(nums, i, memo);

			if (temp.size() > ans.size()) {
				ans = temp;
			}
		}
		return ans;
	}

	private List<Integer> recur(int[] nums, int index, Map<Integer, List<Integer>> memo) {

		if (memo.containsKey(index)) {
			return memo.get(index);
		}
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = index + 1; i < nums.length; i++) {

			if (nums[i] % nums[index] == 0) {
				List<Integer> holder = recur(nums, i, memo);

				if (holder.size() > list.size()) {
					list = (ArrayList) holder;
				}
			}
		}
		list = new ArrayList<>(list);
		list.add(0, nums[index]);

		memo.put(index, list);
		return list;
	}
	//===========================================================================
	//Bottom Up approach
	public List<Integer> largestDivisibleSubset1(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(nums);
        Map<Integer, List<Integer>> memo = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            memo.put(i, list);
        }
        
        for (int index = nums.length - 1; index >= 0; index--) {
            ArrayList<Integer> list = new ArrayList<>();
        
            for (int i = index + 1; i < nums.length; i++) {
            
                if ( nums[i] % nums[index] == 0) {
                    List<Integer> holder = memo.get(i);
                
                    if (holder.size() > list.size()) {
                        list = (ArrayList)holder;
                    }
                }
            }
            list = new ArrayList<>(list);
            list.add(0, nums[index]);
            memo.put(index, list);
            
            if (list.size() > ans.size()) {
                ans = list;
            }
        }
        return ans;
    }
}
