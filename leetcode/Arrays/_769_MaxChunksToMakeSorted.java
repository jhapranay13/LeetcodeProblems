package leetcode.Arrays;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an integer array arr of length n that represents a
 *         permutation of the integers in the range [0, n - 1].
 * 
 *         We split arr into some number of chunks (i.e., partitions), and
 *         individually sort each chunk. After concatenating them, the result
 *         should equal the sorted array.
 * 
 *         Return the largest number of chunks we can make to sort the array.
 * 
 *         Example 1:
 * 
 *         Input: arr = [4,3,2,1,0] Output: 1 Explanation: Splitting into two or
 *         more chunks will not return the required result. For example,
 *         splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2],
 *         which isn't sorted. 
 *         
 *         Example 2:
 * 
 *         Input: arr = [1,0,2,3,4] Output: 4 Explanation: We can split into two
 *         chunks, such as [1, 0], [2, 3, 4]. However, splitting into [1, 0],
 *         [2], [3], [4] is the highest number of chunks possible.
 * 
 * 
 *         Constraints:
 * 
 *         n == arr.length 1 <= n <= 10 0 <= arr[i] < n All the elements of arr
 *         are unique.
 *
 */

public class _769_MaxChunksToMakeSorted {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Monotonic Approach
	public int maxChunksToSorted(int[] arr) {
		Deque<Integer> monoStack = new LinkedList<>();

		for (int num : arr) {
			if (monoStack.isEmpty()) {
				monoStack.push(num);
			} else {

				if (monoStack.peek() <= num) {
					monoStack.push(num);
				} else {
					//Since this value is greater we don't need to push num.
					//We just need to pop all the nums which are still greater than num
					//because it will ofrm one whole chunk
					int temp = monoStack.pop();

					while (!monoStack.isEmpty() && monoStack.peek() > num) {
						monoStack.pop();
					}
					monoStack.push(temp);
				}
			}
		}
		return monoStack.size();
	}
	//=============================================================================
	//Normal Iterative Approach using a window and two pointers
	public int maxChunksToSorted1(int[] arr) {;
    int count = 0;
    
    for (int i = 0; i < arr.length; i++) {
        
        if (arr[i] == i) {
            count++;
        } else {
            //Making a window and checking if window expands or not.
            int start = i + 1;
            int end = arr[i];
            
            while (start <= end) {
                
                if (arr[start] > end) {
                    end = arr[start];
                }
                start++;
            }
            count++;
            i = end;
        }
    }
    return count;
}
//===================================================================================
	//Another approach
public int maxChunksToSorted3(int[] arr) {;
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
