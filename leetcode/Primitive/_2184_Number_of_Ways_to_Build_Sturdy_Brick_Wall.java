package leetcode.Primitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *You are given integers height and width which specify the dimensions of a brick wall you are building. You are also given a 0-indexed array of unique integers bricks, where the ith brick has a height of 1 and a width of bricks[i]. You have an infinite supply of each type of brick and bricks may not be rotated.
 *
 * Each row in the wall must be exactly width units long. For the wall to be sturdy, adjacent rows in the wall should not join bricks at the same location, except at the ends of the wall.
 *
 * Return the number of ways to build a sturdy wall. Since the answer may be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *       ___________          ___________           _____________
 *      |_______|__|         |___|______|          |_________|__|
 *      |__|_______|         |_______|__|          |_________|__|
 *
 *          correct              correct                incorrect
 *
 *
 * Input: height = 2, width = 3, bricks = [1,2]
 * Output: 2
 * Explanation:
 * The first two walls in the diagram show the only two ways to build a sturdy brick wall.
 * Note that the third wall in the diagram is not sturdy because adjacent rows join bricks 2 units from the left.
 * Example 2:
 *
 * Input: height = 1, width = 1, bricks = [5]
 * Output: 0
 * Explanation:
 * There are no ways to build a sturdy wall because the only type of brick we have is longer than the width of the wall.
 *
 *
 * Constraints:
 *
 * 1 <= height <= 100
 * 1 <= width <= 10
 * 1 <= bricks.length <= 10
 * 1 <= bricks[i] <= 10
 * All the values of bricks are unique.
 *
 *
 */

public class _2184_Number_of_Ways_to_Build_Sturdy_Brick_Wall {

    //Top Down Approach
    public int buildWall(int height, int width, int[] bricks) {
        List<Integer> wallsConfig = new ArrayList<>();
        getConfig(wallsConfig, bricks, width, 0);
        Integer[][] memo = new Integer[height + 1][1 << width];
        return recur(wallsConfig, height, 0, memo);
    }

    private void getConfig(List<Integer> wallsConfig, int[] bricks, int width, int config) {

        if (width == 0) {
            wallsConfig.add(config);
            return;
        }

        for (int brick : bricks) {

            if (width - brick > 0) {
                int tempConfig = 1 << (width - brick);
                getConfig(wallsConfig, bricks, width - brick, tempConfig | config);
            } else if (width - brick == 0) {
                //Since we don't need the outermost boundry
                //We only need to cosider the middle portion. So nott adding it to config
                getConfig(wallsConfig, bricks, width - brick, config);
            }
        }
    }

    private int recur(List<Integer> wallsConfig, int height, int prevConfig, Integer[][] memo) {

        if (height == 0) {
            return 1;
        }

        if (memo[height][prevConfig] != null) {
            return memo[height][prevConfig];
        }
        int ans = 0;

        for (int config : wallsConfig) {

            if ((prevConfig & config) > 0) {
                continue;
            }
            ans = (ans + recur(wallsConfig, height - 1, config, memo)) % 1000000007;

        }
        return memo[height][prevConfig] = ans;
    }

    //=============================================================================================
    //Another top down Merging both generation and the recurance
    public int buildWall2(int height, int width, int[] bricks) {
        Integer[][] memo = new Integer[height + 1][1 << width];
        return recur(bricks, width, height, 0, 0, 0, memo);
    }

    public int recur(int[] bricks, int width, int height, int currWidth,
                     int prevBrickConfig, int currBrickConfig, Integer[][] memo) {

        if (memo[height][prevBrickConfig] != null) {
            return memo[height][prevBrickConfig];
        }
        if (height == 0) {
            return 1;
        }

        if (width == currWidth) {
            return recur(bricks, width, height - 1, 0, currBrickConfig, 0, memo);
        }
        int ans = 0;

        for (int brick : bricks) {
            int tempWidth = currWidth + brick;

            if (tempWidth > width) {
                continue;
            }
            int tempConfig = tempWidth < width ? ((1 << tempWidth) | currBrickConfig) : currBrickConfig;

            if ((tempConfig & prevBrickConfig) > 0) {
                continue;
            }
            ans = (ans + recur(bricks, width, height, tempWidth, prevBrickConfig, tempConfig, memo)) % 1000000007;
        }
        //We are saving the result only when the prev layer has been built wall has been built
        //We are using just two states height and prevBrick config because all other states are
        //used in generating the layer. Once layer is generated then saving the result for
        //which logically only two states have changed.
        if (currBrickConfig == 0)
            memo[height][prevBrickConfig] = ans;
        return ans;
    }
    //=============================================================================================
    //Another Top down using HashMap. TLE after last test case
    public int buildWall3(int height, int width, int[] bricks) {
        Map<String, Integer> memo = new HashMap<>();
        return recur(bricks, width, height, 0, 0, 0, memo);
    }

    public int recur(int[] bricks, int width, int height, int currWidth,
                     int prevBrickConfig, int currBrickConfig, Map<String, Integer> memo) {
        String key = height + "|" + prevBrickConfig;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (height == 0) {
            return 1;
        }

        if (width == currWidth) {
            return recur(bricks, width, height - 1, 0, currBrickConfig, 0, memo);
        }
        int ans = 0;

        for (int brick : bricks) {
            int tempWidth = currWidth + brick;

            if (tempWidth > width) {
                continue;
            }
            int tempConfig = tempWidth < width ? ((1 << tempWidth) | currBrickConfig) : currBrickConfig;

            if ((tempConfig & prevBrickConfig) > 0) {
                continue;
            }
            ans = (ans + recur(bricks, width, height, tempWidth, prevBrickConfig, tempConfig, memo)) % 1000000007;
        }
        //We are saving the result only when the wall has been built
        if (currBrickConfig == 0)
            memo.put(key, ans);
        return ans;
    }
}
