package leetcode.BackTrackingRecursion;


import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (variables), and internal nodes (nodes with two children) correspond to the operators. In this problem, we only consider the '+' operator (i.e. addition).
 *
 * You are given the roots of two binary expression trees, root1 and root2. Return true if the two binary expression trees are equivalent. Otherwise, return false.
 *
 * Two binary expression trees are equivalent if they evaluate to the same value regardless of what the variables are set to.
 *
 *
 *
 * Example 1:
 *
 * Input: root1 = [x], root2 = [x]
 * Output: true
 * Example 2:
 *                +                 +
 *              /  \              /  \
 *             a    +            a    +
 *                /  \              /  \
 *               b    c            b    c
 *
 * Input: root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,c]
 * Output: true
 * Explaination: a + (b + c) == (b + c) + a
 * Example 3:
 *                +                 +
 *              /  \              /  \
 *             a    +            a    +
 *                /  \              /  \
 *               b    c            b    d
 *
 *
 * Input: root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,d]
 * Output: false
 * Explaination: a + (b + c) != (b + d) + a
 *
 *
 * Constraints:
 *
 * The number of nodes in both trees are equal, odd and, in the range [1, 4999].
 * Node.val is '+' or a lower-case English letter.
 * It's guaranteed that the tree given is a valid binary expression tree.
 *
 *
 * Follow up: What will you change in your solution if the tree also supports the '-' operator (i.e. subtraction)?
 *
 *
 */

/**
 * Definition for a binary tree node.
 * class Node {
 *     char val;
 *     Node left;
 *     Node right;
 *     Node() {this.val = ' ';}
 *     Node(char val) { this.val = val; }
 *     Node(char val, Node left, Node right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class _1612_Check_If_Two_Expression_Trees_are_Equivalent {
    class Node {
      char val;
      Node left;
      Node right;
      Node() {this.val = ' ';}
      Node(char val) { this.val = val; }
      Node(char val, Node left, Node right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    public boolean checkEquivalence(Node root1, Node root2) {
        Map<Character, Integer> holder1 = new HashMap<>();
        Map<Character, Integer> holder2 = new HashMap<>();
        recur(root1, holder1, 1);
        recur(root2, holder2, 1);

        if (holder1.size() != holder2.size()) {
            return false;
        }

        for (char ch : holder1.keySet()) {

            if (!holder2.containsKey(ch) || holder1.get(ch) != holder2.get(ch)) {
                return false;
            }
        }
        return true;
    }

    private void recur(Node node, Map<Character, Integer> holder, int sign) {

        if (node == null) {
            return;
        }

        if (node.val == '+') {
            recur(node.left, holder, sign);
            recur(node.right, holder, sign);
        } else if (node.val == '-') { // if sign is -
            recur(node.left, holder, sign);
            // Left side is not effected by root sign
            recur(node.right, holder, -sign);
        } else {
            int val = holder.getOrDefault(node.val, 0);
            val += sign;
            holder.put(node.val, val);
        }
    }
}
