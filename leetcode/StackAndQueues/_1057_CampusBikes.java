package leetcode.StackAndQueues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         On a campus represented as a 2D grid, there are N workers and M
 *         bikes, with N <= M. Each worker and bike is a 2D coordinate on this
 *         grid.
 * 
 *         Our goal is to assign a bike to each worker. Among the available
 *         bikes and workers, we choose the (worker, bike) pair with the
 *         shortest Manhattan distance between each other, and assign the bike
 *         to that worker. (If there are multiple (worker, bike) pairs with the
 *         same shortest Manhattan distance, we choose the pair with the
 *         smallest worker index; if there are multiple ways to do that, we
 *         choose the pair with the smallest bike index). We repeat this process
 *         until there are no available workers.
 * 
 *         The Manhattan distance between two points p1 and p2 is Manhattan(p1,
 *         p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 * 
 *         Return a vector ans of length N, where ans[i] is the index
 *         (0-indexed) of the bike that the i-th worker is assigned to.
 * 
 * 
 * 
 *         Example 1:
 * 
 * 
 * 
 *         Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]] Output: [1,0]
 *         Explanation: Worker 1 grabs Bike 0 as they are closest (without
 *         ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
 * 
 * 
 *         Example 2:
 * 
 * 
 * 
 *         Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 *         Output: [0,2,1] Explanation: Worker 0 grabs Bike 0 at first. Worker 1
 *         and Worker 2 share the same distance to Bike 2, thus Worker 1 is
 *         assigned to Bike 2, and Worker 2 will take Bike 1. So the output is
 *         [0,2,1].
 * 
 * 
 *         Note:
 * 
 *         0 <= workers[i][j], bikes[i][j] < 1000 All worker and bike locations
 *         are distinct. 1 <= workers.length <= bikes.length <= 1000
 *
 */

public class _1057_CampusBikes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ==============================================================================
	// Bucket Sort Example
	public int[] assignBikes(int[][] workers, int[][] bikes) {
		List<int[]>[] buckets = new List[2000];

		for (int i = 0; i < buckets.length; ++i) {
			buckets[i] = new ArrayList();
		}

		for (int i = 0; i < workers.length; i++) {

			for (int j = 0; j < bikes.length; j++) {
				int distance = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
				buckets[distance].add(new int[] { i, j });
			}
		}
		int[] result = new int[workers.length];
		Arrays.fill(result, -1);
		int[] bikesVisited = new int[bikes.length];
		Arrays.fill(bikesVisited, -1);
		int counter = 0;

		for (List<int[]> bucket : buckets) {

			if (counter == result.length) {
				break;
			}

			if (bucket.size() == 0) {
				continue;
			}

			for (int[] detail : bucket) {
				int worker = detail[0];
				int bike = detail[1];

				if (result[worker] == -1 && bikesVisited[bike] == -1) {
					result[worker] = bike;
					bikesVisited[bike] = 1;
					counter++;
				}
			}
		}
		return result;
	}

	// ============================================================================
	// Priority Queue IMplementation
	public int[] assignBikes1(int[][] workers, int[][] bikes) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] x, int[] y) {
				int distance1 = x[2];
				int distance2 = y[2];
				int worker1 = x[0];
				int worker2 = y[0];
				int bike1 = x[1];
				int bike2 = y[1];

				if (distance1 != distance2) {
					return distance1 - distance2;
				} else if (worker1 != worker2) {
					return worker1 - worker2;
				}
				return bike1 - bike2;
			}
		});
		for (int i = 0; i < workers.length; i++) {
			for (int j = 0; j < bikes.length; j++) {
				int worker[] = workers[i];
				int bike[] = bikes[j];
				int distance = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
				pq.offer(new int[] { i, j, distance });
			}
		}
		int[] ans = new int[workers.length];
		boolean[] workersSet = new boolean[workers.length];
		boolean[] bikesSet = new boolean[bikes.length];
		int count = workers.length;

		while (count > 0) {
			int[] node = pq.poll();
			int worker = node[0];
			int bike = node[1];

			if (workersSet[worker] || bikesSet[bike]) {
				continue;
			}
			workersSet[worker] = true;
			bikesSet[bike] = true;
			ans[worker] = bike;
			count--;
		}
		return ans;
	}
}
