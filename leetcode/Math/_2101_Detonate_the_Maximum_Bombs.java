package leetcode.Math;

import java.util.*;

/**
 *
 * You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. This area is in the shape of a circle with the center as the location of the bomb.
 *
 * The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.
 *
 * You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.
 *
 * Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: bombs = [[2,1,3],[6,1,4]]
 * Output: 2
 * Explanation:
 * The above figure shows the positions and ranges of the 2 bombs.
 * If we detonate the left bomb, the right bomb will not be affected.
 * But if we detonate the right bomb, both bombs will be detonated.
 * So the maximum bombs that can be detonated is max(1, 2) = 2.
 * Example 2:
 *
 *
 * Input: bombs = [[1,1,5],[10,10,5]]
 * Output: 1
 * Explanation:
 * Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.
 * Example 3:
 *
 *
 * Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
 * Output: 5
 * Explanation:
 * The best bomb to detonate is bomb 0 because:
 * - Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
 * - Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
 * - Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
 * Thus all 5 bombs are detonated.
 *
 *
 * Constraints:
 *
 * 1 <= bombs.length <= 100
 * bombs[i].length == 3
 * 1 <= xi, yi, ri <= 10^5
 *
 */

public class _2101_Detonate_the_Maximum_Bombs {
    //BFS
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> blastRadius = new HashMap<>();

        for (int i = 0; i < bombs.length; i++) {

            for (int j = 0; j < bombs.length; j++) {

                if (i == j) {
                    continue;
                }

                if (isBombWithInRange(bombs, i, j)) {
                    List<Integer> child = blastRadius.getOrDefault(i, new ArrayList<>());
                    child.add(j);
                    blastRadius.put(i, child);
                }
            }
        }
        int ans = 0;

        for (int i = 0; i < bombs.length; i++) {
            boolean[] v = new boolean[bombs.length];
            ans = Math.max(ans, bfs(blastRadius, v, i));
        }
        return ans;
    }

    private int bfs(Map<Integer, List<Integer>> blastRadius, boolean[] v, int index) {
        int ans = 0;
        Deque<Integer> q = new LinkedList<>();
        v[index] = true;
        q.offer(index);

        while(!q.isEmpty()) {
            int size = q.size();
            ans += size;

            while(size-- > 0) {
                int curr = q.poll();
                List<Integer> child = blastRadius.getOrDefault(curr, new ArrayList<>());

                for (int next : child) {
                    if (!v[next]) {
                        q.offer(next);
                        v[next] = true;
                    }
                }
            }
        }
        return ans;
    }

    public boolean isBombWithInRange(int[][] bombs, int currentBombNumber, int bombToCheck){
        int distance = (int)(Math.pow((bombs[bombToCheck][0] - bombs[currentBombNumber][0]),2) +
                Math.pow((bombs[bombToCheck][1] - bombs[currentBombNumber][1]),2) -
                Math.pow(bombs[currentBombNumber][2],2));

        if(distance <=0){
            return true;
        }

        return false;
    }
    //=============================================================================================
    //DFS
    public int maximumDetonation1(int[][] bombs) {
        Map<Integer, List<Integer>> blastRadius = new HashMap<>();

        for (int i = 0; i < bombs.length; i++) {

            for (int j = 0; j < bombs.length; j++) {

                if (i == j) {
                    continue;
                }

                if (isBombWithInRange(bombs, i, j)) {
                    List<Integer> child = blastRadius.getOrDefault(i, new ArrayList<>());
                    child.add(j);
                    blastRadius.put(i, child);
                }
            }
        }
        int ans = 0;

        for (int i = 0; i < bombs.length; i++) {
            boolean[] v = new boolean[bombs.length];
            ans = Math.max(ans, dfs(blastRadius, v, i));
        }
        return ans;
    }

    private int dfs(Map<Integer, List<Integer>> blastRadius, boolean[] v, int index) {
        List<Integer> child = blastRadius.getOrDefault(index, new ArrayList<>());
        int ans = 1;
        v[index] = true;

        for (int next : child) {

            if (!v[next]) {
                ans += dfs(blastRadius, v, next);
            }
        }
        return ans;
    }
}
