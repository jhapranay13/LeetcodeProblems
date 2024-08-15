package leetcode.LinkedList;

/**
 *
 *
 * Given a non-negative integer represented as a linked list of digits, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [1,2,3]
 * Output: [1,2,4]
 * Example 2:
 *
 * Input: head = [0]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * The number represented by the linked list does not contain leading zeros except for the zero itself.
 *
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

public class _369_Plus_One_Linked_List {
    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode plusOne(ListNode head) {
        Deque<ListNode> stack = new LinkedList<>();

        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        int carry = 0;
        ListNode ans = null;

        while (!stack.isEmpty()) {
            ListNode curr = stack.pop();
            int val = curr.val + carry;

            if (ans == null) {
                val += 1;
            }
            carry = val / 10;
            curr.val = val % 10;
            ans = curr;
        }

        if (carry != 0) {
            ListNode temp = new ListNode();
            temp.val = carry;
            temp.next = ans;
            ans = temp;
        }
        return ans;
    }
}
