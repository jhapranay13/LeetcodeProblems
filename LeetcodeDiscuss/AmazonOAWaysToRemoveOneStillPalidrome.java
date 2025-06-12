package LeetcodeDiscuss;

/**
 *
 * First problem was about number of ways to remove one character so that
 * new string still remains palindrome. I solved using rolling hash with O(n) time complexity.
 *
 */
public class AmazonOAWaysToRemoveOneStillPalidrome {

    public static void main(String[] args) {
        System.out.println("String: \"aba\" -> Ways: " + countWaysToRemoveOne("aba")); // Expected: 1 (remove 'b' -> "aa")
        System.out.println("String: \"abca\" -> Ways: " + countWaysToRemoveOne("abca")); // Expected: 1 (remove 'b' -> "aca")
        System.out.println("String: \"racecar\" -> Ways: " + countWaysToRemoveOne("racecar")); // Expected: 0
        System.out.println("String: \"abacaba\" -> Ways: " + countWaysToRemoveOne("abacaba")); // Expected: 1 (remove middle 'c' -> "abaaba")
        System.out.println("String: \"madam\" -> Ways: " + countWaysToRemoveOne("madam")); // Expected: 1 (remove 'd' -> "maam")
        System.out.println("String: \"a\" -> Ways: " + countWaysToRemoveOne("a")); // Expected: 1
        System.out.println("String: \"\" -> Ways: " + countWaysToRemoveOne("")); // Expected: 0
        System.out.println("String: \"aa\" -> Ways: " + countWaysToRemoveOne("aa")); // Expected: 2 (remove first 'a' -> "a"; remove second 'a' -> "a")
        System.out.println("String: \"abc\" -> Ways: " + countWaysToRemoveOne("abc")); // Expected: 0
        System.out.println("String: \"abccba\" -> Ways: " + countWaysToRemoveOne("abccba")); // Expected: 2 (remove either 'c' -> "abcba")
        System.out.println("String: \"level\" -> Ways: " + countWaysToRemoveOne("level")); // Expected: 1 (remove 'v' -> "leel")
        System.out.println("String: \"google\" -> Ways: " + countWaysToRemoveOne("google")); // Expected: 0
        System.out.println("String: \"topcoderopen\" -> Ways: " + countWaysToRemoveOne("topcoderopen")); // Expected: 0
        System.out.println("String: \"aaaa\" -> Ways: " + countWaysToRemoveOne("aaaa")); // Expected: 4 (removing any 'a' leaves "aaa")
    }
    static long prime = 37;
    static int mod = 1000000007;
    static long[] powers;

    private static int countWaysToRemoveOne(String str) {

        if (str.trim().length() == 0) {
            return 0;
        }
        long[] hash = new long[str.length()];
        long[] reverseHash = new long[str.length()];
        powers = new long[str.length()];
        powers[0] = 1;

        for (int i = 1; i < str.length(); i++) {
            powers[i] = (powers[i - 1] * prime) % mod;
        }
        generateHash(str, hash);
        generateReverseHash(str, reverseHash);
        int ans = 0;
        int n = str.length() - 1;

        for (int i = 0; i < str.length(); i++) {
            int removeIndex = i;
            int lengthLeft = removeIndex;
            int lengthRight = n - removeIndex;
            long fwdHashLeft = getHash(0, removeIndex - 1, hash);
            long fwdHashRight = getHash(removeIndex + 1, n, hash);
            long revHashRight = reverseGetHash(0, n - (removeIndex + 1), reverseHash);
            long revHashLeft = reverseGetHash(n - (removeIndex - 1), n, reverseHash);
            long combineFwdHash = (fwdHashRight + (fwdHashLeft * powers[lengthRight]) % mod) % mod;
            long combineRevHash = (revHashLeft + (revHashRight * powers[i]) % mod) % mod;

            if (combineFwdHash == combineRevHash) {
                ans++;
            }
        }
        return ans;
    }

    private static long getHash(int start, int end, long[] hash) {
        if (start > end) {
            return 0; // Invalid range
        }
        long prevHash = start <= 0 ? 0 : hash[start - 1];
        long currentHash = hash[end];
        long hashValue = (currentHash - (prevHash * powers[end - start + 1]) % mod + mod) % mod;
        return hashValue;
    }

    private static long reverseGetHash(int start, int end, long[] reverseHash) {
        if (start > end) {
            return 0; // Invalid range
        }
        long prevHash = start <= 0 ? 0 : reverseHash[start - 1];
        long currentHash = reverseHash[end];
        long hashValue = (currentHash - (prevHash * powers[end - start + 1]) % mod + mod) % mod;
        return hashValue;
    }

    private static void generateReverseHash(String str, long[] reverseHash) {
        int n = str.length() - 1;
        reverseHash[0] = (str.charAt(n) - 'a' + 1) % mod;

        for (int i = n - 1; i >= 0; i--) {
            reverseHash[n - i] = (reverseHash[n - i - 1] * prime + (str.charAt(i) - 'a' + 1)) % mod;
        }
    }

    private static void generateHash(String str, long[] hash) {
        hash[0] = (str.charAt(0) - 'a' + 1) % mod;

        for (int i = 1; i < str.length(); i++) {
            hash[i] = (hash[i - 1] * prime + (str.charAt(i) - 'a' + 1)) % mod;
        }
    }
}
