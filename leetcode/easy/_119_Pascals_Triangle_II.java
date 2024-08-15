package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 *
 *
 *
 * Example 1:
 *
 *                      1
 *                     1 1
 *                    1 2 1
 *                   1 3 3 1
 *
 * Input: rowIndex = 3
 * Output: [1,3,3,1]
 * Example 2:
 *
 * Input: rowIndex = 0
 * Output: [1]
 * Example 3:
 *
 * Input: rowIndex = 1
 * Output: [1,1]
 *
 *
 * Constraints:
 *
 * 0 <= rowIndex <= 33
 *
 *
 * Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
 *
 */

public class _119_Pascals_Triangle_II {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return new ArrayList<>();
        }
        List<Integer> holder = new ArrayList<>();
        holder.add(1);
        List<Integer> curr = new ArrayList<>();

        for (int i = 0; i < rowIndex; i++) {
            curr.add(1);

            for (int j = 1; j < holder.size(); j++) {
                curr.add(holder.get(j) + holder.get(j - 1));
            }
            curr.add(1);
            holder = curr;
            curr = new ArrayList<>();
        }
        return holder;
    }
    //=============================================================================================
    // Recursive Approach
    public List<Integer> getRow1(int rowIndex) {
        if (rowIndex < 0) {
            return new ArrayList<>();
        }
        return get(rowIndex);
    }

    private List<Integer> get(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        if(n == 0) {
            return list;
        }
        List<Integer> prev = get(n-1);

        for(int i = 1; i < prev.size(); i++) {
            list.add(prev.get(i - 1) + prev.get(i));
        }
        list.add(1);
        return list;
    }
}
