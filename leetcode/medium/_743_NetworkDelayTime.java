package leetcode.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given a network of n nodes, labeled from 1 to n. You are also
 *         given times, a list of travel times as directed edges times[i] = (ui,
 *         vi, wi), where ui is the source node, vi is the target node, and wi
 *         is the time it takes for a signal to travel from source to target.
 * 
 *         We will send a signal from a given node k. Return the time it takes
 *         for all the n nodes to receive the signal. If it is impossible for
 *         all the n nodes to receive the signal, return -1.
 * 
 *         Example 1:
 * 							1<--------2
 * 									  |
 * 									  |
 *                                    V
 *                          4<--------3		
 * 
 *         Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2 Output: 2
 *         
 *         Example 2:
 * 
 *         Input: times = [[1,2,1]], n = 2, k = 1 Output: 1 
 *         
 *         Example 3:
 * 
 *         Input: times = [[1,2,1]], n = 2, k = 2 Output: -1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= k <= n <= 100 1 <= times.length <= 6000 times[i].length == 3 1
 *         <= ui, vi <= n ui != vi 0 <= wi <= 100 All the pairs (ui, vi) are
 *         unique. (i.e., no multiple edges.)
 *
 */

public class _743_NetworkDelayTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//============================================================================
	//Using Shortest Path Algo.
	public int networkDelayTime(int[][] times, int n, int k) {
		Map<Integer, List<int[]>> adjacency = new HashMap<>();

		for (int[] time : times) {
			int source = time[0];
			int destintation = time[1];
			int timeTaken = time[2];
			List<int[]> list = adjacency.getOrDefault(source, new ArrayList<>());
			list.add(new int[] {destintation, timeTaken});
			adjacency.put(source, list);
		}
		//Sorting according to the minimum distance
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
			public int compare(int[] arr1, int[] arr2) {
				return arr1[1] - arr2[1];
			}
		});
		pq.offer(new int[] {k, 0});
		int ans = 0;
		Set<Integer> visited = new HashSet<>();

		while(!pq.isEmpty()) {
			int[] nodeDistance = pq.poll();
			int node = nodeDistance[0];

			if (visited.contains(node)) {
				continue;
			}
			visited.add(node);
			int distance = nodeDistance[1];
			ans = Math.max(ans, distance);
			List<int[]> adj = adjacency.getOrDefault(node, new ArrayList<>());

			for (int[] childDistance : adj) {
				int child = childDistance[0];
				int cDistance = childDistance[1];
				pq.offer(new int[] {child, cDistance + distance});
			}
		}
		return visited.size() == n ? ans : -1;
	}
	//=============================================================================
	//Different Version
	public int networkDelayTime1(int[][] times, int N, int K) {

		/* if( times == null || N == 0 || times.length != N ) {
	            return 0;
	        }*/
		Map< Integer, List<int[]> > neighbourMap = new HashMap<>();

		for( int i = 0; i < times.length; i++ ) {
			int node = times[ i ][ 0 ];
			int neighbour = times[ i ][ 1 ];
			int distance = times[ i ][ 2 ];
			List< int[] > neighbourList = neighbourMap.getOrDefault( node, new ArrayList< int[] >() );
			neighbourList.add( new int[] { neighbour, distance } );
			neighbourMap.put( node, neighbourList );
		}
		PriorityQueue< int[] > queue = new PriorityQueue( new Comparator<int[]>() {

			public int compare( int[] a, int[] b ) {
				return a[ 1 ] - b[ 1 ];
			}
		} );
		Set< String > visited = new HashSet<>();
		queue.add( new int[]{ K, 0 } );
		Map< Integer, Integer > distanceMap = new HashMap<>();


		while( !queue.isEmpty() ) {
			int[] node = queue.poll();
			int distance = node[ 1 ];
			int nodeTag = node[ 0 ];

			if( ! distanceMap.containsKey( nodeTag ) ) {
				distanceMap.put( nodeTag, distance );

				if( neighbourMap.containsKey( nodeTag ) ) {

					for( int[] edge : neighbourMap.get( nodeTag ) ) {

						if( !distanceMap.containsKey( edge[ 0 ] ) ) {
							queue.offer( new int[] { edge[ 0 ], distance + edge[ 1 ] } );
						}
					}
				}
			} 
		}

		if( distanceMap.size() < N ) {
			return -1;
		}
		int ans = 0;

		for( int candidate : distanceMap.values() ) {
			ans = Math.max( ans, candidate );
		}
		return ans;
	}
}
