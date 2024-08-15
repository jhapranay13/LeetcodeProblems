package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *
 *Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]

Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output: 
[
[1,2,2],
[5]
]
 
Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
 *
 */


public class _40_CombinationSum2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> ans = new ArrayList<>();
		ArrayList<Integer> partial = new ArrayList<>();
		Arrays.sort( candidates );
		recur( candidates, target, 0, ans, partial, 0 );
		return ans;
	}

	private void recur(int[] candidates, int target, int sum,  List<List<Integer>> ans, 
			ArrayList<Integer> partial, int index) {

		if (sum == target) {
			ans.add((ArrayList<Integer>)partial.clone());
			return;
		}

		if (sum > target || index == candidates.length) {
			return;
		}

		for (int i = index; i < candidates.length; i++) {

			if (i > index && candidates[i] == candidates[i - 1]) {
				continue;
			}
			partial.add(candidates[i]);
			recur(candidates, target, sum + candidates[i], ans, partial, i + 1);
			partial.remove(partial.size() - 1);
		}
	}
}
