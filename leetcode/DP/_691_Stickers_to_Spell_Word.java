package leetcode.DP;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * We are given n different types of stickers. Each sticker has a lowercase English word on it.
 *
 * You would like to spell out the given string target by cutting individual letters from your collection of stickers and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each sticker.
 *
 * Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.
 *
 * Note: In all test cases, all words were chosen randomly from the 1000 most common US English words, and target was chosen as a concatenation of two random words.
 *
 *
 *
 * Example 1:
 *
 * Input: stickers = ["with","example","science"], target = "thehat"
 * Output: 3
 * Explanation:
 * We can use 2 "with" stickers, and 1 "example" sticker.
 * After cutting and rearrange the letters of those stickers, we can form the target "thehat".
 * Also, this is the minimum number of stickers necessary to form the target string.
 * Example 2:
 *
 * Input: stickers = ["notice","possible"], target = "basicbasic"
 * Output: -1
 * Explanation:
 * We cannot form the target "basicbasic" from cutting letters from the given stickers.
 *
 *
 * Constraints:
 *
 * n == stickers.length
 * 1 <= n <= 50
 * 1 <= stickers[i].length <= 10
 * 1 <= target.length <= 15
 * stickers[i] and target consist of lowercase English letters.
 *
 */

public class _691_Stickers_to_Spell_Word {
    public int minStickers(String[] stickers, String target) {
        Map<String, Map<Character, Integer>> holder = new HashMap<>();

        for (String sticker : stickers) {
            Map<Character, Integer> temp = new HashMap<>();

            for (char ch : sticker.toCharArray()) {
                temp.put(ch, temp.getOrDefault(ch, 0) + 1);
            }
            holder.put(sticker, temp);
        }
        Map<String, Integer> memo = new HashMap<>();
        int ans = recur(holder, target, memo);

        return ans <= 0 || ans >= Integer.MAX_VALUE ? -1 : ans;
    }

    private int recur(Map<String, Map<Character, Integer>> holder, String target, Map<String, Integer> memo) {

        if (target.length() == 0) {
            return 0;
        }

        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        int ans = Integer.MAX_VALUE;

        for (String sticker : holder.keySet()) {
            Map<Character, Integer> temp = new HashMap<>(holder.get(sticker));
            StringBuilder next = new StringBuilder();
            //This if statement
            //if (temp.containsKey(target.charAt(0))) {

            for (char ch : target.toCharArray()) {

                if (!temp.containsKey(ch)) {
                    next.append(ch);
                } else {
                    if (temp.get(ch) == 1) {
                        temp.remove(ch);
                    } else {
                        temp.put(ch, temp.get(ch) - 1);
                    }
                }
            }

            if (next.length() != target.length()) {
                int res = 1 + recur(holder, next.toString(), memo);
                ans = Math.min(ans, res);
            }
            //}
        }
        memo.put(target, ans);
        return ans;
    }
}
