package leetcode.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         A tree is an undirected graph in which any two vertices are connected
 *         by exactly one path. In other words, any connected graph without
 *         simple cycles is a tree.
 * 
 *         Given a tree of n nodes labelled from 0 to n - 1, and an array of n -
 *         1 edges where edges[i] = [ai, bi] indicates that there is an
 *         undirected edge between the two nodes ai and bi in the tree, you can
 *         choose any node of the tree as the root. When you select a node x as
 *         the root, the result tree has height h. Among all possible rooted
 *         trees, those with minimum height (i.e. min(h)) are called minimum
 *         height trees (MHTs).
 * 
 *         Return a list of all MHTs' root labels. You can return the answer in
 *         any order.
 * 
 *         The height of a rooted tree is the number of edges on the longest
 *         downward path between the root and a leaf.
 * 
 * 
 * 
 *         Example 1:
 * 					
 * 					0     			1				2				3
 * 					|			  / | \             |               |
 * 					1			 0	2  3			1				1
 * 				   / \                             / \             / \
 * 				  2   3                           0   3           0   2		
 * 
 *         Input: n = 4, edges = [[1,0],[1,2],[1,3]] Output: [1] Explanation: As
 *         shown, the height of the tree is 1 when the root is the node with
 *         label 1 which is the only MHT. 
 *         
 *         Example 2:
 * 										
 * 							3								4
 * 						  /	/\ \                           / \
 * 						 0  1 2 4						  5   3					
 * 								|                            /|\
 * 								5                           0 1 2
 * 
 *         Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]] Output: [3,4]
 *         
 *         Example 3:
 * 
 *         Input: n = 1, edges = [] Output: [0] Example 4:
 * 
 *         Input: n = 2, edges = [[0,1]] Output: [0,1]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 2 * 104 edges.length == n - 1 0 <= ai, bi < n ai != bi All
 *         the pairs (ai, bi) are distinct. The given input is guaranteed to be
 *         a tree and there will be no repeated edges.
 *
 */

public class _310_MinimumHeightTrees {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Two Queue Approach
	// Pruning concept. Start with outer edges towards inwords
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {

		if (n == 1 && edges.length == 0) {
			List<Integer> ans = new ArrayList<>();
			ans.add(0);
			return ans;
		}
		int[] incoming = new int[n];
		Map<Integer, List<Integer>> adjacency = new HashMap<>();
		initIncomingAndAdjacency(incoming, adjacency, edges);
		System.out.println(adjacency);

		List<Integer> ans = getMinimumHeightTreeRoot(incoming, adjacency);
		return ans;
	}

	private List<Integer> getMinimumHeightTreeRoot(int[] incoming, Map<Integer, List<Integer>> adjacency) {
		Deque<Integer> queue = new LinkedList<>();
		Deque<Integer> nextQueue = new LinkedList<>();
		List<Integer> ans = new ArrayList<>();

		for (int i = 0; i < incoming.length; i++) {

			if (incoming[i] == 1) {
				queue.offer(i);
			}
		}
		Set<Integer> visited = new HashSet<>();

		while (!queue.isEmpty()) {
			int node = queue.poll();
			visited.add(node);
			ans.add(node);
			List<Integer> children = adjacency.get(node);

			for (int child : children) {

				if (visited.contains(child)) {
					continue;
				}

				if (--incoming[child] == 1) {
					nextQueue.add(child);
				}
			}

			if (queue.isEmpty() && !nextQueue.isEmpty()) {
				ans.clear();
				queue = nextQueue;
				nextQueue = new LinkedList<>();
			}
		}
		return ans;
	}

	private void initIncomingAndAdjacency(int[] incoming, Map<Integer, List<Integer>> adjacency, int[][] edges) {

		for (int[] edge : edges) {
			int node1 = edge[0];
			int node2 = edge[1];
			incoming[node1]++;
			incoming[node2]++;
			List<Integer> child1 = adjacency.getOrDefault(node1, new ArrayList<>());
			List<Integer> child2 = adjacency.getOrDefault(node2, new ArrayList<>());
			child1.add(node2);
			child2.add(node1);
			adjacency.put(node1, child1);
			adjacency.put(node2, child2);
		}
	}
	//============================================================================
	//Single Queue Implementation
	//Pruning concept. Start with outer edges towards inwords
    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        
        if (n == 1 && edges.length == 0) {
            List<Integer> ans = new ArrayList<>();
            ans.add(0);
            return ans;
        }
        int[] incoming = new int[n];
        Map<Integer, List<Integer>> adjacency = new HashMap<>();
        initIncomingAndAdjacency(incoming, adjacency, edges);
        List<Integer> ans = getMinimumHeightTreeRoot1(incoming, adjacency);
        return ans;
    }
    
    private List<Integer> getMinimumHeightTreeRoot1(int[] incoming, 
                                                   Map<Integer, List<Integer>> adjacency) {
        Deque<Integer> queue = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        
        for (int i = 0; i < incoming.length; i++) {
            
            if (incoming[i] == 1) {
                queue.offer(i);
            }
        }
        Set<Integer> visited = new HashSet<>();
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            while (size-- > 0) {
                int node = queue.poll();
                visited.add(node);
                ans.add(node);
                List<Integer> children = adjacency.get(node);
            
                for (int child : children) {
                
                    if (visited.contains(child)) {
                        continue;
                    }
                
                    if (--incoming[child] == 1) {
                        queue.add(child);
                    }
                }
            }
            if (!queue.isEmpty()) {
                ans.clear();
            }
        }
        return ans;
    }
}
