package leetcode.Arrays;

import java.util.*;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an array of transactions transactions where
 *         transactions[i] = [fromi, toi, amounti] indicates that the person
 *         with ID = fromi gave amounti $ to the person with ID = toi.
 * 
 *         Return the minimum number of transactions required to settle the
 *         debt.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: transactions = [[0,1,10],[2,0,5]] Output: 2 Explanation:
 *         Person #0 gave person #1 $10. Person #2 gave person #0 $5. Two
 *         transactions are needed. One way to settle the debt is person #1 pays
 *         person #0 and #2 $5 each. 
 *         
 *         Example 2:
 * 
 *         Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]] Output: 1
 *         Explanation: Person #0 gave person #1 $10. Person #1 gave person #0
 *         $1. Person #1 gave person #2 $5. Person #2 gave person #0 $5.
 *         Therefore, person #1 only need to give person #0 $4, and all debt is
 *         settled.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= transactions.length <= 8 transactions[i].length == 3 0 <= fromi,
 *         toi <= 20 fromi != toi 1 <= amounti <= 100
 *
 */

public class _465_OptimalAccountBalancing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int minTransfers(int[][] transactions) {
		Map<Integer, Integer> pr = new HashMap<>();

		for (int[] trans : transactions) {
			int paye = trans[0];
			int rece = trans[1];
			int amnt = trans[2];
			int amntg = pr.getOrDefault(paye, 0);
			pr.put(paye, amntg + amnt);
			int amntr = pr.getOrDefault(rece, 0);
			pr.put(rece, amntr - amnt);
		}
		List<Integer> tlist = new ArrayList<>();

		for (Integer key : pr.keySet()) {
			tlist.add(pr.get(key));
		}
		Collections.sort(tlist);
		int min = dfs(tlist, 0);
		return min;
	}

	private int dfs(List<Integer> tlist, int index) {

		if (index == tlist.size()) {
			return 0;
		}
		int curr = tlist.get(index);

		if (curr == 0) {
			return dfs(tlist, index + 1);
		}
		int ans = Integer.MAX_VALUE;

		for (int i = index + 1; i < tlist.size(); i++) {
			int next = tlist.get(i);
			int trans = 0;

			if (next == 0 || next * curr > 0) {
				continue;
			}
			int amount = curr + next;

			if (amount < 0) {
				tlist.remove(i);
				tlist.add(i, 0);
				tlist.remove(index);
				tlist.add(index, amount);
				trans = 1 + dfs(tlist, index);
				tlist.remove(i);
				tlist.add(i, next);
				tlist.remove(index);
				tlist.add(index, curr);
			} else if (amount > 0) {
				tlist.remove(index);
				tlist.add(index, 0);
				tlist.remove(i);
				tlist.add(i, amount);
				trans = 1 + dfs(tlist, index + 1);
				tlist.remove(index);
				tlist.add(index, curr);
				tlist.remove(i);
				tlist.add(i, next);
			} else {
				tlist.remove(index);
				tlist.add(index, amount);
				tlist.remove(i);
				tlist.add(i, amount);
				trans = 1 + dfs(tlist, index + 1);
				tlist.remove(index);
				tlist.add(index, curr);
				tlist.remove(i);
				tlist.add(i, next);
			}
			ans = Math.min(trans, ans);
		}
		return ans;
	}
	//=============================================================================================
	//Using Array
	public int minTransfers1(int[][] transactions) {
		Map<Integer, Integer> recviablePayableMap = new HashMap<>(); //those who have payed -ve recieved +ve

		for (int[] trans : transactions) {
			int paye = trans[0];
			int rece = trans[1];
			int amount = trans[2];
			recviablePayableMap.put(paye, recviablePayableMap.getOrDefault(paye, 0) - amount);
			recviablePayableMap.put(rece, recviablePayableMap.getOrDefault(rece, 0) + amount);
		}
		int[] transHolder = new int[recviablePayableMap.size()];
		int index = 0;

		for (int key : recviablePayableMap.keySet()) {
			transHolder[index++] = recviablePayableMap.get(key);
		}
		Arrays.sort(transHolder);
		return recur(transHolder, 0);
	}

	private int recur(int[] transHolder, int index) {
		if (index == transHolder.length) {
			return 0;
		}

		if (transHolder[index] == 0) {
			return recur(transHolder, index + 1);
		}
		int ans = Integer.MAX_VALUE;
		int currVal = transHolder[index];


		for (int i = index + 1; i < transHolder.length; i++) {

			if (transHolder[i] * transHolder[index] >= 0) {
				continue;
			}
			int ival = transHolder[i];
			int val = transHolder[i] + transHolder[index];

			if (val < 0) {
				transHolder[i] = 0;
				transHolder[index] = val;
				ans = Integer.min(ans, 1 + recur(transHolder, index));
			} else if (val > 0) {
				transHolder[i] = val;
				transHolder[index] = 0;
				ans = Integer.min(ans, 1 + recur(transHolder, index + 1));
			} else {
				transHolder[i] = 0;
				transHolder[index] = 0;
				ans = Integer.min(ans, 1 + recur(transHolder, index + 1));
			}
			transHolder[i] = ival;
			transHolder[index] = currVal;
		}
		return ans;
	}
}
