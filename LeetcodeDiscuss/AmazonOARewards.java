package LeetcodeDiscuss;


import java.util.Arrays;

/**
 *
 * Amazon Shopping is running a reward collection event for its customers.
 * There are n customers and the i-th customer has collected initialRewards[i] points so far.
 *
 * One final tournament is to take place where:
 *
 * The winner will be awarded n points,
 *
 * The runner-up gets n - 1 points,
 *
 * The third place gets n - 2 points,
 *
 * ...
 *
 * The last place gets 1 point.
 *
 * Given an integer array initialRewards of length n, representing the initial reward points of the customers before the final tournament:
 *
 * 🔍 Your Task
 * Find the number of customers i (1 ≤ i ≤ n) such that, if the i-th customer wins the final tournament, they would have the highest total points.
 *
 * 🧠 Note:
 * The total points = initialRewards[i] + n (if they win).
 *
 * Other customers also get points in the tournament depending on their ranks (from n - 1 to 1).
 *
 * You must check if the i-th customer, upon winning, ends up with the highest total score, regardless of how others place.
 *
 * 🧪 Example:
 * Input:
 * ini
 * Copy
 * Edit
 * n = 3
 * initialRewards = [1, 3, 4]
 * Output:
 * Copy
 * Edit
 * 2
 * Explanation:
 * Customer 1: 1 + 3 = 4 → Not highest, since customer 3 can get 4 + 2 = 6.
 *
 * Customer 2: 3 + 3 = 6 → Yes, highest possible.
 *
 * Customer 3: 4 + 3 = 7 → Yes, highest possible.
 *
 * ✅ Customers 2 and 3 are valid → Answer: 2
 *
 * 🧪 Another Example:
 * Input:
 * ini
 * Copy
 * Edit
 * n = 3
 * initialRewards = [8, 10, 9]
 * Output:
 * Copy
 * Edit
 * 2
 * Explanation:
 * Customer 2: 10 + 3 = 13 → Highest.
 *
 * Customer 3: 9 + 3 = 12 → Valid, since others can't beat 12 even if placed second.
 *
 * ✅ Again, 2 valid customers.
 *
 */

public class AmazonOARewards {
    public static void main(String[] args) {
        // Example 1
        int n1 = 3;
        int[] initialRewards1 = {1, 3, 4};
        System.out.println("Input: n = " + n1 + ", initialRewards = " + Arrays.toString(initialRewards1));
        System.out.println("Output: " + countValidWinners(n1, initialRewards1)); // Expected: 2

        // Example 2
        int n2 = 3;
        int[] initialRewards2 = {8, 10, 9};
        System.out.println("Input: n = " + n2 + ", initialRewards = " + Arrays.toString(initialRewards2));
        System.out.println("Output: " + countValidWinners(n2, initialRewards2)); // Expected: 2

        // Custom Test Cases
        // Test Case 3: All customers can win if they are the chosen winner
        int n3 = 4;
        int[] initialRewards3 = {1, 1, 1, 1};
        System.out.println("Input: n = " + n3 + ", initialRewards = " + Arrays.toString(initialRewards3));
        System.out.println("Output: " + countValidWinners(n3, initialRewards3)); // Expected: 4

        // Test Case 4: No one can win even if they are the chosen winner (one high initial, others low)
        int n4 = 5;
        int[] initialRewards4 = {1, 1, 1, 1, 10};
        System.out.println("Input: n = " + n4 + ", initialRewards = " + Arrays.toString(initialRewards4));
        System.out.println("Output: " + countValidWinners(n4, initialRewards4)); // Expected: 1

        // Test Case 5: n=1 edge case
        int n5 = 1;
        int[] initialRewards5 = {100};
        System.out.println("Input: n = " + n5 + ", initialRewards = " + Arrays.toString(initialRewards5));
        System.out.println("Output: " + countValidWinners(n5, initialRewards5)); // Expected: 1

        // Test Case 6: Larger N
        int n6 = 10;
        int[] initialRewards6 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        System.out.println("Input: n = " + n6 + ", initialRewards = " + Arrays.toString(initialRewards6));
        System.out.println("Output: " + countValidWinners(n6, initialRewards6)); // Expected: 1

        // Test Case 7: Duplicates, larger n
        int n7 = 5;
        int[] initialRewards7 = {5, 8, 8, 8, 10};
        System.out.println("Input: n = " + n7 + ", initialRewards = " + Arrays.toString(initialRewards7));
        System.out.println("Output: " + countValidWinners(n7, initialRewards7)); // Expected: 1

        // Test Case 8: All identical
        int n8 = 4;
        int[] initialRewards8 = {7, 7, 7, 7};
        System.out.println("Input: n = " + n8 + ", initialRewards = " + Arrays.toString(initialRewards8));
        System.out.println("Output: " + countValidWinners(n8, initialRewards8)); // Expected: 4

        // Test Case 9: Two max values, rest are low
        int n9 = 4;
        int[] initialRewards9 = {1, 1, 10, 10};
        // max1=10, max2=10
        // Customer 10: 10+1 >= 10. Valid. (Both 10s are valid)
        // Customer 1: 1+1 >= 10. False.
        // Expected: 2
        System.out.println("Input: n = " + n9 + ", initialRewards = " + Arrays.toString(initialRewards9));
        System.out.println("Output: " + countValidWinners(n9, initialRewards9)); // Expected: 2
    }

    private static int countValidWinners(int n, int[] initialRewards) {
        int maxReward = Integer.MIN_VALUE;
        int secondMaxReward = Integer.MIN_VALUE;

        // Find the maximum and second maximum rewards
        for (int reward : initialRewards) {

            if (reward > maxReward) {
                secondMaxReward = maxReward;
                maxReward = reward;
            } else if (reward > secondMaxReward && reward < maxReward) {
                secondMaxReward = reward;
            }
        }

        int validWinnersCount = 0;

        // Check each customer
        for (int reward : initialRewards) {
            // If the customer wins, their total points would be reward + n
            int totalPointsIfWin = reward + n;

            if (reward == maxReward) {
                // If they already have the maximum reward, they are a valid winner
                validWinnersCount++;
                continue;
            }

            // They can only be a valid winner if their total points is greater than or equal to the second highest possible score
            if (totalPointsIfWin >= maxReward + n - 1) {
                validWinnersCount++;
            }
        }
        return validWinnersCount;
    }
}
