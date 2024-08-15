package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits so that
 * it can be stored in a file or memory buffer, or transmitted across a network connection link to be
 * reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in
 * which each node has no more than N children. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be
 * serialized to a string and this string can be deserialized to the original tree structure.
 *
 * For example, you may serialize the following 3-ary tree
 *
 *
 *
 *
 * as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this
 * format.
 *
 * Or you can follow LeetCode's level order traversal serialization format, where each group of children
 * is separated by the null value.
 *
 *
 *
 *
 * For example, the above tree may be serialized as
 * [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
 *
 * You do not necessarily need to follow the above-suggested formats, there are many more different
 * formats that work so please be creative and come up with different approaches yourself.
 *
 *                                           1
 *                                      /  /   \    \
 *                                     2  3     4    5
 *                                       / \    |   / \
 *                                      6   7   8  9   10
 *                                          |   |  |
 *                                         11  12  13
 *                                         |
 *                                        14
 * Example 1:
 *
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Example 2:
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,null,3,2,4,null,5,6]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The height of the n-ary tree is less than or equal to 1000
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms
 * should be stateless.
 *
 *
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

public class _428_SerializeAndDeserializeNArrayTree {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    //Sample serialization 1 [  2  3 [  6  7 [  11 [  14  ] ] ] 4 [  8 [  12  ] ] 5 [  9 [  13  ] 10  ] ]
    public String serialize(Node root) {
        StringBuilder str = new StringBuilder();
        serialRecur(root, str);
        return str.toString();
    }

    private void serialRecur(Node node, StringBuilder str) {
        if (node == null) {
            return;
        }

        if (str.length() > 0) {
            str.append(" ");
        }
        str.append(node.val + " ");
        List<Node> children = node.children;

        if (children.size() > 0) {
            str.append("[ ");
        }
        for (Node child : children) {
            serialRecur(child, str);
        }
        if (children.size() > 0) {
            str.append(" ]");
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {

        if (data == null || data.trim().length() == 0) {
            return null;
        }
        List<Node> ans = deserialRecur(data, 0);
        return ans.get(0);
    }
    private int index = 0;

    private List<Node> deserialRecur(String data, int i) {
        if (index == data.length()) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        int val = Integer.MIN_VALUE;
        Node prevNode = null;

        while (index < data.length()) {
            char ch = data.charAt(index++);

            if (ch == ' ') {

                if (val == Integer.MIN_VALUE) {
                    continue;
                }
                Node node = new Node(val, new ArrayList<>());
                list.add(node);
                prevNode = node;
                val = Integer.MIN_VALUE;
            }

            if (ch == '[') {
                prevNode.children = deserialRecur(data, index);
            }

            if (ch == ']') {
                break;
            }

            if (Character.isDigit(ch)) {
                val *= 10;
                val += ch - '0';
            }
        }
        return list;
    }
    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));
}
