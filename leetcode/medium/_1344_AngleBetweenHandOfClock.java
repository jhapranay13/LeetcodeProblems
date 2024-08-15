package leetcode.medium;

/**
 *
 * Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.
 *
 * Answers within 10-5 of the actual value will be accepted as correct.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: hour = 12, minutes = 30
 * Output: 165
 * Example 2:
 *
 *
 * Input: hour = 3, minutes = 30
 * Output: 75
 * Example 3:
 *
 *
 * Input: hour = 3, minutes = 15
 * Output: 7.5
 *
 *
 * Constraints:
 *
 * 1 <= hour <= 12
 * 0 <= minutes <= 59
 *
 */

public class _1344_AngleBetweenHandOfClock {
    public double angleClock(int hour, int minutes) {
        int oneMinAngle = 360 / 60;
        int oneHourAngle = 360 / 12;
        // when minute travels 360 degree hour moves 30 degree form clock observation so in i minute hour hand will move
        //30 / 360
        double angleHourMovesPerMinute = (double)30 / 360;
        //if we consider 12 as zero angle
        double totalHour = (hour % 12) + ((double)minutes / 60);
        double hourAngle = totalHour * oneHourAngle;
        //if we conside 12 as zero angle
        double minutesAngle = minutes * oneMinAngle;
        double diff = Math.abs(hourAngle - minutesAngle);
        return Math.min(diff, 360 - diff);
    }
}
