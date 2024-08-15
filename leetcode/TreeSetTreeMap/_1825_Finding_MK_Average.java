package leetcode.TreeSetTreeMap;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 *
 * You are given two integers, m and k, and a stream of integers. You are tasked to implement a data structure that calculates the MKAverage for the stream.
 *
 * The MKAverage can be calculated using these steps:
 *
 * If the number of the elements in the stream is less than m you should consider the MKAverage to be -1. Otherwise, copy the last m elements of the stream to a separate container.
 * Remove the smallest k elements and the largest k elements from the container.
 * Calculate the average value for the rest of the elements rounded down to the nearest integer.
 * Implement the MKAverage class:
 *
 * MKAverage(int m, int k) Initializes the MKAverage object with an empty stream and the two integers m and k.
 * void addElement(int num) Inserts a new element num into the stream.
 * int calculateMKAverage() Calculates and returns the MKAverage for the current stream rounded down to the nearest integer.
 *
 *
 * Example 1:
 *
 * Input
 * ["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
 * [[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
 * Output
 * [null, null, null, -1, null, 3, null, null, null, 5]
 *
 * Explanation
 * MKAverage obj = new MKAverage(3, 1);
 * obj.addElement(3);        // current elements are [3]
 * obj.addElement(1);        // current elements are [3,1]
 * obj.calculateMKAverage(); // return -1, because m = 3 and only 2 elements exist.
 * obj.addElement(10);       // current elements are [3,1,10]
 * obj.calculateMKAverage(); // The last 3 elements are [3,1,10].
 *                           // After removing smallest and largest 1 element the container will be [3].
 *                           // The average of [3] equals 3/1 = 3, return 3
 * obj.addElement(5);        // current elements are [3,1,10,5]
 * obj.addElement(5);        // current elements are [3,1,10,5,5]
 * obj.addElement(5);        // current elements are [3,1,10,5,5,5]
 * obj.calculateMKAverage(); // The last 3 elements are [5,5,5].
 *                           // After removing smallest and largest 1 element the container will be [5].
 *                           // The average of [5] equals 5/1 = 5, return 5
 *
 *
 * Constraints:
 *
 * 3 <= m <= 10^5
 * 1 <= k*2 < m
 * 1 <= num <= 10^5
 * At most 10^5 calls will be made to addElement and calculateMKAverage.
 *
 */

public class _1825_Finding_MK_Average {
    class MKAverage {
        TreeMap<Integer, Integer> middle;
        TreeMap<Integer, Integer> greaterThan;
        TreeMap<Integer, Integer> lessThan;
        int greaterThanSize;
        int lessThanSize;
        int middleSum;
        Deque<Integer> q;
        int m;
        int k;

        public MKAverage(int m, int k) {
            this.middle = new TreeMap<>();
            this.greaterThan = new TreeMap<>();
            this.lessThan = new TreeMap<>();
            this.greaterThanSize = 0;
            this.lessThanSize = 0;
            this.q = new LinkedList<>();
            this.m = m;
            this.k = k;
            this.middleSum = 0;
        }

        public void addElement(int num) {

            if (q.size() == m) {
                int item = q.pollFirst();

                if (greaterThan.containsKey(item)) {
                    remove(greaterThan, item);
                    greaterThanSize--;
                } else if (lessThan.containsKey(item)) {
                    remove(lessThan, item);
                    lessThanSize--;
                } else {
                    remove(middle, item);
                    middleSum -= item;
                }
            }
            q.offerLast(num);
            middleSum += num;
            add(middle, num);

            while (greaterThanSize < k && !middle.isEmpty()) {
                greaterThanSize++;
                middleSum -= middle.lastKey();
                add(greaterThan, remove(middle, middle.lastKey()));
            }
            //balancing greater and middle
            while (!greaterThan.isEmpty() && !middle.isEmpty() && greaterThan.firstKey() < middle.lastKey()) {
                middleSum += greaterThan.firstKey();
                add(middle, remove(greaterThan, greaterThan.firstKey()));
                middleSum -= middle.lastKey();
                add(greaterThan, remove(middle, middle.lastKey()));
            }

            while (lessThanSize < k && !middle.isEmpty()) {
                middleSum -= middle.firstKey();
                lessThanSize++;
                add(lessThan, remove(middle, middle.firstKey()));
            }
            //balancing lesser and middle
            while (!lessThan.isEmpty() && !middle.isEmpty() && lessThan.lastKey() > middle.firstKey()) {
                middleSum += lessThan.lastKey();
                add(middle, remove(lessThan, lessThan.lastKey()));
                middleSum -= middle.firstKey();
                add(lessThan, remove(middle, middle.firstKey()));
            }
        }

        public int calculateMKAverage() {
            return q.size() == m ? middleSum / (m - k * 2) : -1;
        }

        private int remove(TreeMap<Integer, Integer> treeMap, int nums) {
            int freq = treeMap.get(nums);

            if (freq == 1) {
                treeMap.remove(nums);
            } else {
                treeMap.put(nums, freq - 1);
            }
            return nums;
        }

        private void add(TreeMap<Integer, Integer> treeMap, int nums) {
            treeMap.put(nums, treeMap.getOrDefault(nums, 0) + 1);
        }
    }

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */
}
