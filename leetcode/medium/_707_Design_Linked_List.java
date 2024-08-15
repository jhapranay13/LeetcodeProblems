package leetcode.medium;

/**
 *
 * Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
 * A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
 * If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
 *
 * Implement the MyLinkedList class:
 *
 * MyLinkedList() Initializes the MyLinkedList object.
 * int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
 * void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
 * void addAtTail(int val) Append a node of value val as the last element of the linked list.
 * void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
 * void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * Output
 * [null, null, null, null, 2, null, 3]
 *
 * Explanation
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
 * myLinkedList.get(1);              // return 2
 * myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
 * myLinkedList.get(1);              // return 3
 *
 *
 * Constraints:
 *
 * 0 <= index, val <= 1000
 * Please do not use the built-in LinkedList library.
 * At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.
 *
 */

public class _707_Design_Linked_List {
    // Singly Linked List

    class MyLinkedList {
        class ListNode {
            int val;
            ListNode next;
            ListNode(int x) { val = x; }
        }

        int size;
        ListNode head;  // sentinel node as pseudo-head
        public MyLinkedList() {
            size = 0;
            head = new ListNode(0);
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            // if index is invalid
            if (index < 0 || index >= size) return -1;

            ListNode curr = head;
            // index steps needed
            // to move from sentinel node to wanted index
            for(int i = 0; i < index + 1; ++i) curr = curr.next;
            return curr.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            // If index is greater than the length,
            // the node will not be inserted.
            if (index > size) return;

            // [so weird] If index is negative,
            // the node will be inserted at the head of the list.
            if (index < 0) index = 0;

            ++size;
            // find predecessor of the node to be added
            ListNode pred = head;
            for(int i = 0; i < index; ++i) pred = pred.next;

            // node to be added
            ListNode toAdd = new ListNode(val);
            // insertion itself
            toAdd.next = pred.next;
            pred.next = toAdd;
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            // if the index is invalid, do nothing
            if (index < 0 || index >= size) return;

            size--;
            // find predecessor of the node to be deleted
            ListNode pred = head;
            for(int i = 0; i < index; ++i) pred = pred.next;

            // delete pred.next
            pred.next = pred.next.next;
        }
    }
    //=============================================================================================
    // Doubly Linked List
    class MyLinkedList1 {
        class ListNodeDbl {
            int val;
            ListNodeDbl next;
            ListNodeDbl prev;
            ListNodeDbl(int x) { val = x; }
        }

        int size;
        // sentinel nodes as pseudo-head and pseudo-tail
        ListNodeDbl head, tail;
        public MyLinkedList1() {
            size = 0;
            head = new ListNodeDbl(0);
            tail = new ListNodeDbl(0);
            head.next = tail;
            tail.prev = head;
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            // if index is invalid
            if (index < 0 || index >= size) return -1;

            // choose the fastest way: to move from the head
            // or to move from the tail
            ListNodeDbl curr = head;
            if (index + 1 < size - index)
                for(int i = 0; i < index + 1; ++i) curr = curr.next;
            else {
                curr = tail;
                for(int i = 0; i < size - index; ++i) curr = curr.prev;
            }

            return curr.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            ListNodeDbl pred = head, succ = head.next;

            ++size;
            ListNodeDbl toAdd = new ListNodeDbl(val);
            toAdd.prev = pred;
            toAdd.next = succ;
            pred.next = toAdd;
            succ.prev = toAdd;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            ListNodeDbl succ = tail, pred = tail.prev;

            ++size;
            ListNodeDbl toAdd = new ListNodeDbl(val);
            toAdd.prev = pred;
            toAdd.next = succ;
            pred.next = toAdd;
            succ.prev = toAdd;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            // If index is greater than the length,
            // the node will not be inserted.
            if (index > size) return;

            // [so weird] If index is negative,
            // the node will be inserted at the head of the list.
            if (index < 0) index = 0;

            // find predecessor and successor of the node to be added
            ListNodeDbl pred, succ;
            if (index < size - index) {
                pred = head;
                for(int i = 0; i < index; ++i) pred = pred.next;
                succ = pred.next;
            }
            else {
                succ = tail;
                for (int i = 0; i < size - index; ++i) succ = succ.prev;
                pred = succ.prev;
            }

            // insertion itself
            ++size;
            ListNodeDbl toAdd = new ListNodeDbl(val);
            toAdd.prev = pred;
            toAdd.next = succ;
            pred.next = toAdd;
            succ.prev = toAdd;
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            // if the index is invalid, do nothing
            if (index < 0 || index >= size) return;

            // find predecessor and successor of the node to be deleted
            ListNodeDbl pred, succ;
            if (index < size - index) {
                pred = head;
                for(int i = 0; i < index; ++i) pred = pred.next;
                succ = pred.next.next;
            }
            else {
                succ = tail;
                for (int i = 0; i < size - index - 1; ++i) succ = succ.prev;
                pred = succ.prev.prev;
            }

            // delete pred.next
            --size;
            pred.next = succ;
            succ.prev = pred;
        }
    }
}
