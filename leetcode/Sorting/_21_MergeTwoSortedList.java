package leetcode.Sorting;

/**
 * 
 * @author Pranay Jha
 *
 *Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together the nodes of the first two lists.

Example 1:

Input: l1 = [1,2,4], l2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:

Input: l1 = [], l2 = []
Output: []

Example 3:

Input: l1 = [], l2 = [0]
Output: [0]


Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both l1 and l2 are sorted in non-decreasing order.
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

public class _21_MergeTwoSortedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		if (l1 == null) {
			return l2;
		}

		if (l2 == null) {
			return l1;
		}
		ListNode ansHead = null;
		ListNode curr = null;

		while (l1 != null && l2 != null) {
			ListNode temp  = null;

			if (l1.val < l2.val) {
				temp = l1;
				l1 = l1.next;
			} else {
				temp = l2;
				l2 = l2.next;
			}

			if (ansHead == null) {
				ansHead = curr = temp;
			} else {
				curr.next = temp;
				curr = temp;
			}
		}

		while (l1 != null) {
			curr.next = l1;
			curr = l1;
			l1 = l1.next;
		}

		while (l2 != null) {
			curr.next = l2;
			curr = l2;
			l2 = l2.next;
		}
		return ansHead;
	}
	//Time complexity O(N) space complexity O(1)
}
