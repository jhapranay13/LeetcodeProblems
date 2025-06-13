package LeetcodeDiscuss;

import java.util.List;

/**
 *
 * You are playing a city management simulator that takes place on a 1-dimensional grid X tiles long.
 * There are N factories present along the line and you know the location of every factory.
 * Each factory contributes air pollution to the entire grid, decreasing by distance.
 * You want to place your city on the tile with the lowest total air pollution.
 *
 * F1          F2      …         Fn
 * — — — — — — — — — — — — — - - - - - - - - - - -
 * 1 2 3 … x
 *
 * Given a city location Cx and a factory on Fx, the pollution contributed by that factory is 1/abs(Cx - Fx).
 *
 * We have an API to get the pollution.
 *
 * Write a function that determines the optimal space on which to place the city (lowest pollution).
 *
 * Answer can only be integer.
 *
 */
public class GoogleR3FactoriesAndPollution {

    public static void main(String args[]) {
        int X1 = 10;
        int[] factories1 = {2, 7};
        System.out.println("Optimal location for X=" + X1 + ", Factories=" + List.of(2, 7) + ": " +
                findOptimalCityLocation1(X1, factories1));
        System.out.println("Optimal location for X=" + X1 + ", Factories=" + List.of(2, 7) + ": " +
                findOptimalCityLocation2(X1, factories1));

        // Example 2: Factories at grid ends
        int X2 = 5;
        int[] factories2 = {1, 5};
        System.out.println("Optimal location for X=" + X2 + ", Factories=" + List.of(1, 5) + ": " +
                findOptimalCityLocation1(X2, factories2));
        System.out.println("Optimal location for X=" + X2 + ", Factories=" + List.of(1, 5) + ": " +
                findOptimalCityLocation2(X2, factories2));

        // Example 3: Single factory
        int X3 = 10;
        int[] factories3 = {5};
        System.out.println("Optimal location for X=" + X3 + ", Factories=" + List.of(5) + ": " +
                findOptimalCityLocation1(X3, factories3));
        System.out.println("Optimal location for X=" + X3 + ", Factories=" + List.of(5) + ": " +
                findOptimalCityLocation2(X3, factories3));

        // Example 4: City on a factory location (will be avoided)
        int X4 = 5;
        int[] factories4 = {3};
        System.out.println("Optimal location for X=" + X4 + ", Factories=" + List.of(3) + ": " +
                findOptimalCityLocation1(X4, factories4));
        System.out.println("Optimal location for X=" + X4 + ", Factories=" + List.of(3) + ": " +
                findOptimalCityLocation2(X4, factories4));

        // Example 5: No factories
        int X5 = 10;
        int[] factories5 = {};
        System.out.println("Optimal location for X=" + X5 + ", Factories=" + List.of() + ": " +
                findOptimalCityLocation1(X5, factories5));
        System.out.println("Optimal location for X=" + X5 + ", Factories=" + List.of() + ": " +
                findOptimalCityLocation2(X5, factories5));

        // Example 6: Invalid grid length
        int X6 = 0;
        int[] factories6 = {1, 2};
        System.out.println("Optimal location for X=" + X6 + ", Factories=" + List.of(1, 2) + ": " +
                findOptimalCityLocation1(X6, factories6)); // Expected: -1 (Error message printed)
        System.out.println("Optimal location for X=" + X6 + ", Factories=" + List.of(1, 2) + ": " +
                findOptimalCityLocation2(X6, factories6)); // Expected: -1 (Error message printed)
    }

    private static int findOptimalCityLocation1(int gridlength, int[] factories) {
        int location  = -1;
        double minPollution = Double.MAX_VALUE;

        for (int i = 1; i <= gridlength; i++ ) {
            double pollution = 0;
            boolean valid = true;

            for (int factory : factories) {

                if (factory != i) {
                    pollution += 1.0 / Math.abs(factory - i);
                } else {
                    valid = false; //city cannot be placed on a factory
                    break;
                }
            }

            if (valid && pollution < minPollution) {
                minPollution = pollution;
                location = i;
            }
        }
        return location;
    }
    //=============================================================================================
    // Ternery Search to optimize the search for the optimal city location
    /**
     *
     * we are essentially summing terms of the form 1/distance.
     * This sum will typically have a "valley" shape, meaning it will decrease,
     * hit a minimum, and then increase. This makes it a candidate for ternary search.
     *
     * Ternary search works by repeatedly narrowing down the search space for the minimum (or maximum) of a unimodal function.
     *
     * It takes a search range [low, high].
     * It picks two points, mid1 and mid2, that divide the range into three roughly
     * equal parts.
     *
     * mid1 = low + (high - low) / 3
     * mid2 = high - (high - low) / 3
     *
     */
    private static int findOptimalCityLocation2(int gridlength, int[] factories) {
        int position = -1;
        int lo = 1;
        int hi = gridlength;
        double pollution = Double.MAX_VALUE;

        while (hi - lo >= 3) {
            int mid1 = lo + (hi - lo) / 3;
            int mid2 = hi - (hi - lo) / 3;

            // Ensure mid1 and mid2 are distinct and within bounds
            if (mid1 == lo) mid1++; // Avoid mid1 being same as low
            if (mid2 == hi) mid2--; // Avoid mid2 being same as high
            if (mid1 >= mid2) { // Ensure mid1 and mid2 don't cross or overlap
                // This case should ideally not happen if (high - low >= 3) is true
                // and mid1, mid2 calculations are correct.
                // Adjust to ensure they are distinct:
                mid1 = lo + (hi - lo) / 2; // Treat as binary search for this iteration
                mid2 = mid1 + 1;
                if (mid2 > hi) mid2 = hi;
                if (mid1 < lo) mid1 = lo;
            }

            double pollutionMid1 = calculatePollution(mid1, factories);
            double pollutionMid2 = calculatePollution(mid2, factories);

            if (pollutionMid1 < pollutionMid2) {
                hi = mid2;
            } else if (pollutionMid1 > pollutionMid2) {
                lo = mid1;
            } else {
                // If both mid1 and mid2 have the same pollution, we can choose either
                // but we will continue to narrow down the search space.
                lo = mid1;
                hi = mid2;
            }
        }
        for (int i = lo; i <= hi; i++) {
            double currentPollution = calculatePollution(i, factories);
            if (currentPollution < pollution) {
                pollution = currentPollution;
                position = i;
            }
        }

        return position;
    }

    private static double calculatePollution(int position, int[] factories) {
        double pollution = 0;

        for (int factory : factories) {

            if (factory != position) {
                pollution += 1.0 / Math.abs(factory - position);
            } else {
                return Double.MAX_VALUE; // city cannot be placed on a factory
            }
        }
        return pollution;
    }
}
