package leetcode.StackAndQueues.Monotonic;

/**
 * 
 * @author Pranay Jha
 *
 *         Given n non-negative integers representing an elevation map where the
 *         width of each bar is 1, compute how much water it can trap after
 *         raining.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         | | | __ | __ ___________| | __ ____ __ | __ ___| | __ __ | || | __ |
 *         | __ |__|__|___|__||__|___|__||__||__||__||__||__|__
 * 
 * 
 *         Input: height = [0,1,0,2,1,0,1,3,2,1,2,1] Output: 6 Explanation: The
 *         above elevation map (black section) is represented by array
 *         [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue
 *         section) are being trapped.
 * 
 *         Example 2:
 * 
 *         Input: height = [4,2,0,3,2,5] Output: 9
 * 
 * 
 *         Constraints:
 * 
 *         n == height.length 1 <= n <= 2 * 104 0 <= height[i] <= 105
 *
 */

public class _42_TrappingRainWater {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ======================================================================
	// Two pointer approach
	public int trap(int[] height) {

		if (height == null || height.length < 2) {
			return 0;
		}
		int waterCount = 0;
		int start = 0;
		int end = height.length - 1;

		while (height[start] == 0 || start + 1 < end && height[start + 1] > height[start]) {
			start++;
		}

		while (height[end] == 0 || (end - 1 > start && height[end - 1] > height[end])) {
			end--;
		}
		boolean leftMoved = false;
		boolean rightMoved = false;
		int minimumPillor = Math.min(height[start], height[end]);

		while (start <= end) {

			if (start == end) {

				if ((leftMoved && !rightMoved) || (!leftMoved && rightMoved) || height[start] == 0) {
					break;
				}
			}

			if (!leftMoved && !rightMoved) {
				int range = end - start - 1;
				waterCount += range * minimumPillor;
			}

			if (leftMoved) {

				if (height[start] <= minimumPillor) {
					waterCount -= height[start];
				} else {
					waterCount -= minimumPillor;
				}
			}

			if (start == end) {
				break;
			}

			if (rightMoved) {

				if (height[end] <= minimumPillor) {
					waterCount -= height[end];
				} else {
					waterCount -= minimumPillor;
				}
			}

			if (height[start] > minimumPillor && height[end] > minimumPillor) {
				int range = end - start - 1;
				waterCount -= range * minimumPillor;
				minimumPillor = Math.min(height[start], height[end]);
				waterCount += range * minimumPillor;
			}

			if (height[start] < height[end]) {
				start++;
				leftMoved = true;
				rightMoved = false;
			} else if (height[start] > height[end]) {
				end--;
				leftMoved = false;
				rightMoved = true;
			} else {
				start++;
				end--;
				leftMoved = true;
				rightMoved = true;
			}
		}
		return waterCount < 0 ? 0 : waterCount;
	}
	//=====================================================================
	//Left to Right and Right To left approach
	public int trap1(int[] height) {

		if( height == null || height.length <= 1 ) {
			return 0;
		}
		int[] lr = new int[ height.length ];
		int[] rl = new int[ height.length ];
		int max = 0;

		for( int i = 0; i < lr.length; i++ ) {

			if( i == 0 ) {
				lr[ i ] = height[ i ];
			} else {

				if( height[ max ] < height[ i ] ) {
					max = i;
				}
				lr[ i ] = height[ max ];
			}
		}
		max = rl.length -1;

		for( int i = rl.length -1; i >= 0; i-- ) {

			if( i == rl.length - 1 ) {
				lr[ i ] = height[ i ];
			} else {

				if( height[ max ] < height[ i ] ) {
					max = i;
				}
				rl[ i ] = height[ max ];
			}
		}
		int ans = 0;

		for( int i = 1; i < height.length - 1; i++ ) {
			int val = Math.min( lr[ i ], rl[ i ] );
			ans += val - height[ i ];
		}
		return ans;
	}
    //=============================================================================================
    // Constant memory
    public int trap2(int[] height) {

        if (height == null || height.length == 0) return 0;

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }
}
