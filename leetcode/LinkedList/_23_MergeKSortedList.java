package leetcode.LinkedList;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an array of k linked-lists lists, each linked-list is
 *         sorted in ascending order.
 * 
 *         Merge all the linked-lists into one sorted linked-list and return it.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: lists = [[1,4,5],[1,3,4],[2,6]] Output: [1,1,2,3,4,4,5,6]
 *         Explanation: The linked-lists are: [ 1->4->5, 1->3->4, 2->6 ] merging
 *         them into one sorted list: 1->1->2->3->4->4->5->6 Example 2:
 * 
 *         Input: lists = [] Output: [] Example 3:
 * 
 *         Input: lists = [[]] Output: []
 * 
 * 
 *         Constraints:
 * 
 *         k == lists.length 0 <= k <= 10^4 0 <= lists[i].length <= 500 -10^4 <=
 *         lists[i][j] <= 10^4 lists[i] is sorted in ascending order. The sum of
 *         lists[i].length won't exceed 10^4.
 *
 */
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */

public class _23_MergeKSortedList {

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

	// ======================================================================
	// PriorityQueue Solution
	public ListNode mergeKLists(ListNode[] lists) {
		ListNode dummy = new ListNode();
		ListNode runner = dummy;
		PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {

			public int compare(ListNode l1, ListNode l2) {
				return l1.val - l2.val;
			}
		});

		for (ListNode list : lists) {

			while (list != null) {
				pq.offer(list);
				list = list.next;
			}
		}

		while (!pq.isEmpty()) {
			ListNode temp = pq.poll();
			runner.next = temp;
			runner = runner.next;
		}
		runner.next = null;
		return dummy.next;
	}

	// ======================================================================
	// Merge Sort Type Of solution
	public static ListNode mergeKLists1(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}

		if (lists.length == 1) {
			return lists[0];
		}
		int nullCounter = 0;

		/*
		 * for( int i = 0; i < lists.length; i++ ) {
		 * 
		 * if( lists[ i ] == null ) { nullCounter++; } }
		 */

		ListNode listNodeHead = null;
		ListNode runner = null;
		int size = lists.length - nullCounter;
		ListNode current[] = new ListNode[size];
		System.out.println(size);
		for (int i = 0; i < lists.length; i++) {

			if (lists[i] != null) {
				current[i] = lists[i];
				size--;
			}
		}
		int counter = current.length;

		if (counter == 1) {
			return current[0];
		}
		int tempCounter = counter;

		while (tempCounter > 0) {
			int indexMin = -1;
			tempCounter = counter;

			for (int i = 0; i < current.length; i++) {

				if (current[i] == null) {
					tempCounter--;
					continue;
				}

				if (indexMin == -1) {
					indexMin = i;
					continue;
				}

				if (current[i] != null && current[i].val < current[indexMin].val) {
					indexMin = i;
				}
			}

			if (indexMin != -1) {

				if (listNodeHead == null) {
					listNodeHead = runner = current[indexMin];
				} else {
					runner.next = current[indexMin];
					runner = runner.next;
				}
				current[indexMin] = current[indexMin].next;
			}
		}
		return listNodeHead;
	}

	// =====================================================================
	// Array Version of merge Sort
	public static ListNode mergeKLists2(ListNode[] lists) {

		if (lists == null || lists.length == 0) {
			return null;
		}

		if (lists.length == 1) {
			return lists[0];
		}

		ListNode listNodeHead = null;
		ListNode runner = null;
		ListNode current[] = new ListNode[lists.length];

		for (int i = 0, j = 0; i < lists.length; i++) {
			current[i] = lists[j++];
		}
		int counter = current.length;

		if (counter == 1) {
			return current[0];
		}
		int tempCounter = counter;

		while (tempCounter > 0) {
			int indexMin = -1;
			tempCounter = counter;

			for (int i = 0; i < current.length; i++) {

				if (current[i] == null) {
					tempCounter--;
					continue;
				}

				if (indexMin == -1) {
					indexMin = i;
					continue;
				}

				if (current[i] != null && current[i].val < current[indexMin].val) {
					indexMin = i;
				}
			}

			if (indexMin != -1) {

				if (listNodeHead == null) {
					listNodeHead = runner = current[indexMin];
				} else {
					runner.next = current[indexMin];
					runner = runner.next;
				}
				current[indexMin] = current[indexMin].next;
			}
		}
		return listNodeHead;
	}
}
