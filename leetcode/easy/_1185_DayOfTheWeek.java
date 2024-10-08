package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a date, return the corresponding day of the week for that date.
 * 
 *         The input is given as three integers representing the day, month and
 *         year respectively.
 * 
 *         Return the answer as one of the following values {"Sunday", "Monday",
 *         "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: day = 31, month = 8, year = 2019 Output: "Saturday"
 * 
 *         Example 2:
 * 
 *         Input: day = 18, month = 7, year = 1999 Output: "Sunday"
 * 
 *         Example 3:
 * 
 *         Input: day = 15, month = 8, year = 1993 Output: "Sunday"
 * 
 * 
 *         Constraints:
 * 
 *         The given dates are valid dates between the years 1971 and 2100.
 *
 */

public class _1185_DayOfTheWeek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String dayOfTheWeek(int day, int mon, int year) {
		int total = 0;
		int[] month = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		String[] days = { "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday" };
		for (int i = 1971; i < year; i++) {
			total += isLeapYear(i) ? 366 : 365;
		}
		for (int i = 0; i < mon - 1; i++) {
			total += month[i];
		}
		total = total + day - 1;
		if (isLeapYear(year) && mon > 2)
			total++;
		int ans = total % 7;
		return days[ans];

	}

	private static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}
}
