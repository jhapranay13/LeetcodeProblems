package leetcode.medium;

import java.util.*;

/**
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.
 *
 * For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
 * Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.
 *
 * You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.
 *
 * Return a boolean array answer, where answer[j] is the answer to the jth query.
 *
 *
 *
 * Example 1:
 *              1 ---> 0
 *
 * Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * Output: [false,true]
 * Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
 * Course 0 is not a prerequisite of course 1, but the opposite is true.
 *
 *
 * Example 2:
 *
 *                  1 ----->  0
 *                    \     ^
 *                     \    |
 *                      \   |
 *                       V  |
 *                         2
 *
 * Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * Output: [false,false]
 * Explanation: There are no prerequisites, and each course is independent.
 * Example 3:
 *
 *
 * Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
 * Output: [true,true]
 *
 *
 * Constraints:
 *
 * 2 <= numCourses <= 100
 * 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
 * prerequisites[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * All the pairs [ai, bi] are unique.
 * The prerequisites graph has no cycles.
 * 1 <= queries.length <= 104
 * 0 <= ui, vi <= n - 1
 * ui != vi
 *
 */

public class _1462_Course_Schedule_IV {
    // Topological Sort Kahn's Algorithm
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int[] incoming = new int[numCourses];
        Map<Integer, Set<Integer>> adjacency = new HashMap<>();

        for (int[] pre : prerequisites) {
            int parent = pre[0];
            int child = pre[1];
            incoming[child]++;
            Set<Integer> adj = adjacency.getOrDefault(parent, new HashSet<>());
            adj.add(child);
            adjacency.put(parent, adj);
        }
        Map<Integer, Set<Integer>> reverseAdj = new HashMap<>();
        Deque<Integer> q = new LinkedList<>();

        for (int i = 0; i < incoming.length; i++) {

            if (incoming[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int curr = q.poll();
            Set<Integer> children = adjacency.getOrDefault(curr, new HashSet<>());
            Set<Integer> parentAncestor = reverseAdj.getOrDefault(curr, new HashSet<>());

            for (int child : children) {
                Set<Integer> ancestor = reverseAdj.getOrDefault(child, new HashSet<>());
                ancestor.add(curr);
                ancestor.addAll(parentAncestor);
                reverseAdj.put(child, ancestor);
                incoming[child]--;

                if (incoming[child] == 0) {
                    q.offer(child);
                }
            }
        }
        List<Boolean> ans = new ArrayList<>();

        for (int[] query : queries) {
            int parent = query[0];
            int child = query[1];
            Set<Integer> reverseSet = reverseAdj.getOrDefault(child, new HashSet<>());

            if (reverseSet.contains(parent)) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }
        return ans;
    }
    //=============================================================================================
    // Floyd Warshal Algo
    public List<Boolean> checkIfPrerequisite1(int numCourses, int[][] prerequisites, int[][] queries) {
        boolean[][] canReach = new boolean[numCourses][numCourses];

        for (int[] pre : prerequisites) {
            int parent = pre[0];
            int child = pre[1];
            canReach[parent][child] = true;
        }

        for (int k = 0; k < numCourses; k++) {

            for (int i = 0; i < numCourses; i++) {

                for (int j = 0; j < numCourses; j++) {

                    if (canReach[i][k] && canReach[k][j]) {
                        canReach[i][j] = true;
                    }
                }
            }
        }
        List<Boolean> ans = new ArrayList<>();

        for (int[] query : queries) {
            int parent = query[0];
            int child = query[1];

            if (canReach[parent][child]) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }
        return ans;
    }
}
