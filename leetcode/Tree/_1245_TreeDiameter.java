package leetcode.Tree;

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
 *         Given an undirected tree, return its diameter: the number of edges in
 *         a longest path in that tree.
 * 
 *         The tree is given as an array of edges where edges[i] = [u, v] is a
 *         bidirectional edge between nodes u and v. Each node has labels in the
 *         set {0, 1, ..., edges.length}.
 * 
 *         Example 1:
 * 							0
 * 						   / \
 * 						  1   2		
 * 							
 *         Input: edges = [[0,1],[0,2]] Output: 2 Explanation: A longest path of
 *         the tree is the path 1 - 0 - 2. 
 *         
 *         Example 2:
 * 					   0----1
 * 						   / \
 * 						  2   4
 * 						 /   /
 * 						3   5
 * 
 *         Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]] Output: 4 Explanation:
 *         A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
 * 
 * 
 *         Constraints:
 * 
 *         0 <= edges.length < 10^4 edges[i][0] != edges[i][1] 0 <= edges[i][j]
 *         <= edges.length The given edges form an undirected tree.
 *
 */

public class _1245_TreeDiameter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int treeDiameter(int[][] edges) {
		Map<Integer, List<Integer>> adjacency = new HashMap<>();

		for (int[] edge : edges) {
			List<Integer> list = adjacency.getOrDefault(edge[0], new ArrayList<>());
			list.add(edge[1]);
			adjacency.put(edge[0], list);
			list = adjacency.getOrDefault(edge[1], new ArrayList<>());
			list.add(edge[0]);
			adjacency.put(edge[1], list);
		}
		Set<Integer> visited = new HashSet<>();
		dfs(adjacency, 0, 0, visited);
		int farthestNode = maxFarNode;
		maxFarNode = -1;
		maxDistance = -1;
		dfs(adjacency, farthestNode, 0, visited);
		return maxDistance;
	}
	private int maxFarNode = -1;
	private int maxDistance = -1;

	private void dfs(Map<Integer, List<Integer>> adjacency, int node, int step, 
			Set<Integer> visited) {
		if(step > maxDistance) {
			maxFarNode = node;
			maxDistance = step;
		}
		visited.add(node);
		List<Integer> list = adjacency.getOrDefault(node, new ArrayList<>());

		for (int child : list) {

			if (!visited.contains(child)) {
				dfs(adjacency, child, step + 1, visited);
			}
		}
		visited.remove(node);
	}
}
