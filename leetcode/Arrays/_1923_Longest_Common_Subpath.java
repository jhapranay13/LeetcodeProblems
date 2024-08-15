package leetcode.Arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 * There is a country of n cities numbered from 0 to n - 1. In this country, there is a road connecting every pair of cities.
 *
 * There are m friends numbered from 0 to m - 1 who are traveling through the country. Each one of them will take a path consisting of some cities. Each path is represented by an integer array that contains the visited cities in order. The path may contain a city more than once, but the same city will not be listed consecutively.
 *
 * Given an integer n and a 2D integer array paths where paths[i] is an integer array representing the path of the ith friend, return the length of the longest common subpath that is shared by every friend's path, or 0 if there is no common subpath at all.
 *
 * A subpath of a path is a contiguous sequence of cities within that path.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, paths = [[0,1,2,3,4],
 *                        [2,3,4],
 *                        [4,0,1,2,3]]
 * Output: 2
 * Explanation: The longest common subpath is [2,3].
 * Example 2:
 *
 * Input: n = 3, paths = [[0],[1],[2]]
 * Output: 0
 * Explanation: There is no common subpath shared by the three paths.
 * Example 3:
 *
 * Input: n = 5, paths = [[0,1,2,3,4],
 *                        [4,3,2,1,0]]
 * Output: 1
 * Explanation: The possible longest common subpaths are [0], [1], [2], [3], and [4]. All have a length of 1.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * m == paths.length
 * 2 <= m <= 10^5
 * sum(paths[i].length) <= 10^5
 * 0 <= paths[i][j] < n
 * The same city is not listed multiple times consecutively in paths[i].
 *
 */

public class _1923_Longest_Common_Subpath {
    public int longestCommonSubpath(int n, int[][] paths) {
        int lo = 1;
        int hi = paths[0].length;
        // hi cannot be greater than the minimum path length
        for (int[] path : paths) {
            hi = Math.min(hi, path.length);
        }
        int ans = 0;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (pivot == 0) {
                break;
            }

            if (isCommon(paths, pivot)) {
                ans = pivot;
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }
    private long MOD = (long)(Math.pow(10,11) + 3);
    // Rolling Hash rabin karp
    private boolean isCommon(int[][] paths, int length) {
        Map<Long, Integer> hashCounterMap = new HashMap<>();

        for (int[] path : paths) {
            Set<Long> hashes = getRollingHash(path, length);

            for (long hashVal : hashes) {
                hashCounterMap.put(hashVal, hashCounterMap.getOrDefault(hashVal, 0) + 1);
            }
        }
        // If every path contains the hash
        for (long hashVal : hashCounterMap.keySet()) {

            if (hashCounterMap.get(hashVal) == paths.length) {
                return true;
            }
        }
        return false;
    }

    private Set<Long> getRollingHash(int[] path, int length) {
        Set<Long> hashes = new HashSet<>();
        long[] pow = new long[length];
        long prime = 100003;
        pow[length - 1] = 1;


        for (int i = length - 2; i >= 0 ; i--) {

            pow[i] = ((pow[i + 1]) * prime) % MOD;
        }
        long hashVal = 0;

        for (int i = 0; i < length; i++) {
            hashVal = (hashVal + (path[i] *  pow[i]) % MOD) % MOD;
        }
        hashes.add(hashVal);

        for (int i = length; i < path.length; i++) {
            hashVal -= (path[i - length] * pow[0]) % MOD;
            hashVal = (hashVal * prime) % MOD;
            long temp = (long)path[i];

            if (temp < 0) {
                temp = (temp + MOD) % MOD;
            }
            long tempHashVal = hashVal + temp;

            if (tempHashVal < 0) {
                tempHashVal = (tempHashVal + MOD) % MOD;
            }
            hashVal = tempHashVal;
            hashes.add(hashVal);
        }
        return hashes;
    }
}
