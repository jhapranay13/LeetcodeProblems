package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         Given the head of a singly linked list, group all the nodes with odd
 *         indices together followed by the nodes with even indices, and return
 *         the reordered list.
 * 
 *         The first node is considered odd, and the second node is even, and so
 *         on.
 * 
 *         Note that the relative order inside both the even and odd groups
 *         should remain as it was in the input.
 * 
 *         You must solve the problem in O(1) extra space complexity and O(n)
 *         time complexity.
 * 
 * 
 *         Example 1:
 * 
 * 
 *         Input: head = [1,2,3,4,5] Output: [1,3,5,2,4] 
 *         
 *         Example 2:
 * 
 * 
 *         Input: head = [2,1,3,5,6,4,7] Output: [2,3,6,7,1,5,4]
 * 
 * 
 *         Constraints:
 * 
 *         n == number of nodes in the linked list 0 <= n <= 104 -106 <=
 *         Node.val <= 106
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

public class _328_OddEvenLinkedList {

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

	public ListNode oddEvenList(ListNode head) {

		if (head == null || head.next == null) {
			return head;
		}
		ListNode oddHead = null;
		ListNode evenHead = null;
		ListNode oddCurr = null;
		ListNode evenCurr = null;
		int count = 0;

		while (head != null) {
			count++;

			if (count % 2 == 0) {

				if (evenHead == null) {
					evenHead = head;
					evenCurr = head;
				} else {
					evenCurr.next = head;
					evenCurr = head;
				}
			} else {

				if (oddHead == null) {
					oddHead = head;
					oddCurr = head;
				} else {
					oddCurr.next = head;
					oddCurr = head;
				}
			}
			head = head.next;
		}
		oddCurr.next = evenHead;
		evenCurr.next = null;
		return oddHead;
	}
}
