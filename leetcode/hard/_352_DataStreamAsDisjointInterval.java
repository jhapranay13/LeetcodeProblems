package leetcode.hard;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * Given a data stream input of non-negative integers a1, a2, ..., an, summarize the numbers seen so far as a list of disjoint intervals.
 *
 * Implement the SummaryRanges class:
 *
 * SummaryRanges() Initializes the object with an empty stream.
 * void addNum(int val) Adds the integer val to the stream.
 * int[][] getIntervals() Returns a summary of the integers in the stream currently as a list of disjoint intervals [starti, endi].
 *
 *
 * Example 1:
 *
 * Input
 * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
 * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * Output
 * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
 *
 * Explanation
 * SummaryRanges summaryRanges = new SummaryRanges();
 * summaryRanges.addNum(1);      // arr = [1]
 * summaryRanges.getIntervals(); // return [[1, 1]]
 * summaryRanges.addNum(3);      // arr = [1, 3]
 * summaryRanges.getIntervals(); // return [[1, 1], [3, 3]]
 * summaryRanges.addNum(7);      // arr = [1, 3, 7]
 * summaryRanges.getIntervals(); // return [[1, 1], [3, 3], [7, 7]]
 * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
 * summaryRanges.getIntervals(); // return [[1, 3], [7, 7]]
 * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
 * summaryRanges.getIntervals(); // return [[1, 3], [6, 7]]
 *
 *
 * Constraints:
 *
 * 0 <= val <= 104
 * At most 3 * 104 calls will be made to addNum and getIntervals.
 *
 *
 * Follow up: What if there are lots of merges and the number of disjoint intervals is small compared to the size of the data stream?
 *
 */

public class _352_DataStreamAsDisjointInterval {

    class SummaryRanges {
        private LinkedList<int[]> list;

        public SummaryRanges() {
            this.list = new LinkedList<>();
        }

        public void addNum(int val) {

            if (list.isEmpty()) {
                list.add(new int[] {val, val});
            } else {
                int index = binarySearchJustGreaterThan(val);
                add(val, index);
            }
        }

        public int[][] getIntervals() {
            int[][] ans = new int[list.size()][2];

            for (int i = 0; i < list.size(); i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }

        private void add(int val, int index) {

            if (index == -1) {
                int[] prevInterval = list.get(list.size() - 1);
                int prevVal = prevInterval[1];

                if (prevVal + 1 == val) {
                    prevInterval[1] = val;
                } else if (prevVal + 1 < val) {
                    list.add(new int[] {val, val});
                } else {
                    return;
                }

            } else {
                int[] prevInterval = list.get(index);
                int[] preInterval = index == 0 ?
                        new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE} : list.get(index - 1);

                if (prevInterval[0] == val + 1 && preInterval[1] + 1 == val) {
                    list.remove(index - 1);
                    list.remove(index - 1);
                    list.add(index - 1, new int[] {preInterval[0], prevInterval[1]});
                } else if (preInterval[1] + 1 == val){
                    preInterval[1] = val;
                } else if (prevInterval[0] == val + 1) {
                    prevInterval[0] = val;
                } else if (prevInterval[0] == val || preInterval[1] >= val) {
                    return;
                } else {
                    list.add(index, new int[] {val, val});
                }
            }
        }

        private int binarySearchJustGreaterThan(int val) {
            int lo = 0;
            int hi = list.size() - 1;
            int ans = -1;

            while (lo <= hi) {
                int pivot = lo + (hi - lo) / 2;
                int[] pval = list.get(pivot);

                if (val <= pval[0]) {
                    ans = pivot;
                    hi = pivot - 1;
                }  else {
                    lo = pivot + 1;
                }
            }
            return ans;
        }
    }

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
    //TreeMap Solution
    class SummaryRanges1 {
        TreeMap<Integer, Integer> range;

        public SummaryRanges1() {
            this.range = new TreeMap<>();
        }

        public void addNum(int value) {
            Map.Entry<Integer, Integer> ceilingEntry = range.ceilingEntry(value);
            Map.Entry<Integer, Integer> floorEntry = range.floorEntry(value);
            int start = value;
            int end = value;

            if (floorEntry != null) {

                if ((floorEntry.getKey() <= value && floorEntry.getValue() >= value) ||
                        floorEntry.getKey() + 1 == value || floorEntry.getValue() + 1 == value) {
                    start = Math.min(start, floorEntry.getKey());
                    end = Math.max(end, floorEntry.getValue());
                    range.remove(floorEntry.getKey());
                }
            }

            if (ceilingEntry != null) {

                if (ceilingEntry.getKey() == value || value + 1 == ceilingEntry.getKey()) {
                    start = Math.min(start, ceilingEntry.getKey());
                    end = Math.max(end, ceilingEntry.getValue());
                    range.remove(ceilingEntry.getKey());
                }
            }
            range.put(start, end);
        }

        public int[][] getIntervals() {
            int[][] ans = new int[range.size()][2];
            int index = 0;

            for (int key : range.keySet()) {
                ans[index][0] = key;
                ans[index++][1] = range.get(key);
            }
            return ans;
        }
    }
}
