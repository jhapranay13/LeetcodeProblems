package leetcode.Sorting;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.
 *
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * Example 2:
 *
 * Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
 * Output: [1,1]
 *
 *
 * Constraints:
 *
 * nums.length == k
 * 1 <= k <= 3500
 * 1 <= nums[i].length <= 50
 * -105 <= nums[i][j] <= 10^5
 * nums[i] is sorted in non-decreasing order.
 *
 */

public class _632_Smallest_Range_Covering_Elements_from_K_Lists {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {
                return x[0] - y[0];
            }
        });
        int max = 0;

        for(int i = 0; i < nums.size(); i++) {
            pq.offer(new int[] {nums.get(i).get(0), i, 0});
            max = Math.max(max, nums.get(i).get(0));
        }
        int start = 0;
        int end = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            int[] val = pq.poll();
            int row = val[1];
            int index = val[2];

            if (end - start > max - val[0]) {
                end = max;
                start = val[0];
            }
            index++;

            if (index == nums.get(row).size()) {
                break;
            }
            max = Math.max(max, nums.get(row).get(index));
            pq.offer(new int[] {nums.get(row).get(index), row, index});
        }
        return new int[] {start, end};
    }
}
