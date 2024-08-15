package leetcode.ForBiginners.BinarySearch.SimpleBinarySearch;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a characters array letters that is sorted in non-decreasing
 *         order and a character target, return the smallest character in the
 *         array that is larger than target.
 * 
 *         Note that the letters wrap around.
 * 
 *         For example, if target == 'z' and letters == ['a', 'b'], the answer
 *         is 'a'.
 * 
 * 
 *         Example 1:
 * 
 *         Input: letters = ["c","f","j"], target = "a" Output: "c" 
 *         
 *         Example 2:
 * 
 *         Input: letters = ["c","f","j"], target = "c" Output: "f" 
 *         
 *         Example 3:
 * 
 *         Input: letters = ["c","f","j"], target = "d" Output: "f" 
 *         
 *         Example 4:
 * 
 *         Input: letters = ["c","f","j"], target = "g" Output: "j" 
 *         
 *         Example 5:
 * 
 *         Input: letters = ["c","f","j"], target = "j" Output: "c"
 * 
 * 
 *         Constraints:
 * 
 *         2 <= letters.length <= 104 letters[i] is a lowercase English letter.
 *         letters is sorted in non-decreasing order. letters contains at least
 *         two different characters. target is a lowercase English letter.
 *
 */

public class _744_FindSmallestLetterGreaterThanTarget {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public char nextGreatestLetter(char[] letters, char target) {
        int lo = 0;
        int hi = letters.length;
        
        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;
            
            if (letters[pivot] <= target) {
                lo = pivot + 1;
            } else {
                hi = pivot;
            }
        }
        return letters[lo % letters.length];
    }
    //=============================================================================================
    //another approach
    public char nextGreatestLetter1(char[] letters, char target) {
        int lo = 0;
        int hi = letters.length - 1;
        int index = hi;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (letters[pivot] <= target) {
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
                index = pivot;
            }
        }

        if (index == letters.length - 1 && letters[index] <= target) {
            index = 0;
        }
        return letters[index];
    }
}
