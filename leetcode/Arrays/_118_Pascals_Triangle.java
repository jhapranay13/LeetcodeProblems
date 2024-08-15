package leetcode.Arrays;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 *
 *
 *
 * Example 1:
 *
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * Example 2:
 *
 * Input: numRows = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= numRows <= 30
 *
 */

public class _118_Pascals_Triangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new LinkedList<>();
        int count = 0;

        if (numRows >= 1) {
            List<Integer> list = new LinkedList<>();
            list.add(1);
            ans.add(list);
            count++;
        }

        if (numRows >= 2) {
            List<Integer> list = new LinkedList<>();
            list.add(1);
            list.add(1);
            ans.add(list);
            count++;
        }
        List<Integer> prev = ans.get(ans.size() - 1);

        while (count++ < numRows) {
            List<Integer> curr = new LinkedList<>();
            curr.add(1);

            for (int i = 1; i <= prev.size() - 1; i++) {
                curr.add(prev.get(i - 1) + prev.get(i));
            }
            curr.add(1);
            prev = curr;
            ans.add(curr);
        }
        return ans;
    }
}
