package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 * 
 *         You are given two non-empty linked lists representing two
 *         non-negative integers. The most significant digit comes first and
 *         each of their nodes contains a single digit. Add the two numbers and
 *         return the sum as a linked list.
 * 
 *         You may assume the two numbers do not contain any leading zero,
 *         except the number 0 itself.
 * 
 *         Example 1:
 * 
 *         Input: l1 = [7,2,4,3], l2 = [5,6,4] Output: [7,8,0,7] 
 *         
 *         Example 2:
 * 
 *         Input: l1 = [2,4,3], l2 = [5,6,4] Output: [8,0,7] 
 *         
 *         Example 3:
 * 
 *         Input: l1 = [0], l2 = [0] Output: [0]
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in each linked list is in the range [1, 100]. 0
 *         <= Node.val <= 9 It is guaranteed that the list represents a number
 *         that does not have leading zeros.
 * 
 * 
 *         Follow up: Could you solve it without reversing the input lists?
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

public class _445_AddTwoNumbers2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	// =============================================================================
	// Using Stack
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		Deque<Integer> stack1 = new LinkedList<>();
		Deque<Integer> stack2 = new LinkedList<>();

		while (l1 != null || l2 != null) {

			if (l1 != null) {
				stack1.push(l1.val);
				l1 = l1.next;
			}

			if (l2 != null) {
				stack2.push(l2.val);
				l2 = l2.next;
			}
		}
		int carry = 0;
		ListNode prev = null;

		while (!stack1.isEmpty() || !stack2.isEmpty()) {
			int num1 = 0;
			int num2 = 0;

			if (!stack1.isEmpty()) {
				num1 = stack1.pop();
			}

			if (!stack2.isEmpty()) {
				num2 = stack2.pop();
			}
			int sum = num1 + num2 + carry;
			carry = sum / 10;

			if (prev == null) {
				prev = new ListNode(sum % 10);
			} else {
				ListNode temp = new ListNode(sum % 10);
				temp.next = prev;
				prev = temp;
			}
		}

		if (carry != 0) {
			ListNode temp = new ListNode(carry);
			temp.next = prev;
			prev = temp;
		}
		return prev;
	}

	// =============================================================================
	// Reversing LinkedList
	public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
		ListNode l1Head = reverse(l1);

		ListNode l2Head = reverse(l2);
		ListNode ans = null;
		ListNode next = null;
		int carry = 0;

		while (l1Head != null || l2Head != null) {
			int num1 = 0;
			int num2 = 0;

			if (l1Head != null) {
				num1 = l1Head.val;
				l1Head = l1Head.next;
			}

			if (l2Head != null) {
				num2 = l2Head.val;
				l2Head = l2Head.next;
			}
			int sum = num1 + num2 + carry;
			int val = sum % 10;
			ListNode node = new ListNode();
			node.val = val;
			node.next = next;
			ans = node;
			next = node;
			carry = sum / 10;
		}

		if (carry != 0) {
			ListNode node = new ListNode();
			node.val = carry;
			node.next = ans;
			ans = node;
		}
		return ans;
	}

	private ListNode reverse(ListNode node) {
		ListNode prev = null;
		ListNode next = null;

		while (node != null) {
			next = node.next;
			node.next = prev;
			prev = node;

			if (next == null) {
				break;
			}
			node = next;
		}
		return node;
	}
}
