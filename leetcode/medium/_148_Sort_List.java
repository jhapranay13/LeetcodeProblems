package leetcode.medium;

/**
 *
 *
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2:
 *
 *
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -10^5 <= Node.val <= 10^5
 *
 *
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
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

public class _148_Sort_List {
    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode mid = getMid(node);
        ListNode start1 = mergeSort(node);
        ListNode start2 = mergeSort(mid);
        ListNode ans = merge(start1, start2);
        return ans;
    }

    private ListNode merge(ListNode start1, ListNode start2) {
        ListNode holder = new ListNode();
        ListNode curr = holder;

        while (start1 != null && start2 != null) {

            if (start1.val <= start2.val) {
                ListNode next = start1.next;
                curr.next = start1;
                curr = curr.next;
                curr.next = null;
                start1 = next;
            } else {
                ListNode next = start2.next;
                curr.next = start2;
                curr = curr.next;
                curr.next = null;
                start2 = next;
            }
        }

        while (start1 != null) {
            ListNode next = start1.next;
            curr.next = start1;
            curr = curr.next;
            curr.next = null;
            start1 = next;
        }

        while (start2 != null) {
            ListNode next = start2.next;
            curr.next = start2;
            curr = curr.next;
            curr.next = null;
            start2 = next;
        }
        curr.next = null;
        return holder.next;
    }

    private ListNode getMid(ListNode node) {
        ListNode slow = new ListNode();
        slow.next = node;
        ListNode fast =  new ListNode();
        fast.next = node;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode ans = slow.next;
        slow.next = null;
        return ans;
    }
}
