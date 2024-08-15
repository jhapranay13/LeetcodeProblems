package leetcode.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 *
 * You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 13
 * Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
 * Example 2:
 *
 * Input: n = 2
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 5 * 104
 */

public class _386_Lexicographical_Numbers {
    //DFS Solution
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        recur(n, ans, 0);
        return ans;
    }

    public void recur(int n, List<Integer> ans, int currNum) {

        if (currNum > n) {
            return;
        }

        for (int i = 0; i <= 9; i++) {
            int curr = currNum * 10 + i;//Calculating next set of number

            if (curr == 0) {
                continue;
            }
            if (curr <= n) {
                ans.add(curr);
                recur(n, ans, curr);
            } else {
                return;
            }
        }
    }
    //=============================================================================================
    //Tries Solution
    class TrieNode {
        TrieNode[] child = new TrieNode[10];
        boolean eow = false;
    }

    class Trie {
        TrieNode root = new TrieNode();
        StringBuilder holder = new StringBuilder();

        public void addNum(String num) {
            TrieNode curr = root;
            int index = 0;

            while (curr != null && index < num.length()) {
                int ind = num.charAt(index++) - '0';
                TrieNode temp = curr.child[ind];

                if (temp == null) {
                    curr.child[ind] = new TrieNode();
                    temp = curr.child[ind];
                }
                curr = temp;
            }
            curr.eow = true;
        }

        public void getSorted(List<Integer> ans, TrieNode node) {

            if (node == null) {
                return;
            }

            if (node.eow) {
                ans.add(Integer.parseInt(holder.toString()));
            }

            for (int i = 0; i < root.child.length; i++) {
                holder.append(i);
                getSorted(ans, node.child[i]);
                holder.deleteCharAt(holder.length() - 1);
            }
        }
    }

    public List<Integer> lexicalOrder1(int n) {
        Trie trie = new Trie();

        for (int i = 1; i <= n; i++) {
            trie.addNum(""+ i);
        }
        List<Integer> ans = new ArrayList<>();
        trie.getSorted(ans, trie.root);
        return ans;
    }
}
