package leetcode.Graph.UnionFind;

import java.util.*;

/**
 *
 * You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.
 *
 * Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.
 *
 * The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.
 *
 * Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
 * Output: [0,1,2,3,5]
 * Explanation:
 * At time 0, person 0 shares the secret with person 1.
 * At time 5, person 1 shares the secret with person 2.
 * At time 8, person 2 shares the secret with person 3.
 * At time 10, person 1 shares the secret with person 5.​​​​
 * Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.
 * Example 2:
 *
 * Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
 * Output: [0,1,3]
 * Explanation:
 * At time 0, person 0 shares the secret with person 3.
 * At time 2, neither person 1 nor person 2 know the secret.
 * At time 3, person 3 shares the secret with person 0 and person 1.
 * Thus, people 0, 1, and 3 know the secret after all the meetings.
 * Example 3:
 *
 * Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
 * Output: [0,1,2,3,4]
 * Explanation:
 * At time 0, person 0 shares the secret with person 1.
 * At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.
 * Note that person 2 can share the secret at the same time as receiving it.
 * At time 2, person 3 shares the secret with person 4.
 * Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 105
 * 1 <= meetings.length <= 105
 * meetings[i].length == 3
 * 0 <= xi, yi <= n - 1
 * xi != yi
 * 1 <= timei <= 105
 * 1 <= firstPerson <= n - 1
 *
 */

public class _2092_FindAllPeopleWithSecrets {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Map<Integer, List<int[]>> adjMap = new HashMap<>();
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {
                return x[1] - y[1];
            }
        });
        List<int[]> temp = new ArrayList<>();
        temp.add(new int[] {firstPerson, 0});
        adjMap.put(0, temp);

        for (int[] meeting : meetings) {
            List<int[]> list = adjMap.getOrDefault(meeting[0], new ArrayList<>());
            list.add(new int[] {meeting[1], meeting[2]});
            adjMap.put(meeting[0], list);
            list = adjMap.getOrDefault(meeting[1], new ArrayList<>());
            list.add(new int[] {meeting[0], meeting[2]});
            adjMap.put(meeting[1], list);
        }

        for (int[] curr : adjMap.get(0)) {
            q.offer(curr);
        }

        for (int[] curr : adjMap.getOrDefault(firstPerson, new ArrayList<>())) {
            q.offer(curr);
        }
        Set<Integer> v = new HashSet<>();
        v.add(0);
        v.add(firstPerson);
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        ans.add(firstPerson);
        int currTime = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            if (!v.contains(curr[0]) && currTime <= curr[1]) {
                currTime = curr[1];
                v.add(curr[0]);
                ans.add(curr[0]);
                List<int[]> list = adjMap.getOrDefault(curr[0], new ArrayList<>());

                for (int[] next : list) {
                    q.offer(next);
                }
            }
        }
        return ans;
    }
    //=============================================================================================
    //Union Find Solution
    class UnionFind {
        int[] parent;

        public UnionFind(int size) {
            this.parent = new int[size];

            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px < py) {
                parent[py] = px;
            } else {
                parent[px] = py;
            }
        }

        public int find(int x) {

            if (parent[x] == x) {
                return x;
            }
            return find(parent[x]);
        }

        public void reset(int x) {
            parent[x] = x;
        }
    }

    public List<Integer> findAllPeople1(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {
                return x[2] - y[2];
            }
        });
        UnionFind uf = new UnionFind(n);
        uf.union(0, firstPerson);
        Set<Integer> v = new HashSet<>();
        int currTime = meetings[0][2];

        for (int[] meeting : meetings) {

            if (meeting[2] == currTime) {
                uf.union(meeting[0], meeting[1]);
            } else {
                //if Meeting time is greater then we need to see how many members now know the                  //secret and so reseting all the others as at that time they do not know the                    //secret
                int parentZero = uf.find(0);

                for (int person : v) {

                    if (uf.find(person) != parentZero) {
                        uf.reset(person);
                    }
                }
                v.clear();
                uf.union(meeting[1], meeting[0]);
                currTime = meeting[2];
            }
            v.add(meeting[1]);
            v.add(meeting[0]);
        }
        int zeroParent = uf.find(0);
        List<Integer> ans = new ArrayList<>();
        ans.add(0);

        for (int i = 1 ; i < n; i++) {
            if (uf.find(i) == zeroParent) {
                ans.add(i);
            }
        }
        return ans;
    }
}
