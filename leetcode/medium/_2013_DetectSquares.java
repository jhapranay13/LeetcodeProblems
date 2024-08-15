package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * ou are given a stream of points on the X-Y plane. Design an algorithm that:
 *
 * Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
 * Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query point form an axis-aligned square with positive area.
 * An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.
 *
 * Implement the DetectSquares class:
 *
 * DetectSquares() Initializes the object with an empty data structure.
 * void add(int[] point) Adds a new point point = [x, y] to the data structure.
 * int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
 * [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
 * Output
 * [null, null, null, null, 1, 0, null, 2]
 *
 * Explanation
 * DetectSquares detectSquares = new DetectSquares();
 * detectSquares.add([3, 10]);
 * detectSquares.add([11, 2]);
 * detectSquares.add([3, 2]);
 * detectSquares.count([11, 10]); // return 1. You can choose:
 *                                //   - The first, second, and third points
 * detectSquares.count([14, 8]);  // return 0. The query point cannot form a square with any points in the data structure.
 * detectSquares.add([11, 2]);    // Adding duplicate points is allowed.
 * detectSquares.count([11, 10]); // return 2. You can choose:
 *                                //   - The first, second, and third points
 *                                //   - The first, third, and fourth points
 *
 *
 * Constraints:
 *
 * point.length == 2
 * 0 <= x, y <= 1000
 * At most 3000 calls in total will be made to add and count.
 *
 */

public class _2013_DetectSquares {
    class DetectSquares {
        Map<Integer, Map<Integer, Integer>> rowColCountMap;

        public DetectSquares() {
            this.rowColCountMap = new HashMap<>();
        }

        public void add(int[] point) {
            if (!rowColCountMap.containsKey(point[0])) {
                rowColCountMap.put(point[0], new HashMap<>());
            }
            Map<Integer, Integer> colCountMap = rowColCountMap.get(point[0]);
            colCountMap.put(point[1], colCountMap.getOrDefault(point[1], 0) + 1);
        }

        public int count(int[] point) {
            int r1 = point[0];
            int c1 = point[1];
            int ans = 0;
            Map<Integer, Integer> colMapRow1 = rowColCountMap.get(r1);
            Map<Integer, Map<Integer, Integer>> r = rowColCountMap;
            if (colMapRow1 == null) {
                return ans;
            }

            for (int c2 : colMapRow1.keySet()) {
                int distance = Math.abs(c1 - c2);
                int count1 = colMapRow1.get(c2);

                if (distance > 0) {
                    //checking for upper line
                    //For square both the colmuns should be present in upper row as well
                    if (rowColCountMap.containsKey(r1 + distance) &&
                            rowColCountMap.get(r1 + distance).containsKey(c2) &&
                            rowColCountMap.get(r1 + distance).containsKey(c1)) {
                        int r2 = r1 + distance;
                        int count2 = rowColCountMap.get(r2).get(c2);
                        int count3 = rowColCountMap.get(r2).get(c1);
                        ans += count1 * count2 * count3;
                    }
                    //checking for Lower line
                    //For square both the colmuns should be present in lower row as well
                    if (rowColCountMap.containsKey(r1 - distance) &&
                            rowColCountMap.get(r1 - distance).containsKey(c2) &&
                            rowColCountMap.get(r1 - distance).containsKey(c1)) {
                        int r2 = r1 - distance;
                        int count2 = rowColCountMap.get(r2).get(c2);
                        int count3 = rowColCountMap.get(r2).get(c1);
                        ans += count1 * count2 * count3;
                    }
                }
            }
            return ans;
        }
    }

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */
}
