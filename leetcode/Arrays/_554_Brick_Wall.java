package leetcode.Arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 * There is a rectangular brick wall in front of you with n rows of bricks. The ith row has some number of bricks each of the same height (i.e., one unit) but they can be of different widths. The total width of each row is the same.
 *
 * Draw a vertical line from the top to the bottom and cross the least bricks. If your line goes through the edge of a brick, then the brick is not considered as crossed. You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
 *
 * Given the 2D array wall that contains the information about the wall, return the minimum number of crossed bricks after drawing such a vertical line.
 *
 *
 *
 * Example 1:
 *
 *                Line will start from here as this will cut only 2 bricks
 *          ________________________________V_______________
 *          |________|______________|______________|_______|
 *          |_______________________|_______|______________|
 *          |________|______________________|______________|
 *          |_______________|______________________________|
 *          |_______________________|_______|______________|
 *          |________|______________________|______|_______|
 *
 * Input: wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
 * Output: 2
 * Example 2:
 *
 * Input: wall = [[1],[1],[1]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * n == wall.length
 * 1 <= n <= 104
 * 1 <= wall[i].length <= 104
 * 1 <= sum(wall[i].length) <= 2 * 104
 * sum(wall[i]) is the same for each row i.
 * 1 <= wall[i][j] <= 231 - 1
 *
 *
 */

public class _554_Brick_Wall {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> positionMap = new HashMap<>();
        // the idea is to get the position that is the end.
        // the number of bricks that are crossed will be.
        // total rows - count of end positions
        // taking minimum of that will get the answer

        for (List<Integer> bricks : wall) {
            int endPos = 0;

            for (int i = 0; i < bricks.size() - 1; i++) {
                endPos += bricks.get(i);
                positionMap.put(endPos, positionMap.getOrDefault(endPos, 0) + 1);
            }
        }
        int ans = wall.size();

        for (Map.Entry<Integer, Integer> posCount : positionMap.entrySet()) {
            int bricksIntersect = wall.size() - posCount.getValue();
            ans = Math.min(ans, bricksIntersect);
        }
        return ans;
    }
}
