package leetcode.medium;

/**
 *
 *
 * You are given the head of a linked list.
 *
 * Remove every node which has a node with a strictly greater value anywhere to the right side of it.
 *
 * Return the head of the modified linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [5,2,13,3,8]
 * Output: [13,8]
 * Explanation: The nodes that should be removed are 5, 2 and 3.
 * - Node 13 is to the right of node 5.
 * - Node 13 is to the right of node 2.
 * - Node 8 is to the right of node 3.
 * Example 2:
 *
 * Input: head = [1,1,1,1]
 * Output: [1,1,1,1]
 * Explanation: Every node has value 1, so no nodes are removed.
 *
 *
 * Constraints:
 *
 * The number of the nodes in the given list is in the range [1, 10^5].
 * 1 <= Node.val <= 10^5
 *
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class _2487_Remove_Nodes_From_Linked_List {
    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeNodes(ListNode head) {
        Deque<ListNode> stack = new LinkedList<>();
        ListNode curr = head;

        while (curr != null) {

            while (!stack.isEmpty()) {

                if (curr.val > stack.peek().val) {
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.push(curr);
            curr = curr.next;
        }
        ListNode ans = null;

        while (!stack.isEmpty()) {

            if (ans == null) {
                ans = stack.pop();
                ans.next = null;
            } else {
                stack.peek().next = ans;
                ans = stack.pop();
            }
        }
        return ans;
    }
}
