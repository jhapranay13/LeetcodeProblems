package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         A self-dividing number is a number that is divisible by every digit
 *         it contains.
 * 
 *         For example, 128 is a self-dividing number because 128 % 1 == 0, 128
 *         % 2 == 0, and 128 % 8 == 0. A self-dividing number is not allowed to
 *         contain the digit zero.
 * 
 *         Given two integers left and right, return a list of all the
 *         self-dividing numbers in the range [left, right].
 * 
 *         Example 1:
 * 
 *         Input: left = 1, right = 22 Output: [1,2,3,4,5,6,7,8,9,11,12,15,22]
 *         
 *         Example 2:
 * 
 *         Input: left = 47, right = 85 Output: [48,55,66,77]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= left <= right <= 104
 *
 */

public class _728_SelfDividingNumber {

	public _728_SelfDividingNumber() {
		// TODO Auto-generated constructor stub
	}

	public List<Integer> selfDividingNumbers(int left, int right) {
		List<Integer> ans = new ArrayList<>();

		for (int i = left; i <= right; i++) {

			if (check(i)) {
				ans.add(i);
			}
		}
		return ans;
	}

	private boolean check(int num) {
		String n = String.valueOf(num);

		for (int i = 0; i < n.length(); i++) {
			char ch = n.charAt(i);

			if (ch == '0' || num % (ch -'0') != 0) {
				return false;
			}
		}
		return true;
	}

	//=============================================================================================
	//Another Approach
	public List<Integer> selfDividingNumbers2(int left, int right) {
		List<Integer> ans = new ArrayList<>();

		for (int i = left; i <= right; i++) {

			if (check2(i)) {
				ans.add(i);
			}
		}
		return ans;
	}

	private boolean check2(int num) {
		int n = num;

		while (n != 0) {
			int rem = n % 10;
			n = n / 10;

			if (rem == 0 || num % rem != 0 ) {
				return false;
			}
		}
		return true;
	}
}
