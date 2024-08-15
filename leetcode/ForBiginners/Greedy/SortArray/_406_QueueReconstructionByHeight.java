package leetcode.ForBiginners.Greedy.SortArray;

import java.util.*;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an array of people, people, which are the attributes of
 *         some people in a queue (not necessarily in order). Each people[i] =
 *         [hi, ki] represents the ith person of height hi with exactly ki other
 *         people in front who have a height greater than or equal to hi.
 * 
 *         Reconstruct and return the queue that is represented by the input
 *         array people. The returned queue should be formatted as an array
 *         queue, where queue[j] = [hj, kj] is the attributes of the jth person
 *         in the queue (queue[0] is the person at the front of the queue).
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]] Output:
 *         [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] Explanation: Person 0 has
 *         height 5 with no other people taller or the same height in front.
 *         Person 1 has height 7 with no other people taller or the same height
 *         in front. Person 2 has height 5 with two persons taller or the same
 *         height in front, which is person 0 and 1. Person 3 has height 6 with
 *         one person taller or the same height in front, which is person 1.
 *         Person 4 has height 4 with four people taller or the same height in
 *         front, which are people 0, 1, 2, and 3. Person 5 has height 7 with
 *         one person taller or the same height in front, which is person 1.
 *         Hence [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] is the reconstructed
 *         queue. 
 *         
 *         Example 2:
 * 
 *         Input: people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]] Output:
 *         [[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= people.length <= 2000 0 <= hi <= 106 0 <= ki < people.length It
 *         is guaranteed that the queue can be reconstructed.
 *
 */

public class _406_QueueReconstructionByHeight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, new Comparator<int[]>() {
			public int compare(int[] arr1, int[] arr2) {

				if (arr1[0] == arr2[0]) {
					return arr1[1] - arr2[1];
				}
				return arr2[0] - arr1[0];
			} 
		});
		List<int[]> ans = new ArrayList<>();
		//Using 2nd value as Index position
		for (int[] person : people) {
			ans.add(person[1], person);
		}
		return ans.toArray(new int[people.length][2]);
	}
	//=============================================================================================
	//TreeMap solution
	public int[][] reconstructQueue1(int[][] people) {
		TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>(Collections.reverseOrder());

		for (int[] info : people) {
			int height = info[0];
			int personInFront = info[1];
			TreeSet<Integer> set = map.getOrDefault(height, new TreeSet<>());
			set.add(personInFront);
			map.put(height, set);
		}
		List<int[]> holder = new ArrayList<>();

		for (int key : map.keySet()) {
			TreeSet<Integer> vals = map.get(key);

			for (int val : vals) {
				holder.add(val, new int[] {key, val});
			}
		}
		return holder.toArray(new int[people.length][2]);
	}
}
