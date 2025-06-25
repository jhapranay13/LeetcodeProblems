package LeetcodeDiscuss;

import java.util.Arrays;

/**
 *
 * Given an array of integers,
 * find any element that is less than or equal to both of its neighbors.
 *
 */
public class MetaDSAFindLocalMinimum {
    public static void main(String[] args) {
        System.out.println("--- Test Cases ---");

        // Example 1: Basic valley
        int[] arr1 = {5, 2, 8, 1, 9}; // 2 at index 1, 1 at index 3
        System.out.println("Array: " + Arrays.toString(arr1));
        System.out.println("Valley element index: " + findValleyElement(arr1)); // Expected: 1 or 3

        // Example 2: Monotonically increasing
        int[] arr2 = {1, 2, 3, 4, 5}; // 1 at index 0
        System.out.println("Array: " + Arrays.toString(arr2));
        System.out.println("Valley element index: " + findValleyElement(arr2)); // Expected: 0

        // Example 3: Monotonically decreasing
        int[] arr3 = {5, 4, 3, 2, 1}; // 1 at index 4
        System.out.println("Array: " + Arrays.toString(arr3));
        System.out.println("Valley element index: " + findValleyElement(arr3)); // Expected: 4

        // Example 4: Single element
        int[] arr4 = {10};
        System.out.println("Array: " + Arrays.toString(arr4));
        System.out.println("Valley element index: " + findValleyElement(arr4)); // Expected: 0

        // Example 5: Two elements
        int[] arr5 = {20, 10}; // 10 at index 1
        System.out.println("Array: " + Arrays.toString(arr5));
        System.out.println("Valley element index: " + findValleyElement(arr5)); // Expected: 1

        int[] arr6 = {10, 20}; // 10 at index 0
        System.out.println("Array: " + Arrays.toString(arr6));
        System.out.println("Valley element index: " + findValleyElement(arr6)); // Expected: 0

        // Example 7: Plateaus (equal neighbors)
        int[] arr7 = {1, 2, 2, 1, 3}; // 2 at index 2 (2 <= 2 and 2 <= 1 is FALSE, 2 <= 2 and 2 <= 1 is FALSE)
        // Correct: 2 at index 2 (2 <= 2 and 2 <= 1) is FALSE.
        // The 1 at index 3 (1 <= 2 and 1 <= 3) is a valley.
        System.out.println("Array: " + Arrays.toString(arr7));
        System.out.println("Valley element index: " + findValleyElement(arr7)); // Expected: 3

        int[] arr8 = {1, 5, 5, 5, 1}; // 5 at index 2 (5 <= 5 and 5 <= 5) is TRUE
        System.out.println("Array: " + Arrays.toString(arr8));
        System.out.println("Valley element index: " + findValleyElement(arr8)); // Expected: 2 (or 1, 3)

        int[] arr9 = {1, 2, 3, 2, 1}; // 1 at index 0 or 4
        System.out.println("Array: " + Arrays.toString(arr9));
        System.out.println("Valley element index: " + findValleyElement(arr9)); // Expected: 0 or 4
    }

    private static int findValleyElement(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        int n = arr.length;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            int leftNeighbor = (pivot > 0) ? arr[pivot - 1] : Integer.MAX_VALUE; // Treat non-existent as very large
            int rightNeighbor = (pivot < n - 1) ? arr[pivot + 1] : Integer.MAX_VALUE; // Treat non-existent as very large

            if (arr[pivot] <= leftNeighbor && arr[pivot] <= rightNeighbor) {
                return pivot; // Found a valley element
            }

            else if (leftNeighbor < arr[pivot]) {
                hi = pivot - 1; // Move left
            } else {
                lo = pivot + 1; // Move right
            }
        }
        return -1;
    }
}
