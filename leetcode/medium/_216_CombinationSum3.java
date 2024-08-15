package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         Find all valid combinations of k numbers that sum up to n such that
 *         the following conditions are true:
 * 
 *         Only numbers 1 through 9 are used. Each number is used at most once.
 *         Return a list of all possible valid combinations. The list must not
 *         contain the same combination twice, and the combinations may be
 *         returned in any order.
 * 
 *         Example 1:
 * 
 *         Input: k = 3, n = 7 Output: [[1,2,4]] Explanation: 1 + 2 + 4 = 7
 *         There are no other valid combinations.
 * 
 *         Example 2:
 * 
 *         Input: k = 3, n = 9 Output: [[1,2,6],[1,3,5],[2,3,4]] Explanation: 1
 *         + 2 + 6 = 9 1 + 3 + 5 = 9 2 + 3 + 4 = 9 There are no other valid
 *         combinations.
 * 
 *         Example 3:
 * 
 *         Input: k = 4, n = 1 Output: [] Explanation: There are no valid
 *         combinations. Using 4 different numbers in the range [1,9], the
 *         smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are
 *         no valid combination.
 * 
 *         Example 4:
 * 
 *         Input: k = 3, n = 2 Output: [] Explanation: There are no valid
 *         combinations.
 * 
 *         Example 5:
 * 
 *         Input: k = 9, n = 45 Output: [[1,2,3,4,5,6,7,8,9]] Explanation: 1 + 2
 *         + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45 There are no other valid
 *         combinations.
 * 
 *         Constraints:
 * 
 *         2 <= k <= 9 1 <= n <= 60
 *
 */

public class _216_CombinationSum3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> ans = new ArrayList<>();
		ArrayList<Integer> partial = new ArrayList<>();
		recur(ans, partial, k, n, 1, 0);
		return ans;
	}

	private void recur(List<List<Integer>> ans, ArrayList<Integer> partial, int k, int n, int val, int sum) {
		if (partial.size() == k) {

			if (sum == n) {
				ans.add((ArrayList<Integer>) partial.clone());
			}
			return;
		}

		for (int i = val; i <= 9; i++) {
			partial.add(i);
			recur(ans, partial, k, n, i + 1, sum + i);
			partial.remove(partial.size() - 1);
		}
	}
	//=============================================================================================
	//Another approach
	public List<List<Integer>> combinationSum3_2(int k, int n) {
		List<List<Integer>> ans = new ArrayList<>();
		ArrayList<Integer> p = new ArrayList<>();
		recur1(ans, p, k, n, 0, 1);
		return ans;
	}

	private void recur1(List<List<Integer>> ans, ArrayList<Integer> p, int k, int n, int sum, int num) {

		if (n == sum && k == 0) {
			ans.add((ArrayList<Integer>)p.clone());
			return;
		}

		if (num > 9 || k < 0) {
			return;
		}
		p.add(num);
		recur(ans, p, k - 1, n, sum + num, num + 1);
		p.remove(p.size() - 1);
		recur(ans, p, k, n, sum, num + 1);
	}
}
