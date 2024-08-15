package leetcode.LinkedList;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the head of a singly linked list, return true if it is a
 *         palindrome.
 * 
 *         Example 1:
 * 
 * 
 *         Input: head = [1,2,2,1] Output: true Example 2:
 * 
 * 
 *         Input: head = [1,2] Output: false
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the list is in the range [1, 105]. 0 <=
 *         Node.val <= 9
 * 
 * 
 *         Follow up: Could you do it in O(n) time and O(1) space?
 *
 */

public class _234_PalindromeLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean isPalindrome(ListNode head) {

		if (head == null || head.next == null) {
			return true;
		}
		int length = getLength(head);
		Deque<ListNode> stack = new LinkedList<>();
		ListNode mid = getMid(head, length);

		if (length % 2 != 0) {
			divide(mid.next, stack);
		} else {
			divide(mid, stack);
		}
		return check(head, stack);
	}

	private boolean check(ListNode node, Deque<ListNode> stack) {

		while (!stack.isEmpty()) {

			if (node.val != stack.peek().val) {
				return false;
			}
			stack.pop();
			node = node.next;
		}
		return true;
	}

	private void divide(ListNode node, Deque<ListNode> stack) {

		while (node != null) {
			stack.push(node);
			node = node.next;
		}
	}

	private ListNode getMid(ListNode node, int length) {
		int mid = length / 2;

		while (mid-- > 0) {
			node = node.next;
		}
		return node;
	}

	private int getLength(ListNode node) {
		int length = 0;

		while (node != null) {
			node = node.next;
			length++;
		}
		return length;
	}

	/*
	 * Another solution Find the end of the first half. Reverse the second half.
	 * Determine whether or not there is a palindrome. Restore the list. Return the
	 * result.
	 */
}
