package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Return any binary tree that matches the given preorder and postorder
 *         traversals.
 * 
 *         Values in the traversals pre and post are distinct positive integers.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1] Output:
 *         [1,2,3,4,5,6,7]
 * 
 * 
 *         Note:
 * 
 *         1 <= pre.length == post.length <= 30 pre[] and post[] are both
 *         permutations of 1, 2, ..., pre.length. It is guaranteed an answer
 *         exists. If there exists multiple answers, you can return any of them.
 *
 */
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */

public class _889_ConstructBSTFromPreAndPostOrderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class TreeNode {
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

	// =============================================================================
	// Using Pre Order position Map
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
		Map<Integer, Integer> prePosMap = getOrderPositionMap(pre);
		index = post.length - 1;
		TreeNode ans = construct(prePosMap, pre, post, 0, post.length - 1);
		return ans;
	}

	private int index = 0;

	private TreeNode construct(Map<Integer, Integer> prePosMap, int[] pre, int[] post, int lo, int hi) {

		if (lo > hi) {
			return null;
		}
		int val = post[index--];
		TreeNode node = new TreeNode();
		node.val = val;
		int position = prePosMap.get(val);
		node.right = construct(prePosMap, pre, post, position + 1, hi);

		if (node.right != null) {
			int rightPos = prePosMap.get(node.right.val);
			node.left = construct(prePosMap, pre, post, position + 1, rightPos - 1);
		}
		return node;
	}

	private Map<Integer, Integer> getOrderPositionMap(int[] order) {
		Map<Integer, Integer> orderPosMap = new HashMap<>();

		for (int i = 0; i < order.length; i++) {
			orderPosMap.put(order[i], i);
		}
		return orderPosMap;
	}
	//============================================================================
	//using Post Order Position Map
	public TreeNode constructFromPrePost1(int[] pre, int[] post) {
        Map<Integer, Integer> postPosMap = getOrderPositionMap(post);
        TreeNode ans = construct1(postPosMap, pre, post, 0, pre.length - 1);
        return ans;
    }
    
    private TreeNode construct1(Map<Integer, Integer> postPosMap, int[] pre, int[] post, 
                               int lo, int hi) {
        
        if (lo > hi) {
            return null;
        }
        int val = pre[index++];
        TreeNode node = new TreeNode();
        node.val = val;
        int position = postPosMap.get(val);
        node.left = construct(postPosMap, pre, post, lo, position - 1);
        
        if (node.left != null) {
            int leftPos = postPosMap.get(node.left.val);
            node.right = construct(postPosMap, pre, post, leftPos + 1, position - 1);
        }
        return node;
    }
	//=============================================================================================
	//Another Approach Pre Order Map
	public TreeNode constructFromPrePost3(int[] preorder, int[] postorder) {
		Map<Integer, Integer> indexMap = new HashMap<>();

		for (int i = 0; i < preorder.length; i++) {
			indexMap.put(preorder[i], i);
		}
		index = postorder.length - 1;
		return recur(postorder, indexMap, 0, postorder.length - 1);
	}

	private TreeNode recur(int[] postorder, Map<Integer, Integer> indexMap, int lo, int hi) {

		if (lo > hi || index < 0) {
			return null;
		}
		int currVal = postorder[index--];
		int currIndex = indexMap.get(currVal);
		TreeNode node = new TreeNode(currVal);

		if (index < 0) {
			return node;
		}
		int nextIndex = indexMap.get(postorder[index]);
		//next right cannot lie in the left side
		if (currIndex > nextIndex) {
			return node;
		}
		node.right = recur(postorder, indexMap, nextIndex, hi);
		node.left = recur(postorder, indexMap, currIndex + 1, nextIndex - 1);
		return node;
	}
	//=============================================================================================
	//Different Approach postoreder Map
	public TreeNode constructFromPrePost4(int[] preorder, int[] postorder) {
		Map<Integer, Integer> indexMap = new HashMap<>();

		for (int i = 0; i < postorder.length; i++) {
			indexMap.put(postorder[i], i);
		}
		index = 0;
		return recur1(preorder, indexMap, 0, postorder.length - 1);
	}

	private TreeNode recur1(int[] preorder, Map<Integer, Integer> indexMap, int lo, int hi) {

		if (lo > hi || index == preorder.length) {
			return null;
		}
		int currVal = preorder[index++];
		int currIndex = indexMap.get(currVal);
		TreeNode node = new TreeNode(currVal);

		if (index == preorder.length) {
			return node;
		}
		int nextIndex = indexMap.get(preorder[index]);
		//next right cannot lie in the right side
		if (currIndex < nextIndex) {
			return node;
		}
		node.left = recur1(preorder, indexMap, lo, currIndex);
		node.right = recur1(preorder, indexMap, nextIndex + 1, currIndex - 1);
		return node;
	}
}
