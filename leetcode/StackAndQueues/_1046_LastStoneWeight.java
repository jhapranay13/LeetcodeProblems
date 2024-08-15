package leetcode.StackAndQueues;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         We have a collection of stones, each stone has a positive integer
 *         weight.
 * 
 *         Each turn, we choose the two heaviest stones and smash them together.
 *         Suppose the stones have weights x and y with x <= y. The result of
 *         this smash is:
 * 
 *         If x == y, both stones are totally destroyed; If x != y, the stone of
 *         weight x is totally destroyed, and the stone of weight y has new
 *         weight y-x. At the end, there is at most 1 stone left. Return the
 *         weight of this stone (or 0 if there are no stones left.)
 * 
 *         Example 1:
 * 
 *         Input: [2,7,4,1,8,1] Output: 1 Explanation: We combine 7 and 8 to get
 *         1 so the array converts to [2,4,1,1,1] then, we combine 2 and 4 to
 *         get 2 so the array converts to [2,1,1,1] then, we combine 2 and 1 to
 *         get 1 so the array converts to [1,1,1] then, we combine 1 and 1 to
 *         get 0 so the array converts to [1] then that's the value of last
 *         stone.
 * 
 * 
 *         Note:
 * 
 *         1 <= stones.length <= 30 1 <= stones[i] <= 1000
 *
 */

public class _1046_LastStoneWeight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int lastStoneWeight(int[] stones) {
		PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {

			public int compare(Integer i1, Integer i2) {
				return i2.compareTo(i1);
			}
		});

		for (int s : stones) {
			q.offer(s);
		}
		int curr = q.poll();

		while (!q.isEmpty()) {
			int temp = q.poll();
			int res = Math.abs(curr - temp);

			if (res != 0) {
				q.offer(res);
			}

			if (!q.isEmpty()) {
				curr = q.poll();
			} else {
				curr = res;
			}
		}
		return curr;
	}
}
