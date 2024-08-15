package leetcode.Arrays;

/**
 *
 * Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
 *
 * A string is represented by an array if the array elements concatenated in order forms the string.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
 * Output: true
 * Explanation:
 * word1 represents string "ab" + "c" -> "abc"
 * word2 represents string "a" + "bc" -> "abc"
 * The strings are the same, so return true.
 * Example 2:
 *
 * Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
 * Output: false
 * Example 3:
 *
 * Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 10^3
 * 1 <= word1[i].length, word2[i].length <= 10^3
 * 1 <= sum(word1[i].length), sum(word2[i].length) <= 10^3
 * word1[i] and word2[i] consist of lowercase letters.
 *
 */

public class _1662_Check_If_Two_String_Arrays_are_Equivalent {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int rowIndex1 = 0;
        int rowIndex2 = 0;
        int colIndex1 = 0;
        int colIndex2 = 0;

        while (rowIndex1 < word1.length || rowIndex2 < word2.length) {

            if (rowIndex1 == word1.length || rowIndex2 == word2.length) {
                return false;
            }

            while (colIndex1 < word1[rowIndex1].length() || colIndex2 < word2[rowIndex2].length()) {

                if (word1[rowIndex1].charAt(colIndex1++) != word2[rowIndex2].charAt(colIndex2++)) {
                    return false;
                }

                if (colIndex1 == word1[rowIndex1].length() || colIndex2 == word2[rowIndex2].length()){

                    if (colIndex1 == word1[rowIndex1].length()){
                        colIndex1 = 0;
                        rowIndex1++;
                    }

                    if (colIndex2 == word2[rowIndex2].length()){
                        colIndex2 = 0;
                        rowIndex2++;
                    }
                    break;
                }
            }
        }
        return true;
    }
}
