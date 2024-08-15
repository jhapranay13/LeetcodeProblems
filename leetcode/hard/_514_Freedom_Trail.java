package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring" and use the dial to spell a specific keyword to open the door.
 *
 * Given a string ring that represents the code engraved on the outer ring and another string key that represents the keyword that needs to be spelled, return the minimum number of steps to spell all the characters in the keyword.
 *
 * Initially, the first character of the ring is aligned at the "12:00" direction. You should spell all the characters in key one by one by rotating ring clockwise or anticlockwise to make each character of the string key aligned at the "12:00" direction and then by pressing the center button.
 *
 * At the stage of rotating the ring to spell the key character key[i]:
 *
 * You can rotate the ring clockwise or anticlockwise by one place, which counts as one step. The final purpose of the rotation is to align one of ring's characters at the "12:00" direction, where this character must equal key[i].
 * If the character key[i] has been aligned at the "12:00" direction, press the center button to spell, which also counts as one step. After the pressing, you could begin to spell the next character in the key (next stage). Otherwise, you have finished all the spelling.
 *
 *
 * Example 1:
 *                          g
 *                      g        o
 *                   n              d
 *                      i        d
 *
 * Input: ring = "godding", key = "gd"
 * Output: 4
 * Explanation:
 * For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
 * For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
 * Also, we need 1 more step for spelling.
 * So the final output is 4.
 * Example 2:
 *
 * Input: ring = "godding", key = "godding"
 * Output: 13
 *
 *
 * Constraints:
 *
 * 1 <= ring.length, key.length <= 100
 * ring and key consist of only lower case English letters.
 * It is guaranteed that key could always be spelled by rotating ring.
 *
 */

public class _514_Freedom_Trail {
    public int findRotateSteps(String ring, String key) {
        Map<Character, List<Integer>> charIndexMap = new HashMap<>();
        int index = 0;

        for (char ch : ring.toCharArray()) {
            List<Integer> list = charIndexMap.getOrDefault(ch, new ArrayList<>());
            list.add(index++);
            charIndexMap.put(ch, list);
        }
        Integer[][] memo = new Integer[ring.length()][key.length()];
        return recur(ring, key, charIndexMap, 0, 0, memo);
    }

    private int recur(String ring, String key,
                      Map<Character, List<Integer>> charIndexMap, int prevIndex, int keyIndex, Integer[][] memo) {

        if (keyIndex == key.length()) {
            return 0;
        }

        if (memo[prevIndex][keyIndex] != null) {
            return memo[prevIndex][keyIndex];
        }
        char ch = key.charAt(keyIndex);
        List<Integer> indexes = charIndexMap.get(ch);
        int ans = Integer.MAX_VALUE;

        for (int index : indexes) {
            int stepOneSide = Math.abs(index - prevIndex);
            int stepAnotherSide = ring.length() - stepOneSide;
            int step = 1 + Math.min(stepOneSide, stepAnotherSide);
            ans = Math.min(ans, step + recur(ring, key, charIndexMap, index, keyIndex + 1, memo));
        }
        return memo[prevIndex][keyIndex] = ans;
    }
}
