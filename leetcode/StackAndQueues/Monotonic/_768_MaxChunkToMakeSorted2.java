package leetcode.StackAndQueues.Monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an integer array arr.
 * 
 *         We split arr into some number of chunks (i.e., partitions), and
 *         individually sort each chunk. After concatenating them, the result
 *         should equal the sorted array.
 * 
 *         Return the largest number of chunks we can make to sort the array.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: arr = [5,4,3,2,1] Output: 1 Explanation: Splitting into two or
 *         more chunks will not return the required result. For example,
 *         splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3],
 *         which isn't sorted.
 * 
 *         Example 2:
 * 
 *         Input: arr = [2,1,3,4,4] Output: 4 Explanation: We can split into two
 *         chunks, such as [2, 1], [3, 4, 4]. However, splitting into [2, 1],
 *         [3], [4], [4] is the highest number of chunks possible.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= arr.length <= 2000 0 <= arr[i] <= 108
 *
 */

public class _768_MaxChunkToMakeSorted2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int maxChunksToSorted(int[] arr) {
		Deque<Integer> stack = new LinkedList<>();

		for (int i : arr) {

			if (stack.isEmpty()) {
				stack.push(i);
			} else {

				if (stack.peek() <= i) {
					stack.push(i);
				} else {
					int temp = stack.pop();

					while (!stack.isEmpty() && stack.peek() > i) {
						stack.pop();
					}
					stack.push(temp);
				}
			}
		}
		return stack.size();
	}
	//=============================================================================================
	//Another approach
	public int maxChunksToSorted1(int[] arr) {
		Deque<Integer> mono = new LinkedList<>();

		for (int i = 0; i < arr.length; i++) {

			if (!mono.isEmpty()) {

				if (!mono.isEmpty() && arr[mono.peek()] > arr[i]) {
					int temp = mono.pop();

					while (!mono.isEmpty() && arr[mono.peek()] > arr[i]) {
						mono.pop();
					}
					mono.push(temp);
					continue;
				}
			}
			mono.push(i);
		}
		return mono.size();
	}
}
