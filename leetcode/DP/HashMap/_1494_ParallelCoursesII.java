package leetcode.DP.HashMap;

import java.util.*;

/**
 *
 * You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei. Also, you are given the integer k.
 *
 * In one semester, you can take at most k courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.
 *
 * Return the minimum number of semesters needed to take all courses. The testcases will be generated such that it is possible to take every course.
 *
 *
 *
 * Example 1:
 *              2 ----> 1 ------ 4
 *                    /^
 *                  /
 *                 3
 *
 * Input: n = 4, dependencies = [[2,1],[3,1],[1,4]], k = 2
 * Output: 3
 * Explanation: The figure above represents the given graph.
 * In the first semester, you can take courses 2 and 3.
 * In the second semester, you can take course 1.
 * In the third semester, you can take course 4.
 * Example 2:
 *                  2
 *                    \
 *                  3--> 1----5
 *                    /
 *                  4
 *
 * Input: n = 5, dependencies = [[2,1],[3,1],[4,1],[1,5]], k = 2
 * Output: 4
 * Explanation: The figure above represents the given graph.
 * In the first semester, you can take courses 2 and 3 only since you cannot take more than two per semester.
 * In the second semester, you can take course 4.
 * In the third semester, you can take course 1.
 * In the fourth semester, you can take course 5.
 * Example 3:
 *
 * Input: n = 11, dependencies = [], k = 2
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 <= n <= 15
 * 1 <= k <= n
 * 0 <= relations.length <= n * (n-1) / 2
 * relations[i].length == 2
 * 1 <= prevCoursei, nextCoursei <= n
 * prevCoursei != nextCoursei
 * All the pairs [prevCoursei, nextCoursei] are unique.
 * The given graph is a directed acyclic graph.
 *
 */

public class _1494_ParallelCoursesII {
    public int minNumberOfSemesters(int n, int[][] relations, int k) {

        if (relations.length == 0) {
            int temp = n / k;

            if (n % k != 0) {
                temp++;
            }
            return temp;
        }
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        int[] incoming = new int[n + 1];
        incoming[0] = -1;

        for (int[] relation : relations) {
            List<Integer> list = adjMap.getOrDefault(relation[0], new ArrayList<>());
            list.add(relation[1]);
            adjMap.put(relation[0], list);
            incoming[relation[1]]++;
        }
        Map<String, Integer> memo = new HashMap<>();
        return recur(adjMap, incoming, k, memo);
    }

    public int recur(Map<Integer, List<Integer>> adjMap, int[] incoming, int k, Map<String, Integer> memo) {
        boolean returnFlag = true;

        for (int in : incoming) {

            if (in >= 0) {
                returnFlag = false;
                break;
            }
        }

        if (returnFlag) {
            return 0;
        }
        String key = Arrays.toString(incoming);

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < incoming.length; i++) {

            if (incoming[i] == 0) {
                list.add(i);
            }
        }

        if (list.size() <= k) {
            int [] newIncoming = getIncoming(adjMap, list, incoming);
            int temp = 1 + recur(adjMap, newIncoming, k, memo);
            memo.put(key, temp);
            return temp;
        }
        List<List<Integer>> combinations = new ArrayList<>();
        ArrayList<Integer> p = new ArrayList<>();
        getCombinations(list, combinations, p, 0, 0, k);
        int ans = Integer.MAX_VALUE;

        for (List<Integer> combination : combinations) {
            int [] newIncoming = getIncoming(adjMap, combination, incoming);
            ans = Math.min(ans, 1 + recur(adjMap, newIncoming, k, memo));
        }
        memo.put(key, ans);
        return ans;
    }

    private void getCombinations(List<Integer> list, List<List<Integer>> combinations, ArrayList<Integer> p,
                                 int index, int listIndex, int k) {

        if (p.size() == k) {
            combinations.add((ArrayList<Integer>)p.clone());
            return;
        }

        if (index == list.size()) {
            return;
        }
        p.add(list.get(index));
        getCombinations(list, combinations, p, index + 1, listIndex, k);
        p.remove(p.size() - 1);
        getCombinations(list, combinations, p, index + 1, listIndex, k);
    }

    private int[] getIncoming(Map<Integer, List<Integer>> adjMap, List<Integer> list, int[] incoming) {
        int[] in = incoming.clone();

        for (int elem : list) {
            in[elem]--;
            List<Integer> adj = adjMap.getOrDefault(elem, new ArrayList<>());

            for (int next : adj) {
                in[next]--;
            }
        }
        return in;
    }
}
