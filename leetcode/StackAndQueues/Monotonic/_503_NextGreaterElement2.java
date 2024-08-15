package leetcode.StackAndQueues.Monotonic;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a circular integer array nums (i.e., the next element of
 *         nums[nums.length - 1] is nums[0]), return the next greater number for
 *         every element in nums.
 * 
 *         The next greater number of a number x is the first greater number to
 *         its traversing-order next in the array, which means you could search
 *         circularly to find its next greater number. If it doesn't exist,
 *         return -1 for this number.
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,2,1] Output: [2,-1,2] Explanation: The first 1's
 *         next greater number is 2; The number 2 can't find next greater
 *         number. The second 1's next greater number needs to search
 *         circularly, which is also 2. Example 2:
 * 
 *         Input: nums = [1,2,3,4,3] Output: [2,3,4,-1,4]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 104 -109 <= nums[i] <= 109
 *
 */

public class _503_NextGreaterElement2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//==============================================================================
	public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        Deque<Integer> monoStack = new LinkedList<>();
        Arrays.fill(ans, -1);
        
        for (int i = 0; i < nums.length * 2; i++) {
            int index = i % nums.length;
            
            while (!monoStack.isEmpty() && nums[monoStack.peek()] < nums[index]) {
                int val = monoStack.pop();
                ans[val] = nums[index];
            }
            monoStack.push(index);
        }
        return ans;
    }
	//=============================================================================
	//Montonic stack with hashmap
	public int[] nextGreaterElements1(int[] nums) {
        
        if( nums == null || nums.length == 0 ) {
            int res[] = new int[ 0 ];
            return  res;
        }
        
        if( nums.length == 1 ) {
            int res[] = new int[ 1 ];
            res[ 0 ] = -1;
            return res;
        }
        Map< Integer, Integer > indexNextVal = new HashMap<>();
        Deque< Integer > stack = new LinkedList<>();
        int length = nums.length;
        
        for( int i = 0; i < ( length * 2 ) - 1; i++ ) {
            int index = i % length;
            
            while( !stack.isEmpty() && nums[ stack.peek() ] < nums[ index ] ) {
                indexNextVal.put( stack.pop(), nums[ index ] );
            }
            stack.push( index );
        }
        int result[] = new int[ nums.length ];
        
        for( int i = 0; i < nums.length; i++ ) {
            result[ i ] = indexNextVal.getOrDefault( i, -1 );
        }
        return result;
    }

}
