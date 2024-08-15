package leetcode.HashMapHashSet;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * Given a string formula representing a chemical formula, return the count of each atom.
 *
 * The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
 *
 * One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow.
 *
 * For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.
 * Two formulas are concatenated together to produce another formula.
 *
 * For example, "H2O2He3Mg4" is also a formula.
 * A formula placed in parentheses, and a count (optionally added) is also a formula.
 *
 * For example, "(H2O2)" and "(H2O2)3" are formulas.
 * Return the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
 *
 * The test cases are generated so that all the values in the output fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: formula = "H2O"
 * Output: "H2O"
 * Explanation: The count of elements are {'H': 2, 'O': 1}.
 * Example 2:
 *
 * Input: formula = "Mg(OH)2"
 * Output: "H2MgO2"
 * Explanation: The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
 * Example 3:
 *
 * Input: formula = "K4(ON(SO3)2)2"
 * Output: "K4N2O14S4"
 * Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
 *
 *
 * Constraints:
 *
 * 1 <= formula.length <= 1000
 * formula consists of English letters, digits, '(', and ')'.
 * formula is always valid.
 *
 */

public class _726_NumberOfAtoms {
    public String countOfAtoms(String formula) {
        Map<String, Integer> atomMap= recur(formula);
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        StringBuilder ans = new StringBuilder();

        for (String key : atomMap.keySet()) {
            pq.offer(key);
        }

        while (!pq.isEmpty()) {
            int num = atomMap.get(pq.peek());
            ans.append(pq.poll());

            if (num > 1) {
                ans.append(num);
            }
        }
        return ans.toString();
    }

    private int index = 0;

    private Map<String, Integer> recur(String formula) {
        Map<String, Integer> atomMap = new HashMap<>();
        StringBuilder str = new StringBuilder();
        int count = 0;

        while (index < formula.length()) {
            char ch = formula.charAt(index++);

            if (ch == '(') {
                Map<String, Integer> nestedMap = recur(formula);
                int num = 0;

                while (index < formula.length() && Character.isDigit(formula.charAt(index))) {
                    int temp = formula.charAt(index) - '0';
                    index++;
                    num *= 10;
                    num += temp;
                }

                if (num == 0) {
                    num = 1;
                }

                for (String key : nestedMap.keySet()) {
                    int val = nestedMap.get(key);
                    atomMap.put(key, atomMap.getOrDefault(key, 0) + (val * num));
                }
                continue;
            }

            if (ch == ')') {
                break;
            }

            if (Character.isAlphabetic(ch)) {
                str.append(ch);

                while (index < formula.length() && Character.isLowerCase(formula.charAt(index))) {
                    str.append(formula.charAt(index++));
                }

                while(index < formula.length() && Character.isDigit(formula.charAt(index))) {
                    int temp = formula.charAt(index) - '0';
                    count *= 10;
                    count += temp;
                    index++;
                }
                int tempCount = atomMap.getOrDefault(str.toString(), 0);
                atomMap.put(str.toString(), tempCount + (count == 0 ? 1 : count));
                count = 0;
                str = new StringBuilder();
            }
        }

        if (str.length() > 0) {
            atomMap.put(str.toString(), count == 0 ? 1 : count);
        }
        return atomMap;
    }
}
