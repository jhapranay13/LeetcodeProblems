package LeetcodeDiscuss;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * You and a friend have received a special gold chain as a gift.
 * The chain links each have an integer weight, not necessarily the same.
 * You and your friend must choose one of the links to be removed and provided to charity, after which the chain will be reconnected.
 * After that, you can choose one place along the chain to split it in two, such that it creates two equally-weighted sections for you and your friend to take home.
 * For a given input chain (list of link weights), determine if a solution is possible
 *
 * 5 2 1 3 2 7
 *
 * Output : Yes.
 *
 * We can give 2 at index 1 to the charity.
 * The remaining array would be 5 1 3 2 7.
 * We can divide the remaining array into two parts (5 1 3) & (2 7).
 *
 */
public class GoogleDSALinkChainRemoval {
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {5, 2, 1, 3, 2, 7},      // Yes
                {1, 2, 3, 6},            // No
                {4, 1, 1, 1, 1, 4},      // Yes
                {10, 5, 5, 10},          // Yes
                {1, 1, 1, 1, 1, 1},      // No
                {2, 2, 2, 2, 2},         // Yes
                {100},                  // No (only one link)
                {3, 3, 6, 3, 3},         // Yes
                {7, 1, 2, 3, 4, 10},     // No
                {8, 4, 1, 3, 5, 7, 2},   // Yes
        };

        for (int i = 0; i < testCases.length; i++) {
            boolean result = canSplitChain(testCases[i]);
            System.out.println("Test case " + (i + 1) + ": " + Arrays.toString(testCases[i]));
            System.out.println("Output: " + (result ? "Yes" : "No"));
            System.out.println("--------------------------");
        }
    }

    private static boolean canSplitChain(int[] num) {
        Set<Integer> prefixSumSet = new HashSet<>();
        Set<Integer> suffixSumSet = new HashSet<>();
        int totalSum = 0;

        for (int weight : num) {
            totalSum += weight;
        }
        int currSum = 0;

        for (int i = 0; i < num.length; i++) {

            if ((totalSum - num[i]) % 2 == 0) {
                int target = (totalSum - num[i]) / 2;

                if (prefixSumSet.contains(target)) {
                    return true;
                }
            }
            currSum += num[i];
            prefixSumSet.add(currSum);
        }
        currSum = 0;

        for (int i = num.length - 1; i >= 0; i--) {
            if ((totalSum - num[i]) % 2 == 0) {
                int target = (totalSum - num[i]) / 2;

                if (suffixSumSet.contains(target)) {
                    return true;
                }
            }
            currSum += num[i];
            suffixSumSet.add(currSum);
        }
        return false;
    }

}
