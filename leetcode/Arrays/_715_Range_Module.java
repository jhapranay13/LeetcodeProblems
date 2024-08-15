package leetcode.Arrays;

import java.util.*;

/**
 *
 * A Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented as half-open intervals and query about them.
 *
 * A half-open interval [left, right) denotes all the real numbers x where left <= x < right.
 *
 * Implement the RangeModule class:
 *
 * RangeModule() Initializes the object of the data structure.
 * void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
 * boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently being tracked, and false otherwise.
 * void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).
 *
 *
 * Example 1:
 *
 * Input
 * ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
 * [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
 * Output
 * [null, null, null, true, false, true]
 *
 * Explanation
 * RangeModule rangeModule = new RangeModule();
 * rangeModule.addRange(10, 20);
 * rangeModule.removeRange(14, 16);
 * rangeModule.queryRange(10, 14); // return True,(Every number in [10, 14) is being tracked)
 * rangeModule.queryRange(13, 15); // return False,(Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
 * rangeModule.queryRange(16, 17); // return True, (The number 16 in [16, 17) is still being tracked, despite the remove operation)
 *
 *
 * Constraints:
 *
 * 1 <= left < right <= 10^9
 * At most 104 calls will be made to addRange, queryRange, and removeRange.
 *
 */

public class _715_Range_Module {
    class RangeModule {

        private static class Interval implements Comparable<Interval> {
            public int start , end;
            public Interval(int start , int end) {
                this.start = start;
                this.end = end;
            }
            @Override
            public int compareTo(Interval interval) {
                if (this.start < interval.start) {
                    return - 1;
                } else if (this.start > interval.start) {
                    return 1;
                } else {
                    return 0;
                }
            }
            public String toString() {
                return String.format("(%d,%d)" , start , end);
            }
        }

        private List<Interval> list = new ArrayList<>();

        public RangeModule() {
        }

        public void addRange(int left, int right) {

            list.add(new Interval(left , right));
            merge();

        }

        public boolean queryRange(int left, int right) {

            if (list.isEmpty()) {
                return false;
            } else {
                int low = 0 , high = list.size();
                while (low < high) {
                    int mid = (low + high) / 2;
                    if (list.get(mid).start > left) {
                        high = mid;
                    } else {
                        low = mid + 1;
                    }
                }
                int index = high - 1;
                if (index >= 0) {
                    if (list.get(index).start <= left && list.get(index).end >= right) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }

        }

        public void removeRange(int left, int right) {

            List<Interval> temp = new ArrayList<>();
            for (Interval interval : list) {
                if (interval.start >= left && interval.end <= right) {
                    continue;
                } else if (interval.start <= left && interval.end >= right) {
                    if (left > interval.start) {
                        temp.add(new Interval(interval.start , left));
                    }
                    if (right < interval.end) {
                        temp.add(new Interval(right , interval.end));
                    }
                } else {
                    if (left >= interval.start && left <= interval.end) {
                        temp.add(new Interval(interval.start , left));
                    } else if (right >= interval.start && right <= interval.end) {
                        temp.add(new Interval(right , interval.end));
                    } else {
                        temp.add(interval);
                    }
                }
            }
            list = temp;

        }

        private void merge() {

            Collections.sort(list);
            int i , n = list.size();
            List<Interval> temp = new ArrayList<>();
            for (i = 0;i < n;i ++) {
                int start = list.get(i).start , end = list.get(i).end;
                while (i < n && list.get(i).start <= end) {
                    if (list.get(i).end > end) {
                        end = list.get(i).end;
                    }
                    i ++;
                }
                i --;
                temp.add(new Interval(start , end));
            }
            list = temp;

        }
    }

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
    //TreeMap Solution
    class RangeModule1 {
        TreeMap<Integer, Integer> range;

        public RangeModule1() {
            this.range = new TreeMap<>();
        }

        public void addRange(int left, int right) {

            if (left >= right) {
                return;
            }
            Map.Entry<Integer, Integer> floorLeft = range.floorEntry(left);

            if (floorLeft != null && floorLeft.getValue() >= left) {
                range.remove(floorLeft.getKey());
                left = Math.min(floorLeft.getKey(), left);
                right = Math.max(right, floorLeft.getValue());
            }

            while (range.ceilingKey(left) != null && range.ceilingKey(left) <= right) {
                Map.Entry<Integer, Integer> ceilingLeft = range.ceilingEntry(left);
                range.remove(ceilingLeft.getKey());
                right = Math.max(right, ceilingLeft.getValue());

            }
            range.put(left, right);
        }

        public boolean queryRange(int left, int right) {

            Map.Entry<Integer, Integer> floorLeft = range.floorEntry(left);

            if (floorLeft != null) {

                if (right <= floorLeft.getValue()) {
                    return true;
                }
            }
            return false;
        }

        public void removeRange(int left, int right) {
            addRange(left, right); // Merging all the range so that removal becomes easier
            Map.Entry<Integer, Integer> floorLeft = range.floorEntry(left);

            if (floorLeft != null) {
                range.remove(floorLeft.getKey());
                addRange(Math.min(left, floorLeft.getKey()), left);
                addRange(right, Math.max(floorLeft.getValue(), right));
            }
        }
    }
}
