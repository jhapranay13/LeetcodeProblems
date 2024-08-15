package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *You are given the head of a singly linked-list. The list can be represented as:

L0 -> L1 -> � -> Ln - 1 -> Ln
Reorder the list to be on the following form:

L0 -> Ln -> L1 -> Ln - 1 -> L2 -> Ln - 2 -> �
You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 
 Example 1:

Input: head = [1,2,3,4]
Output: [1,4,2,3]

Example 2:

Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]
 
Constraints:

The number of nodes in the list is in the range [1, 5 * 104].
1 <= Node.val <= 1000
 *
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

public class _143_ReorderList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public void reorderList(ListNode head) {

		if (head == null || head.next == null || head.next.next == null) {
			return;
		}
		int size = 1;
		ListNode fast = head;
		ListNode slow = head;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;

			if (fast == null) {
				size++;
			} else {
				size += 2;
			}
			slow = slow.next;
		}
		slow = size % 2 == 0 ? slow : slow.next;
		Deque<ListNode> stack = new LinkedList<>();

		while (slow != null) {
			stack.push(slow);
			slow = slow.next;
		}
		ListNode curr = head;

		while (!stack.isEmpty()) {
			ListNode temp = curr.next;
			ListNode stackNode = stack.pop();
			curr.next = stackNode;
			stackNode.next = temp;
			curr = temp;
		}
		curr.next = null;
	}
}
