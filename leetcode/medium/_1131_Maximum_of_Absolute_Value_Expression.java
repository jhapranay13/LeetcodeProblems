package leetcode.medium;

/**
 *
 *
 * Given two arrays of integers with equal lengths, return the maximum value of:
 *
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 *
 * where the maximum is taken over all 0 <= i, j < arr1.length.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
 * Output: 13
 * Example 2:
 *
 * Input: arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
 * Output: 20
 *
 *
 * Constraints:
 *
 * 2 <= arr1.length == arr2.length <= 40000
 * -10^6 <= arr1[i], arr2[i] <= 10^6
 *
 *
 */

public class _1131_Maximum_of_Absolute_Value_Expression {
    /*Solving the equation gives us this
    (arr1[i] + arr2[i] + i) - (arr1[j] + arr2[j] + j)
    (arr1[i] - arr2[i] + i) - (arr1[j] - arr2[j] + j)
    (-arr1[j] - arr2[j] + j) - (-arr1[i] - arr2[i] + i)
    (-arr1[j] + arr2[j] + j) - (-arr1[i] + arr2[i] + i)
    So finding out max and min nad difference will give us the max difference
    */
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int ans = 0;
        int[] signs = new int[] {1, -1};

        for (int s1 : signs) {

            for (int s2 : signs) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;

                for (int i = 0; i < arr1.length; i++) {
                    int val = s1 * arr1[i] + s2 * arr2[i] + i;
                    min = Math.min(val, min);
                    max = Math.max(val, max);
                }
                ans = Math.max(ans, max - min);
            }
        }
        return ans;
    }
}
