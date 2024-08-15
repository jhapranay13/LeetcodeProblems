package leetcode.Arrays;

/**
 *
 *
 * You are given two arrays of strings that represent two inclusive events that happened on the same day, event1 and event2, where:
 *
 * event1 = [startTime1, endTime1] and
 * event2 = [startTime2, endTime2].
 * Event times are valid 24 hours format in the form of HH:MM.
 *
 * A conflict happens when two events have some non-empty intersection (i.e., some moment is common to both events).
 *
 * Return true if there is a conflict between two events. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: event1 = ["01:15","02:00"], event2 = ["02:00","03:00"]
 * Output: true
 * Explanation: The two events intersect at time 2:00.
 * Example 2:
 *
 * Input: event1 = ["01:00","02:00"], event2 = ["01:20","03:00"]
 * Output: true
 * Explanation: The two events intersect starting from 01:20 to 02:00.
 * Example 3:
 *
 * Input: event1 = ["10:00","11:00"], event2 = ["14:00","15:00"]
 * Output: false
 * Explanation: The two events do not intersect.
 *
 *
 * Constraints:
 *
 * evnet1.length == event2.length == 2.
 * event1[i].length == event2[i].length == 5
 * startTime1 <= endTime1
 * startTime2 <= endTime2
 * All the event times follow the HH:MM format.
 *
 */

public class _2446_Determine_if_Two_Events_Have_Conflict {
    public boolean haveConflict(String[] event1, String[] event2) {
        String startMinute1 = event1[0].substring(0, 2);
        String startSecond1 = event1[0].substring(3);
        String endMinute1 = event1[1].substring(0, 2);
        String endSecond1 = event1[1].substring(3);
        String startMinute2 = event2[0].substring(0, 2);
        String startSecond2 = event2[0].substring(3);
        String endMinute2 = event2[1].substring(0, 2);
        String endSecond2 = event2[1].substring(3);
        int startEvent1 = Integer.parseInt(startMinute1 + startSecond1);
        int endEvent1 = Integer.parseInt(endMinute1 + endSecond1);
        int startEvent2 = Integer.parseInt(startMinute2 + startSecond2);
        int endEvent2 = Integer.parseInt(endMinute2 + endSecond2);

        if (startEvent1 <= startEvent2 && startEvent2 <= endEvent1) {
            return true;
        }

        if (startEvent1 >= startEvent2 && startEvent1 <= endEvent2) {
            return true;
        }
        return false;
    }
}
