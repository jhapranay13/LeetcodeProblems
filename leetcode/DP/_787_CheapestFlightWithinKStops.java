package leetcode.DP;

import java.util.*;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         There are n cities connected by some number of flights. You are given
 *         an array flights where flights[i] = [fromi, toi, pricei] indicates
 *         that there is a flight from city fromi to city toi with cost pricei.
 * 
 *         You are also given three integers src, dst, and k, return the
 *         cheapest price from src to dst with at most k stops. If there is no
 *         such route, return -1.
 * 
 * 
 * 
 *         Example 1:
 * 
 * 								0
 * 							   / \
 * 						100   /   \ 500
 * 							 /	   \
 * 							1-------2
 * 								100
 * 
 *         Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst
 *         = 2, k = 1 Output: 200 Explanation: The graph is shown. The cheapest
 *         price from city 0 to city 2 with at most 1 stop costs 200, as marked
 *         red in the picture. 
 *         
 *         Example 2:
 * 
 * 
 *         Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst
 *         = 2, k = 0 Output: 500 Explanation: The graph is shown. The cheapest
 *         price from city 0 to city 2 with at most 0 stop costs 500, as marked
 *         blue in the picture.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 100 0 <= flights.length <= (n * (n - 1) / 2)
 *         flights[i].length == 3 0 <= fromi, toi < n fromi != toi 1 <= pricei
 *         <= 104 There will not be any multiple flights between two cities. 0
 *         <= src, dst, k < n src != dst
 *
 */

public class _787_CheapestFlightWithinKStops {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//============================================================================
	//Dijkstra's Algorithm
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		Map<Integer, List<int[]>> adjacency = new HashMap<>();

		for (int[] flight : flights) {
			int sorc = flight[0];
			int dest = flight[1];
			int price = flight[2];
			List<int[]> child = adjacency.getOrDefault(sorc, new ArrayList<>());
			child.add(new int[] { dest, price });
			adjacency.put(sorc, child);
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
			public int compare(int[] arr1, int[] arr2) {
				return arr1[1] - arr2[1];
			}
		});
		pq.offer(new int[] { src, 0, 0 });
		Map<Integer, int[]> visited = new HashMap<>();

		while (!pq.isEmpty()) {
			int[] nodePriceStop = pq.poll();
			int node = nodePriceStop[0];
			int price = nodePriceStop[1];
			int stops = nodePriceStop[2];

			if (visited.containsKey(node) && visited.get(node)[0] < price && visited.get(node)[1] < stops) {
				continue;
			}

			if (node == dst) {
				return price;
			}

			if (stops > k) {
				continue;
			}
			visited.put(node, new int[] { price, stops });
			List<int[]> adj = adjacency.getOrDefault(node, new ArrayList<>());

			for (int[] nodePrice : adj) {
				int child = nodePrice[0];
				int childPrice = nodePrice[1] + price;
				int childStop = stops + 1;
				pq.offer(new int[] { child, childPrice, childStop });
			}
		}
		return -1;
	}
	//=============================================================================================
	//Bellman Ford Implementation
	public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
		int[] price = new int[n];
		Arrays.fill(price, Integer.MAX_VALUE);
		int[] tempprice = new int[n];
		Arrays.fill(tempprice, Integer.MAX_VALUE);

		price[src] = 0;

		for (int i = 0; i <= k; i++) {

			for (int[] flight : flights) {
				int source = flight[0];

				if (price[source] == Integer.MAX_VALUE) {
					continue;
				}
				int destination = flight[1];
				int cost = flight[2];

				if (tempprice[destination] > price[source] + cost ) {
					tempprice[destination] = price[source] + cost;
				}
			}
			price = tempprice.clone();
		}
		return price[dst] != Integer.MAX_VALUE ? price[dst] : -1;
	}
	//=============================================================================================
	//DP top Down
	public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int k) {
		Map<Integer, List<int[]>> adjMap = new HashMap<>();

		for (int[] flight : flights) {
			List<int[]> list = adjMap.getOrDefault(flight[0], new ArrayList<>());
			list.add(new int[] {flight[1], flight[2]});
			adjMap.put(flight[0], list);
		}
		int[][] memo = new int[n][k + 2];
		int ans = recur(adjMap, dst, src, k + 1, memo);
		return ans != Integer.MAX_VALUE ? ans : -1;
	}

	private int recur(Map<Integer, List<int[]>> adjMap, int d, int s, int k, int[][] memo) {

		if (s == d) {

			if (k >= 0) {
				return 0;
			}
			return Integer.MAX_VALUE;
		}

		if (k < 0) {
			return Integer.MAX_VALUE;
		}

		if (memo[s][k] > 0) {
			return memo[s][k];
		}
		List<int[]> list = adjMap.getOrDefault(s, new ArrayList<>());
		int ans = Integer.MAX_VALUE;

		for (int[] next : list) {
			int temp = recur(adjMap, d, next[0], k - 1, memo);

			if (temp != Integer.MAX_VALUE) {
				ans = Math.min(ans, temp + next[1]);
			}
		}
		return memo[s][k] = ans;
	}
}
