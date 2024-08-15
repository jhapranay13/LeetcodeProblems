package leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
 *
 * Return true if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited. Return false otherwise.
 *
 *
 *
 * Example 1:
 *                E
 *              _______
 *         N   |      |    S
 *             |      |
 *
 * Input: path = "NES"
 * Output: false
 * Explanation: Notice that the path doesn't cross any point more than once.
 * Example 2:
 *              E
 *           _________
 *      N   |         |   S
 *  __ _____|_________|
 *       W       W
 *
 * Input: path = "NESWW"
 * Output: true
 * Explanation: Notice that the path visits the origin twice.
 *
 *
 * Constraints:
 *
 * 1 <= path.length <= 10^4
 * path[i] is either 'N', 'S', 'E', or 'W'.
 *
 */

public class _1496_Path_Crossing {
    int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public boolean isPathCrossing(String path) {
        Map<Character, Integer> charIndexMap = new HashMap<>();
        charIndexMap.put('N', 0);
        charIndexMap.put('E', 1);
        charIndexMap.put('S', 2);
        charIndexMap.put('W', 3);
        int prevx = 0;
        int prevy = 0;
        Set<String> v = new HashSet<>();

        for (char ch : path.toCharArray()) {
            int[] dir = dirs[charIndexMap.get(ch)];
            v.add(prevx + "|" + prevy);
            int nextx = prevx + dir[0];
            int nexty = prevy + dir[1];

            if (v.contains(nextx + "|" + nexty)) {
                return true;
            }
            prevx = nextx;
            prevy = nexty;
        }
        return false;
    }
}
