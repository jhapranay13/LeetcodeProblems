package leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.
 *
 * A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).
 *
 * The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * Implement the MyCalendarTwo class:
 *
 * MyCalendarTwo() Initializes the calendar object.
 * boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * Output
 * [null, true, true, true, false, true, true]
 *
 * Explanation
 * MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
 * myCalendarTwo.book(10, 20); // return True, The event can be booked.
 * myCalendarTwo.book(50, 60); // return True, The event can be booked.
 * myCalendarTwo.book(10, 40); // return True, The event can be double booked.
 * myCalendarTwo.book(5, 15);  // return False, The event cannot be booked, because it would result in a triple booking.
 * myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
 * myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 *
 *
 * Constraints:
 *
 * 0 <= start < end <= 10^9
 * At most 1000 calls will be made to book.
 *
 */

public class _731_My_Calendar_II {
    //Brute Force
    class MyCalendarTwo {
        List<int[]> dates;
        List<int[]> overlap;

        public MyCalendarTwo() {
            this.dates = new ArrayList<>();
            this.overlap = new ArrayList<>();
        }

        public boolean book(int start, int end) {

            for (int[] ol : overlap) {

                if (ol[0] < end && ol[1] > start) {
                    return false;
                }
            }

            for (int[] date : dates) {

                if (date[0] < end && date[1] > start) {
                    overlap.add(new int[] {Math.max(date[0], start), Math.min(date[1], end)});
                }
            }
            dates.add(new int[] {start, end});
            return true;
        }
    }

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
    //TreeMap solution Line Sweep Algo
    class MyCalendarTwo1 {
        TreeMap<Integer, Integer> lineSweep;

        public MyCalendarTwo1() {
            this.lineSweep = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            lineSweep.put(start, lineSweep.getOrDefault(start, 0) + 1);
            lineSweep.put(end, lineSweep.getOrDefault(end, 0) - 1);
            int numOfOverlap = 0;

            for (int key : lineSweep.keySet()) {
                numOfOverlap += lineSweep.get(key);

                if (numOfOverlap > 2) {

                    if (lineSweep.get(start) == 1) {
                        lineSweep.remove(start);
                    } else {
                        lineSweep.put(start, lineSweep.getOrDefault(start, 0) - 1);
                    }

                    if (lineSweep.get(end) == -1) {
                        lineSweep.remove(end);
                    } else {
                        lineSweep.put(end, lineSweep.getOrDefault(end, 0) + 1);
                    }
                    return false;
                }
            }
            return true;
        }
    }
}
