package leetcode.Math;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Solve a given equation and return the value of 'x' in the form of a string "x=#value". The equation contains only '+', '-' operation, the variable 'x' and its coefficient. You should return "No solution" if there is no solution for the equation, or "Infinite solutions" if there are infinite solutions for the equation.
 *
 * If there is exactly one solution for the equation, we ensure that the value of 'x' is an integer.
 *
 *
 *
 * Example 1:
 *
 * Input: equation = "x+5-3+x=6+x-2"
 * Output: "x=2"
 * Example 2:
 *
 * Input: equation = "x=x"
 * Output: "Infinite solutions"
 * Example 3:
 *
 * Input: equation = "2x=x"
 * Output: "x=0"
 *
 *
 * Constraints:
 *
 * 3 <= equation.length <= 1000
 * equation has exactly one '='.
 * equation consists of integers with an absolute value in the range [0, 100] without any leading zeros, and the variable 'x'.
 *
 */

public class _640_Solve_the_Equation {
    public String solveEquation(String equation) {
        String[] equationPart = equation.split("=");
        List<Integer> leftSolve = solve(equationPart[0]);
        List<Integer> rightSolve = solve(equationPart[1]);
        int rightSide = 0;
        int leftSide = 0;
        rightSide -= leftSolve.get(1);
        rightSide += rightSolve.get(1);
        leftSide += leftSolve.get(0);
        leftSide -= rightSolve.get(0);

        if (leftSide == 0) {

            if (rightSide == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        }
        return "x=" + (rightSide /leftSide);
    }

    public List<Integer> solve(String expression) {
        StringBuilder holder = new StringBuilder();
        int xSum  = 0;
        int numSum = 0;
        int op = 1;

        for (int i = 0; i < expression.length(); i++) {

            if (expression.charAt(i) == 'x') {

                if (!holder.isEmpty()) {
                    int num = Integer.parseInt(holder.toString());
                    xSum += num * op;
                } else {
                    xSum += 1 * op;
                }
                holder = new StringBuilder();
            } else if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {

                if (!holder.isEmpty()) {
                    numSum += Integer.parseInt(holder.toString()) * op;
                    holder = new StringBuilder();
                }
                op = expression.charAt(i) == '+' ? 1 : -1;
            } else {
                holder.append(expression.charAt(i));
            }
        }

        if (!holder.isEmpty()) {
            numSum += Integer.parseInt(holder.toString()) * op;
            holder = new StringBuilder();
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(xSum);
        ans.add(numSum);
        return ans;
    }
}
