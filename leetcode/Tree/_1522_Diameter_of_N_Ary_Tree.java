package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.
 *
 * The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. This path may or may not pass through the root.
 *
 * (Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)
 *
 *
 *
 * Example 1:
 *                  1
 *              /   |   \
 *             3    2   4
 *           /   \
 *          5    6
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: 3
 * Explanation: Diameter is shown in red color.
 * Example 2:
 *                   1
 *                 /
 *                2
 *              /   \
 *             3     4
 *            /       \
 *           5         6
 *
 *
 * Input: root = [1,null,2,null,3,4,null,5,null,6]
 * Output: 4
 * Example 3:
 *                          1
 *                   /   /      \    \
 *                 2    3        4    5
 *                    /   \      |  /   \
 *                   6     7     8 9     10
 *                         |     | |
 *                         11   12 13
 *                         |
 *                         14
 *
 *
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: 7
 *
 *
 * Constraints:
 *
 * The depth of the n-ary tree is less than or equal to 1000.
 * The total number of nodes is between [1, 104].
 *
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;


    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }

    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

public class _1522_Diameter_of_N_Ary_Tree {
    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private int ans = 0;

    public int diameter(Node root) {
        recur(root);
        return ans;
    }

    private int recur(Node node) {

        if (node == null) {
            return 0;
        }
        List<Node> children = node.children;
        int max1 = 0;
        int max2 = 0;

        for (Node child : children) {
            int temp = recur(child);

            if (temp > max1) {
                max2 = max1;
                max1 = temp;
            } else if (temp > max2) {
                max2 = temp;
            }
        }
        ans = Math.max(ans, max1 + max2);
        return Math.max(max1, max2) + 1;
    }
}
