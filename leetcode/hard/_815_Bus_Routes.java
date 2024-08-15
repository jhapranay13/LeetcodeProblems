package leetcode.hard;

import java.util.*;

/**
 *
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 *
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
 *
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 *
 *
 *
 * Example 1:
 *
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * Example 2:
 *
 * Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5
 * All the values of routes[i] are unique.
 * sum(routes[i].length) <= 10^5
 * 0 <= routes[i][j] < 10^6
 * 0 <= source, target < 10^6
 *
 */

public class _815_Bus_Routes {
    public int numBusesToDestination(int[][] routes, int source, int target) {

        if (source == target) {
            return 0;
        }

        for (int[] route : routes) {
            Arrays.sort(route);
        }
        Map<Integer, List<Integer>> commonRouteMap = new HashMap<>();

        for (int i = 0; i < routes.length; i++) {

            for (int j = i + 1; j < routes.length; j++) {

                if (isCommonStop(routes[i], routes[j])) {
                    List<Integer> list = commonRouteMap.getOrDefault(i, new ArrayList<>());
                    list.add(j);
                    commonRouteMap.put(i, list);
                    list = commonRouteMap.getOrDefault(j, new ArrayList<>());
                    list.add(i);
                    commonRouteMap.put(j, list);
                }
            }
        }
        Deque<int[]> q = new LinkedList<>();
        Set<Integer> targets = new HashSet<>();

        for (int i  = 0; i < routes.length; i++) {

            if (binarySearch(routes[i], source)) {
                q.offer(new int[] {i ,  0});
            }

            if (binarySearch(routes[i], target)) {
                targets.add(i);
            }
        }
        Set<Integer> v = new HashSet<>();

        while (!q.isEmpty()) {
            int[] point = q.poll();

            if (targets.contains(point[0])) {
                return point[1] + 1;
            }
            v.add(point[0]);

            for (int nextRoute : commonRouteMap.getOrDefault(point[0], new ArrayList<>())) {

                if (!v.contains(nextRoute)) {
                    q.offer(new int[] {nextRoute, point[1] + 1});
                }
            }
        }
        return -1;
    }

    private boolean binarySearch(int[] route, int target) {
        int lo = 0;
        int hi = route.length - 1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (route[pivot] == target) {
                return true;
            } else if (route[pivot] < target) {
                lo = pivot + 1;
            } else {
                hi = pivot -1;
            }
        }
        return false;
    }

    private boolean isCommonStop(int route1[], int route2[]) {
        int i = 0;
        int j = 0;

        while (i < route1.length && j < route2.length) {

            if (route1[i] == route2[j]) {
                return true;
            } else if (route1[i] < route2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return false;
    }
}
