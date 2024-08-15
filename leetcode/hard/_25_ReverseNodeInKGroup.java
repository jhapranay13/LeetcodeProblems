package leetcode.hard;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a linked list, reverse the nodes of a linked list k at a time
 *         and return its modified list.
 * 
 *         k is a positive integer and is less than or equal to the length of
 *         the linked list. If the number of nodes is not a multiple of k then
 *         left-out nodes, in the end, should remain as it is.
 * 
 *         You may not alter the values in the list's nodes, only nodes
 *         themselves may be changed.
 * 
 *         Example 1:
 * 
 * 
 *         Input: head = [1,2,3,4,5], k = 2 Output: [2,1,4,3,5] 
 *         
 *         Example 2:
 * 
 * 
 *         Input: head = [1,2,3,4,5], k = 3 Output: [3,2,1,4,5] 
 *         
 *         Example 3:
 * 
 *         Input: head = [1,2,3,4,5], k = 1 Output: [1,2,3,4,5] 
 *         
 *         Example 4:
 * 
 *         Input: head = [1], k = 1 Output: [1]
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the list is in the range sz. 1 <= sz <= 5000 0
 *         <= Node.val <= 1000 1 <= k <= sz
 * 
 * 
 *         Follow-up: Can you solve the problem in O(1) extra memory space?
 *
 */
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */

public class _25_ReverseNodeInKGroup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public class ListNode {
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

	public ListNode reverseKGroup(ListNode head, int k) {

		if (head == null || head.next == null || k == 1) {
			return head;
		}
		ListNode start = head;
		ListNode curr = head;
		ListNode lastChunk = null;
		;
		ListNode ans = null;
		int count = k;

		while (curr != null) {
			count--;
			curr = curr.next;

			if (count == 1 && curr != null) {
				count = k;
				ListNode temp = curr.next;

				if (ans == null) {
					ans = curr;
				}
				reverse(start, curr);

				if (lastChunk == null) {
					lastChunk = start;
				} else {
					lastChunk.next = curr;
					lastChunk = start;
				}
				start = temp;
				curr = temp;
			}
		}

		if (start != null) {
			lastChunk.next = start;
		}

		return ans == null ? head : ans;
	}

	private void reverse(ListNode start, ListNode end) {
		ListNode curr = start;
		ListNode prev = null;

		while (curr != end) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		end.next = prev;
	}
}
