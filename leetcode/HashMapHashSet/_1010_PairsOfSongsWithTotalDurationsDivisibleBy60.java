package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 *ou are given a list of songs where the ith song has a duration of time[i] seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 *
 *
 * Example 1:
 *
 * Input: time = [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 * Example 2:
 *
 * Input: time = [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 *
 *
 * Constraints:
 *
 * 1 <= time.length <= 6 * 104
 * 1 <= time[i] <= 500
 *
 */

public class _1010_PairsOfSongsWithTotalDurationsDivisibleBy60 {
    //=============================================================================================
    //Similar to two sum
    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> remainderCountMap = new HashMap<>();
        int ans = 0;

        for (int num : time) {
            int remainder = num % 60;
            ans += remainderCountMap.getOrDefault(60 - remainder, 0);

            if (remainder == 0) {
                remainderCountMap.put(60, remainderCountMap.getOrDefault(60, 0) + 1);
            } else {
                remainderCountMap.put(remainder, remainderCountMap.getOrDefault(remainder, 0) + 1);
            }
        }
        return ans;
    }
}