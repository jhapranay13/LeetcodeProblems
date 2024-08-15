package leetcode.BinarySearch;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *You have an inventory of different colored balls, and there is a customer that wants orders balls of any color.

The customer weirdly values the colored balls. Each colored ball's value is the number of balls of that color you currently have in your inventory. For example, if you own 6 yellow balls, the customer would pay 6 for the first yellow ball. After the transaction, there are only 5 yellow balls left, so the next yellow ball is then valued at 5 (i.e., the value of the balls decreases as you sell more to the customer).

You are given an integer array, inventory, where inventory[i] represents the number of balls of the ith color that you initially own. You are also given an integer orders, which represents the total number of balls that the customer wants. You can sell the balls in any order.

Return the maximum total value that you can attain after selling orders colored balls. As the answer may be too large, return it modulo 109 + 7.



Example 1:

Input: inventory = [2,5], orders = 4
Output: 14
Explanation: Sell the 1st color 1 time (2) and the 2nd color 3 times (5 + 4 + 3).
The maximum total value is 2 + 5 + 4 + 3 = 14.

Example 2:

Input: inventory = [3,5], orders = 6
Output: 19
Explanation: Sell the 1st color 2 times (3 + 2) and the 2nd color 4 times (5 + 4 + 3 + 2).
The maximum total value is 3 + 2 + 5 + 4 + 3 + 2 = 19.

Example 3:

Input: inventory = [2,8,4,10,6], orders = 20
Output: 110

Example 4:

Input: inventory = [1000000000], orders = 1000000000
Output: 21
Explanation: Sell the 1st color 1000000000 times for a total value of 500000000500000000. 500000000500000000 modulo 109 + 7 = 21.

Constraints:

1 <= inventory.length <= 105
1 <= inventory[i] <= 109
1 <= orders <= min(sum(inventory[i]), 109)
 *
 */

public class _1648_SellDiminishingValuedColoredBalls {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_1648_SellDiminishingValuedColoredBalls obj = new _1648_SellDiminishingValuedColoredBalls();
		//System.out.println(obj.maxProfit(new int[] {2,8,4,10,6}, 20));

		//System.out.println(obj.maxProfit(new int[] {2,5}, 4));
		System.out.println(obj.maxProfit(
				new int[] {565259708,715164401,716563713,958255469,844600740,823949511,180479359,287829385,164248818,73361150,230686692,322986846,598720034,338241127,748922260,181241085,833659853,509571179,250093451,690995620,703292727,595636202}, 
				650114768));
		//System.out.println(obj.maxProfit(new int[] {1000000000}, 1000000000));
	}

	public int maxProfit(int[] inventory, int orders) {
		Arrays.sort(inventory);
		long profit = 0;
		int lo = 0;
		int hi = inventory[inventory.length - 1];

		//Finding out base level of All cells of array to fuilfill order using BinarySearch
		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;
			long numberOfBalls = 0;

			for (int i = 0; i < inventory.length; i++) {

				if (inventory[i] >= pivot) {
					numberOfBalls += inventory[i] - pivot;
				}
			}

			if (numberOfBalls < orders) {
				hi = pivot - 1;
			} else if (numberOfBalls > orders) {
				lo = pivot + 1;
			} else {
				lo = pivot;
				break;
			}
		}
		int baseLevel = lo;

		for (int i = inventory.length - 1; i >= 0; i--) {

			if (inventory[i] < baseLevel) {
				break;
			}
			orders -= inventory[i] - baseLevel;
			profit += (sumOfNNaturalNumbers(inventory[i]) -
					sumOfNNaturalNumbers(baseLevel)) % overflowCheck;
			profit %= overflowCheck;
		}

		if (orders > 0) {
			profit += (long)orders * baseLevel;
			profit %= overflowCheck;
		}
		return (int)profit;
	}

	//Arithmetic Progression to get the sum of n to 1 in constant time
	private long sumOfNNaturalNumbers(int num) {
		return (long)num * (num + 1) / 2;
	}

	private long overflowCheck = 1000000007;
	//============================================================================================================
	//Greedy Approach
	public int maxProfit1(int[] inventory, int orders) {
		Arrays.sort(inventory);
		long profit = 0;
		int range = 0;
		int index = inventory.length - 1;

		while (orders > 0) {

			while (index > 0 && inventory[index] == inventory[index - 1]) {
				index--;
			}
			int outSideRange = index > 0 ? inventory[index - 1] : 0;
			range = inventory.length - index;
			int availableBalls = range * (inventory[index] - outSideRange);

			if (availableBalls <= orders) {
				orders -= availableBalls;
				profit += range * (sum(inventory[index]) - sum(outSideRange));
			} else {
				long ballsTakenRow = orders / range;
				long ballsTakenColumn = orders % range;
				long lowRow = inventory[index] - ballsTakenRow;
				long sumOfDiff = sum(inventory[index]) - sum(lowRow);
				profit += range * sumOfDiff;
				long lastRow = inventory[index] - ballsTakenRow;
				profit += (ballsTakenColumn * lastRow);
				orders = 0;
			}
			index--;
			profit %= overflowCheck;
		}
		return (int)profit;
	}

	private long sum(long num) {
		return (long) num *(num + 1) / 2;
	}
}
