package leetcode.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node c1:

	 a1 -> a2 
			 \
			  c1 -> c2 -> c3
			 /
b1 -> b2 -> b3

It is guaranteed that there are no cycles anywhere in the entire linked structure.

Note that the linked lists must retain their original structure after the function returns.

Example 1:

	    4 -> 1  
			 \
			  8 -> 4 -> 5
			 /
  5 -> 6 -> 1


Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.

Example 2:

   1 -> 9 -> 1  
			 \
			  2 -> 4
			 /
  			3

Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'
Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.

Example 3:

 2 -> 6 -> 4

 1 -> 5

Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection
Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.

Constraints:

The number of nodes of listA is in the m.
The number of nodes of listB is in the n.
0 <= m, n <= 3 * 104
1 <= Node.val <= 105
0 <= skipA <= m
0 <= skipB <= n
intersectVal is 0 if listA and listB do not intersect.
intersectVal == listA[skipA + 1] == listB[skipB + 1] if listA and listB intersect.

Follow up: Could you write a solution that runs in O(n) time and use only O(1) memory?
 *
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class _160_IntersectionOfTwoLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        if (headA == null) {
            return headA;
        }
        
        if (headB == null) {
            return headB;
        }
        ListNode currA = headA;
        ListNode currB = headB;
        int lengthA = 0;
        int lengthB = 0;
        
        while (currA != null) {
            currA = currA.next;
            lengthA++;
        }
        
        while (currB != null) {
            currB = currB.next;
            lengthB++;
        }
        currA = headA;
        currB = headB;
        
        while (currA != null && lengthA > lengthB) {
            lengthA--;
            currA = currA.next;
        }
        
        while (currB != null && lengthB > lengthA) {
            lengthB--;
            currB = currB.next;
        }
        
        while (currA != null && currB != null) {
            
            if (currA.equals(currB)) {
                return currA;
            }
            currA = currA.next;
            currB = currB.next;
        }
        return null;
    }
}
