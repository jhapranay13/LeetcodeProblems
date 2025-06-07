package LeetcodeDiscuss;

import java.util.Arrays;

/**
 *
 * Amazon's online shopping app has n retailers on board. Retailer/operates in the region regionStart[i] to regionEnd[i]. A set of k retailers is said to be inclusive if there exists at least one retailer such that their operating region intersects with (or touches) all the remaining (k-1) retailers' operating regions. Amazon wants to shift some retailers to a different location. Find the minimum number of retailers
 *
 * to relocate such that the remaining retailers form an inclusive set.
 *
 *
 *
 * Example
 *
 * regionStart=[1, 3, 4, 6, 9]
 *
 * regionEnd=[2, 8, 5, 7, 10]
 *
 * Retailer 1 ranges from 1 to 2
 *
 * Retailer 5 ranges from 9 to 10.
 *
 * Retailers 2, 3, and 4 already are inclusive.
 *
 * Move retailers 1 and 5 to intersect with Retailer 2's region. The minimum number of retailers to relocate is 2.
 *
 *
 *
 * regionStart[] size n = 4 -> regionStart = [1, 2, 3, 4]
 *
 *
 *
 * -> regionEnd = [2, 3, 5,5]
 *
 * Sample Output
 *
 * 1
 *
 *
 *
 * Region 1 (1, 2) intersects only region 2. (move regions 3 and 4) Region 2 (2, 3) intersects regions 1 and 3. (move region 4) Region 3 (3, 5) intersects regions 2 and 4. (move region 1) Region 4 (4, 5) intersects region 3. (move regions 1 and 2)
 *
 * The minimum number of moves is 1, moving region 1 or 4.
 *
 */

public class AmazonOARetailersIntersect {

