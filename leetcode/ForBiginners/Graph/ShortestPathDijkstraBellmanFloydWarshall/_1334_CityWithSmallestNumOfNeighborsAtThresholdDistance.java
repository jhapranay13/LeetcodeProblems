package leetcode.ForBiginners.Graph.ShortestPathDijkstraBellmanFloydWarshall;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         There are n cities numbered from 0 to n-1. Given the array edges
 *         where edges[i] = [fromi, toi, weighti] represents a bidirectional and
 *         weighted edge between cities fromi and toi, and given the integer
 *         distanceThreshold.
 * 
 *         Return the city with the smallest number of cities that are reachable
 *         through some path and whose distance is at most distanceThreshold, If
 *         there are multiple such cities, return the city with the greatest
 *         number.
 * 
 *         Notice that the distance of a path connecting cities i and j is equal
 *         to the sum of the edges' weights along that path.
 * 
 * 
 * 
 *         Example 1:
 *         					3
 * 						0---------1
 * 								/ |
 * 							 4 /  | 1
 * 							  /	  |
 * 	       					 3----2 	
 * 								1
 * 
 *         Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]],
 *         distanceThreshold = 4 Output: 3 Explanation: The figure above
 *         describes the graph. The neighboring cities at a distanceThreshold =
 *         4 for each city are: City 0 -> [City 1, City 2] City 1 -> [City 0,
 *         City 2, City 3] City 2 -> [City 0, City 1, City 3] City 3 -> [City 1,
 *         City 2] Cities 0 and 3 have 2 neighboring cities at a
 *         distanceThreshold = 4, but we have to return city 3 since it has the
 *         greatest number. 
 *         
 *         Example 2:
 *    							3
 * 							1-------2
 * 						2  /|       |
 *                        / |       |
 *                       0 2|       | 1
 *                        \ |       |
 *                       8 \|_______|
 *                         	4   1   3
 *                         
 *                         
 *         Input: n = 5, edges =
 *         [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold
 *         = 2 Output: 0 Explanation: The figure above describes the graph. The
 *         neighboring cities at a distanceThreshold = 2 for each city are: City
 *         0 -> [City 1] City 1 -> [City 0, City 4] City 2 -> [City 3, City 4]
 *         City 3 -> [City 2, City 4] City 4 -> [City 1, City 2, City 3] The
 *         city 0 has 1 neighboring city at a distanceThreshold = 2.
 * 
 * 
 *         Constraints:
 * 
 *         2 <= n <= 100 1 <= edges.length <= n * (n - 1) / 2 edges[i].length ==
 *         3 0 <= fromi < toi < n 1 <= weighti, distanceThreshold <= 10^4 All
 *         pairs (fromi, toi) are distinct.
 */

public class _1334_CityWithSmallestNumOfNeighborsAtThresholdDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int findTheCity(int n, int[][] edges, int distanceThreshold) {
		//Implementing Floyd Warshall. Shortest Path fromall node to all node
		int[][] adjacency = new int[n][n];

		for (int[] adj : adjacency) {
			Arrays.fill(adj, Integer.MAX_VALUE / 10);
		}

		for (int[] edge : edges) {
			adjacency[edge[0]][edge[1]] = edge[2];
			adjacency[edge[1]][edge[0]] = edge[2];
		}
		//x to y via k
		for (int k = 0; k < n; k++) {

			for (int x = 0; x < n; x++) {

				for (int y = 0; y < n; y++) {

					if (x != y && adjacency[x][y] > adjacency[x][k] + adjacency[k][y]) {
						adjacency[x][y] = adjacency[x][k] + adjacency[k][y];
					}
				}
			}
		}
		int count = Integer.MAX_VALUE;
		int ans = -1;

		for (int i = 0; i < n; i++) {
			int tempCount = 0;

			for (int j = 0; j < n; j++) {

				if (i != j && adjacency[i][j] <= distanceThreshold) {
					tempCount++;
				}
			}

			if (tempCount <= count) {
				ans = i;
				count = tempCount;
			}
		}
		return ans;
	}
}
