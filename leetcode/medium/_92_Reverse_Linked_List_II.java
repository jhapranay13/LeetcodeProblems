package leetcode.medium;

/**
 *
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 *
 * Follow up: Could you do it in one pass?
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

public class _92_Reverse_Linked_List_II {
    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (left == right) {
            return head;
        }
        ListNode holder = new ListNode();
        holder.next = head;
        ListNode prev = holder;
        ListNode curr = head;
        int count = 1;

        while (curr != null) {

            if (count == left) {
                ListNode root = prev;

                while (curr != null) {
                    ListNode next = curr.next;
                    int val = -1;

                    if (next != null) {
                        val = next.val;
                        ListNode nextNext = next.next;
                        curr.next = nextNext;
                        next.next = root.next;
                        root.next = next;
                    }

                    if (++count == right) {
                        break;
                    }
                }
            }

            if (curr != null) {
                prev = curr;
                curr = curr.next;
            }
            count++;
        }
        return holder.next;
    }
}
