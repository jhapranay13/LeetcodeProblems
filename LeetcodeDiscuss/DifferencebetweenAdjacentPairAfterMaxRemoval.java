package LeetcodeDiscuss;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * I got this question in online assessment of Amazon, I could not solve it.
 * Pasting the question below, please comment if anyone has any idea how to solve this question
 *
 * Given an array 5 4 0 3 3 1, take sum of absolute differences between adjacent pairs i.e |5-4|+|4-0|+|0-3|+|3-3|+|3-1| = 10
 * The task is to remove as many elements from the array such that the sum remains same.
 * soln 1=> 5 4 0 3 1 : in this case sum of absolute differences between adjacent pairs is same as 10
 * soln 2 => 5 0 3 1 : in this case as well sum of absolute differences between adjacent pairs is same as 10
 *
 * soln 2 is acceptable answer as it has minimum elements
 *
 * Example 2:
 *
 * 6 4 4 3 3 2
 *
 * answer : 6 4 3 2
 *
 */

public class DifferencebetweenAdjacentPairAfterMaxRemoval {

    public static void main(String[] args) {
        int[] arr = {5, 4, 0, 3, 3, 1};
        //int[] arr = {6, 4, 4, 3, 3, 2};
        //int[] arr = {5, 4, 1, 3, 3, 1};
        //int[] arr = {5, 1,3, 1} ;
        //
        System.out.println(sumOfAdjacentPairs(arr));
        List<Integer> ans = afterMaxRemoval(arr);
        System.out.println("After max removal: " + ans);
    }
    // this is like monotonic problem
    // |5-4|+|4-0|+|0-3|+|3-3|+|3-1| in this example
    // 4 cancels out to 0
    // 3 cancels out to 0
    // and the equation becomes |5-0|+|0-3|+|3-1|
    private static List<Integer> afterMaxRemoval(int[] arr) {
        List<Integer> ans = new ArrayList<>();

        if (arr.length == 2) {
            // if both are equal then we can remove both
            // making total sum as 0
            if (arr[0] == arr[1]) {
                return ans;
            }
            ans.add(arr[0]);
            ans.add(arr[1]);
            return ans;
        }
        ans.add(arr[0]);

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] <= arr[i - 1]) {

                if (i == arr.length - 1) {
                    ans.add(arr[i]);
                }
                continue;
            }
            ans.add(arr[i - 1]);
            ans.add(arr[i]);
        }
        return ans;
    }

    public static int sumOfAdjacentPairs(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            sum += Math.abs(arr[i] - arr[i + 1]);
        }
        return sum;
    }
}
