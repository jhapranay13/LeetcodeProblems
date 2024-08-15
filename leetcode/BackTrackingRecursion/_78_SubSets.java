package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:

Input: nums = [0]
Output: [[],[0]]

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 *
 */

public class _78_SubSets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		ArrayList<Integer> partial = new ArrayList<>();
		recur(nums, ans, partial, 0);
		return ans;
	}

	private void recur(int[] nums, List<List<Integer>> ans, ArrayList<Integer> partial, 
			int index) {

		if (index == nums.length) {
			ans.add((ArrayList<Integer>)partial.clone());
			return;
		}
		recur(nums, ans, partial, index + 1);
		partial.add(nums[index]);
		recur(nums, ans, partial, index + 1);
		partial.remove(partial.size() - 1);
	}
}
