package LeetcodeDiscuss;

import java.util.*;

/**
 * Maximum area of all possible rectangles that can be formed.
 * The twist here is that you can also reduce any element by atmost 1 at a time. find the maximum area
 * of all the rectangles that can be formed.
 *
 * Note: One element can only be used once to form a single rectangle.
 *
 * Input1: n = 8 arr[] = [2,3,3,4,6,6,8,8]
 * Output: 54
 * Explanation: There are two rectangles that can be formed one with edges [6,6,8,8] and
 * another with by reducing 4 by 1 to 3 and reducing 3 by 1 to 2 so the edges will [2,2,3,3]
 * Therefore 6 * 8 + 2 * 3 = 54
 *
 * Input2: [2,1,6,5,4,4]
 * Output: 20
 * Explanation: Here just 1 rectangle is possible with maximum by reducing 6 with 5 and hence 5*4 = 20
 */
public class AmazonOAMaxAreaOfAllRectanglesFormed {

    public static void main(String ...args) {
       int[] arr = {2, 3, 3, 4, 6, 6, 8, 8};
        System.out.println(maxArea(arr)); // Output: 54

        int[] arr2 = {2, 1, 6, 5, 4, 4};
        System.out.println(maxArea(arr2)); // Output: 20

        int[] arr3 = {1, 2, 3, 4};
        System.out.println("Input 3: " + Arrays.toString(arr3));
        System.out.println("Output 3: " + maxArea(arr3)); // Expected: 3

        // Case 2: Only exact pairs
        int[] arr4 = {5, 5, 10, 10, 10, 10};
        // Rectangles: 10x10 -> 100
        System.out.println("Input 4: " + Arrays.toString(arr4));
        System.out.println("Output 4: " + maxArea(arr4));

        int[] arr6 = {1, 2, 2, 3, 3, 3, 5, 5, 6, 7};
        // Rectangles: 6 * 5 + 3 * 2
        System.out.println("Input 6: " + Arrays.toString(arr6));
        System.out.println("Output 6: " + maxArea(arr6));

        // Test Case 3: No rectangles possible (less than 4 sticks, or no pairs)
        int[] arr3_a = {1, 2, 3}; // Less than 4 sticks
        System.out.println("Input 3a: " + Arrays.toString(arr3_a));
        System.out.println("Output 3a (Expected: 0): " + maxArea(arr3_a));

        int[] arr5 = {1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
        // Sorted: [1,1,1,1,2,2,2,3,3,4]
        // counts: {1:4, 2:3, 3:2, 4:1}
        // i=4: c=1. leftover 4. pair with 3. Yes. Add 3 to pairs. counts[3]=1, counts[4]=0. pairs=[3]
        // i=3: c=1. leftover 3. pair with 2. Yes. Add 2 to pairs. counts[2]=2, counts[3]=0. pairs=[3,2]
        // i=2: c=2. numExact=1. Add 2 to pairs. counts[2]=0. pairs=[3,2,2]
        // i=1: c=4. numExact=2. Add 1, 1 to pairs. counts[1]=0. pairs=[3,2,2,1,1]
        // availablePairedSides = [3,2,2,1,1]
        // Sorted: [3,2,2,1,1] (descending)
        // Area: (3 * 2) + (2 * 1) = 6 + 2 = 8.
        System.out.println("Input 5: " + Arrays.toString(arr5));
        System.out.println("Output 5 (Expected: 8): " + maxArea(arr5));
    }

    private static int maxArea(int[] arr) {
        TreeMap<Integer, Integer> freqMap = new TreeMap<>();

        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        List<Integer> keys = new ArrayList<>(freqMap.descendingKeySet());
        int[] values = new int[arr.length];
        int index = 0;
        int prev = 0;  // to keep track of the previous frequency which will be added to the current freq

        for (int key : keys) {
            int freq = freqMap.remove(key) + prev;
            int numberOfPairs = freq / 2;
            values[index] = key;
            index += numberOfPairs;
            prev = freq % 2; // if there is an odd frequency, it will be carried over to the next iteration and will be added to next frequency as it will be decremented
        }

        for (int i = 0; i < index; i++) {

            if (values[i] == 0) {
                values[i] = values[i - 1];
            }
        }

        for (int i = 0; i < index - 1; i += 2) {
            int area = values[i] * values[i + 1];
            ans += area;
        }

        return ans;
    }
}
