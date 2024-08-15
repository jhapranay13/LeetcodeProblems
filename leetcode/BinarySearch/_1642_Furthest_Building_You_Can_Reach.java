package leetcode.BinarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
 *
 * You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
 *
 * While moving from building i to building i+1 (0-indexed),
 *
 * If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
 * If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
 * Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
 * Output: 4
 * Explanation: Starting at building 0, you can follow these steps:
 * - Go to building 1 without using ladders nor bricks since 4 >= 2.
 * - Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
 * - Go to building 3 without using ladders nor bricks since 7 >= 6.
 * - Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
 * It is impossible to go beyond building 4 because you do not have any more bricks or ladders.
 * Example 2:
 *
 * Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
 * Output: 7
 * Example 3:
 *
 * Input: heights = [14,3,19,3], bricks = 17, ladders = 0
 * Output: 3
 *
 * Constraints:
 *
 * 1 <= heights.length <= 10^5
 * 1 <= heights[i] <= 10^6
 * 0 <= bricks <= 10^9
 * 0 <= ladders <= heights.length
 */

public class _1642_Furthest_Building_You_Can_Reach {
    //Binary Search Solution
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int lo = 0;
        int hi = heights.length - 1;
        int ans = 0;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (isReachable(heights, bricks, ladders, pivot)) {
                lo = pivot + 1;
                ans = pivot;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }

    private boolean isReachable(int[] heights, int bricks, int ladders, int index) {
        List<Integer> diffs = new ArrayList<>();

        for (int i = 0; i < index; i++) {
            int diff = heights[i + 1] - heights[i];

            if (diff <= 0) {
                continue;
            }
            diffs.add(diff);
        }
        Collections.sort(diffs);

        for(int diff : diffs) {

            if (bricks >= diff) {
                bricks -= diff;
            } else if (ladders > 0) {
                ladders--;
            } else {
                return false;
            }
        }
        return true;
    }
    //=============================================================================================
    //PriorityQueue Solution
    public int furthestBuilding1(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];

            if (diff <= 0) {
                continue;
            }
            bricks -= diff;
            pq.offer(diff);

            if (ladders == 0 && bricks < 0) {
                return i;
            }
            //Replacing the largest brick use with ladder for now
            if (bricks < 0) {
                bricks += pq.poll();
                ladders--;
            }
        }
        return heights.length - 1;
    }
}
