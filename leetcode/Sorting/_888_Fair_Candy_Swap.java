package leetcode.Sorting;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Alice and Bob have a different total number of candies. You are given two integer arrays aliceSizes and bobSizes where aliceSizes[i] is the number of candies of the ith box of candy that Alice has and bobSizes[j] is the number of candies of the jth box of candy that Bob has.
 *
 * Since they are friends, they would like to exchange one candy box each so that after the exchange, they both have the same total amount of candy. The total amount of candy a person has is the sum of the number of candies in each box they have.
 *
 * Return an integer array answer where answer[0] is the number of candies in the box that Alice must exchange, and answer[1] is the number of candies in the box that Bob must exchange. If there are multiple answers, you may return any one of them. It is guaranteed that at least one answer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: aliceSizes = [1,1], bobSizes = [2,2]
 * Output: [1,2]
 * Example 2:
 *
 * Input: aliceSizes = [1,2], bobSizes = [2,3]
 * Output: [1,2]
 * Example 3:
 *
 * Input: aliceSizes = [2], bobSizes = [1,3]
 * Output: [2,3]
 *
 *
 * Constraints:
 *
 * 1 <= aliceSizes.length, bobSizes.length <= 10^4
 * 1 <= aliceSizes[i], bobSizes[j] <= 10^5
 * Alice and Bob have a different total number of candies.
 * There will be at least one valid answer for the given input.
 *
 */
//Can also be done using sorting and binary search
public class _888_Fair_Candy_Swap {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sumAlice = 0;
        Set<Integer> numSet = new HashSet<>();

        for (int num : aliceSizes) {
            sumAlice += num;
        }
        int sumBob = 0;

        for (int num : bobSizes) {
            sumBob += num;
            numSet.add(num);
        }
        // totalAliceCandy-AliceExchangeCandy = totalBobCandy-BobExchangeCandy
        // difference of Candies Alice and Bob = (totalAliceCandy-totalBobCandy ) /2
        // (totalAliceCandy-totalBobCandy ) /2 + BobExchangeCandy = AliceExchangeCandy
        int diff = (sumBob - sumAlice) / 2;

        for (int num : aliceSizes) {

            if (numSet.contains(diff + num)) {
                return new int[] {num, diff + num};
            }
        }
        return new int[] {0, 0};
    }
    //=============================================================================================
    // Binary Search Sorting
    public int[] fairCandySwap1(int[] aliceSizes, int[] bobSizes) {
        int sumAlice = 0;
        Set<Integer> numSet = new HashSet<>();

        for (int num : aliceSizes) {
            sumAlice += num;
        }
        int sumBob = 0;

        for (int num : bobSizes) {
            sumBob += num;
            numSet.add(num);
        }
        int diff = (sumBob - sumAlice) / 2;
        Arrays.sort(bobSizes);

        for (int num : aliceSizes) {
            int  index = binarySearch(bobSizes, diff + num);

            if (index > -1) {
                return new int[] {num, diff + num};
            }
        }
        return new int[] {0, 0};
    }

    private int binarySearch(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;
        int ans = -1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (arr[pivot] == target) {
                return pivot;
            }

            if (arr[pivot] < target) {
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }
}
