package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an integer array cards of length 4. You have four
 *         cards, each containing a number in the range [1, 9]. You should
 *         arrange the numbers on these cards in a mathematical expression using
 *         the operators ['+', '-', '*', '/'] and the parentheses '(' and ')' to
 *         get the value 24.
 * 
 *         You are restricted with the following rules:
 * 
 *         The division operator '/' represents real division, not integer
 *         division. For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12. Every
 *         operation done is between two numbers. In particular, we cannot use
 *         '-' as a unary operator. For example, if cards = [1, 1, 1, 1], the
 *         expression "-1 - 1 - 1 - 1" is not allowed. You cannot concatenate
 *         numbers together For example, if cards = [1, 2, 1, 2], the expression
 *         "12 + 12" is not valid. Return true if you can get such expression
 *         that evaluates to 24, and false otherwise.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: cards = [4,1,8,7] Output: true Explanation: (8-4) * (7-1) = 24
 *         Example 2:
 * 
 *         Input: cards = [1,2,1,2] Output: false
 * 
 * 
 *         Constraints:
 * 
 *         cards.length == 4 1 <= cards[i] <= 9
 *
 */

public class _679_24Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean judgePoint24(int[] nums) {
		List<Double> numL = new ArrayList<>();

		for (int i : nums) {
			numL.add((double) i);
		}
		return judge(numL);
	}

	public boolean judge(List<Double> numList) {

		if (numList.size() == 0) {
			return false;
		}

		if (numList.size() == 1) {
			return (Math.abs(numList.get(0) - 24.0) < 1e-6);
		}

		for (int i = 0; i < numList.size(); i++) {

			for (int j = 0; j < numList.size(); j++) {

				if (i == j) {
					continue;
				}
				List<Double> nextNums = new ArrayList<>();

				for (int l = 0; l < numList.size(); l++) {

					if (l != i && l != j) {
						nextNums.add(numList.get(l));
					}
				}

				for (int k = 0; k < 4; k++) {

					if (k == 0) {
						nextNums.add(numList.get(i) + numList.get(j));
					}

					if (k == 1) {
						nextNums.add(numList.get(i) - numList.get(j));
					}

					if (k == 2) {
						nextNums.add(numList.get(i) * numList.get(j));
					}

					if (k == 3) {

						if (numList.get(j) != 0) {
							nextNums.add(numList.get(i) / numList.get(j));
						} else {
							continue;
						}
					}

					if (judge(nextNums)) {
						return true;
					}
					nextNums.remove(nextNums.size() - 1);
				}
			}
		}
		return false;
	}
}
