package leetcode.ForBiginners.Graph.UnionFind;

import java.util.*;

/**
 * 
 * @author Pranay Jha
 *
 *         On a 2D plane, we place n stones at some integer coordinate points.
 *         Each coordinate point may have at most one stone.
 * 
 *         A stone can be removed if it shares either the same row or the same
 *         column as another stone that has not been removed.
 * 
 *         Given an array stones of length n where stones[i] = [xi, yi]
 *         represents the location of the ith stone, return the largest possible
 *         number of stones that can be removed.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]] Output: 5
 *         Explanation: One way to remove 5 stones is as follows: 1. Remove
 *         stone [2,2] because it shares the same row as [2,1]. 2. Remove stone
 *         [2,1] because it shares the same column as [0,1]. 3. Remove stone
 *         [1,2] because it shares the same row as [1,0]. 4. Remove stone [1,0]
 *         because it shares the same column as [0,0]. 5. Remove stone [0,1]
 *         because it shares the same row as [0,0]. Stone [0,0] cannot be
 *         removed since it does not share a row/column with another stone still
 *         on the plane. Example 2:
 * 
 *         Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]] Output: 3
 *         Explanation: One way to make 3 moves is as follows: 1. Remove stone
 *         [2,2] because it shares the same row as [2,0]. 2. Remove stone [2,0]
 *         because it shares the same column as [0,0]. 3. Remove stone [0,2]
 *         because it shares the same row as [0,0]. Stones [0,0] and [1,1]
 *         cannot be removed since they do not share a row/column with another
 *         stone still on the plane. Example 3:
 * 
 *         Input: stones = [[0,0]] Output: 0 Explanation: [0,0] is the only
 *         stone on the plane, so you cannot remove it.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= stones.length <= 1000 0 <= xi, yi <= 104 No two stones are at
 *         the same coordinate point.
 *
 */

public class _947_MostStonesRemovedFromSameRowOrColumn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int removeStones(int[][] stones) {
		Map<Integer, List<int[]>> colMatrix = new HashMap<>();
		Map<Integer, List<int[]>> rowMatrix = new HashMap<>();

		for (int[] stone : stones) {
			List<int[]> list = rowMatrix.getOrDefault(stone[0], new ArrayList<>());
			list.add(stone);
			rowMatrix.put(stone[0], list);
			list = colMatrix.getOrDefault(stone[1], new ArrayList<>());
			list.add(stone);
			colMatrix.put(stone[1], list);
		}
		Set<String> visited = new HashSet<>();
		int ans = 0;

		for (int[] stone : stones) {
			String key = stone[0] + "," + stone[1];

			if (!visited.contains(key)) {
				dfs(rowMatrix, colMatrix, visited, stone[0], stone[1]);
				ans += count;
				count = -1;
			}
		}
		return ans;
	}
	//Count -1 since last one won't be counted because till we reach last co ordinate all the
	//stones shared in column and rows are already removed
	private int count = -1;

	private void dfs(Map<Integer, List<int[]>> rowMatrix, Map<Integer, List<int[]>> colMatrix, Set<String> visited,
			int row, int col) {
		String key = row + "," + col;

		if (visited.contains(key)) {
			return;
		}
		visited.add(key);
		count++;
		List<int[]> nextMoves = new ArrayList<>();
		nextMoves.addAll(rowMatrix.get(row));
		nextMoves.addAll(colMatrix.get(col));

		for (int[] move : nextMoves) {
			dfs(rowMatrix, colMatrix, visited, move[0], move[1]);
		}
	}
	//===============================================================================
	public int removeStones1(int[][] stones) {
		int num = stones.length;

		DSU dsu = new DSU(num);

		Map<Integer, Set<Integer>> xToIndex = new HashMap();
		Map<Integer, Set<Integer>> yToIndex = new HashMap();

		for (int i = 0; i < num; i++) {
			int[] stone = stones[i];

			int x = stone[0];
			int y = stone[1];

			// stones with same x till now
			if (xToIndex.containsKey(x)) {
				Set<Integer> withSameXPoints = xToIndex.get(x);
				for (int index : withSameXPoints) {
					dsu.union(i, index);
					break;
				}
			}

			// stones with same y till now
			if (yToIndex.containsKey(y)) {
				Set<Integer> withSameYPoints = yToIndex.get(y);
				for (int index : withSameYPoints) {
					dsu.union(i, index);
					break;
				}
			}

			// adding stone index in x's map
			if (!xToIndex.containsKey(x)) {
				xToIndex.put(x, new HashSet());
			}
			xToIndex.get(x).add(i);

			// adding stone index in y's map
			if (!yToIndex.containsKey(y)) {
				yToIndex.put(y, new HashSet());
			}
			yToIndex.get(y).add(i);
		}

		return num - dsu.n;
	}


	class DSU {
		int n;
		int[] parent;
		int[] rank;

		public DSU(int n) {
			this.n = n;

			parent = new int[n];
			rank = new int[n];

			for (int i = 0; i < n; i++) {
				parent[i] = i;
				rank[i] = 0;
			}
		}

		public int find(int node) {
			if (parent[node] != node) {
				parent[node] = find(parent[node]);
			}
			return parent[node];
		}

		public void union(int node1, int node2) {
			int parent1 = find(node1);
			int parent2 = find(node2);

			// already in the same component
			if (parent1 == parent2) {
				return;
			}

			if (rank[parent1] > rank[parent2]) {
				parent[parent2] = parent1;
			}
			else if (rank[parent1] < rank[parent2]) {
				parent[parent1] = parent2;
			}
			else {
				parent[parent2] = parent1;
				rank[parent1]++;
			}

			n--;
		}
	}
	//=============================================================================================
	//Another approach UNION FIND
	class UnionFind {
		int[] parent;
		int edgeCount;

		public UnionFind(int size) {
			this.parent = new int[size];
			this.edgeCount = 0;

			for (int i = 0; i < size; i++) {
				parent[i] = i;
			}
		}

		public int find(int x) {
			if (x != parent[x]) {
				return find(parent[x]);
			}
			return x;
		}

		public void union(int x, int y) {
			int px = find(x);
			int py = find(y);

			if (px != py) {
				parent[py] = px;
				edgeCount++;
			}
		}
	}
	class Solution {
		public int removeStones(int[][] stones) {
			int num = stones.length;

			UnionFind dsu = new UnionFind(num);

			Map<Integer, Set<Integer>> xToIndex = new HashMap();
			Map<Integer, Set<Integer>> yToIndex = new HashMap();

			for (int i = 0; i < num; i++) {
				int[] stone = stones[i];

				int x = stone[0];
				int y = stone[1];

				// stones with same x till now
				if (xToIndex.containsKey(x)) {
					Set<Integer> withSameXPoints = xToIndex.get(x);
					for (int index : withSameXPoints) {
						dsu.union(i, index);
						break;
					}
				}

				// stones with same y till now
				if (yToIndex.containsKey(y)) {
					Set<Integer> withSameYPoints = yToIndex.get(y);
					for (int index : withSameYPoints) {
						dsu.union(i, index);
						break;
					}
				}

				// adding stone index in x's map
				if (!xToIndex.containsKey(x)) {
					xToIndex.put(x, new HashSet());
				}
				xToIndex.get(x).add(i);

				// adding stone index in y's map
				if (!yToIndex.containsKey(y)) {
					yToIndex.put(y, new HashSet());
				}
				yToIndex.get(y).add(i);
			}

			return dsu.edgeCount;
		}
	}
}
