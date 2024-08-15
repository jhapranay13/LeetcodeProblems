package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Let's define a function countUniqueChars(s) that returns the number of unique characters on s.
 *
 * For example if s = "LEETCODE" then "L", "T", "C", "O", "D" are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
 * Given a string s, return the sum of countUniqueChars(t) where t is a substring of s.
 *
 * Notice that some substrings can be repeated so in this case you have to count the repeated ones too.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ABC"
 * Output: 10
 * Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
 * Evey substring is composed with only unique letters.
 * Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
 * Example 2:
 *
 * Input: s = "ABA"
 * Output: 8
 * Explanation: The same as example 1, except countUniqueChars("ABA") = 1.
 * Example 3:
 *
 * Input: s = "LEETCODE"
 * Output: 92
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of uppercase English letters only.
 *
 *
 */

public class _828_CountUniqueCharactersOfAllSubstringsOfaGivenString {

    /**
     This is is very simple and can be done in linear time.

     Let K be a character in a particular string. Let left be the number of contiguous characters to the left of K not containing any K. Let right be the number of contiguous characters to the right of K not containing any K.

     The number of subarrays that K appears exactly onces are (left+1)*(right+1).

     For example, Consider the first O in SOLUTION, there is only 1 character to the left of O and there are 4 characters to the right of O that do not include any other O. Hence, left = 1, right = 4. Therefore, the number of subarrays that the first O in SOLUTION appears exactly once is (1+1)(4+1) = 10, which correspond to the following subarrays
     O, OL, OLU, OLUT, OLUTI
     SO, SOL, SOLU, SOLUT, SOLUTI
     This means that the contribution that the first O in SOLUTION makes to the final answer is 10.

     More Example: Consider LEETCODE
     L ----> left = 0, right = 7, subarrays = (0+1) * (7+1) = 8
     E ----> left = 1, right = 0, subarrays = (1+1) * (0+1) = 2
     E ----> left = 0, right = 4, subarrays = (0+1) * (4+1) = 5
     T ----> left = 3, right = 4, subarrays = (3+1) * (4+1) = 20
     C ----> left = 4, right = 3, subarrays = (4+1) * (3+1) = 20
     O ----> left = 5, right = 2, subarrays = (5+1) * (2+1) = 18
     D ----> left = 6, right = 1, subarrays = (6+1) * (1+1) = 14
     E -----> left = 4, right = 0, subarrays = (4+1) * (0+1) = 5

     Total contribution for all characters = 8 + 2 + 5 + 20 + 20 + 18 + 14 + 5 = 92


     */
    public int uniqueLetterString(String s) {
        int[] leftPos = new int[s.length()];
        Map<Character, Integer> lastPos = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int pos = lastPos.getOrDefault(ch, -1);

            if (pos == -1) {
                lastPos.put(ch, i);
                leftPos[i] = i - 0;
            } else {
                lastPos.put(ch, i);
                leftPos[i] = i - pos - 1;
            }
        }
        int[] rightPos = new int[s.length()];
        lastPos.clear();

        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            int pos = lastPos.getOrDefault(ch, -1);

            if (pos == -1) {
                lastPos.put(ch, i);
                rightPos[i] = s.length() - 1 - i;
            } else {
                lastPos.put(ch, i);
                rightPos[i] = pos - i - 1;
            }
        }
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int left = leftPos[i];
            int right = rightPos[i];
            ans += (left + 1) * (right + 1);
        }
        return ans;
    }
    //=============================================================================================
    //Another Approach
    public int uniqueLetterString1(String s) {
        Map<Character, Integer> lastPos = new HashMap<>();
        int[] fromLeft = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            fromLeft[i] = lastPos.getOrDefault(ch, -1);
            lastPos.put(ch, i);
        }
        int[] fromRight = new int[s.length()];
        lastPos.clear();

        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            fromRight[i] = lastPos.getOrDefault(ch, s.length());
            lastPos.put(ch, i);
        }
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int subStringCountLeft = i - fromLeft[i];
            int subStringCountRight = fromRight[i] - i;

            ans += (subStringCountLeft) * (subStringCountRight);
        }
        return ans;
    }
}
