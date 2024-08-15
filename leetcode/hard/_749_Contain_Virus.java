package leetcode.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.
 *
 * The world is modeled as an m x n binary grid isInfected, where isInfected[i][j] == 0 represents uninfected cells, and isInfected[i][j] == 1 represents cells contaminated with the virus. A wall (and only one wall) can be installed between any two 4-directionally adjacent cells, on the shared boundary.
 *
 * Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall. Resources are limited. Each day, you can install walls around only one region (i.e., the affected area (continuous block of infected cells) that threatens the most uninfected cells the following night). There will never be a tie.
 *
 * Return the number of walls used to quarantine all the infected regions. If the world will become fully infected, return the number of walls used.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: isInfected = [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]
 * Output: 10
 * Explanation: There are 2 contaminated regions.
 * On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:
 *
 * On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.
 *
 * Example 2:
 *
 *
 * Input: isInfected = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: 4
 * Explanation: Even though there is only one cell saved, there are 4 walls built.
 * Notice that walls are only built on the shared boundary of two different cells.
 * Example 3:
 *
 * Input: isInfected = [[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]
 * Output: 13
 * Explanation: The region on the left only builds two new walls.
 *
 *
 * Constraints:
 *
 * m == isInfected.length
 * n == isInfected[i].length
 * 1 <= m, n <= 50
 * isInfected[i][j] is either 0 or 1.
 * There is always a contiguous viral region throughout the described process that will infect strictly more uncontaminated squares in the next round.
 *
 */

public class _749_Contain_Virus {
    class Region {
        Set<Integer> infected = new HashSet<>();
        Set<Integer> unInfected = new HashSet<>();
        int walls = 0;
    }

    public int containVirus(int[][] isInfected) {
        int ans = 0;
        int re = isInfected.length;
        int ce = isInfected[0].length;

        while (true) {
            List<Region> holder = new ArrayList<>();
            boolean[][] v = new boolean[re][ce];

            for (int r = 0; r < re; r++) {

                for (int c = 0; c < ce; c++) {

                    if (isInfected[r][c] == 1 && !v[r][c]) {
                        Region region = new Region();
                        getRegion(isInfected, region, re, ce, v, r, c);
                        holder.add(region);
                    }
                }
            }
            int indexOfMaxUnInfected = 0;
            int sizeOfMaxUnInfected = 0;
            // finding the Region that can infect maximum cells
            for (int i = 0; i < holder.size(); i++) {
                Region region = holder.get(i);

                if (region.unInfected.size() > sizeOfMaxUnInfected) {
                    sizeOfMaxUnInfected = region.unInfected.size();
                    indexOfMaxUnInfected = i;
                }
            }

            if (holder.size() == 0) {
                break;
            }
            // Bordering the Region
            Set<Integer> maxSet = holder.get(indexOfMaxUnInfected).infected;

            for (int rowCol : maxSet) {
                int r = rowCol / ce;
                int c = rowCol % ce;
                isInfected[r][c] = 2;
            }
            //counting walls
            ans += holder.get(indexOfMaxUnInfected).walls;
            //Expanding other Region
            for (int i = 0; i < holder.size(); i++) {

                if (i == indexOfMaxUnInfected) {
                    continue;
                }
                Region region = holder.get(i);
                Set<Integer> unInfected = region.unInfected;

                for (int rowCol : unInfected) {
                    int r = rowCol / ce;
                    int c = rowCol % ce;
                    isInfected[r][c] = 1;
                }
            }
        }
        return ans;
    }
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private void getRegion(int[][] isInfected, Region region, int re, int ce,
                           boolean[][] v, int r, int c) {

        if (r < 0 || c < 0 || r == re || c == ce || isInfected[r][c] == 2) {
            return;
        }

        if (isInfected[r][c] == 1) {

            if (v[r][c]) {
                return;
            }
            region.infected.add(r * ce + c);
        }

        if (isInfected[r][c] == 0) {
            region.unInfected.add(r * ce + c);
            region.walls++;
            return;
        }
        v[r][c] = true;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            getRegion(isInfected, region, re, ce, v, nr, nc);
        }
    }
}
