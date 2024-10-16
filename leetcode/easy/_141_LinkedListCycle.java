package leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.



Example 1:
			3--->2---->0----> -4
	             ^_____________|

Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Example 2:
				 1------------>2
	             ^_____________|

Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

Example 3:


Input: head = [1], pos = -1
Output: false
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

public class _141_LinkedListCycle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//Fast and Slow Pointer/ Tortoise or Hare
	public boolean hasCycle(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;

		while (fast != null) {
			slow = slow.next;
			fast = fast.next;
			fast = fast == null ? null : fast.next;

			if (fast != null && slow != null && fast.equals(slow)) {
				return true;
			}
		}
		return false;
	}
	//==============================================================================
	//HashSet Approach
	public boolean hasCycle1(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        
        while (head != null) {
            
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}