package leetcode.easy;

/**
 *
 * Write a program to count the number of days between two dates.
 *
 * The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.
 *
 *
 *
 * Example 1:
 *
 * Input: date1 = "2019-06-29", date2 = "2019-06-30"
 * Output: 1
 * Example 2:
 *
 * Input: date1 = "2020-01-15", date2 = "2019-12-31"
 * Output: 15
 *
 *
 * Constraints:
 *
 * The given dates are valid dates between the years 1971 and 2100.
 *
 */

public class _1360_Number_of_Days_Between_Two_Dates {
    int[] daysPerMonth = new int[] {31,28,31,30,31,30,31,31,30,31,30,31};
    public int daysBetweenDates(String date1, String date2) {

        return Math.abs(daysFrom1971(date1)- daysFrom1971(date2));
    }
    public int daysFrom1971(String date) {

        String[] dateArray = date.split("-");
        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int day = Integer.parseInt(dateArray[2]);

        int yearsFrom1971 = year - 1971;

        boolean isLeap = (year%4==0 &&(year % 100 != 0 || year %400 == 0));

        if(month>2 && isLeap) day++;

        for(int m = 1; m < month;m++)
            day+=daysPerMonth[m-1];

        return day + yearsFrom1971*365 + countLeapYears(year);
    }
    public int countLeapYears(int year) {
        int count = 0;
        for(int y = 1971; y < year; y++) {
            if(y%4==0 &&(y % 100 != 0 || y %400 == 0))
                count++;
        }
        return count;
    }
}
