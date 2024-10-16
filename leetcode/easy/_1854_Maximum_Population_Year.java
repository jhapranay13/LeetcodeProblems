package leetcode.easy;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.
 *
 * The population of some year x is the number of people alive during that year. The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted in the year that they die.
 *
 * Return the earliest year with the maximum population.
 *
 *
 *
 * Example 1:
 *
 * Input: logs = [[1993,1999],[2000,2010]]
 * Output: 1993
 * Explanation: The maximum population is 1, and 1993 is the earliest year with this population.
 * Example 2:
 *
 * Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
 * Output: 1960
 * Explanation:
 * The maximum population is 2, and it had happened in years 1960 and 1970.
 * The earlier year between them is 1960.
 *
 *
 * Constraints:
 *
 * 1 <= logs.length <= 100
 * 1950 <= birthi < deathi <= 2050
 *
 */

public class _1854_Maximum_Population_Year {
    // Line sweep Algo
    public int maximumPopulation(int[][] logs) {
        TreeMap<Integer, Integer> lineHolder = new TreeMap<>();

        for (int[] log : logs) {
            lineHolder.put(log[0], lineHolder.getOrDefault(log[0], 0) + 1);
            lineHolder.put(log[1], lineHolder.getOrDefault(log[1], 0) - 1);
        }
        int ans = 0;
        int max = 0;
        int temp = 0;

        for (Map.Entry<Integer, Integer> entry : lineHolder.entrySet()) {
            int year = entry.getKey();
            int val = entry.getValue();
            temp += val;

            if (temp > max) {
                max = temp;
                ans = year;
            }
        }
        return ans;
    }
}