package leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         There are 8 prison cells in a row and each cell is either occupied or
 *         vacant.
 * 
 *         Each day, whether the cell is occupied or vacant changes according to
 *         the following rules:
 * 
 *         If a cell has two adjacent neighbors that are both occupied or both
 *         vacant, then the cell becomes occupied. Otherwise, it becomes vacant.
 *         Note that because the prison is a row, the first and the last cells
 *         in the row can't have two adjacent neighbors.
 * 
 *         You are given an integer array cells where cells[i] == 1 if the ith
 *         cell is occupied and cells[i] == 0 if the ith cell is vacant, and you
 *         are given an integer n.
 * 
 *         Return the state of the prison after n days (i.e., n such changes
 *         described above).
 * 
 *         Example 1:
 * 
 *         Input: cells = [0,1,0,1,1,0,0,1], n = 7 Output: [0,0,1,1,0,0,0,0]
 *         Explanation: The following table summarizes the state of the prison
 *         on each day: Day 0: [0, 1, 0, 1, 1, 0, 0, 1] Day 1: [0, 1, 1, 0, 0,
 *         0, 0, 0] Day 2: [0, 0, 0, 0, 1, 1, 1, 0] Day 3: [0, 1, 1, 0, 0, 1, 0,
 *         0] Day 4: [0, 0, 0, 0, 0, 1, 0, 0] Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 *         Day 6: [0, 0, 1, 0, 1, 1, 0, 0] Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 * 
 *         Example 2:
 * 
 *         Input: cells = [1,0,0,1,0,0,1,0], n = 1000000000 Output:
 *         [0,0,1,1,1,1,1,0]
 * 
 * 
 *         Constraints:
 * 
 *         cells.length == 8 cells[i] is either 0 or 1. 1 <= n <= 109
 *
 */

public class _957_PrisonCellsAfterNDays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] prisonAfterNDays(int[] cells, int N) {
		Map<String, Integer> map = new HashMap<>();
		String cellsStr = Arrays.toString(cells);

		for (int i = 0; i < N; i++) {

			map.put(cellsStr, i); // keep track of the state and day

			cells = nextDay(cells); // advance the state
			cellsStr = Arrays.toString(cells); // serialize it

			// if we've seen this state before, fast-forward
			if (map.containsKey(cellsStr)) {
				int daysAgo = i + 1 - map.get(cellsStr); // how many days ago was it when we saw this state?
				int daysLeft = N - (i + 1); // how many days do we have left (if we don't fast-forward)?
				return doLastNDays(cells, daysLeft % daysAgo);
			}

		}

		// if we never get a cycle, the for-loop will exit on its own after N days.
		return cells;
	}

	// do N days of advancement
	private int[] doLastNDays(int[] cells, int N) {
		for (int i = 0; i < N; i++) {
			cells = nextDay(cells);
		}

		return cells;
	}

	// advance the state by one day
	private int[] nextDay(int[] cells) {
		int[] newCells = new int[8];

		for (int i = 1; i < cells.length - 1; i++) {
			if (cells[i - 1] == cells[i + 1])
				newCells[i] = 1;
			else
				newCells[i] = 0;
		}

		return newCells;
	}
}
