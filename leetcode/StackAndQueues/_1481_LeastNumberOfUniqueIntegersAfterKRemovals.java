package leetcode.StackAndQueues;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [5,5,4], k = 1
 * Output: 1
 * Explanation: Remove the single 4, only 5 is left.
 * Example 2:
 * Input: arr = [4,3,1,1,3,3,2], k = 3
 * Output: 2
 * Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^9
 * 0 <= k <= arr.length
 *
 */

public class _1481_LeastNumberOfUniqueIntegersAfterKRemovals {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int num : arr) {
            int freq = freqMap.getOrDefault(num, 0);
            freqMap.put(num, ++freq);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                return freqMap.get(x) - freqMap.get(y);
            }
        });

        for (int num : freqMap.keySet()) {
            pq.add(num);
        }

        while (!pq.isEmpty() && k-- > 0) {
            int num = pq.poll();

            if (freqMap.get(num) == 1) {
                freqMap.remove(num);
            } else {
                freqMap.put(num, freqMap.get(num) - 1);
                pq.offer(num);
            }
        }
        return pq.size();
    }
}
