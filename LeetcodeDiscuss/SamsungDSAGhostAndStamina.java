package LeetcodeDiscuss;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * You're on a haunted straight road of length L with N ghosts positioned along it.
 *
 * Each ghost is described by:
 *
 * A position A[i] on the road (0 ≤ A[i] ≤ L)
 *
 * A stamina level B[i]
 *
 * You are allowed to trap exactly one ghost, and this ghost becomes your trap ghost.
 * Starting from this position, you can move only to the right, hunting more ghosts
 * under the following rules:
 *
 * Rules for hunting:
 * You may only hunt ghosts whose stamina is greater than or equal to the trap ghost's stamina.
 *
 * Hunting a ghost costs energy equal to:
 *
 * (distance from the trap)×(ghost’s stamina)
 *
 * The total energy used must not exceed a given limit E.
 *
 * Goal:
 * Determine the maximum number of ghosts you can hunt including the trap ghost,
 * under the above constraints.
 *
 * Input:
 * L = 20
 * N = 5
 * E = 100
 *
 * A = [2, 5, 10, 14, 18]
 * B = [3, 2, 4, 5, 6]
 *
 * Output: 3
 * Explanation:
 *
 * If you trap the ghost at position 10 with stamina 4
 * You can hunt ghosts at positions 14 (stamina 5) and 18 (stamina 6)
 * Cost to hunt at 14: (14 - 10) * 5 = 20
 * Cost to hunt at 18: (18 - 10) * 6 = 48
 * Total cost: 20 + 48 = 68 ≤ 100
 * Total ghosts hunted = 3 (at positions 10, 14, 18)
 *
 */
public class SamsungDSAGhostAndStamina {
    public static void main(String[] args) {
        int L1 = 20;
        int N1 = 5;
        long E1 = 100;
        int[] A1 = {2, 5, 10, 14, 18};
        int[] B1 = {3, 2, 4, 5, 6};
        // Expected Output: 3 (Trap at 10 (stamina 4), hunt 14 (stamina 5) cost 20, hunt 18 (stamina 6) cost 48. Total 68 <= 100)
        System.out.println("Example 1 Output: " + maxGhostsHunted(L1, N1, E1, A1, B1)); // Expected: 3

        // Test case: All ghosts can be hunted
        int L2 = 10;
        int N2 = 3;
        long E2 = 1000;
        int[] A2 = {1, 3, 5};
        int[] B2 = {1, 1, 1};
        // Expected: 3 (Trap at 1. Hunt 3 (cost 2*1=2). Hunt 5 (cost 4*1=4). Total 6)
        System.out.println("Example 2 Output: " + maxGhostsHunted(L2, N2, E2, A2, B2)); // Expected: 3

        // Test case: No ghosts can be hunted beyond trap
        int L3 = 10;
        int N3 = 3;
        long E3 = 1; // Very low energy
        int[] A3 = {1, 3, 5};
        int[] B3 = {1, 1, 1};
        // Expected: 1 (Only trap ghost)
        System.out.println("Example 3 Output: " + maxGhostsHunted(L3, N3, E3, A3, B3)); // Expected: 1

        // Test case: Unsorted positions (should be handled by sorting)
        int L4 = 15;
        int N4 = 4;
        long E4 = 50;
        int[] A4 = {8, 2, 12, 5}; // Unsorted
        int[] B4 = {3, 4, 2, 5};
        // Sorted: (2,4), (5,5), (8,3), (12,2)
        // Trap (2,4):
        //   (5,5): dist=3, cost=3*5=15. E=15. Count=2.
        //   (8,3): stamina too low.
        //   Max 2.
        // Trap (5,5):
        //   (8,3): stamina too low.
        //   Max 1.
        // Expected: 2
        System.out.println("Example 4 Output: " + maxGhostsHunted(L4, N4, E4, A4, B4)); // Expected: 2

        // Test case: All stamina levels are high, energy limits them
        int L5 = 100;
        int N5 = 5;
        long E5 = 20;
        int[] A5 = {10, 20, 30, 40, 50};
        int[] B5 = {5, 5, 5, 5, 5};
        // Trap at 10 (stamina 5)
        //  Hunt 20 (stamina 5): (20-10)*5 = 50. Too much for E=20.
        // Expected: 1 (only the trap ghost)
        System.out.println("Example 5 Output: " + maxGhostsHunted(L5, N5, E5, A5, B5)); // Expected: 1

        // Test case: All stamina levels are low, but energy allows for many
        int L6 = 100;
        int N6 = 5;
        long E6 = 1000;
        int[] A6 = {10, 20, 30, 40, 50};
        int[] B6 = {1, 1, 1, 1, 1};
        // Trap at 10 (stamina 1)
        //  Hunt 20: (10)*1 = 10
        //  Hunt 30: (20)*1 = 20
        //  Hunt 40: (30)*1 = 30
        //  Hunt 50: (40)*1 = 40
        // Total = 10+20+30+40 = 100 <= 1000. All 5 hunted.
        System.out.println("Example 6 Output: " + maxGhostsHunted(L6, N6, E6, A6, B6)); // Expected: 5
    }

    private static int maxGhostsHunted(int l1, int numberOfElements, long limit, int[] pos, int[] stamina) {
        int ans = 0;
        int[][] ghostStaminaIndex = new int[numberOfElements][3];

        for (int i = 0; i < numberOfElements; i++) {
            ghostStaminaIndex[i][0] = pos[i]; // Position
            ghostStaminaIndex[i][1] = stamina[i]; // Stamina
            ghostStaminaIndex[i][2] = i; // Original index
        }
        Arrays.sort(ghostStaminaIndex, Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < numberOfElements; i++) {
            int trapPosition = ghostStaminaIndex[i][0];
            int trapStamina = ghostStaminaIndex[i][1];
            long energyUsed = 0;
            int count = 1; // Start with the trap ghost

            for (int j = i + 1; j < numberOfElements; j++) {

                int ghostPosition = ghostStaminaIndex[j][0];
                int ghostStamina = ghostStaminaIndex[j][1];

                if (ghostStamina < trapStamina) continue; // Cannot hunt this ghost

                long distance = Math.abs(ghostPosition - trapPosition);
                long cost = distance * ghostStamina;

                if (energyUsed + cost <= limit) {
                    energyUsed += cost;
                    count++;
                } else {
                    break; // No more energy to hunt further ghosts
                }
            }
            ans = Math.max(ans, count); // Update maximum ghosts hunted
        }
        return ans;
    }
}
