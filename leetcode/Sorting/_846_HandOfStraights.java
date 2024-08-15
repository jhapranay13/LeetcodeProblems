package leetcode.Sorting;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Alice has a hand of cards, given as an array of integers.
 * 
 *         Now she wants to rearrange the cards into groups so that each group
 *         is size groupSize, and consists of groupSize consecutive cards.
 * 
 *         Return true if and only if she can.
 * 
 *         Note: This question is the same as 1296:
 *         https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3 Output: true
 *         Explanation: Alice's hand can be rearranged as
 *         [1,2,3],[2,3,4],[6,7,8] Example 2:
 * 
 *         Input: hand = [1,2,3,4,5], groupSize = 4 Output: false Explanation:
 *         Alice's hand can't be rearranged into groups of 4.
 * 
 * 
 * 
 *         Constraints:
 * 
 *         1 <= hand.length <= 10000 0 <= hand[i] <= 109 1 <= groupSize <=
 *         hand.length
 *
 */

public class _846_HandOfStraights {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean isNStraightHand(int[] hand, int groupSize) {
		if (hand.length % groupSize != 0) {
			return false;
		}
		Map<Integer, Integer> freqMap = new HashMap<>();

		for (int h : hand) {
			freqMap.put(h, freqMap.getOrDefault(h, 0) + 1);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.addAll(freqMap.keySet());
		Deque<Integer> holder = new LinkedList<>();
		int grpSize = 0;

		while (!pq.isEmpty()) {
			int element = pq.poll();

			if (freqMap.containsKey(element)) {
				int freq = freqMap.get(element);

				if (freq == 1) {
					freqMap.remove(element);
				} else {
					freqMap.put(element, --freq);
				}

				if (holder.size() == 0) {
					holder.offer(element);
				} else if (holder.size() > 0 && holder.getLast() + 1 == element) {
					holder.add(element);
				} else {
					return false;
				}
				grpSize++;
			}

			if (grpSize == groupSize) {

				while (!holder.isEmpty()) {
					int pollElement = holder.poll();

					if (freqMap.containsKey(pollElement)) {
						pq.add(pollElement);
					}
				}
				grpSize = 0;
			}
		}
		// grpSize will be zero if the group is divided or else will not be zero
		return grpSize == 0;
	}
}
