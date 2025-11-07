package leetcode.Sorting;

import java.util.Arrays;

/**
 *
 * Alice is a caretaker of n gardens and she wants to plant flowers to maximize the total beauty of all her gardens.
 *
 * You are given a 0-indexed integer array flowers of size n, where flowers[i] is the number of flowers already planted in the ith garden. Flowers that are already planted cannot be removed. You are then given another integer newFlowers, which is the maximum number of flowers that Alice can additionally plant. You are also given the integers target, full, and partial.
 *
 * A garden is considered complete if it has at least target flowers. The total beauty of the gardens is then determined as the sum of the following:
 *
 * The number of complete gardens multiplied by full.
 * The minimum number of flowers in any of the incomplete gardens multiplied by partial. If there are no incomplete gardens, then this value will be 0.
 * Return the maximum total beauty that Alice can obtain after planting at most newFlowers flowers.
 *
 *
 *
 * Example 1:
 *
 * Input: flowers = [1,3,1,1], newFlowers = 7, target = 6, full = 12, partial = 1
 * Output: 14
 * Explanation: Alice can plant
 * - 2 flowers in the 0th garden
 * - 3 flowers in the 1st garden
 * - 1 flower in the 2nd garden
 * - 1 flower in the 3rd garden
 * The gardens will then be [3,6,2,2]. She planted a total of 2 + 3 + 1 + 1 = 7 flowers.
 * There is 1 garden that is complete.
 * The minimum number of flowers in the incomplete gardens is 2.
 * Thus, the total beauty is 1 * 12 + 2 * 1 = 12 + 2 = 14.
 * No other way of planting flowers can obtain a total beauty higher than 14.
 * Example 2:
 *
 * Input: flowers = [2,4,5,3], newFlowers = 10, target = 5, full = 2, partial = 6
 * Output: 30
 * Explanation: Alice can plant
 * - 3 flowers in the 0th garden
 * - 0 flowers in the 1st garden
 * - 0 flowers in the 2nd garden
 * - 2 flowers in the 3rd garden
 * The gardens will then be [5,4,5,5]. She planted a total of 3 + 0 + 0 + 2 = 5 flowers.
 * There are 3 gardens that are complete.
 * The minimum number of flowers in the incomplete gardens is 4.
 * Thus, the total beauty is 3 * 2 + 4 * 6 = 6 + 24 = 30.
 * No other way of planting flowers can obtain a total beauty higher than 30.
 * Note that Alice could make all the gardens complete but in this case, she would obtain a lower total beauty.
 *
 *
 * Constraints:
 *
 * 1 <= flowers.length <= 10^5
 * 1 <= flowers[i], target <= 10^5
 * 1 <= newFlowers <= 10^10
 * 1 <= full, partial <= 10^5
 *
 */

public class _2234_Maximum_Total_Beauty_of_the_Gardens {
    public long maximumBeauty2(int[] flowers, long newFlowers, int target, int full, int partial) {
        int n = flowers.length;
        Arrays.sort(flowers);

        // Prefix sum: prefix[i] = sum of first i elements (1-based for convenience)
        long[] prefix = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + flowers[i - 1];
        }

        // If even the smallest garden already >= target → all full
        if (flowers[0] >= target) return 1L * n * full;

        // Find last garden below target (index of last incomplete garden)
        int lastIncomplete = n - 1;
        while (lastIncomplete >= 0 && flowers[lastIncomplete] >= target) {
            lastIncomplete--;
        }

        // Gardens already full
        int alreadyFull = n - 1 - lastIncomplete;
        long baseBeauty = (long) alreadyFull * full;

        // If all are full already
        if (alreadyFull == n) return baseBeauty;

        long maxBeauty = 0;

        // Try completing different numbers of largest incomplete gardens
        for (int completeCount = 0; completeCount <= lastIncomplete + 1; completeCount++) {
            // Number of gardens we plan to make full from the end of the incomplete group
            long flowersNeeded = 0;
            if (completeCount > 0) {
                int start = lastIncomplete - completeCount + 1;
                // Cost to make these gardens reach target
                flowersNeeded = (long) target * completeCount
                        - (prefix[lastIncomplete + 1] - prefix[start]);
            }

            if (flowersNeeded > newFlowers) break; // not enough to make more gardens full

            long remaining = newFlowers - flowersNeeded;
            long partialValue = 0;

            // If not all gardens are complete, maximize min among the remaining incomplete ones
            if (lastIncomplete - completeCount + 1 > 0) {
                int incompleteCount = lastIncomplete - completeCount + 1;
                partialValue = findMaxPartial(flowers, prefix, incompleteCount, remaining, target);
            }

            long totalBeauty = baseBeauty + (long) completeCount * full + partialValue * partial;
            maxBeauty = Math.max(maxBeauty, totalBeauty);
        }

        // Special check: can all gardens be made full?
        long totalFlowersNeeded = (long) target * (lastIncomplete + 1) - prefix[lastIncomplete + 1];
        if (totalFlowersNeeded <= newFlowers) {
            maxBeauty = Math.max(maxBeauty, 1L * n * full);
        }

