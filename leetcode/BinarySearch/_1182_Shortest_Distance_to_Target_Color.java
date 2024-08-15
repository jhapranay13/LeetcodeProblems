package leetcode.BinarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * You are given an array colors, in which there are three colors: 1, 2 and 3.
 *
 * You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
 * Output: [3,0,3]
 * Explanation:
 * The nearest 3 from index 1 is at index 4 (3 steps away).
 * The nearest 2 from index 2 is at index 2 itself (0 steps away).
 * The nearest 1 from index 6 is at index 3 (3 steps away).
 * Example 2:
 *
 * Input: colors = [1,2], queries = [[0,3]]
 * Output: [-1]
 * Explanation: There is no 3 in the array.
 *
 *
 * Constraints:
 *
 * 1 <= colors.length <= 5*10^4
 * 1 <= colors[i] <= 3
 * 1 <= queries.length <= 5*10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] < colors.length
 * 1 <= queries[i][1] <= 3
 *
 *
 */

public class _1182_Shortest_Distance_to_Target_Color {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        Map<Integer, List<Integer>> colorIndex = new HashMap<>();

        for (int i = 0; i < colors.length; i++) {
            List<Integer> list = colorIndex.getOrDefault(colors[i], new ArrayList<>());
            list.add(i);
            colorIndex.put(colors[i], list);
        }
        List<Integer> holder = new ArrayList<>();

        for (int[] query : queries) {
            int index = query[0];
            int color = query[1];
            List<Integer> list = colorIndex.getOrDefault(color, new ArrayList<>());

            if (list.size() == 0) {
                holder.add(-1);
                continue;
            }
            int lo = 0;
            int hi = list.size() - 1;
            int ans = hi;
            //just greater than equalTo

            while (lo <= hi) {
                int pivot = lo + (hi - lo) / 2;

                if (list.get(pivot) < index) {
                    lo = pivot + 1;
                } else {
                    hi = pivot - 1;
                    ans = pivot;
                }
            }

            if (ans == 0) {
                holder.add(list.get(ans) - index);
            } else {
                int right = list.get(ans) - index;
                int left = index - list.get(ans - 1);
                holder.add(Math.min(Math.abs(left), Math.abs(right)));
            }
        }
        return holder;
    }
}
