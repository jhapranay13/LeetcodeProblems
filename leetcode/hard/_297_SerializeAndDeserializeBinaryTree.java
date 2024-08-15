package leetcode.hard;

/**
 * 
 * @author Pranay Jha
 *
 *         Serialization is the process of converting a data structure or object
 *         into a sequence of bits so that it can be stored in a file or memory
 *         buffer, or transmitted across a network connection link to be
 *         reconstructed later in the same or another computer environment.
 * 
 *         Design an algorithm to serialize and deserialize a binary tree. There
 *         is no restriction on how your serialization/deserialization algorithm
 *         should work. You just need to ensure that a binary tree can be
 *         serialized to a string and this string can be deserialized to the
 *         original tree structure.
 * 
 *         Clarification: The input/output format is the same as how LeetCode
 *         serializes a binary tree. You do not necessarily need to follow this
 *         format, so please be creative and come up with different approaches
 *         yourself.
 * 
 * 
 * 
 *         Example 1:
 *         
 *         						1
 *         					  /	  \
 *         					 2	   3			
 *                                / \
 *                               4   5	
 * 
 *         Input: root = [1,2,3,null,null,4,5] Output: [1,2,3,null,null,4,5]
 *         Example 2:
 * 
 *         Input: root = [] Output: [] Example 3:
 * 
 *         Input: root = [1] Output: [1] Example 4:
 * 
 *         Input: root = [1,2] Output: [1,2]
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [0, 104]. -1000 <=
 *         Node.val <= 1000
 *
 */
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */

public class _297_SerializeAndDeserializeBinaryTree {

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

	public class Codec {

		// Encodes a tree to a single string.
		public String serialize(TreeNode root) {

			if (root == null) {
				return "";
			}
			StringBuilder builder = new StringBuilder();
			serializeHelper(root, builder);
			return builder.toString();
		}

		public void serializeHelper(TreeNode node, StringBuilder builder) {

			if (node == null) {
				builder.append("null,");
				return;
			}
			builder.append(node.val + ",");
			serializeHelper(node.left, builder);
			serializeHelper(node.right, builder);
		}

		// Decodes your encoded data to tree.
		public TreeNode deserialize(String data) {

			if (data == null || data.trim().equals("")) {
				return null;
			}
			StringBuilder builder = new StringBuilder(data);
			return deSerializeHelper(builder);
		}

		public TreeNode deSerializeHelper(StringBuilder data) {

			if (data == null || data.equals("")) {
				return null;
			}
			int index = data.indexOf(",");
			String currentData = null;

			if (index != -1) {
				currentData = data.substring(0, index);
			} else {
				currentData = data.toString();
			}

			if (currentData.equals("null") || currentData.equals("")) {

				if (index == -1) {
					data.delete(0, data.length());
				} else {
					data.delete(0, index + 1);
				}
				return null;
			}
			StringBuilder forwardData = data.delete(0, index + 1);
			TreeNode node = new TreeNode(Integer.parseInt(currentData));
			node.left = deSerializeHelper(forwardData);
			node.right = deSerializeHelper(forwardData);
			return node;
		}
	}

	// Your Codec object will be instantiated and called as such:
	// Codec codec = new Codec();
	// codec.deserialize(codec.serialize(root));
}
