package leetcode.ForBiginners.Graph.ColoringOrBipartition;

import java.util.*;

/**
 * 
 * @author Pranay Jha
 *
 *         There is an undirected graph with n nodes, where each node is
 *         numbered between 0 and n - 1. You are given a 2D array graph, where
 *         graph[u] is an array of nodes that node u is adjacent to. More
 *         formally, for each v in graph[u], there is an undirected edge between
 *         node u and node v. The graph has the following properties:
 * 
 *         There are no self-edges (graph[u] does not contain u). There are no
 *         parallel edges (graph[u] does not contain duplicate values). If v is
 *         in graph[u], then u is in graph[v] (the graph is undirected). The
 *         graph may not be connected, meaning there may be two nodes u and v
 *         such that there is no path between them. A graph is bipartite if the
 *         nodes can be partitioned into two independent sets A and B such that
 *         every edge in the graph connects a node in set A and a node in set B.
 * 
 *         Return true if and only if it is bipartite.
 * 
 * 
 * 
 *         Example 1:
 * 
 * 					0------1		
 * 					| \	   |
 * 					|  \   |
 * 					|   \  |
 *                  |    \ |
 *                  3------2
 *                  
 *         Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]] Output: false
 *         Explanation: There is no way to partition the nodes into two
 *         independent sets such that every edge connects a node in one and a
 *         node in the other. 
 *         
 *         Example 2:
 * 
 * 					0------1		
 * 					|  	   |
 * 					|      |
 *                  3------2				
 * 	
 *         Input: graph = [[1,3],[0,2],[1,3],[0,2]] Output: true Explanation: We
 *         can partition the nodes into two sets: {0, 2} and {1, 3}.
 * 
 * 
 *         Constraints:
 * 
 *         graph.length == n 1 <= n <= 100 0 <= graph[u].length < n 0 <=
 *         graph[u][i] <= n - 1 graph[u] does not contain u. All the values of
 *         graph[u] are unique. If graph[u] contains v, then graph[v] contains
 *         u.
 *
 */

public class _785_IsGraphBipartite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//===============================================================================
	public boolean isBipartite(int[][] graph) {

		if (graph == null) {
			return false;
		}
		Map<Integer, Set<Integer>> adjacency = new HashMap<>();

		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				Set<Integer> child = adjacency.getOrDefault(i, new HashSet<>());
				Set<Integer> parent = adjacency.getOrDefault(graph[i][j], new HashSet<>());
				child.add(graph[i][j]);
				parent.add(i);
				adjacency.put(i, child);
				adjacency.put(graph[i][j], parent);
			}
		}


		for (int i = 0; i < graph.length; i++) {
			Deque<int[]> queue = new LinkedList<>();
			queue.offer(new int[] {i, 0});
			Map<Integer, Integer> visitedStateMap = new HashMap<>();


			while (!queue.isEmpty()) {
				int size = queue.size();

				while (size-- > 0) {
					int[] nodeState = queue.poll();
					int node = nodeState[0];
					int state = nodeState[1];
					Set<Integer> adj = adjacency.getOrDefault(node, new HashSet<>());

					for (int child : adj) {

						if (!visitedStateMap.containsKey(child)) {
							visitedStateMap.put(child, state ^ 1); //Xor to swith between 0 & 1
							queue.offer(new int[] {child, state ^ 1});
						} else {
							int childState = visitedStateMap.get(child);

							if (childState == state) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	//============================================================================
	//Little Different version
	public boolean isBipartite1(int[][] graph) {

		if( graph == null ) {
			return false;
		}
		Map< Integer, Set< Integer > > adjSet = new HashMap<>();

		for( int i = 0; i < graph.length; i++ ) {
			int currNode = i;
			int[] con = graph[ i ];

			for( int c : con ) {
				Set< Integer > currSet = adjSet.getOrDefault( currNode,
						new HashSet<>() );
				currSet.add( c );
				adjSet.put( currNode, currSet );
				Set< Integer > cSet = adjSet.getOrDefault( c,
						new HashSet<>() );
				cSet.add( currNode );
				adjSet.put( c, cSet );
			}
		}
		Map< Integer, Integer > stateMap = new HashMap<>();

		for( int i = 0; i < graph.length; i++ ) {    
			Deque< Integer > q = new LinkedList<>();
			Deque< Integer > nq = new LinkedList<>();

			if( stateMap.containsKey( i ) ) {
				continue;
			}
			q.offer( i );
			stateMap.put( i, 0 );

			while( !q.isEmpty() ) {
				int node = q.poll();
				int nodeState = stateMap.get( node );
				Set< Integer > adj = adjSet.getOrDefault( node, new HashSet<>() );
				int state = nodeState == 0 ? 1 : 0;

				for( Integer n : adj ) {

					if( !stateMap.containsKey( n ) ) {
						nq.offer( n );
						stateMap.put( n, state );    
					} else {
						int currState = stateMap.get( n );

						if( currState == nodeState ) {
							return false;
						}
					}
				}

				if( q.isEmpty() ) {
					q = nq;
					nq = new LinkedList< Integer >();
				}
			}
		}
		return stateMap.size() == graph.length;
	}
	//=============================================================================================
	//State can be outside the loop
	public boolean isBipartite2(int[][] graph) {

		if (graph == null) {
			return false;
		}
		Map<Integer, Set<Integer>> adjacency = new HashMap<>();

		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				Set<Integer> child = adjacency.getOrDefault(i, new HashSet<>());
				Set<Integer> parent = adjacency.getOrDefault(graph[i][j], new HashSet<>());
				child.add(graph[i][j]);
				parent.add(i);
				adjacency.put(i, child);
				adjacency.put(graph[i][j], parent);
			}
		}
		Map<Integer, Integer> visitedStateMap = new HashMap<>();

		for (int i = 0; i < graph.length; i++) {
			Deque<int[]> queue = new LinkedList<>();
			queue.offer(new int[] {i, 0});

			if (!visitedStateMap.containsKey(i))
				while (!queue.isEmpty()) {
					int size = queue.size();

					while (size-- > 0) {
						int[] nodeState = queue.poll();
						int node = nodeState[0];
						int state = nodeState[1];
						Set<Integer> adj = adjacency.getOrDefault(node, new HashSet<>());

						for (int child : adj) {

							if (!visitedStateMap.containsKey(child)) {
								visitedStateMap.put(child, state ^ 1); //Xor to swith between 0 & 1
								queue.offer(new int[] {child, state ^ 1});
							} else {
								int childState = visitedStateMap.get(child);

								if (childState == state) {
									return false;
								}
							}
						}
					}
				}
		}
		return true;
	}
}
