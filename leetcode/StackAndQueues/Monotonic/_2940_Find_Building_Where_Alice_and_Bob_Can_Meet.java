package leetcode.StackAndQueues.Monotonic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _2940_Find_Building_Where_Alice_and_Bob_Can_Meet {

    /**
     *
     * You are given a 0-indexed array heights of positive integers, where heights[i] represents the height of the ith building.
     *
     * If a person is in building i, they can move to any other building j if and only if i < j and heights[i] < heights[j].
     *
     * You are also given another array queries where queries[i] = [ai, bi]. On the ith query, Alice is in building ai while Bob is in building bi.
     *
     * Return an array ans where ans[i] is the index of the leftmost building where Alice and Bob can meet on the ith query. If Alice and Bob cannot move to a common building on query i, set ans[i] to -1.
     *
     *
     *
     * Example 1:
     *
     * Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
     * Output: [2,5,-1,5,2]
     * Explanation: In the first query, Alice and Bob can move to building 2 since heights[0] < heights[2] and heights[1] < heights[2].
     * In the second query, Alice and Bob can move to building 5 since heights[0] < heights[5] and heights[3] < heights[5].
     * In the third query, Alice cannot meet Bob since Alice cannot move to any other building.
     * In the fourth query, Alice and Bob can move to building 5 since heights[3] < heights[5] and heights[4] < heights[5].
     * In the fifth query, Alice and Bob are already in the same building.
     * For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
     * For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.
     * Example 2:
     *
     * Input: heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
     * Output: [7,6,-1,4,6]
     * Explanation: In the first query, Alice can directly move to Bob's building since heights[0] < heights[7].
     * In the second query, Alice and Bob can move to building 6 since heights[3] < heights[6] and heights[5] < heights[6].
     * In the third query, Alice cannot meet Bob since Bob cannot move to any other building.
     * In the fourth query, Alice and Bob can move to building 4 since heights[3] < heights[4] and heights[0] < heights[4].
     * In the fifth query, Alice can directly move to Bob's building since heights[1] < heights[6].
     * For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
     * For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.
     *
     *
     *
     * Constraints:
     *
     * 1 <= heights.length <= 5 * 104
     * 1 <= heights[i] <= 109
     * 1 <= queries.length <= 5 * 104
     * queries[i] = [ai, bi]
     * 0 <= ai, bi <= heights.length - 1
     *
     */
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int[] ans  = new int[queries.length];
        Arrays.fill(ans, -1);
        int index = 0;
        List<int[]> queryList = new ArrayList<>();

        for (int[] query : queries) {
            int a = query[0];
            int b = query[1];

            if (a == b) {
                ans[index] = a;
            } else {

                if (heights[a] > heights[b]) {

                    if (a < b) {
                        queryList.add(new int[] {heights[a], index, b});
                    } else {
                        // Since greater height is towards right of smaller side that would be the answer
                        ans[index] = a;
                    }

                } else if (heights[a] < heights[b]) {

                    if (a < b) {
                        // Since greater height is towards right of smaller side that would be the answer
                        ans[index] = b;
                    } else {
                        queryList.add(new int[] {heights[b], index, a});
                    }
                } else {
                    // trying to figure out which index to start from which is the last value of the array
                    if (a < b) {
                        queryList.add(new int[] {heights[b], index, b});
                    } else {
                        queryList.add(new int[] {heights[b], index, a});
                    }
                }
            }
            index++;
        }
        // sorting from where the search has to start
        Collections.sort(queryList, (a, b) -> a[2] - b[2]);
        index = heights.length - 1;
        List<int[]> mono = new ArrayList<>();

        for (int i = queryList.size() - 1; i >= 0; i--) {
            int hgt = queryList.get(i)[0];
            int ansIndex = queryList.get(i)[1];
            int startIndex = queryList.get(i)[2];

            while (startIndex <= index) {
                int monoLast = mono.size() - 1;

                while(mono.size() > 0 && heights[index] >= mono.get(monoLast)[0]) {
                    mono.remove(monoLast);
                    monoLast--;
                }
                mono.add(new int[] {heights[index], index--});
            }

            int position = bsGreaterThan(mono, hgt);

            if (position != -1) {
                ans[ansIndex] = mono.get(position)[1];
            }
        }
        return ans;
    }

    private int bsGreaterThan(List<int[]> mono, int target) {
        int lo = 0;
        int hi = mono.size() - 1;
        int ans = -1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (mono.get(pivot)[0] > target) {
                ans = pivot;
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }
}
