package leetcode.TreeSetTreeMap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * Design a data structure to find the frequency of a given value in a given subarray.
 *
 * The frequency of a value in a subarray is the number of occurrences of that value in the subarray.
 *
 * Implement the RangeFreqQuery class:
 *
 * RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
 * int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].
 * A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that contains the elements of nums between indices left and right (inclusive).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["RangeFreqQuery", "query", "query"]
 * [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
 * Output
 * [null, 1, 2]
 *
 * Explanation
 * RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
 * rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
 * rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in the whole array.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i], value <= 10^4
 * 0 <= left <= right < arr.length
 * At most 105 calls will be made to query
 *
 */

public class _2080_Range_Frequency_Queries {
    class RangeFreqQuery {
        Map<Integer, TreeMap<Integer, Integer>> valueRangeCount;

        public RangeFreqQuery(int[] arr) {
            this.valueRangeCount = new HashMap<>();
            // like calculating presum for position
            for (int i = 0; i < arr.length; i++) {
                TreeMap<Integer, Integer> posCount = valueRangeCount.getOrDefault(arr[i], new TreeMap<>());
                posCount.put(i, posCount.size() + 1);
                valueRangeCount.put(arr[i], posCount);
            }
        }

        public int query(int left, int right, int value) {
            int ans = 0;

            if (!valueRangeCount.containsKey(value)) {
                return ans;
            }
            TreeMap<Integer, Integer> posCount = valueRangeCount.get(value);
            Map.Entry<Integer, Integer> leftEntry = posCount.ceilingEntry(left);
            Map.Entry<Integer, Integer> rightEntry = posCount.floorEntry(right);

            if (leftEntry == null || rightEntry == null) {
                return ans;
            }
            return rightEntry.getValue() - leftEntry.getValue() + 1;
        }
    }

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
}
