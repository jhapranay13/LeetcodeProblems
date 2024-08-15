package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *Given the head of a singly linked list, reverse the list, and return the reversed list.

Example 1:

Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Example 2:

Input: head = [1,2]
Output: [2,1]

Example 3:

Input: head = []
Output: []

Constraints:

The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000


Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
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


public class _206_ReverseLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	//=============================================================================
	//Iterative Solution
	public ListNode reverseList(ListNode head) {
		ListNode curr = head;
		ListNode prev = null;

		while (curr != null) {
			ListNode next = curr.next;

			if (next == null) {
				curr.next = prev;
				break;
			}
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return curr;
	}
	//=============================================================================
	//Recursive Solution
	public ListNode reverseList1(ListNode head) {

		if (head != null) {
			ListNode tail = recur(head);
			tail.next = null;
		}
		return ans;    
	}

	private ListNode ans = null;

	private ListNode recur(ListNode node) {

		if (node.next == null) {
			ans = node;
			return node;
		}
		ListNode prev = recur(node.next);
		prev.next = node;
		return node;
	}
	//===============================================================================
	//Another recursive version
	public ListNode reverseList2(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode p = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return p;
	}
	//Can also be done using Stack.
	public ListNode reverseList3(ListNode head) {

		if( head == null || head.next == null ) {
			return head;
		}
		Deque< ListNode > stack = new LinkedList<>();

		while( head != null ) {
			stack.push( head );
			head = head.next;
		}
		ListNode curr = null;
		ListNode ans = null;

		while( !stack.isEmpty() ) {
			curr = stack.pop();

			if( ans == null ) {
				ans = curr;
			}
			curr.next = stack.isEmpty() ? null : stack.peek();
		}
		return ans;
	}
}
