package leetcode.medium;

/**
 *
 * You are given a 0-indexed string pattern of length n consisting of the characters 'I' meaning increasing and 'D' meaning decreasing.
 *
 * A 0-indexed string num of length n + 1 is created using the following conditions:
 *
 * num consists of the digits '1' to '9', where each digit is used at most once.
 * If pattern[i] == 'I', then num[i] < num[i + 1].
 * If pattern[i] == 'D', then num[i] > num[i + 1].
 * Return the lexicographically smallest possible string num that meets the conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: pattern = "IIIDIDDD"
 * Output: "123549876"
 * Explanation:
 * At indices 0, 1, 2, and 4 we must have that num[i] < num[i+1].
 * At indices 3, 5, 6, and 7 we must have that num[i] > num[i+1].
 * Some possible values of num are "245639871", "135749862", and "123849765".
 * It can be proven that "123549876" is the smallest possible num that meets the conditions.
 * Note that "123414321" is not possible because the digit '1' is used more than once.
 * Example 2:
 *
 * Input: pattern = "DDD"
 * Output: "4321"
 * Explanation:
 * Some possible values of num are "9876", "7321", and "8742".
 * It can be proven that "4321" is the smallest possible num that meets the conditions.
 *
 *
 * Constraints:
 *
 * 1 <= pattern.length <= 8
 * pattern consists of only the letters 'I' and 'D'.
 *
 */
public class _2375_Construct_Smallest_Number_From_DI_String {
    class Solution {
        public String smallestNumber(String pattern) {

            for (int i = 0; i < nums.length; i++) {
                nums[i] = i + 1;
            }
            int indexOfNum = 0;
            // if D is a series which digit to start with
            if (pattern.charAt(0) == 'D') {

                while (indexOfNum < pattern.length() && pattern.charAt(indexOfNum) == 'D') {
                    indexOfNum++;
                }
            }

            while (indexOfNum < nums.length) {
                StringBuilder holder = new StringBuilder();
                holder.append(nums[indexOfNum]);
                nums[indexOfNum] *= -1;

                if (recur(pattern, 0, holder)) {
                    return holder.toString();
                }
                nums[indexOfNum++] *= -1;
            }
            return "";
        }
        private int[] nums = new int[9];

        private boolean recur(String pattern, int index, StringBuilder holder) {

            if (pattern.length() == index) {
                return true;
            }
            boolean ans = false;
            int indx = -1;
            int num = Integer.parseInt("" + holder.charAt(holder.length() - 1));
            int end = nums.length;
            // setting start and end depending on current index and current character
            if (pattern.charAt(index) == 'I') {
                indx = num;
            } else {
                indx = 0;
                end = num;
            }

            while (indx < end) {
                if (nums[indx] < 0) {
                    indx++;
                    continue;
                }
                holder.append(nums[indx]);
                nums[indx] *= -1;

                if (recur(pattern, index + 1, holder)) {
                    ans = true;
                    break;
                }
                nums[indx++] *= -1;
                holder.deleteCharAt(holder.length() - 1);
            }
            return ans;
        }
    }
}
