package leetcode.StackAndQueues;

import java.util.*;

/**
 *
 *
 * You have n processes forming a rooted tree structure. You are given two integer arrays pid and ppid, where pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.
 *
 * Each process has only one parent process but may have multiple children processes. Only one process has ppid[i] = 0, which means this process has no parent process (the root of the tree).
 *
 * When a process is killed, all of its children processes will also be killed.
 *
 * Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the processes that will be killed. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *                  3
 *                 / \
 *                1   5
 *                   /
 *                 10
 *
 * Input: pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
 * Output: [5,10]
 * Explanation: The processes colored in red are the processes that should be killed.
 * Example 2:
 *
 * Input: pid = [1], ppid = [0], kill = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * n == pid.length
 * n == ppid.length
 * 1 <= n <= 5 * 104
 * 1 <= pid[i] <= 5 * 104
 * 0 <= ppid[i] <= 5 * 104
 * Only one process has no parent.
 * All the values of pid are unique.
 * kill is guaranteed to be in pid.
 *
 */

public class _582_KillProcess {

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> parentProcessMap = new HashMap<>();

        for (int i = 0; i < pid.size(); i++) {
            int process = pid.get(i);
            int parent = ppid.get(i);
            List<Integer> list = parentProcessMap.getOrDefault(parent, new ArrayList<>());
            list.add(process);
            parentProcessMap.put(parent, list);
        }
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> q = new LinkedList<>();
        q.offer(kill);

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int node = q.poll();
                ans.add(node);
                List<Integer> list = parentProcessMap.getOrDefault(node, new ArrayList<>());

                for (int next : list) {
                    q.offer(next);
                }
            }
        }
        return ans;
    }
}
