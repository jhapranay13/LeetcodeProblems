package leetcode.TreeSetTreeMap;

import java.util.*;

/**
 *
 * You are given a stream of records about a particular stock. Each record contains a timestamp and the corresponding price of the stock at that timestamp.
 *
 * Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect. Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.
 *
 * Design an algorithm that:
 *
 * Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
 * Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
 * Finds the maximum price the stock has been based on the current records.
 * Finds the minimum price the stock has been based on the current records.
 * Implement the StockPrice class:
 *
 * StockPrice() Initializes the object with no price records.
 * void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
 * int current() Returns the latest price of the stock.
 * int maximum() Returns the maximum price of the stock.
 * int minimum() Returns the minimum price of the stock.
 *
 *
 * Example 1:
 *
 * Input
 * ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
 * [[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
 * Output
 * [null, null, null, 5, 10, null, 5, null, 2]
 *
 * Explanation
 * StockPrice stockPrice = new StockPrice();
 * stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
 * stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
 * stockPrice.current();     // return 5, the latest timestamp is 2 with the price being 5.
 * stockPrice.maximum();     // return 10, the maximum price is 10 at timestamp 1.
 * stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
 *                           // Timestamps are [1,2] with corresponding prices [3,5].
 * stockPrice.maximum();     // return 5, the maximum price is 5 after the correction.
 * stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
 * stockPrice.minimum();     // return 2, the minimum price is 2 at timestamp 4.
 *
 *
 * Constraints:
 *
 * 1 <= timestamp, price <= 109
 * At most 105 calls will be made in total to update, current, maximum, and minimum.
 * current, maximum, and minimum will be called only after update has been called at least once.
 *
 */

public class _2034_StockPriceFluctuation {
    class StockPrice {
        PriorityQueue<int[]> minq;
        PriorityQueue<int[]> maxq;
        Map<Integer, Integer> cache;
        int maxTimeStamp;

        public StockPrice() {
            this.minq = new PriorityQueue<>(new Comparator<int[]>() {
                public int compare(int[] x, int[] y) {
                    return x[1] - y[1];
                }
            });
            this.maxq = new PriorityQueue<>(new Comparator<int[]>() {
                public int compare(int[] x, int[] y) {
                    return y[1] - x[1];
                }
            });
            this.cache = new HashMap<>();
            this.maxTimeStamp = Integer.MIN_VALUE;
        }

        public void update(int timestamp, int price) {
            cache.put(timestamp, price);
            maxTimeStamp = Math.max(maxTimeStamp, timestamp);
            minq.offer(new int[] {timestamp, price});
            maxq.offer(new int[] {timestamp, price});
        }

        public int current() {
            return cache.get(maxTimeStamp);
        }

        public int maximum() {
            int[] entry = maxq.poll();

            while (entry[1] != cache.get(entry[0])) {
                entry = maxq.poll();
            }
            maxq.offer(entry);
            return maxq.peek()[1];
        }

        public int minimum() {
            int[] entry = minq.poll();

            while (entry[1] != cache.get(entry[0])) {
                entry = minq.poll();
            }
            minq.offer(entry);
            return minq.peek()[1];
        }
    }

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
    //TreeMap solution
    class StockPrice1 {
        TreeMap<Integer, Integer> timeToPrice;
        TreeMap<Integer, Integer> priceToCount;

        public StockPrice1() {
            this.timeToPrice = new TreeMap<>();
            this.priceToCount = new TreeMap<>();
        }

        public void update(int timestamp, int price) {

            if (timeToPrice.containsKey(timestamp)) {
                int val = timeToPrice.get(timestamp);
                int count = priceToCount.get(val);

                if (count == 1) {
                    priceToCount.remove(val);
                } else {
                    priceToCount.put(val, count - 1);
                }
            }
            timeToPrice.put(timestamp, price);
            priceToCount.put(price, priceToCount.getOrDefault(price, 0) + 1);
        }

        public int current() {
            return timeToPrice.get(timeToPrice.lastKey());
        }

        public int maximum() {
            return priceToCount.lastKey();
        }

        public int minimum() {
            return priceToCount.firstKey();
        }
    }
}
