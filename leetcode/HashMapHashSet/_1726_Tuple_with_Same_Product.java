package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an array nums of distinct positive integers, return the number of tuples (a, b, c, d) such that a * b = c * d where a, b, c, and d are elements of nums, and a != b != c != d.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,4,6]
 * Output: 8
 * Explanation: There are 8 valid tuples:
 * (2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
 * (3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
 * Example 2:
 *
 * Input: nums = [1,2,4,5,10]
 * Output: 16
 * Explanation: There are 16 valid tuples:
 * (1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
 * (2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
 * (2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,5,4)
 * (4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^4
 * All elements in nums are distinct.
 *
 */

public class _1726_Tuple_with_Same_Product {
    // Since all values are distinct that means a every product with one commant elemnt will be unique
    // e.g 2 * 3. 2 * 4.  2 * 7 all will be different
    // As shown in example 1 every 2 couple can create 8 * ((n * (n - 1)) / 2) tuple
    // Technically every new pair will add 8 new touple in combination
    // So if Freq == 3. Touple count will be 24 1.e 16 nee count with prevoius 2. i.e 8 count with each
    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> productFreq = new HashMap<>();
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {
                int product = nums[i] * nums[j];
                int freq = productFreq.getOrDefault(product, 0);

                if (freq >= 1) {
                    ans += 8 * freq;
                }
                productFreq.put(product, ++freq);
            }
        }
        return ans;
    }
    //=============================================================================================
    // Another approach
    public int tupleSameProduct1(int[] nums) {
        Map<Integer, Integer> productFreq = new HashMap<>();
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {
                int product = nums[i] * nums[j];
                int freq = productFreq.getOrDefault(product, 0);
                productFreq.put(product, ++freq);
            }
        }

        for (int key : productFreq.keySet()) {

            if (productFreq.get(key) > 1) {
                int freq = productFreq.get(key);
                ans += 8 * (freq * (freq - 1) / 2);
            }
        }
        return ans;
    }
}
