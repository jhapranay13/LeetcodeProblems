package leetcode.hard;

import java.util.*;

/**
 *
 * There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal to -1 if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.
 *
 * Return a sorted list of the items such that:
 *
 * The items that belong to the same group are next to each other in the sorted list.
 * There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
 * Return any solution if there is more than one solution and return an empty list if there is no solution.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
 * Output: [6,3,4,1,5,2,0,7]
 * Example 2:
 *
 * Input: n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
 * Output: []
 * Explanation: This is the same as example 1 except that 4 needs to be before 6 in the sorted list.
 *
 *
 * Constraints:
 *
 * 1 <= m <= n <= 3 * 10^4
 * group.length == beforeItems.length == n
 * -1 <= group[i] <= m - 1
 * 0 <= beforeItems[i].length <= n - 1
 * 0 <= beforeItems[i][j] <= n - 1
 * i != beforeItems[i][j]
 * beforeItems[i] does not contain duplicates elements.
 *
 */

public class _1203_Sort_Items_by_Groups_Respecting_Dependencies {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int[] indegreeItem = new int[n];
        Map<Integer, Integer> incoming = new HashMap<>();
        Map<Integer, List<Integer>> grpGrpHolder = new HashMap<>();
        Map<Integer, List<Integer>> adjacency = new HashMap<>();

        for (int i = 0; i < group.length; i++) {
            int grp = group[i];

            if (grp == -1) {
                //Creating Seperate group for each -1 as
                group[i] = m;
                grp = m++;
            }

            if (!incoming.containsKey(grp)) {
                incoming.put(grp, 0);
            }
            List<Integer> beforeItem = beforeItems.get(i);

            for (int parent : beforeItem) {
                List<Integer> child = adjacency.getOrDefault(parent, new ArrayList<>());
                child.add(i);
                adjacency.put(parent, child);
                indegreeItem[i]++;
                int parentGrp = group[parent];

                if (parentGrp == -1) {
                    group[parent] = m;
                    parentGrp = m++;
                }

                if (parentGrp != grp) {
                    List<Integer> list = grpGrpHolder.getOrDefault(parentGrp, new ArrayList<>());
                    list.add(grp);
                    grpGrpHolder.put(parentGrp, list);
                    incoming.put(grp, incoming.getOrDefault(grp, 0) + 1);
                }
                if (!incoming.containsKey(parentGrp)) {
                    incoming.put(parentGrp, 0);
                }
            }
        }
        Map<Integer, List<Integer>> sortedGrpHolder = new HashMap<>();
        Deque<Integer> q = new LinkedList<>();

        for (int i = 0; i < indegreeItem.length; i++) {
            int in = indegreeItem[i];

            if (in == 0) {
                q.offer(i);
                List<Integer> list = sortedGrpHolder.getOrDefault(group[i], new ArrayList<>());
                list.add(i);
                sortedGrpHolder.put(group[i], list);
            }
        }
        List<Integer> ansHolder = new ArrayList<>();

        while (!q.isEmpty()) {
            int item = q.poll();
            List<Integer> child = adjacency.getOrDefault(item, new ArrayList<>());

            for (int ch : child) {
                indegreeItem[ch]--;

                if (indegreeItem[ch] == 0) {
                    q.offer(ch);
                    int grp = group[ch];
                    List<Integer> list = sortedGrpHolder.getOrDefault(grp, new ArrayList<>());
                    list.add(ch);
                    sortedGrpHolder.put(grp, list);
                } else if (indegreeItem[ch] < 0) {
                    //cyclic
                    return new int[0];
                }
            }
        }
        q = new LinkedList<>();

        for (int key : incoming.keySet()) {

            if (incoming.get(key) == 0) {
                q.offer(key);
            }
        }

        while (!q.isEmpty()) {
            int grp = q.poll();

            if (!sortedGrpHolder.containsKey(grp)) {
                return new int[0];
            }
            ansHolder.addAll(sortedGrpHolder.get(grp));
            List<Integer> children = grpGrpHolder.getOrDefault(grp, new ArrayList<>());

            for (int child : children) {
                int degree = incoming.get(child);
                degree--;
                incoming.put(child, degree);

                if (degree < 0) {
                    return new int[0]; // cycle;
                }

                if (degree == 0) {
                    q.offer(child);
                }
            }
        }

        if (ansHolder.size() == n) {
            int ans[] = new int[n];

            for (int i = 0; i < n; i++) {
                ans[i] = ansHolder.get(i);
            }
            return ans;
        }

        return new int[0];
    }
}
