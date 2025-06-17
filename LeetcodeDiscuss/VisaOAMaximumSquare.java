package LeetcodeDiscuss;


import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * You are tasked with analyzing the potential space in a cityscape outlined by a series of skyscrapers. Each skyscraper's height is represented by an element in the array cityline, where the width of each skyscraper is consistently 1, and they are placed directly adjacent to each other along a road with no gaps. Your mission is to determine the largest square area that can fit within this row of skyscrapers.
 *
 * Example
 *
 * For cityline = [1, 2, 3, 2, 1], the output should be solution(cityline) = 4.
 *
 * In this configuration, there are several 2x2 squares that can be accommodated within the skyscrapers, but no larger square can fit owing to the limitations of their heights.
 *
 *
 *        |
 *      3 |        ____
 *        |       |   |
 *      2 |    ___|   |___
 *        |   |   |   |  |
 *      1 |___|   |   |  |___
 *        |   |   |   |  |  |
 *        |___|___|___|__|__|_________________
 *
 */
public class VisaOAMaximumSquare {

    public static void main(String[] args) {

        System.out.println("--- Monotonic Stack Solution ---");
        System.out.println("cityline = [1, 2, 3, 2, 1], Output: " + solution(new int[]{1, 2, 3, 2, 1})); // Expected: 4
        System.out.println("cityline = [5, 5, 5, 5, 5], Output: " + solution(new int[]{5, 5, 5, 5, 5})); // Expected: 25
        System.out.println("cityline = [1, 1, 1, 1, 1], Output: " + solution(new int[]{1, 1, 1, 1, 1})); // Expected: 1
        System.out.println("cityline = [10], Output: " + solution(new int[]{10}));                       // Expected: 1
        System.out.println("cityline = [2, 3, 4, 5], Output: " + solution(new int[]{2, 3, 4, 5}));       // Expected: 4
        System.out.println("cityline = [5, 4, 3, 2], Output: " + solution(new int[]{5, 4, 3, 2}));       // Expected: 4
        System.out.println("cityline = [3, 2, 1], Output: " + solution(new int[]{3, 2, 1}));             // Expected: 1
        System.out.println("cityline = [], Output: " + solution(new int[]{}));                           // Expected: 0
        System.out.println("cityline = [4, 2, 4, 2, 4], Output: " + solution(new int[]{4, 2, 4, 2, 4})); // Expected: 4
        System.out.println("cityline = [6, 2, 5, 4, 5, 1, 6], Output: " + solution(new int[]{6, 2, 5, 4, 5, 1, 6})); // Expected: 9
        System.out.println("cityline = [7, 7, 7, 7, 7, 7, 7], Output: " + solution(new int[]{7, 7, 7, 7, 7, 7, 7})); // Expected: 49
        System.out.println("cityline = [1,5,3,4,2], Output: " + solution(new int[]{1,5,3,4,2})); // Expected: 4 (from 2,3,4, or from 3,4,2 -- 2x2. Max side is 2)
        System.out.println("cityline = [1,2,3], Output: " + solution(new int[]{1,2,3})); // Expected: 4

    }

    private static int solution(int[] skyline) {
        Deque<Integer> mono = new LinkedList<>();
        int ans = 0;

        for (int i = 0; i <= skyline.length; i++) {
            int val = (i == skyline.length) ? 0 : skyline[i];

            while (!mono.isEmpty() && skyline[mono.peekLast()] >= val) {
                int height = skyline[mono.pollLast()];
                int width = mono.isEmpty() ? i : i - mono.peekLast() - 1;
                int side = Math.min(height, width);

                if (side > 0) {
                    ans = Math.max(ans, side * side); // Return the area of the largest square found
                }
            }
            mono.addLast(i);
        }
        return ans;
    }
}
