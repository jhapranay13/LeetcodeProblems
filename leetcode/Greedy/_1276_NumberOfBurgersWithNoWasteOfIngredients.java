package leetcode.Greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two integers tomatoSlices and cheeseSlices. The ingredients of
 *         different burgers are as follows:
 * 
 *         Jumbo Burger: 4 tomato slices and 1 cheese slice. Small Burger: 2
 *         Tomato slices and 1 cheese slice. Return [total_jumbo, total_small]
 *         so that the number of remaining tomatoSlices equal to 0 and the
 *         number of remaining cheeseSlices equal to 0. If it is not possible to
 *         make the remaining tomatoSlices and cheeseSlices equal to 0 return
 *         [].
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: tomatoSlices = 16, cheeseSlices = 7 Output: [1,6] Explantion:
 *         To make one jumbo burger and 6 small burgers we need 4*1 + 2*6 = 16
 *         tomato and 1 + 6 = 7 cheese. There will be no remaining ingredients.
 * 
 *         Example 2:
 * 
 *         Input: tomatoSlices = 17, cheeseSlices = 4 Output: [] Explantion:
 *         There will be no way to use all ingredients to make small and jumbo
 *         burgers.
 * 
 *         Example 3:
 * 
 *         Input: tomatoSlices = 4, cheeseSlices = 17 Output: [] Explantion:
 *         Making 1 jumbo burger there will be 16 cheese remaining and making 2
 *         small burgers there will be 15 cheese remaining.
 * 
 *         Example 4:
 * 
 *         Input: tomatoSlices = 0, cheeseSlices = 0 Output: [0,0]
 * 
 *         Example 5:
 * 
 *         Input: tomatoSlices = 2, cheeseSlices = 1 Output: [0,1]
 * 
 * 
 *         Constraints:
 * 
 *         0 <= tomatoSlices <= 10^7 0 <= cheeseSlices <= 10^7
 *
 */

public class _1276_NumberOfBurgersWithNoWasteOfIngredients {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 4x+2y=t
	// x+y=c
	// y = (4c-t)/2
	// x = (t-2c)/2;
	public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
		List<Integer> list = new ArrayList<>();
		double x = (tomatoSlices - 2 * cheeseSlices) / 2.0;
		double y = (4 * cheeseSlices - tomatoSlices) / 2.0;

		if (x < 0 || y < 0 || (int) x != x || (int) y != y) {
			return list;
		}
		list.add((int) x);
		list.add((int) y);

		return list;
	}
}
