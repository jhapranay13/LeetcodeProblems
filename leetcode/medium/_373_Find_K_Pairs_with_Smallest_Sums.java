package leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 *
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 *
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3:
 *
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [[1,3],[2,3]]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 10^5
 * -109 <= nums1[i], nums2[i] <= 10^9
 * nums1 and nums2 both are sorted in ascending order.
 * 1 <= k <= 10^4
 *
 */

public class _373_Find_K_Pairs_with_Smallest_Sums {
    // can be solved in n^2 with priority queue
    // this will be k log k
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                a[0] + a[1] - (b[0] + b[1]));
        for (int i = 0; i < nums1.length && i < k; i++) {
            pq.offer(new int[] {nums1[i], nums2[0], 0});
        }
        List<List<Integer>> ans = new ArrayList<>();

        while (k-- >0 && !pq.isEmpty()) {
            int[] data = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(data[0]);
            list.add(data[1]);
            int indexList2 = data[2];
            ans.add(list);

            if (indexList2 == nums2.length - 1) {
                continue;
            }
            pq.offer(new int[] {data[0], nums2[indexList2 + 1], indexList2 + 1});
        }
        return ans;
    }
}
