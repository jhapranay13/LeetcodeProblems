package leetcode.medium;

/**
 *
 * Suppose you are at a party with n people (labeled from 0 to n - 1), and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her, but he/she does not know any of them.
 *
 * Now you want to find out who the celebrity is or verify that there is not one. The only thing you are
 * allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information about whether
 * A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions
 * as possible (in the asymptotic sense).
 *
 * You are given a helper function bool knows(a, b) which tells you whether A knows B.
 * Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the
 * party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity,
 * return -1.
 *
 *
 *
 * Example 1:
 *
 *                        0
 *                      /  ^
 *                    V     \
 *                   1 <-----2
 *
 * Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
 * Output: 1
 * Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i
 * knows person j, otherwise graph[i][j] = 0 means person i does not know person j.
 * The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
 *
 * Example 2:
 *                      0
 *                    ^  \
 *                  /     V
 *                 1 <-----2
 *
 * Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
 * Output: -1
 * Explanation: There is no celebrity.
 *
 *
 * Constraints:
 *
 * n == graph.length
 * n == graph[i].length
 * 2 <= n <= 100
 * graph[i][j] is 0 or 1.
 * graph[i][i] == 1
 *
 *
 * Follow up: If the maximum number of allowed calls to the API knows is 3 * n, could you find a
 * solution without exceeding the maximum number of calls?
 *
 *
 */

public class _277_FindTheCelebrity {

    /* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
    boolean knows(int a, int b){return false;}
    //=============================================================================================
    //Approach using DFS and Khans algorithm
    public int findCelebrity(int n) {
        int outgoing[] = new int[n];
        int incoming[] = new int[n];

        for (int i = 0; i < n; i++) {

            if (outgoing[i] == 0) {
                recur(i, n, outgoing, incoming);
            }
        }
        int ans = -1;

        for (int i = 0; i < outgoing.length; i++) {
            if (outgoing[i] == 0 && incoming[i] == n - 1) {

                if (ans != -1) {
                    ans = -1;
                    break;
                }
                ans = i;
            }
        }
        return ans;
    }

    private void recur(int node, int n, int[] outgoing, int[] incoming) {

        if (node == n || outgoing[node] >= 1) {
            return;
        }

        for (int i = 0; i < n; i++) {
            if (i != node && knows(node, i)) {
                outgoing[node] += 1;
                incoming[i] += 1;
                recur(i, n, outgoing, incoming);
            }
        }
    }
    //=============================================================================================
    //Itertive approach
    public int findCelebrity1(int n) {
        int outgoing[] = new int[n];
        int incoming[] = new int[n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++ ) {

                if (i != j) {
                    if (knows(i, j)) {
                        outgoing[i]++;
                        incoming[j]++;
                    }
                }
            }
        }
        int ans = -1;

        for (int i = 0; i < outgoing.length; i++) {
            if (outgoing[i] == 0 && incoming[i] == n - 1) {

                if (ans != -1) {
                    ans = -1;
                    break;
                }
                ans = i;
            }
        }
        return ans;
    }
}