    public static void main(String[] args) {
        int[] regionStart = {1, 3, 4, 6, 9};
        int[] regionEnd = {2, 8, 5, 7, 10};

        System.out.println(minimumRelocations(regionStart, regionEnd));
        System.out.println(minimumRelocations1(regionStart, regionEnd));

        int[] regionStart1 = {1, 3, 4, 6, 9};
        int[] regionEnd1 = {2, 8, 5, 7, 10};
        // Expected: 2 (as per problem description)
        System.out.println("Example 1:");
        System.out.println("regionStart: " + Arrays.toString(regionStart1));
        System.out.println("regionEnd: " + Arrays.toString(regionEnd1));
        System.out.println("Minimum relocations: " + minimumRelocations(regionStart1, regionEnd1)); // Output: 2
        System.out.println("Minimum relocations: " + minimumRelocations1(regionStart1, regionEnd1)); // Output: 2

        // Example 2 (from problem description)
        int[] regionStart2 = {1, 2, 3, 4};
        int[] regionEnd2 = {2, 3, 5, 5};
        // Expected: 1
        System.out.println("\nExample 2:");
        System.out.println("regionStart: " + Arrays.toString(regionStart2));
        System.out.println("regionEnd: " + Arrays.toString(regionEnd2));
        System.out.println("Minimum relocations: " + minimumRelocations(regionStart2, regionEnd2)); // Output: 1
        System.out.println("Minimum relocations: " + minimumRelocations1(regionStart2, regionEnd2)); // Output: 1

        // Custom Test Case 1: No overlaps
        int[] regionStart3 = {1, 3, 5};
        int[] regionEnd3 = {2, 4, 6};
        // Each retailer only intersects with itself. Max inclusive set size = 1.
        // n=3, relocations = 3-1 = 2
        System.out.println("\nCustom Test Case 1 (No overlaps):");
        System.out.println("regionStart: " + Arrays.toString(regionStart3));
        System.out.println("regionEnd: " + Arrays.toString(regionEnd3));
        System.out.println("Minimum relocations: " + minimumRelocations(regionStart3, regionEnd3)); // Expected: 2
        System.out.println("Minimum relocations: " + minimumRelocations1(regionStart3, regionEnd3)); // Expected: 2
        // Custom Test Case 2: All overlap
        int[] regionStart4 = {1, 1, 1};
        int[] regionEnd4 = {10, 10, 10};
        // Any retailer can be central. All 3 intersect. Max inclusive set size = 3.
        // n=3, relocations = 3-3 = 0
        System.out.println("\nCustom Test Case 2 (All overlap):");
        System.out.println("regionStart: " + Arrays.toString(regionStart4));
        System.out.println("regionEnd: " + Arrays.toString(regionEnd4));
        System.out.println("Minimum relocations: " + minimumRelocations(regionStart4, regionEnd4)); // Expected: 0
        System.out.println("Minimum relocations: " + minimumRelocations1(regionStart4, regionEnd4)); // Expected: 0
        // Custom Test Case 3: N=0
        int[] regionStart5 = {};
        int[] regionEnd5 = {};
        System.out.println("\nCustom Test Case 3 (N=0):");
        System.out.println("regionStart: " + Arrays.toString(regionStart5));
        System.out.println("regionEnd: " + Arrays.toString(regionEnd5));
        System.out.println("Minimum relocations: " + minimumRelocations(regionStart5, regionEnd5)); // Expected: 0
        System.out.println("Minimum relocations: " + minimumRelocations1(regionStart5, regionEnd5)); // Expected: 0
        // Custom Test Case 4: N=1
        int[] regionStart6 = {5};
        int[] regionEnd6 = {10};
        System.out.println("\nCustom Test Case 4 (N=1):");
        System.out.println("regionStart: " + Arrays.toString(regionStart6));
        System.out.println("regionEnd: " + Arrays.toString(regionEnd6));
        System.out.println("Minimum relocations: " + minimumRelocations(regionStart6, regionEnd6)); // Expected: 0
        System.out.println("Minimum relocations: " + minimumRelocations1(regionStart6, regionEnd6)); // Expected: 0
        // Custom Test Case 5: Some overlaps, some disjoint
        int[] regionStart7 = {1, 2, 6, 8};
        int[] regionEnd7 = {3, 4, 7, 9};
        // R1:[1,3], R2:[2,4], R3:[6,7], R4:[8,9]
        // R1 central: intersects R2 (size 2)
        // R2 central: intersects R1 (size 2)
        // R3 central: intersects R4 (size 2)
        // R4 central: intersects R3 (size 2)
        // Max inclusive set size = 2. n=4. Relocations = 4-2=2.
        System.out.println("\nCustom Test Case 5 (Some overlaps, some disjoint):");
        System.out.println("regionStart: " + Arrays.toString(regionStart7));
        System.out.println("regionEnd: " + Arrays.toString(regionEnd7));
        System.out.println("Minimum relocations: " + minimumRelocations(regionStart7, regionEnd7)); // Expected: 2
        System.out.println("Minimum relocations: " + minimumRelocations1(regionStart7, regionEnd7)); // Expected: 2
    }

    private static int minimumRelocations(int[] regionStart, int[] regionEnd) {
        int n = regionStart.length;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int inclusive = 1;

            for (int j = 0; j < n; j++) {

                if (i != j) {

                    if (Math.max(regionStart[i], regionStart[j]) <= Math.min(regionEnd[i], regionEnd[j])) {
                        inclusive++;
                    }
                }
            }
            ans = Math.min(ans, n - inclusive);
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    private static int minimumRelocations1(int[] regionStart, int[] regionEnd) {
        Arrays.sort(regionStart);
        Arrays.sort(regionEnd);
        int n = regionStart.length;

        if (n == 0 || n == 1) {
            return 0; // No regions, no relocations needed
        }
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int start = regionStart[i];
            int end = regionEnd[i];

            int greaterThanEnd = binarySearchGreaterThan(regionStart, end);
            int lessThanStart = binarySearchLessThan(regionEnd, start);
            int totalDisjointRegions = greaterThanEnd + lessThanStart;
            ans = Math.min(ans, totalDisjointRegions);
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    private static int binarySearchLessThan(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (arr[pivot] < target) {
                lo = pivot + 1;
            } else {
                hi = pivot;
            }
        }
        return lo;
    }

    private static int binarySearchGreaterThan(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (arr[pivot] <= target) {
                lo = pivot + 1;
            } else {
                hi = pivot;
            }
        }
        return arr.length - lo;
    }
}
