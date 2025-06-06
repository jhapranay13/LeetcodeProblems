package LeetcodeDiscuss;

import java.util.Arrays;

/**
 *  Hermione is preparing a cheat-sheet for her final exam in Potions class.
 *  To create a potion, one must combine ingredients in a specific order,
 *  any of which may be repeated.
 *
 * As an example, consider the following potion which uses 4 distinct ingredients
 * (A,B,C,D) in 11 steps: A, B, A, B, C, A, B, A, B, C, D
 *
 * Hermione realizes she can save tremendous space on her cheat-sheet by introducing a
 * special instruction, '*', which means "repeat from the beginning".
 *
 * Using these optimizations, Hermione is able to encode the potion above using only 6
 * characters: A,B,* ,C,*,D
 *
 * Your job is to write a function that takes as input an un-encoded potion and
 * returns the minimum number of characters required to encode the potion on Hermione's Cheat Sheet.
 */

public class GoldManOAHermoineCheatSheetCompression {


    public static void main(String[] args) {

        String potion1 = "ABABCABABCAD".replace(" ", "");
        System.out.println("Potion: " + potion1);
        System.out.println("Min encoded length: " + minimumEncodedLength(potion1)); // Expected: 6
        System.out.println("Min encoded length Bottom up: " + recurBottomUp(potion1)); // Expected: 6

        String potion2 = "ABABAB";
        System.out.println("Potion: " + potion2);
        System.out.println("Min encoded length: " + minimumEncodedLength(potion2)); // Expected: 5
        System.out.println("Min encoded length Bottom up: " + recurBottomUp(potion2)); // Expected: 6

        String potion3 = "AAAAA";
        System.out.println("Potion: " + potion3);
        System.out.println("Min encoded length: " + minimumEncodedLength(potion3)); // Expected: 4
        System.out.println("Min encoded length Bottom up: " + recurBottomUp(potion3)); // Expected: 6

        String potion4 = "ABCDEFG";
        System.out.println("Potion: " + potion4);
        System.out.println("Min encoded length: " + minimumEncodedLength(potion4)); // Expected: 7
        System.out.println("Min encoded length Bottom up: " + recurBottomUp(potion4)); // Expected: 6

        String potion5 = "ABCABCABCABC";
        System.out.println("Potion: " + potion5);
        System.out.println("Min encoded length: " + minimumEncodedLength(potion5)); // Expected: 5
        System.out.println("Min encoded length Bottom up: " + recurBottomUp(potion5)); // Expected: 6

        String potion6 = "";
        System.out.println("Potion: " + potion6);
        System.out.println("Min encoded length: " + minimumEncodedLength(potion6)); // Expected: 0
        System.out.println("Min encoded length Bottom up: " + recurBottomUp(potion6)); // Expected: 6

        String potion7 = "ABABCABABCD".replace(" ", "");
        System.out.println("Potion: " + potion7);
        System.out.println("Min encoded length: " + minimumEncodedLength(potion7)); // Expected: 6
        System.out.println("Min encoded length Bottom up: " + recurBottomUp(potion7)); // Expected: 6

    }
    static long[] hashes;
    static long[] powers;
    static long prime = 37;
    static int mod = 1000000007;

    private static void precomputeHashes(String potion) {
        int n = potion.length();
        hashes = new long[n];
        powers = new long[n + 1];
        powers[0] = 1;
        hashes[0] = (potion.charAt(0) - 'A' + 1) % mod;

        for (int i = 1; i < n; i++) {
            hashes[i] = (hashes[i - 1] * prime + (potion.charAt(i) - 'A' + 1)) % mod;
            powers[i] = (powers[i - 1] * prime) % mod;
        }
    }

    private static long getHash(int start, int end) {
        long prevHash = start <= 0 ? 0 : hashes[start - 1];
        long currentHash = hashes[end];
        long hash = (currentHash - (prevHash * powers[end - start + 1]) % mod + mod) % mod;
        return hash;
    }

    private static int minimumEncodedLength(String potion) {
        int n = potion.length();

        if (n == 0) {
            return 0;
        }
        precomputeHashes(potion);
        int[] memo = new int[n];
        Arrays.fill(memo, -1);

        return recur(potion, n - 1, memo);
    }

    private static int recur(String potion, int index, int[] memo) {
        // There is no point in checking if the index is 0,
        if (index == 0) {
            return 1;
        }

        if (memo[index] != -1) {
            return memo[index];
        }

        int ans = recur(potion, index - 1, memo) + 1; // + 1 for the current character
        // For even lengths
        if (index % 2 == 1) {
            int pivot = index / 2;
            long hash1 = getHash(pivot + 1, index); // This is to ensure the hash is computed
            long hash2 = getHash(0, pivot);

            if (hash1 == hash2) {
                ans = Math.min(ans, recur(potion, pivot, memo) + 1); // + 1 for *
            }
        }
        return memo[index] = ans;
    }

    private static int recurBottomUp(String potion) {
        int n = potion.length();

        if (n == 0) {
            return 0;
        }
        precomputeHashes(potion);
        int[] memo = new int[n];
        memo[0] = 1; // Base case: empty potion requires 0 characters

        for (int i = 1; i < n; i++) {
            memo[i] = memo[i - 1] + 1; // Default case: add current character

            if (i % 2 == 1) { // Check for even lengths
                int pivot = i / 2;
                long hash1 = getHash(pivot + 1, i);
                long hash2 = getHash(0, pivot);

                if (hash1 == hash2) {
                    memo[i] = Math.min(memo[i], memo[pivot] + 1); // +1 for '*'
                }
            }
        }
        return memo[n -1];
    }

}
