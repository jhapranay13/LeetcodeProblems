package leetcode.ForBiginners.Graph.ColoringOrBipartition;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         We want to split a group of n people (labeled from 1 to n) into two
 *         groups of any size. Each person may dislike some other people, and
 *         they should not go into the same group.
 * 
 *         Given the integer n and the array dislikes where dislikes[i] = [ai,
 *         bi] indicates that the person labeled ai does not like the person
 *         labeled bi, return true if it is possible to split everyone into two
 *         groups in this way.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: n = 4, dislikes = [[1,2],[1,3],[2,4]] Output: true
 *         Explanation: group1 [1,4] and group2 [2,3]. 
 *         
 *         Example 2:
 * 
 *         Input: n = 3, dislikes = [[1,2],[1,3],[2,3]] Output: false 
 *         
 *         Example 3:
 * 
 *         Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]] Output:
 *         false
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 2000 0 <= dislikes.length <= 104 dislikes[i].length == 2 1
 *         <= dislikes[i][j] <= n ai < bi All the pairs of dislikes are unique.
 *
 */

public class _886_PossibleBipartition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean possibleBipartition(int n, int[][] dislikes) {
		int[] visited = new int[n];
		Map<Integer, Set<Integer>> dislikeMap = new HashMap<>();

		for (int[] dislike : dislikes) {
			Set<Integer> set = dislikeMap.getOrDefault(dislike[0], new HashSet<>());
			set.add(dislike[1]);
			dislikeMap.put(dislike[0], set);
			set = dislikeMap.getOrDefault(dislike[1], new HashSet<>());
			set.add(dislike[0]);
			dislikeMap.put(dislike[1], set);
		}
		Arrays.fill(visited, -1);

		for (int i = 1; i <= n; i++) {
			if (visited[i - 1] == -1 && !isGraphBipartite(i, dislikeMap, visited)) {
				return false;
			}
		}
		return true;
	}

	private boolean isGraphBipartite(int person, Map<Integer, Set<Integer>> dislikeMap, int[] visited) {
		Deque<int[]> q = new LinkedList<>();
		q.add(new int[] { person, 0 });

		while (!q.isEmpty()) {
			int size = q.size();

			while (size-- > 0) {
				int[] node = q.poll();
				visited[node[0] - 1] = node[1];
				Set<Integer> childs = dislikeMap.getOrDefault(node[0], new HashSet<>());

				for (int child : childs) {

					if (visited[child - 1] != -1) {
						if (visited[child - 1] == visited[node[0] - 1]) {
							return false;
						}
						continue;
					}
					q.offer(new int[] { child, node[1] ^ 1 });
				}
			}
		}
		return true;
	}
}
