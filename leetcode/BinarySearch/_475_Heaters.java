package leetcode.BinarySearch;

import java.util.Arrays;

/**
 *
 *
 * Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.
 *
 * Every house can be warmed, as long as the house is within the heater's warm radius range.
 *
 * Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses.
 *
 * Notice that all the heaters follow your radius standard, and the warm radius will the same.
 *
 *
 *
 * Example 1:
 *
 * Input: houses = [1,2,3], heaters = [2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 * Example 2:
 *
 * Input: houses = [1,2,3,4], heaters = [1,4]
 * Output: 1
 * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 * Example 3:
 *
 * Input: houses = [1,5], heaters = [2]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= houses.length, heaters.length <= 3 * 10^4
 * 1 <= houses[i], heaters[i] <= 10^9
 *
 */

public class _475_Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int lo = 0;
        int hi = 1000000001;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (checkCoverage(houses, heaters, pivot)) {
                hi = pivot;
            } else {
                lo = pivot + 1;
            }
        }
        return hi;
    }

    private boolean checkCoverage(int[] houses, int[] heaters, int radius) {
        int houseIndex = 0;
        int heaterIndex = 0;

        while (houseIndex < houses.length && heaterIndex < heaters.length) {

            if (houses[houseIndex] >= heaters[heaterIndex] - radius && houses[houseIndex] <= heaters[heaterIndex] + radius) {
                houseIndex++;
            } else {
                heaterIndex++;
            }
        }
        if (houseIndex < houses.length) {
            return false;
        }
        return true;
    }
}