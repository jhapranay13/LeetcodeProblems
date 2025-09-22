package leetcode.medium;

/**
 *
 *
 * There is a circle of red and blue tiles. You are given an array of integers colors and an integer k. The color of tile i is represented by colors[i]:
 *
 * colors[i] == 0 means that tile i is red.
 * colors[i] == 1 means that tile i is blue.
 * An alternating group is every k contiguous tiles in the circle with alternating colors (each tile in the group except the first and last one has a different color from its left and right tiles).
 *
 * Return the number of alternating groups.
 *
 * Note that since colors represents a circle, the first and the last tiles are considered to be next to each other.
 *
 *
 *
 * Example 1:
 *
 * Input: colors = [0,1,0,1,0], k = 3
 *
 * Output: 3
 *
 * Explanation:
 *
 *                           0(0) --->  1(1)
 *                           ^              |
 *                           |              V
 *                           |             0(2)
 *                           |             |
 *                          1(3) <--- 0(4) V
 *
 *
 * Alternating groups:
 *
 *
 *
 * Example 2:
 *
 * Input: colors = [0,1,0,0,1,0,1], k = 6
 *
 * Output: 2
 *
 * Explanation:
 *
 *
 *
 * Alternating groups:
 *
 *
 *
 * Example 3:
 *
 * Input: colors = [1,1,0,1], k = 4
 *
 * Output: 0
 *
 * Explanation:
 *
 *
 *
 *
 *
 * Constraints:
 *
 * 3 <= colors.length <= 105
 * 0 <= colors[i] <= 1
 * 3 <= k <= colors.length
 *
 *
 */

public class _3208_Alternating_Groups_II {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int ans = 0;
        int slow = 0;
        int fast = 0;
        // extending it to be circular
        // colors.length + k - 1 to avoid the end index of first group
        while (fast < (colors.length + k - 1)) {
            int tFast = fast % colors.length;
            int prev = fast == 0 ? -1 : (fast - 1) % colors.length;

            if (prev != -1 && colors[prev] == colors[tFast]) {
                slow = fast;
            }

            if (fast - slow + 1 == k) {
                ans++;
                slow++;
            }
            fast++;
        }
        return ans;
    }
}
