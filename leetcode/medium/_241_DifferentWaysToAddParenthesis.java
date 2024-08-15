package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: expression = "2-1-1"
 * Output: [0,2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Example 2:
 *
 * Input: expression = "2*3-4*5"
 * Output: [-34,-14,-10,-10,10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 *
 * Constraints:
 *
 * 1 <= expression.length <= 20
 * expression consists of digits and the operator '+', '-', and '*'.
 * All the integer values in the input expression are in the range [0, 99].
 *
 */

public class _241_DifferentWaysToAddParenthesis {

    public List<Integer> diffWaysToCompute(String expression) {

        if (expression.length() < 3) {
            List<Integer> ans = new ArrayList<>();
            ans.add(Integer.parseInt(expression));
            return ans;
        }
        char[] expr = expression.toCharArray();
        return recur(expr, 0, expression.length() - 1);
    }

    private  List<Integer> recur(char arr[], int lo, int hi) {
        if (lo > hi) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        StringBuilder str = new StringBuilder();

        for (int i = lo; i <= hi; i++) {

            if (!Character.isDigit(arr[i])) {
                List<Integer> left = recur(arr, lo, i - 1);
                List<Integer> right= recur(arr, i + 1, hi);
                int calc = 0;

                for (int l : left) {

                    for (int r : right) {

                        if (arr[i] == '-') {
                            calc = l - r;
                        } else if (arr[i] == '+') {
                            calc = l + r;
                        } else if (arr[i] == '*') {
                            calc = l * r;
                        }
                        ans.add(calc);
                    }
                }
            } else {
                str.append(arr[i]);
            }
        }

        if (ans.isEmpty()) {
            ans.add(Integer.parseInt(str.toString()));
        }
        return ans;
    }
    //=============================================================================================
    //Memozation
    public List<Integer> diffWaysToCompute1(String expression) {
        Map<String, List<Integer>> memo = new HashMap<>();
        char[] expr = expression.toCharArray();
        return recur(expr, 0, expression.length() - 1, memo);
    }

    private  List<Integer> recur(char arr[], int lo, int hi, Map<String, List<Integer>> memo) {
        String key = lo + ", " + hi;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (lo > hi) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        StringBuilder str = new StringBuilder();

        for (int i = lo; i <= hi; i++) {

            if (!Character.isDigit(arr[i])) {
                List<Integer> left = recur(arr, lo, i - 1, memo);
                List<Integer> right= recur(arr, i + 1, hi, memo);
                int calc = 0;

                for (int l : left) {

                    for (int r : right) {

                        if (arr[i] == '-') {
                            calc = l - r;
                        } else if (arr[i] == '+') {
                            calc = l + r;
                        } else if (arr[i] == '*') {
                            calc = l * r;
                        }
                        ans.add(calc);
                    }
                }
            } else {
                str.append(arr[i]);
            }
        }

        if (ans.isEmpty()) {
            ans.add(Integer.parseInt(str.toString()));
        }
        memo.put(key, ans);
        return ans;
    }
}
