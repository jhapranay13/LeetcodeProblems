package LeetcodeDiscuss;


import java.util.Arrays;

/**
 *
 * You are given two N-sided dice, P and Q, where each die has N integer
 * values (not necessarily unique or standard).
 *
 * When rolled, each side is equally likely to appear.
 *
 * Given the values on each side of dice P and Q, determine:
 *
 * The number of outcomes where P's roll is greater than Q's roll (i.e., P wins).
 *
 * Follow up 1: The number of outcomes where Q wins.
 *
 * Follow up 2: The number of outcomes where both rolls are equal (tie).
 *
 * Example: (N=3)
 *
 * P = [1, 2, 4]
 * Q = [2, 2, 5]
 *
 */
public class GoogleOaPOrQWinsOrTie {

    public static void main(String[] args) {
        // Example 1: N=3, P = [1, 2, 4], Q = [2, 2, 5]
        int[] P1 = {1, 2, 4};
        int[] Q1 = {2, 2, 5};
        System.out.println("Example 1 (Brute Force): " + Arrays.toString(calculateOutcomesBruteForce(P1, Q1)));
        System.out.println("Example 1 (Optimized):   " + Arrays.toString(calculateOutcomesOptimized(P1, Q1)));
        // Expected: P Wins: 2, Q Wins: 5, Ties: 2

        // Example 2: P = [3, 3, 3], Q = [1, 2, 4]
        int[] P2 = {3, 3, 3};
        int[] Q2 = {1, 2, 4};
        System.out.println("Example 2 (Brute Force): " + Arrays.toString(calculateOutcomesBruteForce(P2, Q2)));
        System.out.println("Example 2 (Optimized):   " + Arrays.toString(calculateOutcomesOptimized(P2, Q2)));
        // Expected: P Wins: 6 (3>1, 3>2 for each 3 in P), Q Wins: 3 (3<4 for each 3 in P), Ties: 0

        // Example 3: All equal
        int[] P3 = {5, 5, 5};
        int[] Q3 = {5, 5, 5};
        System.out.println("Example 3 (Brute Force): " + Arrays.toString(calculateOutcomesBruteForce(P3, Q3)));
        System.out.println("Example 3 (Optimized):   " + Arrays.toString(calculateOutcomesOptimized(P3, Q3)));
        // Expected: P Wins: 0, Q Wins: 0, Ties: 9

        // Example 4: No ties, some wins
        int[] P4 = {10, 20};
        int[] Q4 = {5, 15};
        System.out.println("Example 4 (Brute Force): " + Arrays.toString(calculateOutcomesBruteForce(P4, Q4)));
        System.out.println("Example 4 (Optimized):   " + Arrays.toString(calculateOutcomesOptimized(P4, Q4)));
        // Expected:
        // P=10: vs Q=5 (P wins), vs Q=15 (Q wins)
        // P=20: vs Q=5 (P wins), vs Q=15 (P wins)
        // P Wins: 3, Q Wins: 1, Ties: 0
    }

    public static long[] calculateOutcomesBruteForce(int[] P, int[] Q) {
        long pWins = 0;
        long qWins = 0;
        long ties = 0;

        int N = P.length;
        if (N == 0) {
            return new long[] {0, 0, 0};
        }

        for (int pVal : P) {
            for (int qVal : Q) {
                if (pVal > qVal) {
                    pWins++;
                } else if (pVal < qVal) {
                    qWins++;
                } else {
                    ties++;
                }
            }
        }
        return new long[] {pWins, qWins, ties};
    }

    public static long[] calculateOutcomesOptimized(int[] P, int[] Q) {
        long pWins = 0;
        long qWins = 0;
        long ties = 0;

        int N = P.length;
        if (N == 0) {
            return new long[] {0, 0, 0};
        }

        // Sort both dice arrays
        Arrays.sort(P);
        Arrays.sort(Q);

        // Calculate P wins and ties against Q
        // For each p_val, we count how many q_val are smaller or equal
        int qPointer = 0; // Pointer for Q array
        for (int p_val : P) {
            // Move qPointer until q_val >= p_val
            // All Q values before qPointer are less than p_val
            while (qPointer < N && Q[qPointer] < p_val) {
                qPointer++;
            }
            // At this point, Q[qPointer] is either >= p_val or qPointer is N

            // Number of Q values strictly less than p_val
            pWins += qPointer;

            // Now, count ties
            int tieCount = 0;
            int tempQPointer = qPointer; // Use a temporary pointer for ties

            while (tempQPointer < N && Q[tempQPointer] == p_val) {
                tieCount++;
                tempQPointer++;
            }
            ties += tieCount;
        }

        // Total possible outcomes
        long totalOutcomes = (long) N * N;

        // Q wins is simply total outcomes minus P wins and ties
        qWins = totalOutcomes - pWins - ties;

        return new long[] {pWins, qWins, ties};
    }
}
