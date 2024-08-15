package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *Given the head of a linked list, rotate the list to the right by k places.

Example 1:

Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]

Example 2:

Input: head = [0,1,2], k = 4
Output: [2,0,1]

Constraints:

The number of nodes in the list is in the range [0, 500].
-100 <= Node.val <= 100
0 <= k <= 2 * 109
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

public class _61_RotateList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_61_RotateList obj = new _61_RotateList();
		ListNode head = new ListNode(1);
		ListNode curr = head;

		for (int i = 2; i <= 5; i++) {
			curr.next = new ListNode(i);
			curr = curr.next;
		}
		obj.rotateRight1(head, 2);
	}

	public ListNode rotateRight(ListNode head, int k) {

		if (head == null || head.next == null || k == 0) {
			return head;
		}
		int length = 0;
		ListNode tempHead = head;

		while (tempHead != null) {
			tempHead = tempHead.next;
			length++;
		}
		k = k % length;

		if (k == 0) {
			return head;
		}
		ListNode slow = head;
		ListNode fast = head;

		while (k > 0) {
			fast = fast.next;
			k--;
		}

		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}
		ListNode newHead = slow.next;
		slow.next = null;
		fast.next = head;
		return newHead;
	}

	//========================================================================================
	//Using Stack
	public ListNode rotateRight1(ListNode head, int k) {

		if (head == null || head.next == null || k == 0) {
			return head;
		}
		Deque<ListNode> stack = new LinkedList<>();
		ListNode tempHead = head;

		while(tempHead != null) {
			stack.push(tempHead);
			tempHead = tempHead.next;
		}
		int length = stack.size();
		k = k % length;

		if (k == 0) {
			return head;
		}
		ListNode tail = stack.peek();
		ListNode newHead = null;

		while (k > 0) {
			newHead = stack.pop();
			k--;
		}
		stack.peek().next = null;
		tail.next = head;
		return newHead;
	}
}
