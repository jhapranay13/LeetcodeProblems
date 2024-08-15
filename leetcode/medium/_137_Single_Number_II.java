package leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,3,2]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * Each element in nums appears exactly three times except for one element which appears once.
 *
 */

public class _137_Single_Number_II {
    // HashSet Maths
    //3×(a+b+c)−(a+a+a+b+b+b+c)=2c
    public int singleNumber(int[] nums) {
        Set<Long> set = new HashSet<>();
        long sumSet = 0, sumArray = 0;

        for(int n : nums) {
            sumArray += n;
            set.add((long)n);
        }
        for(Long s : set) sumSet += s;
        return (int)((3 * sumSet - sumArray) / 2);
    }
    //=============================================================================================
    // HashMap
    public int singleNumber2(int[] nums) {
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (int num : nums)
            hashmap.put(num, hashmap.getOrDefault(num, 0) + 1);

        for (int k : hashmap.keySet())
            if (hashmap.get(k) == 1) return k;
        return -1;
    }
}
