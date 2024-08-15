package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a string s containing only three types of characters: '(', ')'
 *         and '*', return true if s is valid.
 * 
 *         The following rules define a valid string:
 * 
 *         Any left parenthesis '(' must have a corresponding right parenthesis
 *         ')'. Any right parenthesis ')' must have a corresponding left
 *         parenthesis '('. Left parenthesis '(' must go before the
 *         corresponding right parenthesis ')'. '*' could be treated as a single
 *         right parenthesis ')' or a single left parenthesis '(' or an empty
 *         string "".
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "()" Output: true 
 *         
 *         Example 2:
 * 
 *         Input: s = "(*)" Output: true 
 *         
 *         Example 3:
 * 
 *         Input: s = "(*))" Output: true
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 100 s[i] is '(', ')' or '*'.
 *
 */

public class _678_ValidParenthesisString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Stack Based Solution
	public boolean checkValidString(String s) {
		Deque<Integer> aStack = new LinkedList<>();
		Deque<Integer> pStack = new LinkedList<>();
		return recur(s, aStack, pStack, 0);
	}

	private boolean recur(String s, Deque<Integer> aStack, Deque<Integer> pStack, 
			int index ) {
		if (index == s.length()) {
			if (pStack.isEmpty()) {
				return true;
			} else {
				return check(aStack, pStack, s);
			}
		}
		char ch = s.charAt(index);

		if (ch == '(') {
			pStack.push(index);
		} else if (ch == ')') {
			if (!pStack.isEmpty() && s.charAt(pStack.peek()) == '(') {
				pStack.pop();
			} else {
				pStack.push(index);
			}
		} else {
			aStack.push(index);
		}
		return recur(s, aStack, pStack, index + 1);
	}

	private boolean check(Deque<Integer> aStack, Deque<Integer> pStack, String s) {

		while (!pStack.isEmpty()) {

			if (aStack.isEmpty()) {
				return false;
			}

			if (s.charAt(pStack.peek()) == ')') {
				while (!aStack.isEmpty() && aStack.peek() > pStack.peek()) {
					aStack.pop();
				}

				if (!aStack.isEmpty()) {
					aStack.pop();
					pStack.pop();
				} else {
					return false;
				}
			} else if (s.charAt(pStack.peek()) == '(') {
				if(aStack.peek() < pStack.peek()) {
					return false;
				}
				aStack.pop();
				pStack.pop();
			}
		}
		return true;
	}
	//============================================================================
	// Approach #3: Greedy [Accepted]

	// Intuition

	// When checking whether the string is valid, we only cared about the "balance": the number of extra, open left brackets as we parsed through the string. For example, when checking whether '(()())' is valid, we had a balance of 1, 2, 1, 2, 1, 0 as we parse through the string: '(' has 1 left bracket, '((' has 2, '(()' has 1, and so on. This means that after parsing the first i symbols, (which may include asterisks,) we only need to keep track of what the balance could be.

	// For example, if we have string '(***)', then as we parse each symbol, the set of possible values for the balance is [1] for '('; [0, 1, 2] for '(*'; [0, 1, 2, 3] for '(**'; [0, 1, 2, 3, 4] for '(***', and [0, 1, 2, 3] for '(***)'.

	// Furthermore, we can prove these states always form a contiguous interval. Thus, we only need to know the left and right bounds of this interval. That is, we would keep those intermediate states described above as [lo, hi] = [1, 1], [0, 2], [0, 3], [0, 4], [0, 3].

	// Algorithm

	// Let lo, hi respectively be the smallest and largest possible number of open left brackets after processing the current character in the string.

	// If we encounter a left bracket (c == '('), then lo++, otherwise we could write a right bracket, so lo--. If we encounter what can be a left bracket (c != ')'), then hi++, otherwise we must write a right bracket, so hi--. If hi < 0, then the current prefix can't be made valid no matter what our choices are. Also, we can never have less than 0 open left brackets. At the end, we should check that we can have exactly 0 open left brackets.

	public boolean checkValidString1(String S) {
		int low=0,high=0;
		for(char s : S.toCharArray()) {
			if(s=='('){
				++low;
				++high;
			}
			else if(s == '*') {
				--low;
				++high;
			}
			else {
				--low;
				--high;
			}

			if(high<0){
				return false;
			}

			low = Math.max(low, 0);

		}
		return low==0;
	}
}
