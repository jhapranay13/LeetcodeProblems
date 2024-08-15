package leetcode.LinkedList;

/**
 *
 *
 * Design a queue that supports push and pop operations in the front, middle, and back.
 *
 * Implement the FrontMiddleBack class:
 *
 * FrontMiddleBack() Initializes the queue.
 * void pushFront(int val) Adds val to the front of the queue.
 * void pushMiddle(int val) Adds val to the middle of the queue.
 * void pushBack(int val) Adds val to the back of the queue.
 * int popFront() Removes the front element of the queue and returns it. If the queue is empty, return -1.
 * int popMiddle() Removes the middle element of the queue and returns it. If the queue is empty, return -1.
 * int popBack() Removes the back element of the queue and returns it. If the queue is empty, return -1.
 * Notice that when there are two middle position choices, the operation is performed on the frontmost middle position choice. For example:
 *
 * Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
 * Popping the middle from [1, 2, 3, 4, 5, 6] returns 3 and results in [1, 2, 4, 5, 6].
 *
 *
 * Example 1:
 *
 * Input:
 * ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
 * [[], [1], [2], [3], [4], [], [], [], [], []]
 * Output:
 * [null, null, null, null, null, 1, 3, 4, 2, -1]
 *
 * Explanation:
 * FrontMiddleBackQueue q = new FrontMiddleBackQueue();
 * q.pushFront(1);   // [1]
 * q.pushBack(2);    // [1, 2]
 * q.pushMiddle(3);  // [1, 3, 2]
 * q.pushMiddle(4);  // [1, 4, 3, 2]
 * q.popFront();     // return 1 -> [4, 3, 2]
 * q.popMiddle();    // return 3 -> [4, 2]
 * q.popMiddle();    // return 4 -> [2]
 * q.popBack();      // return 2 -> []
 * q.popFront();     // return -1 -> [] (The queue is empty)
 *
 *
 * Constraints:
 *
 * 1 <= val <= 10^9
 * At most 1000 calls will be made to pushFront, pushMiddle, pushBack, popFront, popMiddle, and popBack.
 *
 *
 */

public class _1670_Design_Front_Middle_Back_Queue {
    class FrontMiddleBackQueue {
        private int size;
        private Node head, tail, mid;

        public FrontMiddleBackQueue() {
            size = 0;
            head = tail = new Node(-1);
            head.next = tail;
            tail.prev = head;
            mid = head;     // default mid position
        }

        public void pushFront(int val) {
            Node newNode = new Node(val);
            add(head, newNode); // new node is added right to head
            size++;

            if (size == 1) {    // if its the first ever node
                mid = head.next;    // make it the mid
            }
            if (size % 2 == 0) {    // mid is the former of the two mids
                mid = mid.prev;
            }
        }

        public void pushMiddle(int val) {
            Node newNode = new Node(val);
            // if size is even, new mid will be at perfect centre
            // that is, right next to mid
            if (size % 2 == 0) {
                add(mid, newNode);  // goes right next to mid
                mid = mid.next;
            }
            // else new mid will go to the left of current mid
            // i.e. the right next position of mid's current prev
            else {
                add(mid.prev, newNode); // go next to mid's prev
                mid = mid.prev;
            }
            size++;
        }

        public void pushBack(int val) {
            Node newNode = new Node(val);
            // gets added right before tail
            // i.e. right next to tail's current prev
            add(tail.prev, newNode);
            size++;
            // if size becomes odd, mid will move to perfect centre
            if (size % 2 == 1) {
                mid = mid.next;
            }
        }

        public int popFront() {
            if (size == 0) {
                return -1;
            }
            if (size == 1) {  // if list wil become empty
                mid = head;   // default mid is head
            } else if (size % 2 == 0) {
                // list will become odd, mid will move to next node
                mid = mid.next;
            }

            int val = head.next.value;
            delete(head.next);  // delete the node next to head
            size--;
            return val;
        }

        public int popMiddle() {
            if (size == 0) {
                return -1;
            }

            Node node = mid;
            if (size == 1) {  // list wil become empty
                mid = head;   // default mid is head
            } else {
                // if list will become odd, mid will go to next node
                // if list will become even, mid will go to prev node
                mid = (size % 2 == 0)? mid.next : mid.prev;
            }

            delete(node);   // delete the node (old mid)
            size--;
            return node.value;
        }

        public int popBack() {
            if (size == 0) {
                return -1;
            }
            if (size == 1) {  // list wil become empty
                mid = head;   // default mid is head
            } else if (size % 2 == 1) { // if list will become even
                mid = mid.prev;         // mid will move to prev node
            }

            int val = tail.prev.value;
            delete(tail.prev);  // delete the preceding node of tail
            size--;
            return val;
        }

        // Adds a node `newNode` right next to a reference node `ref`
        private void add(Node ref, Node newNode) {
            newNode.next = ref.next;
            ref.next = newNode;
            newNode.next.prev = newNode;
            newNode.prev = ref;
        }

        // Deletes the passed in node 'node'
        private void delete(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        // Node class
        private class Node {
            int value;
            Node next, prev;
            private Node(int value) {
                this.value = value;
            }
        }
    }
}
