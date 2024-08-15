package leetcode.HashMapHashSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,4,2,7,5,3,8,6,9]
 * Example 2:
 *
 *
 * Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 * Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i].length <= 105
 * 1 <= sum(nums[i].length) <= 105
 * 1 <= nums[i][j] <= 105
 *
 */

public class _1424_Diagonal_Traverse_II {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> idHolder = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        int maxId = 0;

        for (int r = 0; r < nums.size(); r++) {
            List<Integer> row = nums.get(r);

            for (int c = 0; c < row.size(); c++) {
                int id = r + c;
                List<Integer> holder = idHolder.getOrDefault(id, new ArrayList<>());
                holder.add(0, row.get(c));
                idHolder.put(id, holder);
                maxId = Math.max(maxId, id);
            }
        }

        for (int id = 0; id <= maxId; id++) {
            List<Integer> temp = idHolder.get(id);
            ans.addAll(idHolder.get(id));
        }
        int[] arr = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            arr[i] = ans.get(i);
        }
        return arr;
    }
    //=============================================================================================
    //TLE solution
    public int[] findDiagonalOrder1(List<List<Integer>> nums) {
        int maxCols = 0;

        for (List<Integer> numList : nums) {
            maxCols = Math.max(maxCols, numList.size());
        }
        List<Integer> ans = new ArrayList<>();

        for (int r = 0; r < nums.size(); r++) {
            int rTemp = r;

            for (int c = 0; c < maxCols && rTemp >= 0; c++) {
                List<Integer> row = nums.get(rTemp);

                if (c < row.size()) {
                    ans.add(row.get(c));
                }
                rTemp-- ;
            }
        }

        for (int c = 1; c < maxCols; c++) {
            int r = nums.size() - 1;
            int cTemp = c;

            while (r >= 0) {
                List<Integer> row = nums.get(r--);

                if (cTemp < row.size()) {
                    ans.add(row.get(cTemp));
                }
                cTemp++;
            }
        }
        int[] arr = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            arr[i] = ans.get(i);
        }
        return arr;
    }
}
