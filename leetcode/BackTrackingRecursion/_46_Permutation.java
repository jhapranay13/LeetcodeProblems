package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.



Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]

Example 3:

Input: nums = [1]
Output: [[1]]


Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 *
 */

public class _46_Permutation {

	public static void main(String[] args) {
		_46_Permutation obj = new _46_Permutation();
		int[] arr = {1, 2, 3};
		obj.permute(arr);
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		ArrayList<Integer> partial = new ArrayList<>();
		recur(nums, partial, ans, 0);
		return ans;
	}

	private void recur(int[] nums, ArrayList<Integer> partial, List<List<Integer>> ans, 
			int index) {

		if (index == nums.length) {
			ans.add((ArrayList<Integer>)partial.clone());
			return;    
		}

		for (int i = index; i < nums.length; i++) {
			swap(nums, i, index);
			partial.add(nums[index]);
			recur(nums, partial, ans, index + 1);
			swap(nums, i, index);
			partial.remove(partial.size() - 1);
		}
	}

	private void swap(int[] nums, int index1, int index2) {
		int temp = nums[index1];
		nums[index1] = nums[index2];
		nums[index2] = temp;
	}
}
