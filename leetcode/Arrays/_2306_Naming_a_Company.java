package leetcode.Arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 *You are given an array of strings ideas that represents a list of names to be used in the process of naming a company. The process of naming a company is as follows:
 *
 * Choose 2 distinct names from ideas, call them ideaA and ideaB.
 * Swap the first letters of ideaA and ideaB with each other.
 * If both of the new names are not found in the original ideas, then the name ideaA ideaB (the concatenation of ideaA and ideaB, separated by a space) is a valid company name.
 * Otherwise, it is not a valid name.
 * Return the number of distinct valid names for the company.
 *
 *
 *
 * Example 1:
 *
 * Input: ideas = ["coffee","donuts","time","toffee"]
 * Output: 6
 * Explanation: The following selections are valid:
 * - ("coffee", "donuts"): The company name created is "doffee conuts".
 * - ("donuts", "coffee"): The company name created is "conuts doffee".
 * - ("donuts", "time"): The company name created is "tonuts dime".
 * - ("donuts", "toffee"): The company name created is "tonuts doffee".
 * - ("time", "donuts"): The company name created is "dime tonuts".
 * - ("toffee", "donuts"): The company name created is "doffee tonuts".
 * Therefore, there are a total of 6 distinct company names.
 *
 * The following are some examples of invalid selections:
 * - ("coffee", "time"): The name "toffee" formed after swapping already exists in the original array.
 * - ("time", "toffee"): Both names are still the same after swapping and exist in the original array.
 * - ("coffee", "toffee"): Both names formed after swapping already exist in the original array.
 * Example 2:
 *
 * Input: ideas = ["lack","back"]
 * Output: 0
 * Explanation: There are no valid selections. Therefore, 0 is returned.
 *
 *
 * Constraints:
 *
 * 2 <= ideas.length <= 5 * 104
 * 1 <= ideas[i].length <= 10
 * ideas[i] consists of lowercase English letters.
 * All the strings in ideas are unique.
 *
 *
 */

public class _2306_Naming_a_Company {
    public long distinctNames(String[] ideas) {
        Map<Character, Set<String>> firstCharGroup = new HashMap<>();
        // Suffix should not match by removing the first character as the
        // words will be same
        for (String idea : ideas) {
            char ch = idea.charAt(0);
            Set<String> group = firstCharGroup.getOrDefault(ch, new HashSet<>());
            group.add(idea.substring(1));
            firstCharGroup.put(ch, group);
        }
        long ans = 0;

        for (char ch1 : firstCharGroup.keySet()) {
            Set<String> grp1 = firstCharGroup.get(ch1);

            for (char ch2 : firstCharGroup.keySet()) {

                if (ch1 == ch2) {
                    continue;
                }
                Set<String> grp2 = firstCharGroup.get(ch2);
                int commonSuffix = 0;

                for (String suffix : grp1) {

                    if (grp2.contains(suffix)) {
                        commonSuffix++;
                    }
                }
                int validSuffix1 = grp1.size() - commonSuffix;
                int validSuffix2 = grp2.size() - commonSuffix;

                ans += (validSuffix1 * validSuffix2);
            }
        }
        return ans;
    }
}
