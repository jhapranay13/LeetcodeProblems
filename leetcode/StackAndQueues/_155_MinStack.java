package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2

Constraints:

-231 <= val <= 231 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 104 calls will be made to push, pop, top, and getMin.
 *
 */

public class _155_MinStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class MinStack {
		Deque<Integer> minStack;
		Deque<Integer> valStack;

		/** initialize your data structure here. */
		public MinStack() {
			this.minStack = new LinkedList<>();
			this.valStack = new LinkedList<>();
		}

		public void push(int val) {

			if (!valStack.isEmpty()) {

				if (val <= minStack.peek()) {
					minStack.push(val);
				} else {
					minStack.push(minStack.peek());
				}
				valStack.push(val);
			} else {
				minStack.push(val);
				valStack.push(val);
			}
		}

		public void pop() {
			valStack.pop();
			minStack.pop();
		}

		public int top() {
			return valStack.peek();
		}

		public int getMin() {
			return minStack.peek();
		}
	}

	/**
	 * Your MinStack object will be instantiated and called as such:
	 * MinStack obj = new MinStack();
	 * obj.push(val);
	 * obj.pop();
	 * int param_3 = obj.top();
	 * int param_4 = obj.getMin();
	 */
	//===============================================================================
	class MinStack1 {

	    private Deque< Integer > mainStack;
		private Deque< int[] > auxStack;
	    
	    /** initialize your data structure here. */
	    public MinStack1() {
	        mainStack = new LinkedList<Integer>();
		    auxStack = new LinkedList<int[]>();
	    }
	    
	    public void push(int value) {
	        int[] tempVal = null;
			
			if( !auxStack.isEmpty() ) {
				tempVal = auxStack.peek();
				
				if( tempVal[ 0 ] == value ) {
					tempVal[ 1 ]++;
				} else if( tempVal[ 0 ] > value ){
					tempVal = new int[ 2 ];
					tempVal[ 0 ] = value;
					tempVal[ 1 ] = 1;
					auxStack.push(tempVal);
				}
			} else {
				tempVal = new int[ 2 ];
				tempVal[ 0 ] = value;
				tempVal[ 1 ] = 1;
				auxStack.push( tempVal );
			}
			mainStack.push(value);
	    }
	    
	    public void pop() {
	        if( mainStack.isEmpty() ) {
				throw new IllegalStateException( "Stack Empty" );
			}
			int[] tempVal = auxStack.peek();
			
			if( tempVal[ 0 ] == mainStack.peek() ) {
				tempVal[ 1 ]--;
			}
			
			if( tempVal[ 1 ] == 0 ) {
				auxStack.pop();
			}
		    mainStack.pop();
	    }
	    
	    public int top() {
	        return mainStack.peek();
	    }
	    
	    public int getMin() {
	        return auxStack.peek()[ 0 ];
	    }
	}
}
