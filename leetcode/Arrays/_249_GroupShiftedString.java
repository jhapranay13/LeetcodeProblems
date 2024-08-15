package leetcode.Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * We can shift a string by shifting each of its letters to its successive letter.
 *
 * For example, "abc" can be shifted to be "bcd".
 * We can keep shifting the string to form a sequence.
 *
 * For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
 * Given an array of strings strings, group all strings[i] that belong to the same shifting sequence.
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
 * Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
 * Example 2:
 *
 * Input: strings = ["a"]
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= strings.length <= 200
 * 1 <= strings[i].length <= 50
 * strings[i] consists of lowercase English letters.
 *
 */

public class _249_GroupShiftedString {

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> keyValMap = new HashMap<>();

        for (String str : strings) {
            StringBuilder key = new StringBuilder();

            if (str.length() == 1) {
                List<String> list = keyValMap.getOrDefault("-1", new ArrayList<>());
                list.add(str);
                keyValMap.put("-1", list);
                continue;
            }

            for (int i = 1; i < str.length(); i++) {
                key.append("|" + (str.charAt(i) - str.charAt(i - 1) + 26) % 26);
            }
            List<String> list = keyValMap.getOrDefault(key.toString(), new ArrayList<>());
            list.add(str);
            keyValMap.put(key.toString(), list);
        }
        List<List<String>> ans = new ArrayList<>();

        for (String key : keyValMap.keySet()) {
            ans.add(keyValMap.get(key));
        }
        return ans;
    }
}