        return maxBeauty;
    }

    /**
     * Binary search to find the maximum possible minimum value (partialValue)
     * among incomplete gardens given remaining flowers.
     */
    private long findMaxPartial(int[] flowers, long[] prefix, int count, long remaining, int target) {
        long left = 0, right = target - 1, best = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            int idx = upperBound(flowers, count, (int) mid);
            long cost = mid * idx - prefix[idx];

            if (cost <= remaining) {
                best = mid;  // possible, try higher
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return best;
    }

    /**
     * Finds the index of the first garden > value in the first `len` gardens
     * (i.e., count of gardens ≤ value).
     */
    private int upperBound(int[] flowers, int len, int value) {
        int l = 0, r = len;
        while (l < r) {
            int mid = (l + r) / 2;
            if (flowers[mid] <= value) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    //=============================================================================================

    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);

        if (flowers[0] >= target) {
            return (long)flowers.length * (long)full;
        }
        int rightIndex = flowers.length - 1;

        while (rightIndex > 0 && flowers[rightIndex] >= target) {
            rightIndex--;
        }

        while (rightIndex >= 0 && target - flowers[rightIndex] <= newFlowers) {
            newFlowers -= target - flowers[rightIndex--];
        }
        long ans = full * (flowers.length - rightIndex - 1);

        if (rightIndex == -1) {
            newFlowers += target - flowers[0];
            ans = Math.max(ans, (long)full * (flowers.length - 1) + Math.min(target - 1, newFlowers + flowers[0]) * (long)partial);
            return ans;
        }
        long lowestVal = flowers[0];
        long sum = 0;

        for (int leftIndex = 0; lowestVal < target; ) {

            while (leftIndex <= rightIndex && leftIndex < flowers.length
                    && flowers[leftIndex] <= lowestVal) {
                sum += flowers[leftIndex];
                leftIndex++;
            }
            int leftRange = leftIndex;
            long neededFlower = lowestVal * leftRange - sum;

            if (neededFlower > newFlowers) {
                rightIndex++;

                if (rightIndex == flowers.length) {
                    break;
                }

                if(rightIndex < flowers.length) {
                    newFlowers += Math.max(0, target - flowers[rightIndex]);
                }
            } else {
                int rightRange = flowers.length - rightIndex - 1;
                ans = Math.max(ans, lowestVal * (long)partial + rightRange * (long)full);
                lowestVal++;
            }
        }
        return ans;
    }
    //=============================================================================================
    // Prefix Sum Sorting Binary Search
    public long maximumBeauty1(int[] flowers, long newFlowers, int target, int full, int partial) {
        int n = flowers.length;
        long[] prefix = new long[n];
        Arrays.sort(flowers);

        if (flowers[0] >= target){
            return 1L*full*flowers.length;
        }

        for (int i = 0; i < n; i++) {

            if (i == 0) {
                prefix[i] = flowers[i];
            } else {
                prefix[i] = prefix[i - 1] + flowers[i];
            }
        }
        int rightIdx = n - 1;

        while (rightIdx >= 0 && flowers[rightIdx] >= target) {
            rightIdx--;
        }
        int countAboveOrEqTarget = n - 1 - rightIdx;
        long beforeCalc = 0;

        if (countAboveOrEqTarget > 0) {
            beforeCalc = countAboveOrEqTarget * full;

            if (countAboveOrEqTarget == n) {
                return beforeCalc;
            }
        }
        long ans = 0;

        for (int index = rightIdx; index >= 0; index--) {
            int indx = rightIdx;
            long remainingFlower = newFlowers;
            int fullCount = 0;

            if (index < rightIdx) {
                // this will lead to all full and one partial since it will check for i > index
                // so will never check if zero can be transformed into full
                int count = rightIdx - index;
                long flowerPresent = (long)prefix[rightIdx] - prefix[index];
                long flowerNeeded = (long)target * count - flowerPresent;

                if (flowerNeeded <= remainingFlower) {
                    remainingFlower = (long)remainingFlower - flowerNeeded;
                    fullCount = count;
                } else {
                    fullCount = 0;
                    break;
                }
            }
            long fullValue = (long) fullCount * full;
            indx -= fullCount;
            int lo = 0;
            int hi = target - 1;
            long partialValue = 0;

            while (lo <= hi) {
                int pivot = lo + (hi - lo) / 2;
                // Trying to make minimum as pivot;
                int count = getCount(flowers, pivot, indx);
                long flowersNeeded = (long)pivot * count - (count == 0 ? 0 : prefix[count -1]);

                if (flowersNeeded <= remainingFlower) {
                    lo = pivot + 1;
                    partialValue = pivot;
                } else {
                    hi = pivot - 1;
                }
            }
            long temp = partialValue * partial;
            ans = Math.max(ans, (long) beforeCalc + fullValue + temp);
        }
        // checking if zero can be transformed to full
        long totalFlowerPresent = prefix[rightIdx];
        long flowerNeeded = (long) target * rightIdx + 1;
        long totalFlowerNeeded = (long)flowerNeeded - totalFlowerPresent;

        if (totalFlowerNeeded <= newFlowers) {
            long temp = (long)full * n;
            ans = Math.max(ans, temp);
        }
        return ans;
    }

    private int getCount(int[] flowers, int val, int index) {
        int lo = 0;
        int hi = index + 1;
        int count = 0;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (flowers[pivot] <= val) {
                lo = pivot + 1;
                count = pivot + 1;
            } else {
                hi = pivot;
            }
        }
        return count;
    }
}
