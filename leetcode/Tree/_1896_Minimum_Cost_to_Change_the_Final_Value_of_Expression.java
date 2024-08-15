package leetcode.Tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 *
 * You are given a valid boolean expression as a string expression consisting of the characters '1','0','&' (bitwise AND operator),'|' (bitwise OR operator),'(', and ')'.
 *
 * For example, "()1|1" and "(1)&()" are not valid while "1", "(((1))|(0))", and "1|(0&(1))" are valid expressions.
 * Return the minimum cost to change the final value of the expression.
 *
 * For example, if expression = "1|1|(0&0)&1", its value is 1|1|(0&0)&1 = 1|1|0&1 = 1|0&1 = 1&1 = 1. We want to apply operations so that the new expression evaluates to 0.
 * The cost of changing the final value of an expression is the number of operations performed on the expression. The types of operations are described as follows:
 *
 * Turn a '1' into a '0'.
 * Turn a '0' into a '1'.
 * Turn a '&' into a '|'.
 * Turn a '|' into a '&'.
 * Note: '&' does not take precedence over '|' in the order of calculation. Evaluate parentheses first, then in left-to-right order.
 *
 *
 *
 * Example 1:
 *
 * Input: expression = "1&(0|1)"
 * Output: 1
 * Explanation: We can turn "1&(0|1)" into "1&(0&1)" by changing the '|' to a '&' using 1 operation.
 * The new expression evaluates to 0.
 * Example 2:
 *
 * Input: expression = "(0&0)&(0&0&0)"
 * Output: 3
 * Explanation: We can turn "(0&0)&(0&0&0)" into "(0|1)|(0&0&0)" using 3 operations.
 * The new expression evaluates to 1.
 * Example 3:
 *
 * Input: expression = "(0|(1|0&1))"
 * Output: 1
 * Explanation: We can turn "(0|(1|0&1))" into "(0|(0|0&1))" using 1 operation.
 * The new expression evaluates to 0.
 *
 *
 * Constraints:
 *
 * 1 <= expression.length <= 10^5
 * expression only contains '1','0','&','|','(', and ')'
 * All parentheses are properly matched.
 * There will be no empty parentheses (i.e: "()" is not a substring of expression).
 *
 */

public class _1896_Minimum_Cost_to_Change_the_Final_Value_of_Expression {
    class Node {
        char op = '\u0000';
        Node left = null;
        Node right = null;
    }

    public int minOperationsToFlip(String expression) {
        String postFix = getPostFix(expression);
        index = 0;
        int value = reversePosilshNotationEval(postFix);
        int ans = recur(root, 1 - value);// 1 - value  coz if 0  then 1 else 0
        return ans;
    }
    private int index = 0;
    private Node root = null;

    private int recur(Node node, int target) {

        if (node.op == '1' || node.op == '0') {
            int val = node.op - '0';

            if (val == target) {
                return 0;
            }
            return 1;
        }
        int leftChange = recur(node.left, target);
        int rightChange = recur(node.right, target);
        int ans = -1;

        if (target == 0) {
            // if left change or right change then return minmum of left and right +1
            if (node.op == '|') {
                ans = Math.min(1 + Math.min(leftChange, rightChange), leftChange + rightChange);
            } else {
                ans = Math.min(leftChange + rightChange + 1,Math.min(leftChange, rightChange));
            }
        } else {

            if (node.op == '|') {
                ans = Math.min(Math.min(leftChange, rightChange), leftChange + rightChange + 1);
            } else {
                ans = Math.min(1 + Math.min(leftChange, rightChange), leftChange + rightChange);
            }
        }


        return ans;
    }

    private int reversePosilshNotationEval(String postFix) {
        Deque<Integer> stack = new LinkedList<>();
        Deque<Node> nodeStack = new LinkedList<>();

        while (index < postFix.length()) {
            char ch = postFix.charAt(index++);
            Node node = new Node();

            if (ch == '0' || ch == '1') {
                stack.push(ch - '0');
                node.op = ch;
                nodeStack.push(node);
            } else {
                int val1 = stack.pop();
                int val2 = stack.pop();
                node.op = ch;
                node.right = nodeStack.pop();
                node.left = nodeStack.pop();
                nodeStack.push(node);
                int temp = 0;

                if (ch == '&') {
                    temp = val1 & val2;
                } else {
                    temp = val1 | val2;
                }
                stack.push(temp);
            }
        }
        root = nodeStack.pop();
        return stack.pop();
    }

    private String getPostFix(String exp) {
        StringBuilder res = new StringBuilder();
        Deque<Character> st = new LinkedList<>();

        for(char c: exp.toCharArray()) {
            if(c == '0' || c == '1')
                res.append(c);
            else if(c == '(')
                st.push(c);
            else if(c == ')') {
                while(!st.isEmpty() && st.peek() != '(')
                    res.append(st.pop());

                st.pop();
            }
            else {
                while(!st.isEmpty() && st.peek() != '(')
                    res.append(st.pop());

                st.push(c);
            }
        }
        while(!st.isEmpty())
            res.append(st.pop());

        return res.toString();
    }

    /*
    Times out becauseOf recursion
    private String getPostFix(String exp) {

        if (index == exp.length()) {
            return "";
        }
        char op = '\u0000';
        StringBuilder ans = new StringBuilder();

        while (index < exp.length()) {
            char ch = exp.charAt(index++);

            if (ch == ')') {
                break;
            }

            if (ch == '(') {
                ans.append(getPostFix(exp));

                if (op != '\u0000') {
                    ans.append(op);
                    op = '\u0000';
                }
                continue;
            }

            if (ch == '|' || ch == '&') {
                op = ch;
                continue;
            } else {
                ans.append(ch);
            }

            if (op != '\u0000') {
                ans.append(op);
                op = '\u0000';
            }
        }
        return ans.toString();
    }*/
}
