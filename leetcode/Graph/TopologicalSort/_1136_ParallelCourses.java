package leetcode.Graph.TopologicalSort;

import java.util.*;

/**
 *
 * You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.
 *
 * In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.
 *
 * Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.
 *
 *
 *
 * Example 1:
 *                  1    2
 *                   \  /
 *                   V V
 *                    3
 * Input: n = 3, relations = [[1,3],[2,3]]
 * Output: 2
 * Explanation: The figure above represents the given graph.
 * In the first semester, you can take courses 1 and 2.
 * In the second semester, you can take course 3.
 * Example 2:
 *
 *                      1 <--3
 *  *                   \    ^
 *  *                   V   /
 *  *                    2
 *
 * Input: n = 3, relations = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation: No course can be studied because they are prerequisites of each other.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 5000
 * 1 <= relations.length <= 5000
 * relations[i].length == 2
 * 1 <= prevCoursei, nextCoursei <= n
 * prevCoursei != nextCoursei
 * All the pairs [prevCoursei, nextCoursei] are unique.
 *
 *
 */

public class _1136_ParallelCourses {
    //=============================================================================================
    //Kahn's Algorithm BFS
    public int minimumSemesters(int n, int[][] relations) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        int[] indegree = new int[n + 1];

        for (int[] relation : relations) {
            List<Integer> list = adjMap.getOrDefault(relation[0], new ArrayList<>());
            list.add(relation[1]);
            adjMap.put(relation[0], list);
            indegree[relation[1]]++;
        }
        int count = 0;
        Deque<Integer> q = new LinkedList<>();

        for (int i = 1; i <= n; i++) {

            if (indegree[i] == 0) {
                q.offer(i);
                indegree[i]--;
            }
        }
        int ans = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int node = q.poll();
                List<Integer> list = adjMap.getOrDefault(node, new ArrayList<>());

                for (int child : list) {
                    indegree[child]--;

                    if (indegree[child] == 0) {
                        q.offer(child);
                    }
                }
                count++;
            }
            ans++;
        }

        if (count != n) {
            return -1;
        }
        return ans;
    }
    //=============================================================================================
    //DFS Topological Sort
    public int minimumSemesters1(int n, int[][] relations) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();

        for (int[] relation : relations) {
            List<Integer> list = adjMap.getOrDefault(relation[0], new ArrayList<>());
            list.add(relation[1]);
            adjMap.put(relation[0], list);
        }
        Map<Integer, Integer> v = new HashMap<>();
        int ans = -1;
        for (int i = 1; i <= n; i++) {
            Set<Integer> cycle = new HashSet<>();

            if (!v.containsKey(i)) {
                int temp = recur(i, adjMap, v, cycle);

                if (temp == -1) {
                    return -1;
                }
                ans = Math.max(ans, temp);
            }
        }
        return ans;
    }

    private int recur(int node, Map<Integer, List<Integer>> adjMap, Map<Integer, Integer> v, Set<Integer> cycle) {

        if (cycle.contains(node)) {
            return -1;
        }

        if (v.containsKey(node)) {
            return v.get(node);
        }
        cycle.add(node);
        int ans = 1;
        List<Integer> list = adjMap.getOrDefault(node, new ArrayList<>());

        for (int child : list) {
            int temp = recur(child, adjMap, v, cycle);

            if (temp == -1) {
                return -1;
            }
            ans = Math.max(ans, 1 + temp);
        }
        v.put(node, ans);
        cycle.remove(node);
        return ans;
    }
}
