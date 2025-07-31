package leetcode.DP.HashMap;

import java.util.*;

public class _2035_Partition_Array_Into_Two_Arrays_to_Minimize_Sum_Difference {

    //TLE
    public int minimumDifference(int[] nums) {
        int n = nums.length / 2;
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }
        Map<String, Integer> memo = new HashMap<>();
        return recur(nums, n, sum, 0, 0, 0, memo);
    }

    private int recur(int[] nums, int n, int sum, int index, int count, int currSum,
                      Map<String, Integer> memo) {

        if (n == count) {
            int sumOfOtherPart = sum - currSum;
            return Math.abs(sumOfOtherPart - currSum);
        }

        if (index == nums.length) {
            return Integer.MAX_VALUE;
        }
        String key = index + "|" + count + "|" + currSum;

        if(memo.containsKey(key)) {
            return memo.get(key);
        }
        int choose = recur(nums, n, sum, index + 1, count + 1, currSum + nums[index], memo);
        int dontChoose = recur(nums, n, sum, index + 1, count, currSum, memo);
        int ans = Math.min(choose, dontChoose);
        memo.put(key, ans);
        return ans;
    }
    //=============================================================================================
    //Need more understanding on Meet in middle technique
    public int minimumDifference1(int[] nums) {
        int length = nums.length / 2;
        Map<Integer, List<Integer>> sizeLeftSum = new HashMap<>();
        Map<Integer, List<Integer>> sizeRightSum = new HashMap<>();
        recur(nums, sizeLeftSum, 0, 0, 0, length);
        recur(nums, sizeRightSum, length, 0, 0, nums.length);
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i <= length; i++) {
            List<Integer> leftSum = sizeLeftSum.getOrDefault(i, new ArrayList<>());
            List<Integer> rightSum = sizeRightSum.getOrDefault(length - i, new ArrayList<>());
            Collections.sort(rightSum);

            for (int lSum : leftSum) {
                // coz combining two sum can max go to totalSum / 2
                int lo = 0;
                int hi = rightSum.size() - 1;

                while (lo <= hi) {
                    int pivot = lo + (hi - lo) / 2;
                    //|sumA - sumB| = |(groupSum) - (total - groupSum)| = |2 * groupSum - total|
                    // gives us the effective target difference of this grouping — how far off it is from a perfect split.
                    int target = 2 * (lSum + rightSum.get(pivot));

                    if (totalSum == target) {
                        return 0;
                    }
                    if (target < totalSum) {
                        lo = pivot + 1;
                    } else {
                        hi = pivot - 1;
                    }
                }

                if (lo < rightSum.size()) {
                    ans = Math.min(ans, Math.abs(totalSum - 2 * (lSum + rightSum.get(lo))));
                }

                if (hi > 0) {
                    ans = Math.min(ans, Math.abs(totalSum - 2 * (lSum + rightSum.get(hi))));
                }
            }
        }
        return ans;
    }

    private void recur(int[] nums, Map<Integer, List<Integer>> sizeSum, int index, int size, int sum, int endIndex) {

        if (index >= endIndex) {
            List<Integer> sumList = sizeSum.getOrDefault(size, new ArrayList<>());
            sumList.add(sum);
            sizeSum.put(size, sumList);
            return;
        }
        // include
        recur(nums, sizeSum, index + 1, size + 1, sum + nums[index], endIndex);
        // exclude
        recur(nums, sizeSum, index + 1, size, sum, endIndex);

    }

}
