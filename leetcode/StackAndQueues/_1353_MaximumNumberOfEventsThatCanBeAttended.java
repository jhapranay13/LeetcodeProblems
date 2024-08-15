package leetcode.StackAndQueues;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of events where events[i] = [startDayi, endDayi].
 *         Every event i starts at startDayi and ends at endDayi.
 * 
 *         You can attend an event i at any day d where startTimei <= d <=
 *         endTimei. Notice that you can only attend one event at any time d.
 * 
 *         Return the maximum number of events you can attend.
 * 
 * 
 * 
 *         Example 1:
 * 
 * 
 *         Input: events = [[1,2],[2,3],[3,4]] Output: 3 Explanation: You can
 *         attend all the three events. One way to attend them all is as shown.
 *         Attend the first event on day 1. Attend the second event on day 2.
 *         Attend the third event on day 3.
 * 
 *         Example 2:
 * 
 *         Input: events= [[1,2],[2,3],[3,4],[1,2]] Output: 4
 * 
 *         Example 3:
 * 
 *         Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]] Output: 4
 * 
 *         Example 4:
 * 
 *         Input: events = [[1,100000]] Output: 1
 * 
 *         Example 5:
 * 
 *         Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]] Output: 7
 * 
 * 
 *         Constraints:
 * 
 *         1 <= events.length <= 105 events[i].length == 2 1 <= startDayi <=
 *         endDayi <= 105
 *
 */

public class _1353_MaximumNumberOfEventsThatCanBeAttended {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int maxEvents(int[][] events) {
		Arrays.sort(events, new Comparator<int[]>() {
			public int compare(int a1[], int a2[]) {
				if (a1[0] == a2[0]) {
					return a1[1] - a2[1];
				}
				return a1[0] - a2[0];
			}
		});
		// PriorityQueue Sorting based on end time to accomodate the below mentioned
		// logic
		// so that a range with minumu start and maximum end will still be in priority
		// queue
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] x, int[] y) {
				if (x[1] == y[1]) {
					return x[0] - y[0];
				}
				return x[1] - y[1];
			}
		});
		int endDate = -1;
		int day = 0;
		int index = 0;
		int eventAttended = 0;
		// logic is to attend the event at the last available possible time.
		while (index < events.length || !pq.isEmpty()) {

			if (pq.isEmpty()) {
				day = events[index][0];
			}

			while (index < events.length && events[index][0] <= day) {
				pq.offer(events[index++]);
			}
			day++;
			eventAttended++;
			pq.poll(); // removing the event attended

			// Discarding events that cannot be attended
			while (!pq.isEmpty() && pq.peek()[1] < day) {
				pq.poll();
			}
		}
		return eventAttended;
	}

	// ============================================================================
	// Version Without PQ TLE in last test case
	public int maxEvents1(int[][] events) {
		Arrays.sort(events, new Comparator<int[]>() {
			public int compare(int a1[], int a2[]) {
				if (a1[1] == a2[1]) {
					return a1[0] - a2[0];
				}
				return a1[1] - a2[1];
			}
		});
		boolean[] timeSlots = new boolean[events[events.length - 1][1] + 1];
		int eventsAttended = 0;

		for (int[] event : events) {
			int start = event[0];
			int end = event[1];

			while (start <= end) {
				if (!timeSlots[start]) {
					timeSlots[start] = true;
					eventsAttended++;
					break;
				}
				start++;
			}
		}
		return eventsAttended;
	}

	// =============================================================================
	// Using HashSet instead of array
	public int maxEvents2(int[][] events) {
		Arrays.sort(events, new Comparator<int[]>() {
			public int compare(int a1[], int a2[]) {
				if (a1[1] == a2[1]) {
					return a1[0] - a2[0];
				}
				return a1[1] - a2[1];
			}
		});
		Set<Integer> timeSlots = new HashSet<>();
		int eventsAttended = 0;

		for (int[] event : events) {
			int start = event[0];
			int end = event[1];

			while (start <= end) {
				if (timeSlots.add(start)) {
					eventsAttended++;
					break;
				}
				start++;
			}
		}
		return eventsAttended;
	}
}
