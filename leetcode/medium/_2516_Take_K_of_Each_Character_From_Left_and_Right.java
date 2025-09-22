package leetcode.medium;

/**
 *
 *
 * You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.
 *
 * Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aabaaaacaabc", k = 2
 * Output: 8
 * Explanation:
 * Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
 * Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
 * A total of 3 + 5 = 8 minutes is needed.
 * It can be proven that 8 is the minimum number of minutes needed.
 * Example 2:
 *
 * Input: s = "a", k = 1
 * Output: -1
 * Explanation: It is not possible to take one 'b' or 'c' so return -1.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of only the letters 'a', 'b', and 'c'.
 * 0 <= k <= s.length
 *
 */
public class _2516_Take_K_of_Each_Character_From_Left_and_Right {
    // The idea is to find out the max window. Outside of this window the
    // char count should be greater than equal to k
    // recursion will give TLE
    public int takeCharacters(String s, int k) {
        int charCount[] = new int[3];

        for (char ch : s.toCharArray()) {
            charCount[ch - 'a']++;
        }

        for (int count : charCount) {

            if (count < k) {
                return -1;
            }
        }
        int fast = 0;
        int slow = 0;
        int currCount[] = new int[3];
        int maxWindow = 0;

        while (fast < s.length()) {
            char fch = s.charAt(fast);
            currCount[fch - 'a']++;

            while (slow <= fast && (
                    charCount[0] - currCount[0] < k ||
                            charCount[1] - currCount[1] < k ||
                            charCount[2] - currCount[2] < k
            )) {
                char sch = s.charAt(slow);
                currCount[sch - 'a']--;
                slow++;
            }
            maxWindow = Math.max(maxWindow, fast - slow + 1);
            fast++;
        }
        return s.length() - maxWindow;
    }
}
