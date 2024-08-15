package leetcode.medium;

import java.util.*;

/**
 *
 *
 * There is a long and thin painting that can be represented by a number line. The painting was painted with multiple overlapping segments where each segment was painted with a unique color. You are given a 2D integer array segments, where segments[i] = [starti, endi, colori] represents the half-closed segment [starti, endi) with colori as the color.
 *
 * The colors in the overlapping segments of the painting were mixed when it was painted. When two or more colors mix, they form a new color that can be represented as a set of mixed colors.
 *
 * For example, if colors 2, 4, and 6 are mixed, then the resulting mixed color is {2,4,6}.
 * For the sake of simplicity, you should only output the sum of the elements in the set rather than the full set.
 *
 * You want to describe the painting with the minimum number of non-overlapping half-closed segments of these mixed colors. These segments can be represented by the 2D array painting where painting[j] = [leftj, rightj, mixj] describes a half-closed segment [leftj, rightj) with the mixed color sum of mixj.
 *
 * For example, the painting created with segments = [[1,4,5],[1,7,7]] can be described by painting = [[1,4,12],[4,7,7]] because:
 * [1,4) is colored {5,7} (with a sum of 12) from both the first and second segments.
 * [4,7) is colored {7} from only the second segment.
 * Return the 2D array painting describing the finished painting (excluding any parts that are not painted). You may return the segments in any order.
 *
 * A half-closed segment [a, b) is the section of the number line between points a and b including point a and not including point b.
 *
 *
 *
 * Example 1:
 *
 *       ------------
 *                   ------------
 *       --------------------------------
 *      ________________________________________________
 *       1   2   3   4   5   6   7   8   9
 *
 * Input: segments = [[1,4,5],[4,7,7],[1,7,9]]
 * Output: [[1,4,14],[4,7,16]]
 * Explanation: The painting can be described as follows:
 * - [1,4) is colored {5,9} (with a sum of 14) from the first and third segments.
 * - [4,7) is colored {7,9} (with a sum of 16) from the second and third segments.
 * Example 2:
 *       ------------------------
 *                           --------
 *                                   ---------
 *      ________________________________________________
 *       1   2   3   4   5   6   7   8   9   10
 *
 * Input: segments = [[1,7,9],[6,8,15],[8,10,7]]
 * Output: [[1,6,9],[6,7,24],[7,8,15],[8,10,7]]
 * Explanation: The painting can be described as follows:
 * - [1,6) is colored 9 from the first segment.
 * - [6,7) is colored {9,15} (with a sum of 24) from the first and second segments.
 * - [7,8) is colored 15 from the second segment.
 * - [8,10) is colored 7 from the third segment.
 * Example 3:
 *
 *       ------------
 *       ------------
 *                   ------------
 *                   ------------
 *      ________________________________________________
 *       1   2   3   4   5   6   7   8   9
 *
 * Input: segments = [[1,4,5],[1,4,7],[4,7,1],[4,7,11]]
 * Output: [[1,4,12],[4,7,12]]
 * Explanation: The painting can be described as follows:
 * - [1,4) is colored {5,7} (with a sum of 12) from the first and second segments.
 * - [4,7) is colored {1,11} (with a sum of 12) from the third and fourth segments.
 * Note that returning a single segment [1,7) is incorrect because the mixed color sets are different.
 *
 *
 * Constraints:
 *
 * 1 <= segments.length <= 2 * 104
 * segments[i].length == 3
 * 1 <= starti < endi <= 105
 * 1 <= colori <= 109
 * Each colori is distinct.
 *
 *
 */

public class _1943_Describe_the_Painting {
    //Line sweep
    public List<List<Long>> splitPainting(int[][] segments) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        List<List<Long>> ans = new ArrayList<>();

        for (int[] seg : segments) {
            max = Math.max(max, seg[1]);
            min = Math.min(min, seg[0]);
        }
        long[] holder = new long[max + 2];
        Set<Integer> criticalPoint = new HashSet<>();

        for (int[] seg : segments) {
            long prevVal[] = new long[] {holder[seg[0]], holder[seg[1]]};
            holder[seg[0]] += seg[2];
            holder[seg[1]] += -seg[2];
            // Checking the change of value to add critical points
            if (prevVal[0] != 0 && holder[seg[0]] == 0) {
                criticalPoint.add(seg[0]);
            }

            if (prevVal[1] != 0 && holder[seg[1]] == 0) {
                criticalPoint.add(seg[1]);
            }
        }

        for (int i = 1; i < holder.length; i++) {
            holder[i] += holder[i - 1];
        }
        int slow = min;
        int fast = min;
        long curr = holder[slow];

        while (fast <= max) {

            if (fast == max || curr != holder[fast] || criticalPoint.contains(fast)) {

                if (holder[slow] != 0) {
                    List<Long> temp = new ArrayList<>();
                    temp.add((long)slow);
                    temp.add((long)fast);
                    temp.add(curr);
                    ans.add(temp);
                }
                curr = holder[fast];
                slow = fast;
            }
            fast++;
        }
        return ans;
    }
    //=============================================================================================
    // Priority Queue Based
    public List<List<Long>> splitPainting1(int[][] segments) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int[] seg : segments) {
            int start = seg[0];
            int end = seg[1];
            int val = seg[2];
            pq.offer(new int[] {start, val});
            pq.offer(new int[] {end, -val});
        }
        List<List<Long>> ans = new ArrayList<>();
        int[] prev = null;
        long sum = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            if (prev != null && prev[0] != curr[0] && sum != 0) {
                List<Long> temp = new ArrayList<>();
                temp.add((long)prev[0]);
                temp.add((long)curr[0]);
                temp.add(sum);
                ans.add(temp);
            }
            sum += curr[1];
            prev = curr;
        }
        return ans;
    }
    //=============================================================================================
    // TreeMap based
    public List<List<Long>> splitPainting2(int[][] segments) {
        TreeMap<Integer, Long> map = new TreeMap<>();

        for (int[] seg : segments) {
            int start = seg[0];
            int end = seg[1];
            int val = seg[2];
            map.put(start, map.getOrDefault(start, 0L) + val);
            map.put(end, map.getOrDefault(end, 0L) - val);
        }
        List<List<Long>> ans = new ArrayList<>();
        int prevKey = -1;
        long prevSum = 0;
        long sum = 0;

        for (int key : map.keySet()) {
            long currSum = map.get(key);

            if (prevKey != -1 && sum != 0 && prevKey != key) {
                List<Long> temp = new ArrayList<>();
                temp.add((long)prevKey);
                temp.add((long)key);
                temp.add(sum);
                ans.add(temp);
            }
            sum += currSum;
            prevKey = key;
        }
        return ans;
    }
}
