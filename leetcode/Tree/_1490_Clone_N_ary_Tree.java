package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *Given a root of an N-ary tree, return a deep copy (clone) of the tree.
 *
 * Each node in the n-ary tree contains a val (int) and a list (List[Node]) of its children.
 *
 * class Node {
 *     public int val;
 *     public List<Node> children;
 * }
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 *
 *
 *
 * Example 1:
 *                  1
 *               /  |  \
 *              3   2   4
 *            /  \
 *           5    6
 *
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,null,3,2,4,null,5,6]
 *
 * Example 2:
 *                    1
 *               /  |   |  \
 *             2    3   4   5
 *                /  \  |  /  \
 *               6   7  8 9   10
 *                   |  | |
 *                  11 12 13
 *                  |
 *                 14
 *
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 *
 *
 * Constraints:
 *
 * The depth of the n-ary tree is less than or equal to 1000.
 * The total number of nodes is between [0, 104].
 *
 *
 * Follow up: Can your solution work for the graph problem?
 *
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

public class _1490_Clone_N_ary_Tree {
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

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public Node cloneTree(Node root) {
        return recur(root);
    }

    private Node recur(Node node) {

        if (node == null) {
            return null;
        }
        Node copyNode = new Node();
        copyNode.val = node.val;

        for (Node next : node.children) {
            copyNode.children.add(recur(next));
        }
        return copyNode;
    }
}
