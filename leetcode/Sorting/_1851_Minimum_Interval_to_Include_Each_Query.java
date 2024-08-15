package leetcode.Sorting;

import java.util.*;

/**
 *
 * You are given a 2D integer array intervals, where intervals[i] = [lefti, righti] describes the ith interval starting at lefti and ending at righti (inclusive). The size of an interval is defined as the number of integers it contains, or more formally righti - lefti + 1.
 *
 * You are also given an integer array queries. The answer to the jth query is the size of the smallest interval i such that lefti <= queries[j] <= righti. If no such interval exists, the answer is -1.
 *
 * Return an array containing the answers to the queries.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * Output: [3,3,1,4]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.
 * - Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.
 * - Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.
 * - Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.
 * Example 2:
 *
 * Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * Output: [2,-1,4,6]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,3] is the smallest interval containing 2. The answer is 3 - 2 + 1 = 2.
 * - Query = 19: None of the intervals contain 19. The answer is -1.
 * - Query = 5: The interval [2,5] is the smallest interval containing 5. The answer is 5 - 2 + 1 = 4.
 * - Query = 22: The interval [20,25] is the smallest interval containing 22. The answer is 25 - 20 + 1 = 6.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 10^5
 * 1 <= queries.length <= 10^5
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 10^7
 * 1 <= queries[j] <= 10^7
 *
 */

public class _1851_Minimum_Interval_to_Include_Each_Query {
    public int[] minInterval(int[][] intervals, int[] queries) {
        //we can use priority queue to sort by size of the interval
        //but only valid intervals in the queue
        //so we need to sort intervals by starting point and also sort queries
        List<Integer> indexList = new ArrayList<>();
        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            indexList.add(i);
        }
        Collections.sort(indexList, (a, b) -> queries[a] - queries[b]);
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int iindex = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        for (int i = 0; i < indexList.size(); i++) {
            int qindex = indexList.get(i);

            while (iindex < intervals.length && intervals[iindex][0] <= queries[qindex]) {
                pq.offer(new int[] {Math.abs(intervals[iindex][0] - intervals[iindex][1]) + 1,
                        intervals[iindex][1]});
                iindex++;
            }
            // it cannot be used again as we sorted the queries
            while (!pq.isEmpty() && pq.peek()[1] < queries[qindex]) {
                pq.poll();
            }

            if (pq.isEmpty()) {
                ans[qindex] = -1;
            } else {
                ans[qindex] = pq.peek()[0];
            }
        }
        return ans;
    }
}
