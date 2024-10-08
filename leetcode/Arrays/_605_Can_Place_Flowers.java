package leetcode.Arrays;

/**
 *
 * You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
 *
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
 *
 *
 *
 * Example 1:
 *
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: true
 * Example 2:
 *
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= flowerbed.length <= 2 * 10^4
 * flowerbed[i] is 0 or 1.
 * There are no two adjacent flowers in flowerbed.
 * 0 <= n <= flowerbed.length
 *
 *
 */

public class _605_Can_Place_Flowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int[] leftToRight = new int[flowerbed.length];
        int prev = -2;

        for (int i = 0; i < flowerbed.length; i++) {
            leftToRight[i] = prev;

            if (flowerbed[i] == 1) {
                prev = i;
            }
        }

        int[] rightToLeft = new int[flowerbed.length];
        prev = flowerbed.length + 1;

        for (int i = flowerbed.length - 1; i >= 0; i--) {
            rightToLeft[i] = prev;

            if (flowerbed[i] == 1) {
                prev = i;
            }
        }
        int count = 0;

        for (int i = 0; i < flowerbed.length; i++) {

            if (flowerbed[i] == 1) {
                continue;
            }
            int left = leftToRight[i];
            int right = rightToLeft[i];

            if (i - left > 1 && right - i > 1) {
                count++;
                i++;//since I cannot plant in the next so greedliy selecting the next to next
            }
        }
        return count >= n;
    }
}
