package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *
 *Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

Example 1:

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]

Example 2:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


Constraints:

1 <= nums.length <= 8
-10 <= nums[i] <= 10
 *
 *
 */


public class _47_Permutation2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		ArrayList<Integer> partial = new ArrayList<>();
		//Arrays.sort(nums) //optional
		recur(nums, ans, partial, 0);
		return ans;
	}

	private void recur(int[] nums, List<List<Integer>> ans, ArrayList<Integer> partial,
			int index) {

		if (index == nums.length) {
			ans.add((ArrayList<Integer>)partial.clone());
		}

		for (int i = index; i < nums.length; i++) {

			if (isValidSwap(nums, i, index)) {
				swap(nums, i, index);
				partial.add(nums[index]);
				recur(nums, ans, partial, index + 1);
				swap(nums, i, index);
				partial.remove(partial.size() - 1);
			}
		}
	}

	private boolean isValidSwap(int[] nums, int currIndex, int startIndex) {

		for (int i = startIndex; i < currIndex; i++) {

			if (nums[i] == nums[currIndex]) {
				return false;
			}
		}
		return true;
	}

	private void swap(int[] nums, int index1, int index2) {
		int temp = nums[index1];
		nums[index1] = nums[index2];
		nums[index2] = temp;
	}

	//=============================================================================================

	private void recur1(int[] nums, List<List<Integer>> ans, ArrayList<Integer> partial,
			int index) {

		if (index == nums.length) {
			ans.add((ArrayList<Integer>)partial.clone());
		}
		Set<Integer> usedSet = new HashSet<>();

		for (int i = index; i < nums.length; i++) {

			if (usedSet.add(nums[i])) {
				swap(nums, i, index);
				partial.add(nums[index]);
				recur(nums, ans, partial, index + 1);
				swap(nums, i, index);
				partial.remove(partial.size() - 1);
			}
		}
	}

}
