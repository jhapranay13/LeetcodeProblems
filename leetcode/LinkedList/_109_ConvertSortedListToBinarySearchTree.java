package leetcode.LinkedList;

/**
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * -10 --> -3 --> 0 --> 5 --> 9
 * <p>
 * 0
 * /   \
 * -3     9
 * /      /
 * -10     5
 * <p>
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
 * <p>
 * Example 2:
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * Example 3:
 * <p>
 * Input: head = [0]
 * Output: [0]
 * Example 4:
 * <p>
 * Input: head = [1,3]
 * Output: [3,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in head is in the range [0, 2 * 104].
 * -105 <= Node.val <= 105
 */




public class _109_ConvertSortedListToBinarySearchTree {

    private class ListNode {
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

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

    }

    //=============================================================================================
    //Approach 1 find the mid.
    public TreeNode sortedListToBST(ListNode head) {

        if (head == null) {
            return null;
        }
        ListNode mid = findMid(head);
        TreeNode node = new TreeNode(mid.val);

        if (head == mid) {
            return node;
        }
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(mid.next);
        return node;
    }

    private ListNode findMid(ListNode node) {
        ListNode fast = node;
        ListNode slow = node;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (prev == null) {
            prev = slow;
        }
        prev.next = null;
        return slow;
    }
    //=============================================================================================
    //Simulate in order traversal
    public TreeNode sortedListToBST1(ListNode head) {

        if (head == null) {
            return null;
        }
        int size = findSize(head);
        ln = head;
        TreeNode ans = recur(1, size);
        return ans;
    }
    private ListNode ln = null;

    private TreeNode recur(int lo, int hi) {

        if (lo > hi) {
            return null;
        }
        int mid = lo + (hi - lo) / 2;
        TreeNode node = new TreeNode();
        node.left = recur(lo, mid - 1);
        node.val = ln.val;
        ln = ln.next;
        node.right = recur(mid + 1, hi);
        return node;
    }

    private int findSize(ListNode node) {
        int size = 0;

        while (node != null) {
            size++;
            node = node.next;
        }
        return size;
    }
}
