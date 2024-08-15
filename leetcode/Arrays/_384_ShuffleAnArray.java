package leetcode.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * Given an integer array nums, design an algorithm to randomly shuffle the array. All permutations of the array should be equally likely as a result of the shuffling.
 *
 * Implement the Solution class:
 *
 * Solution(int[] nums) Initializes the object with the integer array nums.
 * int[] reset() Resets the array to its original configuration and returns it.
 * int[] shuffle() Returns a random shuffling of the array.
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * Output
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
 *                        // Any permutation of [1,2,3] must be equally likely to be returned.
 *                        // Example: return [3, 1, 2]
 * solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
 * solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * -106 <= nums[i] <= 106
 * All the elements of nums are unique.
 * At most 5 * 104 calls in total will be made to reset and shuffle.
 *
 */

public class _384_ShuffleAnArray {
    class Solution {
        int[] original;
        int[] copy;
        private Random rand = new Random();

        public Solution(int[] nums) {
            this.original = nums;
            this.copy = original.clone();
        }

        public int[] reset() {
            this.copy = original.clone();
            return copy;
        }

        public int[] shuffle() {
            List<Integer> list = getList();
            int[] ans = new int[copy.length];

            for (int i = 0; i < ans.length; i++) {
                int index = rand.nextInt(list.size());
                ans[i] = list.remove(index);
            }
            return ans;
        }

        private List<Integer> getList() {
            List<Integer> list = new ArrayList<>();

            for (int num : copy) {
                list.add(num);
            }
            return list;
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
}
