package leetcode.easy;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.

Example 1:

Input: head = [1,1,2]
Output: [1,2]

Example 2:

Input: head = [1,1,2,3,3]
Output: [1,2,3]


Constraints:

The number of nodes in the list is in the range [0, 300].
-100 <= Node.val <= 100
The list is guaranteed to be sorted in ascending order.
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

public class _83_RemoveDuplicateFromSortedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ListNode deleteDuplicates(ListNode head) {

		if (head == null) {
			return head;
		}
		ListNode curr = head;

		while (curr.next != null) {

			if (curr.val == curr.next.val) {
				curr.next = curr.next.next;
			} else {
				curr = curr.next;
			}
		}
		return head;
	}
	//============================================================================
	//Another Version
	public ListNode deleteDuplicates0(ListNode head) {

		if( head == null ) {
			return head;
		}
		ListNode curr = head;

		while( curr != null ) {

			ListNode tempNext = curr.next;

			while( tempNext != null && tempNext.val == curr.val ) {
				tempNext = tempNext.next;
			}
			curr.next = tempNext;
			curr = tempNext;
		}
		return head;
	}
	//==========================================================================
	//Version using stack
	public ListNode deleteDuplicates1(ListNode head) {
		Deque< ListNode > stack = new LinkedList<>();
		ListNode curr = head;

		while( curr != null ) {

			if( !stack.isEmpty() ) {

				while( curr != null && curr.val == stack.peek().val ) {
					curr = curr.next; 
				}
				stack.peek().next = curr;
				stack.push( curr );
				curr = curr != null ? curr.next : null;
			} else {
				stack.push( curr );
				curr = curr.next;
			}
		}
		return head;
	}
}
