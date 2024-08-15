package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 10^4
 * t.length == s.length
 * s and t consist of any valid ascii character.
 *
 *
 */

public class _205_Isomorphic_Strings {
    public boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> charMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            char tch = t.charAt(i);

            if (charMap.containsKey(ch)) {

                if (charMap.get(ch) != tch) {
                    return false;
                }
            } else {

                if (charMap.containsValue(tch)) {
                    return false;
                } else  {
                    charMap.put(ch, tch);
                }
            }
        }
        return true;
    }
    //=============================================================================================
    //Another way
    public boolean isIsomorphic1(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> charMap = new HashMap<>();
        Map<Character, Character> charMap1 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            char tch = t.charAt(i);

            if (charMap.containsKey(ch) && charMap.get(ch) != tch) {
                return false;
            } else if (charMap1.containsKey(tch) && charMap1.get(tch) != ch) {
                return false;
            } else {
                charMap.put(ch, tch);
                charMap1.put(tch, ch);
            }
        }
        return true;
    }
}
