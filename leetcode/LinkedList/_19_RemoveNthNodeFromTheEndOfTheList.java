package leetcode.LinkedList;


/**
 * 
 * @author Pranay Jha
 *
 *Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:

Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:

Input: head = [1], n = 1
Output: []

Example 3:

Input: head = [1,2], n = 1
Output: [1]


Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz


Follow up: Could you do this in one pass?
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

class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class _19_RemoveNthNodeFromTheEndOfTheList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode fast = head;
		ListNode slow = head;
		ListNode slowPrev = null;

		while (n-- > 1 && fast.next != null) {
			fast = fast.next;
		}

		while (fast.next != null) {
			fast = fast.next;
			slowPrev = slow;
			slow = slow.next;    
		}

		if (slowPrev == null) {
			return slow != null ? slow.next : null;
		} else {
			slowPrev.next = slow.next;
		}
		return head;
	}
	//Time complexity O(n) space complexity constant or O(1).
}
