package LeetcodeDiscuss;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * There is a N player tournament. Players have rank 1 to N and each player has a unique rank. Assume that the best player always wins.
 *
 * The tournament is knockout format. Which means if we have 8 players [a b c d e f g h] with their ranks [1 2 3 4 5 6 7 8], the tournament will look like this:
 * 1st round: [a b] [c d] [e f] [g h]
 * 2nd round: [a c] [e g]
 * 3rd round: [a e]
 * champion : [a]
 *
 * We are calling [a b c d e f g h] or [1 2 3 4 5 6 7 8] a "draw" where in the 1st round: first two players meet in the first match, the next two players meet in the second match and so on.
 *
 * In the 2nd round: in the first match, the winner of the first match of 1st round and the winner of the second match of 1st round will play together. And similarly in the second match,
 * the winner of the third match of 1st round and the winner of the fourth match of 1st round will play together.
 *
 * In short: given a draw, if we don't change the order of the players, players will meet in their order on the draw, and of course the winner moves to the next round. The tournament ends when there is only a single player is remaining.
 *
 * Problem:
 * A draw is a valid draw when in each round, the best (based on rank) player plays with the worst player, the second best player plays with the second worst player and so on.
 *
 * Problem 1:
 * Given a draw, find out whether it is a valid draw.
 *
 * Round 1: [1,8,6,2,7,3,4,5] -> valid
 * Round 2: [1,2,3,4] -> invalid
 *
 * Round 1: [1,8,4,5,3,6,2,7] -> valid
 * Round 2: [1,4,3,2] -> valid
 * Round 3: [1,2] -> valid
 *
 * Round 1: [1,8,4,5,2,7,3,6] -> valid
 * Round 2: [1,4,2,3] -> valid
 * Round 3: [1,2] -> valid
 *
 */
public class GoogleL5ValidDraw {
    public static void main(String[] args) {
       
        System.out.println("Round 1: [1,8,6,2,7,3,4,5] -> " + isValidDraw(new int[]{1, 8, 6, 2, 7, 3, 4, 5})); // Problem says VALID. My code says FALSE.

        // These example fit my interpretation perfectly.
        System.out.println("Round 1: [1,8,4,5,3,6,2,7] -> " + isValidDraw(new int[]{1, 8, 4, 5, 3, 6, 2, 7})); // Problem says VALID. My code says TRUE.
        System.out.println("Round 2: [1,4,3,2] -> " + isValidDraw(new int[]{1, 4, 3, 2})); // Problem says VALID. My code says TRUE.
        System.out.println("Round 3: [1,2] -> " + isValidDraw(new int[]{1, 2}));       // Problem says VALID. My code says TRUE.

        // This example also fits my interpretation.
        System.out.println("Round 1: [1,8,4,5,2,7,3,6] -> " + isValidDraw(new int[]{1, 8, 4, 5, 2, 7, 3, 6})); // Problem says VALID. My code says TRUE.
        System.out.println("Round 2: [1,4,2,3] -> " + isValidDraw(new int[]{1, 4, 2, 3})); // Problem says VALID. My code says TRUE.
        System.out.println("Round 3: [1,2] -> " + isValidDraw(new int[]{1, 2}));       // Problem says VALID. My code says TRUE.


        // Custom test cases
        System.out.println("Custom 1: [1,2,3,4] -> " + isValidDraw(new int[]{1, 2, 3, 4})); // Expected: False (1 vs 2, 3 vs 4. Desired: 1 vs 4, 2 vs 3)
        System.out.println("Custom 2: [1,4,2,3] -> " + isValidDraw(new int[]{1, 4, 2, 3})); // Expected: True
        System.out.println("Custom 3: [5,8,1,2,7,3,4,6] -> " + isValidDraw(new int[]{5,8,1,2,7,3,4,6})); // Expected: False (1 and 8 are not together)

    }

    private static boolean isValidDraw(int[] draws) {
        List<Integer> current = new ArrayList<>();

        for (int pos : draws) {
            current.add(pos);
        }

        while (current.size() > 0) {
            List<Integer> sorted = new ArrayList<>(current);
            sorted.sort((a, b) -> Integer.compare(a, b));
            Set<String> validPairs = new HashSet<>();
            // greater one will always be 2nd element
            for (int i = 0; i < sorted.size() / 2; i++) {
                int first = sorted.get(i);
                int second = sorted.get(sorted.size() - 1 - i);
                String pair = first + "," + second;
                validPairs.add(pair);
            }
            List<Integer> nextRound = new ArrayList<>();

            for (int i = 0; i < current.size() / 2; i++) {
                int first = current.get(i * 2);
                int second = current.get(i * 2 + 1);
                int min = Math.min(first, second);
                int max = Math.max(first, second);
                String pair = min  + "," + max;

                if (!validPairs.contains(pair)) {
                    return false;
                }
                nextRound.add(min);
            }
            current = nextRound;
        }
        return true;
    }
}
