package leetcode.LinkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Notice that you should not modify the linked list.

Example 1:
				3--->2---->0----->-4
	             	^_____________|


Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:
				 1------------>2
	             ^_____________|

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.

Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.

Follow up: Can you solve it using O(1) (i.e. constant) memory?
 *
 */
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class _142_LinkedListCycle2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	//==============================================================================
	//HashSet approach
	public ListNode detectCycle(ListNode head) {
		Set<ListNode> visited = new HashSet<>();

		while (head != null) {

			if (!visited.add(head)) {
				return head;
			}
			head = head.next;
		}
		return null;
	}
	//==============================================================================
	//Fast And slow Pointer approach
	public ListNode detectCycle1(ListNode head) {
		ListNode intersection = findIntersection(head);

		if (intersection == null) {
			return null;
		}
		int loopLength = 0;
		ListNode curr = intersection;
		
		do {
			curr = curr.next;
			loopLength++;
		} while (curr != intersection);
		ListNode slow = head;
		ListNode fast = head;
		//get fast loop distance ahead of slow.
		while (loopLength-- > 0) {
			fast = fast.next;
		}

		while(slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

	public ListNode findIntersection(ListNode node) {
		ListNode slow = node;
		ListNode fast = node;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;

			if (fast == slow) {
				return slow;
			}
		}
		return null;
	}
	//==============================================================================
	//Fast And slow Pointer approach Floyd's algorithm.
	public ListNode detectCycle2(ListNode head) {
        ListNode intersection = findIntersection(head);
        
        if (intersection == null) {
            return null;
        }
        ListNode curr = head;
        //Since fast pointer travels twice the length of slow. if we
        //start one pointer from head and one pointer from point of
        //intersection they should meet again at the start of cycle.
        while(curr != intersection) {
            curr = curr.next;
            intersection = intersection.next;
        }
        return curr;
    }
}
