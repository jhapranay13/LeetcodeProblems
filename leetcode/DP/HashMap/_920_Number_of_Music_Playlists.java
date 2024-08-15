package leetcode.DP.HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Your music player contains n different songs. You want to listen to goal songs (not necessarily different) during your trip. To avoid boredom, you will create a playlist so that:
 *
 * Every song is played at least once.
 * A song can only be played again only if k other songs have been played.
 * Given n, goal, and k, return the number of possible playlists that you can create. Since the answer can be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, goal = 3, k = 1
 * Output: 6
 * Explanation: There are 6 possible playlists: [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], and [3, 2, 1].
 * Example 2:
 *
 * Input: n = 2, goal = 3, k = 0
 * Output: 6
 * Explanation: There are 6 possible playlists: [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], and [1, 2, 2].
 * Example 3:
 *
 * Input: n = 2, goal = 3, k = 1
 * Output: 2
 * Explanation: There are 2 possible playlists: [1, 2, 1] and [2, 1, 2].
 *
 *
 * Constraints:
 *
 * 0 <= k < n <= goal <= 100
 *
 */

public class _920_Number_of_Music_Playlists {
    int mod = 1000000007;
    public int numMusicPlaylists(int n, int goal, int k) {
        Map<String, Integer> memo = new HashMap<>();
        Map<Integer, Integer> lastIndex = new HashMap<>();
        return recur(n, goal, k, lastIndex, 0, memo);
    }

    private int recur(int numOfSongs, int goal, int k, Map<Integer, Integer> lastIndex, int index,
                      Map<String, Integer> memo) {

        if (index == goal) {
            // Since every song should be played atleast once
            if (lastIndex.size() == numOfSongs) {
                return 1;
            }
            return 0;
        }
        String key = lastIndex.size()+ "|" + index;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int ans = 0;

        for (int i = 1; i <= numOfSongs; i++) {
            Integer prevIndex = lastIndex.get(i);

            if (prevIndex != null) {

                if (index - prevIndex - 1 >= k) {
                    lastIndex.put(i, index);
                    ans = (ans + recur(numOfSongs, goal, k, lastIndex, index + 1, memo)) % mod;
                    lastIndex.put(i, prevIndex);
                }
            } else {
                lastIndex.put(i, index);
                ans = (ans + recur(numOfSongs, goal, k, lastIndex, index + 1, memo)) % mod;
                lastIndex.remove(i);
            }
        }
        memo.put(key, ans);
        return ans;
    }
}
