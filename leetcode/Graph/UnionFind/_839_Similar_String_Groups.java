package leetcode.Graph.UnionFind;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.
 *
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
 *
 * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
 *
 * We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["tars","rats","arts","star"]
 * Output: 2
 * Example 2:
 *
 * Input: strs = ["omv","ovm"]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= strs.length <= 300
 * 1 <= strs[i].length <= 300
 * strs[i] consists of lowercase letters only.
 * All words in strs have the same length and are anagrams of each other.
 *
 */

public class _839_Similar_String_Groups {

    //DFS
    public int numSimilarGroups2(String[] strs) {
        int ans = 0;
        int[] v = new int[strs.length];

        for (int i = 0; i < strs.length; i++) {

            if (v[i] == 0) {
                ans++;
                dfs(strs, v, i);
            }
        }
        return ans;
    }

    private void dfs(String[] strs, int[] v, int index) {
        String str = strs[index];
        v[index] = 1;

        for (int i = 0; i < strs.length; i++) {

            if (i == index) {
                continue;
            }

            if (v[i] == 0 && isSimilar(str, strs[i])) {
                dfs(strs, v, i);
            }
        }
    }

    //=============================================================================================
    //BFS :-

    public int numSimilarGroups1(String[] strs) {
        int ans = 0;
        int[] v = new int[strs.length];

        for (int i = 0; i < strs.length; i++) {

            if (v[i] == 0) {
                ans++;
                bfs(strs, v, i);
            }
        }
        return ans;
    }

    private void bfs(String[] strs, int[] v, int index) {
        Deque<Integer> q = new LinkedList<>();
        q.offer(index);

        while (!q.isEmpty()) {
            int indx = q.poll();
            v[indx] = 1;
            String str = strs[indx];

            for (int i = 0; i < strs.length; i++) {

                if (v[i] == 0 && isSimilar(str, strs[i])) {
                    q.offer(i);
                }
            }
        }
    }

    //=============================================================================================
    //UNION FIND :-

    class UnionFind {
        int[] parent;

        public UnionFind(int size) {
            this.parent = new int[size];

            for (int i = 0; i < size; i++) {
                this.parent[i] = i;
            }
        }

        public int find(int x) {

            if (parent[x] != x) {
                return find(parent[x]);
            }
            return x;
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px != py) {
                parent[py] = px;
            }
        }
    }

    public int numSimilarGroups(String[] strs) {
        UnionFind uf = new UnionFind(strs.length);

        for (int i = 0; i < strs.length; i++) {

            for (int j = i + 1; j < strs.length; j++) {

                if (isSimilar(strs[i], strs[j])) {
                    uf.union(i, j);
                }
            }
        }
        int ans = 0;

        for (int i = 0; i < strs.length; i++) {
            if (uf.find(i) == i) {
                ans++;
            }
        }
        return ans;
    }

    private boolean isSimilar(String str1, String str2) {
        int index1 = -1;
        int index2 = -1;

        for (int i = 0; i < str1.length(); i++) {

            if (str1.charAt(i) != str2.charAt(i)) {

                if (index2 != -1) {
                    return false;
                }

                if (index2 == -1) {

                    if (index1 == -1) {
                        index1 = i;
                    } else {
                        index2 = i;
                    }
                }
            }
        }

        if (index1 == -1) {
            return true;
        }

        if (index2 == -1) {
            return false;
        }
        return  str1.charAt(index1) == str2.charAt(index2) && str1.charAt(index2) == str2.charAt(index1);
    }
}
