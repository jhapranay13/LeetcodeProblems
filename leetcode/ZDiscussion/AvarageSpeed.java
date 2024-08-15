package leetcode.ZDiscussion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Description: You have developed a maps application, which shows roads
 *         with traffic. You want to find out avergae speed on each road
 *         segment. You are given <start, end, speed> of cars. Find the average
 *         speed on each segment. Example Input: [[0, 14, 90], [3, 15, 80]]
 *         Output: [[0,3, 90], [3, 14, 85], [14, 15, 80]]
 *
 */

public class AvarageSpeed {

	public static void main(String[] args) {
		List<int[]> ans = new ArrayList<>();
		int[][] roads = { { 0, 14, 90 }, { 3, 15, 80 }, { 2, 10, 75 }, { 3, 8, 80 }, { 3, 7, 80 } };
		int[][] roads1 = { { 0, 10, 90 }, { 12, 15, 80 } };
		int[][] roads3 = { { 0, 14, 90 }, { 3, 15, 80 } };

		getAvarage(roads, ans);
		for (int[] a : ans) {
			System.out.println(Arrays.toString(a));
		}
		ans.clear();
		getAvarage(roads1, ans);
		for (int[] a : ans) {
			System.out.println(Arrays.toString(a));
		}

		ans.clear();
		getAvarage(roads3, ans);
		for (int[] a : ans) {
			System.out.println(Arrays.toString(a));
		}
	}

	private static void getAvarage(int[][] roads, List<int[]> ans) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0]) {
					return b[1] - a[1];
				}
				return a[0] - b[0];
			}
		});
		// Adding start end speed and count.
		for (int[] road : roads) {
			pq.offer(new int[] { road[0], road[1], road[2], 1 });
		}

		while (!pq.isEmpty()) {
			int[] a = pq.poll();

			if (pq.isEmpty() || a[1] <= pq.peek()[0]) {
				ans.add(new int[] { a[0], a[1], a[2] / a[3] });
				continue;
			}
			int[] b = pq.poll();
			// breaking the overlap and adding it back to pq.
			if (a[0] == b[0] && a[1] == b[1]) {
				pq.offer(new int[] { a[0], a[1], a[2] + b[2], a[3] + b[3] });
			} else if (a[0] == b[0] && b[1] < a[1]) {
				pq.offer(new int[] { a[0], b[1], a[2] + b[2], a[3] + b[3] });
				pq.offer(new int[] { b[1], a[1], a[2], a[3] });
			} else if (a[1] == b[1]) {
				pq.offer(new int[] { a[0], b[0], a[2], a[3] });
				pq.offer(new int[] { b[0], b[1], a[2] + b[2], a[3] + b[3] });
			} else if (b[1] < a[1]) {
				pq.offer(new int[] { a[0], b[0], a[2], a[3] });
				pq.offer(new int[] { b[0], b[1], a[2] + b[2], a[3] + b[3] });
				pq.offer(new int[] { b[1], a[1], a[2], a[3] });
			} else {
				pq.offer(new int[] { a[0], b[0], a[2], a[3] });
				pq.offer(new int[] { b[0], a[1], a[2] + b[2], a[3] + b[3] });
				pq.offer(new int[] { a[1], b[1], b[2], b[3] });
			}
		}
	}

	// ==============================================================================
	// HashMap Solution
	private static void getAvarage1(int[][] roads, List<int[]> ans) {
		Map<Integer, List<Integer>> positionSpeedMap = new HashMap();

		for (int[] road : roads) {
			positionSpeedMap.put(road[0], new ArrayList<>());
			positionSpeedMap.put(road[1], new ArrayList<>());
		}

		for (int[] road : roads) {

			for (int i = road[0]; i < road[1]; i++) {
				if (!positionSpeedMap.containsKey(i)) {
					continue;
				}
				List<Integer> list = positionSpeedMap.getOrDefault(i, new ArrayList<>());
				list.add(road[2]);
				positionSpeedMap.put(i, list);
			}
		}

		List<Integer> keys = new ArrayList<>();
		keys.addAll(positionSpeedMap.keySet());
		Collections.sort(keys);
		int anchor = -1;
		List<Integer> holder = null;
		int sum = 0;
		int count = 0;

		for (int key : keys) {
			List<Integer> list = positionSpeedMap.get(key);

			if (anchor == -1) {
				anchor = key;
				holder = list;
				continue;
			}

			for (int speed : holder) {
				sum += speed;
				count++;
			}
			ans.add(new int[] { anchor, key, sum / count });
			sum = 0;
			count = 0;

			if (list.isEmpty()) {
				anchor = -1;
				holder = null;
			} else {
				anchor = key;
				holder = list;
			}
		}
	}

	// ============================================================================
	// Simple N log n solution
	public static List<int[]> solution(int[][] data) {
		int len = data.length;
		int[][] points = new int[len * 2][3];
		int index = 0;
		for (int[] d : data) {

			int[] start = new int[] { d[0], d[2], 0 };
			points[index++] = start;

			int[] end = new int[] { d[1], d[2], 1 };
			points[index++] = end;

		}

		Arrays.sort(points, (a, b) -> {
			if (a[0] == b[0])
				return b[2] - a[2];
			return a[0] - b[0];
		});

		List<int[]> list = new ArrayList<>();
		int total = 0, count = 0;
		int startTime = -1;
		
		for (int i = 0; i < points.length; i++) {

			if (count == 0) {
				startTime = points[i][0];
				total += points[i][1];
				count++;
			} else {
				int endTime = points[i][0];
				
				if (endTime != startTime) {
					list.add(new int[] { startTime, endTime, total / count });
					startTime = endTime;
				}

				if (points[i][2] == 0) {
					count++;
					total += points[i][1];
				} else {
					count--;
					total -= points[i][1];
				}

			}
		}
		return list;
	}
}
