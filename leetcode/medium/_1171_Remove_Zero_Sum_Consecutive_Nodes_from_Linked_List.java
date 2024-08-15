package leetcode.medium;

/**
 *
 * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
 *
 * After doing so, return the head of the final linked list.  You may return any such answer.
 *
 *
 *
 * (Note that in the examples below, all sequences are serializations of ListNode objects.)
 *
 * Example 1:
 *
 * Input: head = [1,2,-3,3,1]
 * Output: [3,1]
 * Note: The answer [1,2,1] would also be accepted.
 * Example 2:
 *
 * Input: head = [1,2,3,-3,4]
 * Output: [1,2,4]
 * Example 3:
 *
 * Input: head = [1,2,3,-3,-2]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The given linked list will contain between 1 and 1000 nodes.
 * Each node in the linked list has -1000 <= node.val <= 1000.
 *
 */

import java.util.HashMap;
import java.util.Map;

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


public class _1171_Remove_Zero_Sum_Consecutive_Nodes_from_Linked_List {
    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode anchor = new ListNode();
        anchor.next = head;
        ListNode curr = head;
        int sum = 0;
        Map<Integer, ListNode> sumHolder = new HashMap<>();
        sumHolder.put(sum, anchor);

        while (curr != null) {
            sum += curr.val;

            if (sumHolder.containsKey(sum)) {
                ListNode temp = sumHolder.get(sum);
                temp = temp.next;
                int tempSum = sum;

                while (temp != curr) {
                    tempSum += temp.val;
                    sumHolder.remove(tempSum);
                    temp = temp.next;
                }
                sumHolder.get(sum).next = curr.next;
            } else {
                sumHolder.put(sum, curr);
            }

            curr = curr.next;
        }
        return anchor.next;
    }
}
