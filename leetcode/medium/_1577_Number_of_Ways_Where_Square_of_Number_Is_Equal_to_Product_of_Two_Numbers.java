package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Given two arrays of integers nums1 and nums2, return the number of triplets formed (type 1 and type 2) under the following rules:
 *
 * Type 1: Triplet (i, j, k) if nums1[i]2 == nums2[j] * nums2[k] where 0 <= i < nums1.length and 0 <= j < k < nums2.length.
 * Type 2: Triplet (i, j, k) if nums2[i]2 == nums1[j] * nums1[k] where 0 <= i < nums2.length and 0 <= j < k < nums1.length.
 *
 *
 * Example 1:
 *
 * Input: nums1 = [7,4], nums2 = [5,2,8,9]
 * Output: 1
 * Explanation: Type 1: (1, 1, 2), nums1[1]2 = nums2[1] * nums2[2]. (42 = 2 * 8).
 * Example 2:
 *
 * Input: nums1 = [1,1], nums2 = [1,1,1]
 * Output: 9
 * Explanation: All Triplets are valid, because 12 = 1 * 1.
 * Type 1: (0,0,1), (0,0,2), (0,1,2), (1,0,1), (1,0,2), (1,1,2).  nums1[i]2 = nums2[j] * nums2[k].
 * Type 2: (0,0,1), (1,0,1), (2,0,1). nums2[i]2 = nums1[j] * nums1[k].
 * Example 3:
 *
 * Input: nums1 = [7,7,8,3], nums2 = [1,2,9,7]
 * Output: 2
 * Explanation: There are 2 valid triplets.
 * Type 1: (3,0,2).  nums1[3]2 = nums2[0] * nums2[2].
 * Type 2: (3,0,1).  nums2[3]2 = nums1[0] * nums1[1].
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 1 <= nums1[i], nums2[i] <= 105
 *
 *
 */

public class _1577_Number_of_Ways_Where_Square_of_Number_Is_Equal_to_Product_of_Two_Numbers {
    public int numTriplets(int[] nums1, int[] nums2) {
        Map<Integer, List<Integer>> valIndexHolder = getMap(nums2);
        int ans = 0;

        for (int num : nums1) {
            long n = (long)num * num;
            ans += getCount(valIndexHolder, n);
        }
        valIndexHolder = getMap(nums1);

        for (int num : nums2) {
            long n = (long)num * num;
            ans += getCount(valIndexHolder, n);
        }
        return ans;
    }

    private int getCount(Map<Integer, List<Integer>> valIndexHolder, long sqNum) {
        int ans = 0;

        for (int val : valIndexHolder.keySet()) {

            if (sqNum % val != 0) {
                continue;
            }
            int result = (int)(sqNum / val);
            List<Integer> resultList = valIndexHolder.getOrDefault(result, new ArrayList<>());

            if (result == val) {

                if (resultList.size() > 1) {
                    ans += resultList.size() * (resultList.size() - 1) / 2;
                }
            } else {
                List<Integer> valList = valIndexHolder.get(val);
                int i = 0;

                for (; i < valList.size(); i++) {
                    int index = binarySearchGreaterThan(resultList, valList.get(i));

                    if (index != -1) {
                        ans += resultList.size() - index;
                    }
                }
            }
        }
        return ans;
    }

    private int binarySearchGreaterThan(List<Integer> resultList, int index) {
        int lo = 0;
        int hi = resultList.size() - 1;
        int ans = -1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (resultList.get(pivot) < index) {
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
                ans = pivot;
            }
        }
        return ans;
    }

    private Map<Integer, List<Integer>> getMap(int[] nums) {
        Map<Integer, List<Integer>> valIndexHolder = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = valIndexHolder.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            valIndexHolder.put(nums[i], list);
        }
        return valIndexHolder;
    }
}
