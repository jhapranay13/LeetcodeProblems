package leetcode.LinkedList;

/**
 *
 * You are given the head of a linked list with n nodes.
 *
 * For each node in the list, find the value of the next greater node. That is, for each node, find the value of the first node that is next to it and has a strictly larger value than it.
 *
 * Return an integer array answer where answer[i] is the value of the next greater node of the ith node (1-indexed). If the ith node does not have a next greater node, set answer[i] = 0.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [2,1,5]
 * Output: [5,5,0]
 * Example 2:
 *
 *
 * Input: head = [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= n <= 10^4
 * 1 <= Node.val <= 10^9
 *
 */

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
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

public class _1019_Next_Greater_Node_In_Linked_List {
    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public int[] nextLargerNodes(ListNode head) {
        int length = 0;
        ListNode curr = head;
        Map<ListNode, Integer> indexMap = new HashMap<>();

        while (curr != null) {
            indexMap.put(curr, length); // setting index
            length++;
            curr = curr.next;
        }
        Deque<ListNode> monoDecreasing = new LinkedList<>();
        curr = head;
        int[] ans = new int[length];

        while (curr != null) {

            while (!monoDecreasing.isEmpty() && monoDecreasing.peek().val < curr.val) {
                ListNode temp = monoDecreasing.pop();
                int index = indexMap.get(temp);
                ans[index] = curr.val;
            }
            monoDecreasing.push(curr);
            curr = curr.next;
        }
        return ans;
    }
}
