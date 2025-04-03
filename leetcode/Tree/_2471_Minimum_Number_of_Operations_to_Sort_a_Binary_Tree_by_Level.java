package leetcode.Tree;

import java.util.*;

/**
 *
 *You are given the root of a binary tree with unique values.
 *
 * In one operation, you can choose any two nodes at the same level and swap their values.
 *
 * Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.
 *
 * The level of a node is the number of edges along the path between it and the root node.
 *
 *
 *
 * Example 1:
 *
 *                                   1
 *                              /        \
 *                             4          3
 *                         /      \     /     \
 *                        7        6   8       5
 *                                    /       /
 *                                  9       10
 *
 * Input: root = [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
 * Output: 3
 * Explanation:
 * - Swap 4 and 3. The 2nd level becomes [3,4].
 * - Swap 7 and 5. The 3rd level becomes [5,6,8,7].
 * - Swap 8 and 7. The 3rd level becomes [5,6,7,8].
 * We used 3 operations so return 3.
 * It can be proven that 3 is the minimum number of operations needed.
 * Example 2:
 *
 *                      1
 *                   /    \
 *                  3      2
 *                /   \  /  \
 *               7    6 5    4
 *
 * Input: root = [1,3,2,7,6,5,4]
 * Output: 3
 * Explanation:
 * - Swap 3 and 2. The 2nd level becomes [2,3].
 * - Swap 7 and 4. The 3rd level becomes [4,6,5,7].
 * - Swap 6 and 5. The 3rd level becomes [4,5,6,7].
 * We used 3 operations so return 3.
 * It can be proven that 3 is the minimum number of operations needed.
 * Example 3:
 *
 *                        1
 *  *                   /    \
 *  *                  2      3
 *  *                /   \  /
 *  *               4    5 6
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 0
 * Explanation: Each level is already sorted in increasing order so return 0.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 105].
 * 1 <= Node.val <= 105
 * All the values of the tree are unique.
 *
 *
 *
 *
 */

public class _2471_Minimum_Number_of_Operations_to_Sort_a_Binary_Tree_by_Level {
    public int minimumOperations(TreeNode root) {
        int swaps = 0;
        Deque<TreeNode> q = new LinkedList<>();

        if (root != null) {
            q.offer(root);
        }

        while(!q.isEmpty()) {
            int size = q.size();
            int arr[] = new int[size];
            int index = 0;

            while(size-- > 0) {
                TreeNode node = q.poll();
                arr[index++] = node.val;

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            swaps += getSwaps(arr);
        }
        return swaps;
    }

    private int getSwaps(int[] arr) {
        int[] clone = arr.clone();
        Arrays.sort(clone);
        int swaps = 0;
        Map<Integer, Integer> originalPos = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            originalPos.put(arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] != clone[i]) {
                swaps++;

                int posInOriginal = originalPos.get(clone[i]);  // Getting prevPos of clone
                originalPos.put(arr[i], posInOriginal); // moving curr original to that pos
                arr[posInOriginal] = arr[i];
            }
        }
        return swaps;
    }
}
