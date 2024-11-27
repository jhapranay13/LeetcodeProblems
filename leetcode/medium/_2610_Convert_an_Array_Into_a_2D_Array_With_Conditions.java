package leetcode.medium;

import java.util.*;

/**
 *
 * You are given an integer array nums. You need to create a 2D array from nums satisfying the following conditions:
 *
 * The 2D array should contain only the elements of the array nums.
 * Each row in the 2D array contains distinct integers.
 * The number of rows in the 2D array should be minimal.
 * Return the resulting array. If there are multiple answers, return any of them.
 *
 * Note that the 2D array can have a different number of elements on each row.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,1,2,3,1]
 * Output: [[1,3,4,2],[1,3],[1]]
 * Explanation: We can create a 2D array that contains the following rows:
 * - 1,3,4,2
 * - 1,3
 * - 1
 * All elements of nums were used, and each row of the 2D array contains distinct integers, so it is a valid answer.
 * It can be shown that we cannot have less than 3 rows in a valid array.
 * Example 2:
 *
 * Input: nums = [1,2,3,4]
 * Output: [[4,3,2,1]]
 * Explanation: All elements of the array are distinct, so we can keep all of them in the first row of the 2D array.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= nums.length
 *
 *
 */

public class _2610_Convert_an_Array_Into_a_2D_Array_With_Conditions {
    public List<List<Integer>> findMatrix(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Set<Integer> keys = new HashSet<>();

        for(int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            keys.add(num);
        }
        List<List<Integer>> ans = new ArrayList<>();

        while (!countMap.isEmpty()) {
            List<Integer> tempList = new ArrayList<>();

            for (int key : keys) {
                int count = countMap.getOrDefault(key, -1);

                if (count == 1) {
                    countMap.remove(key);
                } else {
                    countMap.put(key, count - 1);
                }
                tempList.add(key);
            }
            ans.add(tempList);
            keys = new HashSet(countMap.keySet());
        }
        return ans;
    }
}
