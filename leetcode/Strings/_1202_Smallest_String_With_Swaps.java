package leetcode.Strings;

import java.util.*;

/**
 *
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 *
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 *
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * Example 2:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 * Example 3:
 *
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 *
 */

public class _1202_Smallest_String_With_Swaps {
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
                return parent[x] = find(parent[x]);
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

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UnionFind uf = new UnionFind(s.length());
        //Grouping all the chars which can be sorted
        for (List<Integer> point : pairs) {
            uf.union(point.get(0), point.get(1));
        }
        Map<Integer, List<Integer>> parentChildMap = new HashMap<>();
        //gruoping the indexes according to parent
        for (int i = 0; i < s.length(); i++) {
            int parent = uf.find(i);
            List<Integer> list = parentChildMap.getOrDefault(parent, new ArrayList<>());
            list.add(i);
            parentChildMap.put(parent, list);
        }
        char[] charArr = new char[s.length()];
        // putting the characters in the group in sorted manner
        for (int key : parentChildMap.keySet()) {
            List<Integer> list = parentChildMap.get(key);
            List<Character> charList = new ArrayList<>();

            for (int index : list) {
                charList.add(s.charAt(index));
            }
            Collections.sort(charList);
            int charListIndex = 0;

            for (int index : list) {
                charArr[index] = charList.get(charListIndex++);
            }
        }
        StringBuilder ans = new StringBuilder();

        for (char ch : charArr) {
            ans.append(ch);
        }
        return ans.toString();
    }
}
