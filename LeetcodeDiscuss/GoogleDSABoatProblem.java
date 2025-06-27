package LeetcodeDiscuss;

/**
 *
 * You’re given an initial amount of energy `k` and an array of wind speeds for `n` days. Each day you can either travel (consumes 1 energy, adds distance) or rest (restores 1 energy, no distance gained), but energy must stay within [0, k].
 *
 * *The Problem*
 *
 * You’re on a boat for `n` days.
 *
 * Each day, wind blows at `A[i]` units.
 * You start with energy `k`.
 *
 * Each day, you can either:
 *
 * **Travel**: Move forward `A[i]` units, lose 1 energy.
 * **Rest**: Stay put, gain 1 energy (but not above `k`).
 *
 * Your energy must always stay between `0` and `k`.
 *
 * Goal: *Maximize the total distance you travel in `n` days.*
 *
 */
public class GoogleDSABoatProblem {

    public static void main(String[] args) {
        // Example 1: Basic case
        int k1 = 1;
        int[] A1 = {10, 20, 0};
        System.out.println("Max distance for A1: " + maxDistance(k1, A1)); // Expected: 20

        // Example 2: More energy
        int k2 = 2;
        int[] A2 = {5, 10, 15};
        System.out.println("Max distance for A2: " + maxDistance(k2, A2)); // Expected: 25 (travel, travel, travel)

        // Example 3: Need to rest
        int k3 = 1;
        int[] A3 = {100, 1, 100}; // Travel, rest, travel
        System.out.println("Max distance for A3: " + maxDistance(k3, A3)); // Expected: 200

        // Example 4: Cannot travel initially
        int k4 = 0;
        int[] A4 = {10, 20};
        System.out.println("Max distance for A4: " + maxDistance(k4, A4)); // Expected: 20 (can only rest)

        // Example 5: All zero wind
        int k5 = 5;
        int[] A5 = {0, 0, 0, 0};
        System.out.println("Max distance for A5: " + maxDistance(k5, A5)); // Expected: 0

        // Example 6: Single day
        int k6 = 1;
        int[] A6 = {50};
        System.out.println("Max distance for A6: " + maxDistance(k6, A6)); // Expected: 50

        // Example 7: Cannot reach last day with positive distance
        int k7 = 1;
        int[] A7 = {10, -5, 10}; // Example with negative wind for testing (problem statement implies positive, but for robust check)
        // If wind speeds are guaranteed positive, -5 is not valid. Assuming it can be 0 or positive.
        // If it means "lose 5 distance", that's different.
        // Assuming wind speed A[i] is always non-negative distance gained.
        // Let's re-run with A7 = {10, 0, 10} for k=1.
        // Day 0: dp[0][0]=10 (travel), dp[0][1]=0 (rest)
        // Day 1:
        //  From dp[0][0]=10: Cannot travel. Rest -> dp[1][1]=max(MIN_VAL, 10)=10
        //  From dp[0][1]=0: Travel -> dp[1][0]=max(MIN_VAL, 0+0)=0. Rest -> dp[1][1]=max(10, 0)=10
        // dp[1] = {0, 10}
        // Day 2: A[2]=10
        //  From dp[1][0]=0: Cannot travel. Rest -> dp[2][1]=max(MIN_VAL, 0)=0
        //  From dp[1][1]=10: Travel -> dp[2][0]=max(MIN_VAL, 10+10)=20. Rest -> dp[2][1]=max(0, 10)=10
        // dp[2] = {20, 10}
        // Max: 20
        int k7_corr = 1;
        int[] A7_corr = {10, 0, 10};
        System.out.println("Max distance for A7 (corrected): " + maxDistance(k7_corr, A7_corr)); // Expected: 20

        int[] A_8 = {4, 2, 8, 1, 7}; // wind per day
        int k_8 = 2; // initial energy
        int result = maxDistance(k_8, A_8);
        System.out.println("Maximum distance A8: " + result);  // Expected 19

        int[] A_9 = {5, 3, 8, 2, 6, 4}; // wind per day
        int k_9 = 2; // initial energy
        int result1 = maxDistance(k_9, A_9);
        System.out.println("Maximum distance A9: " + result1);  // Expected 23
    }

    private static int maxDistance(int k, int[] a) {
        int[][] memo = new int[a.length + 1][a.length + 1];

        for(int[] mem : memo) {
            java.util.Arrays.fill(mem, -1); // Initialize memoization table with -1
        }
        return recur(k, a, 0, memo);
    }

    private static int recur(int k, int[] a, int index, int[][] memo) {

        if (index >= a.length || k > a.length) {
            return 0; // Base case: no more days left
        }

        if (memo[index][k] != -1) {
            return memo[index][k]; // Return cached result if available
        }
        int ans = 0;
        int takeRest = recur(k + 1, a, index + 1, memo);
        int takeTravel = 0;

        if (k > 0) {
            takeTravel = recur(k - 1, a, index + 1, memo) + a[index]; // Only travel if k > 0
        }
        ans = Math.max(takeRest, takeTravel);
        return memo[index][k] = ans;
    }
}
