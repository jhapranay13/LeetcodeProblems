package leetcode.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
 *
 * You are also given a string s of length n, where s[i] is the character assigned to node i.
 *
 * Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.
 *
 *
 *
 * Example 1:
 *
 *                   0(a)
 *                 /      \
 *             2(a)        1(b)
 *            /           /    \
 *           5(e)       3(c)    4(b)
 *
 *
 * Input: parent = [-1,0,0,1,1,2], s = "abacbe"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
 * It can be proven that there is no longer path that satisfies the conditions.
 *
 * Example 2:
 *
 *                      0(a)
 *                    /  |   \
 *                3(c)  2(b)  1(a)
 *
 * Input: parent = [-1,0,0,0], s = "aabc"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.
 *
 *
 * Constraints:
 *
 * n == parent.length == s.length
 * 1 <= n <= 10^5
 * 0 <= parent[i] <= n - 1 for all i >= 1
 * parent[0] == -1
 * parent represents a valid tree.
 * s consists of only lowercase English letters.
 *
 */

public class _2246_Longest_Path_With_Different_Adjacent_Characters {
    public int longestPath(int[] parent, String s) {
        Map<Integer, List<Integer>> adj = getGraph(parent);
        dfs(adj, s, 0);
        return ans;
    }
    private int ans = 1;

    private Map<Integer, List<Integer>> getGraph(int[] parent) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int i = 0; i < parent.length; i++) {

            if (parent[i] != -1) {
                List<Integer> list = adj.getOrDefault(parent[i], new ArrayList<>());
                list.add(i);
                adj.put(parent[i], list);
            }
        }
        return adj;
    }

    private int dfs(Map<Integer, List<Integer>> adj, String s, int curr) {
        char currChar = s.charAt(curr);
        List<Integer> child = adj.getOrDefault(curr, new ArrayList<>());
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < child.size(); i++) {
            result.add(dfs(adj, s, child.get(i)));
        }
        int returnVal = 1;
        //Since there can be multiple children taking combination of 2
        for (int i = 0; i < result.size(); i++) {
            char childChar1 = s.charAt(child.get(i));

            if (childChar1 == currChar) {
                continue;
            }

            for (int j = i + 1; j < result.size(); j++) {
                char childChar2 = s.charAt(child.get(j));

                if (childChar2 == currChar) {
                    continue;
                }
                ans = Math.max(ans, result.get(j) + 1);
                returnVal = Math.max(returnVal, result.get(j) + 1);
                ans = Math.max(ans, result.get(i) + result.get(j) + 1);
            }

            ans = Math.max(ans, result.get(i) + 1);
            returnVal = Math.max(returnVal, result.get(i) + 1);
        }
        return returnVal;
    }
}
