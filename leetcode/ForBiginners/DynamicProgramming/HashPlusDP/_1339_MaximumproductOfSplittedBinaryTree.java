package leetcode.ForBiginners.DynamicProgramming.HashPlusDP;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         Given the root of a binary tree, split the binary tree into two
 *         subtrees by removing one edge such that the product of the sums of
 *         the subtrees is maximized.
 * 
 *         Return the maximum product of the sums of the two subtrees. Since the
 *         answer may be too large, return it modulo 109 + 7.
 * 
 *         Note that you need to maximize the answer before taking the mod and
 *         not after taking it.
 * 
 *         Example 1:
 * 							
 * 							1					2 					1
 * 						   / \                 / \                   \
 * 						  2   3               4   5                   3
 * 						 / \ /      ====>                            /
 * 						4  5 6                                      6   	
 * 	
 *         Input: root = [1,2,3,4,5,6] Output: 110 Explanation: Remove the red
 *         edge and get 2 binary trees with sum 11 and 10. Their product is 110
 *         (11*10) 
 *         
 *         Example 2:
 *         
 *         					1
 *         					 \             1               4	
 * 							  2    ===>     \      		  / \
 * 							 / \             2           5   6
 *                          3 	4           /    
 *                             / \         3
 *                            5   6 	
 * 
 *         Input: root = [1,null,2,3,4,null,null,5,6] Output: 90 Explanation:
 *         Remove the red edge and get 2 binary trees with sum 15 and 6.Their
 *         product is 90 (15*6) 
 *         
 *         Example 3:
 * 
 *         Input: root = [2,3,9,10,7,8,6,5,4,11,1] Output: 1025 
 *         
 *         Example 4:
 * 
 *         Input: root = [1,1] Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         The number of nodes in the tree is in the range [2, 5 * 104]. 1 <=
 *         Node.val <= 104
 *
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */


public class _1339_MaximumproductOfSplittedBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode() {}
	      TreeNode(int val) { this.val = val; }
	      TreeNode(int val, TreeNode left, TreeNode right) {
	          this.val = val;
	          this.left = left;
	          this.right = right;
	      }
	  }
	public int maxProduct(TreeNode root) {
        Set<Long> sums = new HashSet<>();
        long total = dfs(root, sums);
        long ans = 0;
        
        for (long sum : sums) {
            long sum1 = total - sum;
            long product = sum1 * sum;
            ans = Math.max(ans, product);
        }
        return (int)(ans % 1000000007);
    }
    
    private long dfs(TreeNode node, Set<Long> sums) {
        if (node == null) {
            return 0L;
        }
        long val = node.val;
        long left = dfs(node.left, sums);
        long right = dfs(node.right, sums);
        long sum = val + left + right;
        sums.add(sum);
        return sum;
    }
}
