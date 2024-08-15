package leetcode.StackAndQueues.Monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *
 *
 */

public class _84_LargestAreaOfHistogram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ============================================================
	// Recursive divide and conquer approach
	public int largestRectangleArea(int[] heights) {

		if (heights == null || heights.length == 0) {
			return 0;
		}

		if (heights.length == 1) {
			return heights[0];
		}
		int max = getMax(heights, 0, heights.length - 1);
		return max;
	}

	private int getMax(int[] h, int lo, int hi) {

		if (lo > hi) {
			return 0;
		}
		int minIndex = lo;

		for (int i = lo + 1; i <= hi; i++) {
			minIndex = h[minIndex] > h[i] ? i : minIndex;
			;
		}
		int distance = hi - lo + 1;
		int ans = distance * h[minIndex];
		int leftAns = getMax(h, lo, minIndex - 1);
		int rightAns = getMax(h, minIndex + 1, hi);
		return Math.max(ans, Math.max(leftAns, rightAns));
	}

	// ===========================================================================
	// Monotonic stack use
	public int largestRectangleArea1(int[] heights) {

		Deque<Integer> stack = new LinkedList<>();
		int ans = 0;

		for (int i = 0; i < heights.length; i++) {

			if (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
				stack.push(i);
			} else {

				while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
					int index = stack.pop();
					int distance = 0;

					if (stack.isEmpty()) {
						distance = i;
					} else {
						//calculatinng left and right ditance
						distance = i - index + (index - stack.peek() - 1);
					}
					ans = Math.max(ans, distance * heights[index]);
				}
				int distance = stack.isEmpty() ? i + 1 : i - stack.peek();
				ans = Math.max(ans, distance * heights[i]);
				stack.push(i);
			}
		}
		while (!stack.isEmpty()) {
			int index = stack.pop();
			int distance = 0;

			if (stack.isEmpty()) {
				distance = heights.length;
			} else {
				//calculating left and right distance
				distance = index - stack.peek() + (heights.length - index - 1);
			}
			ans = Math.max(ans, distance * heights[index]);
		}
		return ans;
	}
}
