package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         You have n gardens, labeled from 1 to n, and an array paths where
 *         paths[i] = [xi, yi] describes a bidirectional path between garden xi
 *         to garden yi. In each garden, you want to plant one of 4 types of
 *         flowers.
 * 
 *         All gardens have at most 3 paths coming into or leaving it.
 * 
 *         Your task is to choose a flower type for each garden such that, for
 *         any two gardens connected by a path, they have different types of
 *         flowers.
 * 
 *         Return any such a choice as an array answer, where answer[i] is the
 *         type of flower planted in the (i+1)th garden. The flower types are
 *         denoted 1, 2, 3, or 4. It is guaranteed an answer exists.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: n = 3, paths = [[1,2],[2,3],[3,1]] Output: [1,2,3]
 *         Explanation: Gardens 1 and 2 have different types. Gardens 2 and 3
 *         have different types. Gardens 3 and 1 have different types. Hence,
 *         [1,2,3] is a valid answer. Other valid answers include [1,2,4],
 *         [1,4,2], and [3,2,1]. 
 *         
 *         Example 2:
 * 
 *         Input: n = 4, paths = [[1,2],[3,4]] Output: [1,2,1,2] 
 *         
 *         Example 3:
 * 
 *         Input: n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]] Output:
 *         [1,2,3,4]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 104 0 <= paths.length <= 2 * 104 paths[i].length == 2 1 <=
 *         xi, yi <= n xi != yi Every garden has at most 3 paths coming into or
 *         leaving it.
 *
 */

public class _1042_FlowerPlantingWithNoAdjacent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] gardenNoAdj(int n, int[][] paths) {
		Map<Integer, List<Integer>> adjacency = getAdjacency(paths);
		int[] ans = new int[n];

		for (int i = 1; i <= n; i++) {
			if (ans[i - 1] == 0) {
				dfs(paths, i, ans, adjacency);
			}
		}
		return ans;
	}

	private void dfs(int[][] paths, int garden, int[] ans, Map<Integer, List<Integer>> adjacency) {
		if (ans[garden - 1] != 0) {
			return;
		}
		Set<Integer> usedFlower = new HashSet<>();
		Set<Integer> gardenNotSet = new HashSet<>();
		List<Integer> list = adjacency.getOrDefault(garden, new ArrayList<>());

		for (int neighbour : list) {
			if (ans[neighbour - 1] == 0) {
				gardenNotSet.add(neighbour);
			} else {
				usedFlower.add(ans[neighbour - 1]);
			}
		}

		for (int i = 1; i <= 4; i++) {

			if (!usedFlower.contains(i)) {
				ans[garden - 1] = i;

				for (int neighbour : gardenNotSet) {
					dfs(paths, neighbour, ans, adjacency);
				}
				return;
			}
		}
	}

	private Map<Integer, List<Integer>> getAdjacency(int[][] paths) {
		Map<Integer, List<Integer>> adjacency = new HashMap<>();

		for (int[] path : paths) {
			List<Integer> list = adjacency.getOrDefault(path[0], new ArrayList<>());
			list.add(path[1]);
			adjacency.put(path[0], list);
			list = adjacency.getOrDefault(path[1], new ArrayList<>());
			list.add(path[0]);
			adjacency.put(path[1], list);
		}
		return adjacency;
	}
}
