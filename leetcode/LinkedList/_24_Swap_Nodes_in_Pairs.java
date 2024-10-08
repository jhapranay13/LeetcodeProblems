package leetcode.LinkedList;

/**
 *
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 *
 * Input: head = []
 * Output: []
 * Example 3:
 *
 * Input: head = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 *
 */
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

public class _24_Swap_Nodes_in_Pairs {
    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode swapPairs(ListNode head) {
        ListNode x = new ListNode();
        x.next = head;
        ListNode curr = head;
        ListNode ans = null;

        while (curr != null && curr.next!= null) {
            ListNode next = curr.next;
            ListNode nextNext = next.next;
            ListNode prev = x.next;
            x.next = next;
            prev.next = nextNext;
            next.next = prev;
            x = prev;
            curr = nextNext;

            if (ans == null) {
                ans = next;
            }
        }
        ans = ans == null ? head : ans;
        return ans;
    }
}
