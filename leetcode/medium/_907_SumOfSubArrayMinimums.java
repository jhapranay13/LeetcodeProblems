package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of integers arr, find the sum of min(b), where b
 *         ranges over every (contiguous) subarray of arr. Since the answer may
 *         be large, return the answer modulo 109 + 7.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: arr = [3,1,2,4] Output: 17 Explanation: Subarrays are [3],
 *         [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 *         Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1. Sum is 17. Example 2:
 * 
 *         Input: arr = [11,81,94,43,3] Output: 444
 * 
 * 
 *         Constraints:
 * 
 *         1 <= arr.length <= 3 * 104 1 <= arr[i] <= 3 * 104
 *
 */

/*
 * 907. Sum of Subarray Minimums 496. Next Greater Element I 503. Next Greater
 * Element II 456. 132 Pattern 739. Daily Temperatures 901. Online Stock Span
 * 84. Largest Rectangle in Histogram 85. Maximal Rectangle 42. Trapping Rain
 * Water 316. Remove Duplicate Letters 402. Remove K Digits 321. Create Maximum
 * Number 1130. Minimum Cost Tree From Leaf Values 768. Max Chunks To Make
 * Sorted II 31. Next Permutation 556. Next Greater Element III
 * 
 * Monotonic Queue 239. Sliding Window Maximum 862. Shortest Subarray with Sum
 * at Least K
 */

public class _907_SumOfSubArrayMinimums {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void MonotonicStack(int[] A) {
		/*
		 * https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-
		 * solution-with-very-detailed-explanation-step-by-step
		 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/
		 * 204290/Monotonic-Queue-Summary Before diving into the solution, we first
		 * introduce a very important stack type, which is called monotonic stack .
		 * 
		 * What is monotonic ascending/descending stack? Roughly speaking, the elements
		 * in the an monotonic ascending stack keeps an ascending/descending order.
		 * 
		 * The typical paradigm for monotonic ascending stack:
		 * 
		 * for(int i = 0; i < A.length; i++){ while(!stack.isEmpty() && stack.peek() >
		 * A[i]){ stack.pop(); } stack.push(A[i]); }
		 * 
		 * What can monotonic ascending stack do? (1) find the previous less element of
		 * each element in a vector with O(n) time: What is the previous less element of
		 * an element? For example: [3, 7, 8, 4] The previous less element of 7 is 3.
		 * The previous less element of 8 is 7. The previous less element of 4 is 3.
		 * There is no previous less element for 3.
		 * 
		 * For simplicity of notation, we use abbreviation PLE to denote Previous Less
		 * Element. Java code (by slightly modifying the paradigm): Instead of directly
		 * pushing the element itself, here for simplicity, we push the index. We do
		 * some record when the index is pushed into the stack.
		 ** 
		 * NOTICE: Never use Stack and Vector class any more because both are
		 * deprecated. Use ArrayDeque or LinkedList instead. Summary: When do we need
		 * MonotonicStack? When we need to find its left and right boundaries. **
		 */
		// previous_less[i] = j means A[j] is the previous less element of A[i].
		Deque<Integer> stack = new ArrayDeque<>();
		int n = A.length;
		int[] previous_less = new int[n];
		Arrays.fill(previous_less, -1);
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && A[stack.peek()] >= A[i]) { // >= means strict less, > means non-strict less
				stack.pop();
			}
			previous_less[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(i);
		}
		System.out.println(Arrays.toString(previous_less));

		// or we can write this way
		Arrays.fill(previous_less, -1);
		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
				previous_less[stack.pop()] = i; // i < stack.peek(), A[i] < A[stack.peek()], strict less, >= means
												// non-strict less
			}
			stack.push(i);
		}
		System.out.println(Arrays.toString(previous_less));
		/*
		 * (2) find the next less element of each element in a vector with O(n) time:
		 * What is the next less element of an element? For example: [3, 7, 8, 4] The
		 * next less element of 8 is 4. The next less element of 7 is 4. There is no
		 * next less element for 3 and 4. For simplicity of notation, we use
		 * abbreviation NLE to denote Next Less Element.
		 * 
		 * Java code (by slightly modifying the paradigm): We do some record when the
		 * index is popped out from the stack.
		 */
		// next_less[i] = j means A[j] is the next less element of A[i].
		int[] next_less = new int[n];
		Arrays.fill(next_less, n);
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
				next_less[stack.pop()] = i;// i > stack.peek(), A[i] < A[stack.peek()], strict less, >= means non-strict
											// less
			}
			stack.push(i);
		}
		System.out.println(Arrays.toString(next_less));

		// or we can write this way
		Arrays.fill(next_less, n);
		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && A[stack.peek()] >= A[i]) {
				stack.pop();// >= means strict less, > means non-strict less
			}
			next_less[i] = stack.isEmpty() ? n : stack.peek();
			stack.push(i);
		}
		System.out.println(Arrays.toString(next_less));
		/*
		 * How can the monotonic ascending stack be applied to this problem? For
		 * example: Consider the element 3 in the following vector:
		 * 
		 * [2, 9, 7, 8, 3, 4, 6, 1] | | the previous less the next less element of 3
		 * element of 3
		 * 
		 * After finding both NLE and PLE of 3, we can determine the distance between 3
		 * and 2(previous less) , and the distance between 3 and 1(next less). In this
		 * example, the distance is 4 and 3 respectively.
		 * 
		 * How many subarrays with 3 being its minimum value? The answer is 4*3.
		 * 
		 * 9 7 8 3 9 7 8 3 4 9 7 8 3 4 6 7 8 3 7 8 3 4 7 8 3 4 6 8 3 8 3 4 8 3 4 6 3 3 4
		 * 3 4 6 How much the element 3 contributes to the final answer? It is 3*(4*3).
		 * What is the final answer? Denote by left[i] the distance between element A[i]
		 * and its PLE. Denote by right[i] the distance between element A[i] and its
		 * NLE.
		 * 
		 * The final answer is, sum(A[i]*left[i]*right[i] ) The last thing that needs to
		 * be mentioned for handling duplicate elements: Method: Set strict less and
		 * non-strict less(less than or equal to) for finding NLE and PLE respectively.
		 * The order doesn't matter
		 */
	}

	public int sumSubarrayMins(int[] A) {
		int mod = (int) Math.pow(10, 9) + 7;
		int n = A.length, res = 0;
		int[] left = new int[n], right = new int[n];
		// right[i] = j means A[j] is the next less element of A[i].
		Arrays.fill(right, n);
		// left[i] = j means A[j] is the previous less or equal element of A[i].
		Arrays.fill(left, -1);
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && A[i] < A[stack.peek()]) {
				right[stack.pop()] = i;
			}
			stack.push(i);
		}
		stack.clear();
		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && A[i] <= A[stack.peek()]) {
				left[stack.pop()] = i;
			}
			stack.push(i);
		}
		for (int i = 0; i < n; i++) {
			res = (res + A[i] * (i - left[i]) * (right[i] - i) % mod) % mod;
		}
		return res;
	}

	public int sumSubarrayMins2(int[] A) {
		int mod = (int) Math.pow(10, 9) + 7, top = -1;
		int n = A.length, res = 0;
		int[] left = new int[n], right = new int[n];
		// right[i] = j means A[j] is the next less element of A[i].
		Arrays.fill(right, n);
		// left[i] = j means A[j] is the previous less or equal element of A[i].
		Arrays.fill(left, -1);
		int[] stack = new int[n];
		for (int i = 0; i < n; i++) {
			while (top > -1 && A[i] < A[stack[top]]) {
				right[stack[top--]] = i;
			}
			stack[++top] = i;
		}
		Arrays.fill(stack, 0);
		top = -1;
		for (int i = n - 1; i >= 0; i--) {
			while (top > -1 && A[i] <= A[stack[top]]) {
				left[stack[top--]] = i;
			}
			stack[++top] = i;
		}
		for (int i = 0; i < n; i++) {
			res = (res + A[i] * (i - left[i]) * (right[i] - i) % mod) % mod;
		}
		return res;
	}

	// need to scan from 0 to n, because we need to process the last element after
	// it is pushed to the stack
	public int sumSubarrayMins3(int[] A) {
		Deque<Integer> stack = new ArrayDeque<>();
		int n = A.length, res = 0, mod = (int) 1e9 + 7, j, k;
		for (int i = 0; i <= n; i++) {
			while (!stack.isEmpty() && A[stack.peek()] > (i == n ? 0 : A[i])) {
				j = stack.pop();// only pops when A[stack.peek()] > A[i], so A[k] <= A[j], A[j] > A[i]
				k = stack.isEmpty() ? -1 : stack.peek();// A[k] is the previous less or equal number and A[i] is the
														// next less number
				res = (res + A[j] * (i - j) * (j - k)) % mod;// calculate the sum on the fly because A[j] is the
																// smallest number between (j, k] and [k, i)
			}
			stack.push(i);
		}
		return res;
	}

	public int sumSubarrayMins4(int[] A) {
		int n = A.length, res = 0, mod = (int) 1e9 + 7, j, k, top = -1;
		int[] stack = new int[n];
		for (int i = 0; i <= n; i++) {
			while (top > -1 && A[stack[top]] > (i == n ? 0 : A[i])) {
				j = stack[top--];// only pops when A[stack.peek()] > A[i], so A[k] <= A[j], A[j] > A[i]
				k = top == -1 ? -1 : stack[top];// A[k] is the previous less or equal number and A[i] is the next less
												// number
				res = (res + A[j] * (i - j) * (j - k)) % mod;// calculate the sum on the fly because A[j] is the
																// smallest number between (j, k] and [k, i)
			}
			stack[++top] = i;
		}
		return res;
	}

	public int sumSubarrayMins5(int[] A) {
		int mod = (int) 1e9 + 7;

		int[] prevLess = new int[A.length], nextLess = new int[A.length];
		Arrays.fill(prevLess, -1);
		Arrays.fill(nextLess, A.length);
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < A.length; i++) {
			while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
				nextLess[stack.pop()] = i;
			}
			prevLess[i] = (stack.isEmpty()) ? -1 : stack.peek();
			stack.push(i);
		}
		long res = 0;

		for (int i = 0; i < A.length; i++) {
			long curMins = (long) (A[i] * (i - (long) prevLess[i]) * ((long) nextLess[i] - i));
			res = (res + curMins) % mod;
		}

		return (int) res;
	}

	public int sumSubarrayMins6(int[] arr) {
		int[] prevLess = new int[arr.length];
		Deque<Integer> prevLessStack = new LinkedList<>();

		for (int i = 0; i < arr.length; i++) {
			while (!prevLessStack.isEmpty() && arr[prevLessStack.peek()] > arr[i]) {
				prevLessStack.pop();
			}
			prevLess[i] = prevLessStack.isEmpty() ? -1 : prevLessStack.peek();
			prevLessStack.push(i);
		}
		int[] nextLess = new int[arr.length];
		Deque<Integer> nextLessStack = new LinkedList<>();

		for (int i = arr.length - 1; i >= 0; i--) {
			while (!nextLessStack.isEmpty() && arr[nextLessStack.peek()] >= arr[i]) {
				nextLessStack.pop();
			}
			nextLess[i] = nextLessStack.isEmpty() ? arr.length : nextLessStack.peek();
			nextLessStack.push(i);
		}
		long ans = 0;

		for (int i = 0; i < arr.length; i++) {
			long temp = (long) (arr[i] * (i - (long) prevLess[i]) * ((long) nextLess[i] - i));
			ans = (ans + temp) % overFlowCheck;
		}
		return (int) ans;
	}

	private long overFlowCheck = 1000000007;
}
