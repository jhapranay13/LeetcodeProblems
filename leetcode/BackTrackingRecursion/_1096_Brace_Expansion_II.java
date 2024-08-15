package leetcode.BackTrackingRecursion;

import java.util.*;

/**
 *
 * Under the grammar given below, strings can represent a set of lowercase words. Let R(expr) denote the set of words the expression represents.
 *
 * The grammar can best be understood through simple examples:
 *
 * Single letters represent a singleton set containing that word.
 * R("a") = {"a"}
 * R("w") = {"w"}
 * When we take a comma-delimited list of two or more expressions, we take the union of possibilities.
 * R("{a,b,c}") = {"a","b","c"}
 * R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
 * When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
 * R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
 * R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
 * Formally, the three rules for our grammar:
 *
 * For every lowercase letter x, we have R(x) = {x}.
 * For expressions e1, e2, ... , ek with k >= 2, we have R({e1, e2, ...}) = R(e1) ∪ R(e2) ∪ ...
 * For expressions e1 and e2, we have R(e1 + e2) = {a + b for (a, b) in R(e1) × R(e2)}, where + denotes concatenation, and × denotes the cartesian product.
 * Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.
 *
 *
 *
 * Example 1:
 *
 * Input: expression = "{a,b}{c,{d,e}}"
 * Output: ["ac","ad","ae","bc","bd","be"]
 * Example 2:
 *
 * Input: expression = "{{a,z},a{b,c},{ab,z}}"
 * Output: ["a","ab","ac","z"]
 * Explanation: Each distinct word is written only once in the final answer.
 *
 *
 * Constraints:
 *
 * 1 <= expression.length <= 60
 * expression[i] consists of '{', '}', ','or lowercase English letters.
 * The given expression represents a set of words based on the grammar given in the description.
 *
 *
 */

public class _1096_Brace_Expansion_II {
    public List<String> braceExpansionII(String expression) {
        Set<String> holder = recur(expression);
        List<String> ans = new ArrayList<>();

        for (String str : holder) {
            ans.add(str);
        }
        Collections.sort(ans);
        return ans;
    }
    private int index = 0;

    private Set<String> recur(String exp) {
        Set<String> ans = new HashSet<>();

        if (index == exp.length()) {
            return ans;
        }
        Set<String> temp = new HashSet<>();
        StringBuilder holder = new StringBuilder();

        while (index < exp.length()) {
            char ch = exp.charAt(index++);

            if (ch == '}') {
                break;
            }

            if (ch == ',') {

                if (holder.length() > 0) {
                    ans.add(holder.toString());
                    holder = new StringBuilder();
                }

                if (temp.size() > 0) {
                    ans.addAll(temp);
                    temp.clear();
                }
            } else if (ch == '{') {
                Set<String> next = recur(exp);

                if (holder.length() > 0) {

                    for (String str : next) {
                        temp.add(holder.toString() + str);
                    }
                    holder = new StringBuilder();
                } else if (temp.size() > 0) {
                    Set<String> nextTemp = new HashSet<>();

                    for (String frmTemp : temp) {

                        for (String frmNext : next) {
                            nextTemp.add(frmTemp + frmNext);
                        }
                    }
                    temp = nextTemp;
                } else {
                    temp = next;
                }
            } else {
                holder.append(ch);

                if (temp.size() > 0) {
                    Set<String> nextTemp = new HashSet<>();

                    for (String frmTemp : temp) {
                        nextTemp.add(frmTemp + holder.toString());
                    }
                    temp = nextTemp;
                    holder = new StringBuilder();
                }
            }
        }

        if (holder.length() > 0) {
            ans.add(holder.toString());
            holder = new StringBuilder();
        }

        if (temp.size() > 0) {
            ans.addAll(temp);
            temp.clear();
        }
        return ans;
    }
}
