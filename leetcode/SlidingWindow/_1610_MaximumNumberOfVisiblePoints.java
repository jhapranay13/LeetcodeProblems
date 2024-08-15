package leetcode.SlidingWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 *You are given an array points, an integer angle, and your location, where location = [posx, posy] and points[i] = [xi, yi] both denote integral coordinates on the X-Y plane.
 *
 * Initially, you are facing directly east from your position. You cannot move from your position, but you can rotate. In other words, posx and posy cannot be changed. Your field of view in degrees is represented by angle, determining how wide you can see from any given view direction. Let d be the amount in degrees that you rotate counterclockwise. Then, your field of view is the inclusive range of angles [d - angle/2, d + angle/2].
 *
 *
 * You can see some set of points if, for each point, the angle formed by the point, your position, and the immediate east direction from your position is in your field of view.
 *
 * There can be multiple points at one coordinate. There may be points at your location, and you can always see these points regardless of your rotation. Points do not obstruct your vision to other points.
 *
 * Return the maximum number of points you can see.
 *
 *
 *
 * Example 1:
 *              |   .
 *              |     .(Points)
 *              |   .
 *              |_________________
 *          location
 *
 * Input: points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
 * Output: 3
 * Explanation: The shaded region represents your field of view. All points can be made visible in your field of view, including [3,3] even though [2,2] is in front and in the same line of sight.
 * Example 2:
 *
 * Input: points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
 * Output: 4
 * Explanation: All points can be made visible in your field of view, including the one at your location.
 * Example 3:
 *
 *
 * Input: points = [[1,0],[2,1]], angle = 13, location = [1,1]
 * Output: 1
 * Explanation: You can only see one of the two points, as shown above.
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 105
 * points[i].length == 2
 * location.length == 2
 * 0 <= angle < 360
 * 0 <= posx, posy, xi, yi <= 100
 *
 */

public class _1610_MaximumNumberOfVisiblePoints {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> angles = new ArrayList<>();
        int sameLocationCount = 0;
        //All point angles keeping location as origin
        for(List<Integer> point : points) {
            int dx = point.get(0) - location.get(0);
            int dy = point.get(1) - location.get(1);

            if(dx == 0 && dy == 0) {
                sameLocationCount++; // if the point lies on the 'location' itself, it comes in the view ALWAYS (so ADD them in last)
                continue;
            }

            double degree = Math.atan2(dy, dx) * (180/Math.PI); // Math.atan2(dy, dx) finds the radian of point (x, y) and +ve X-Axis
            // since for 225 it will come as -135 so Making everything as linear
            if (degree < 0) {
                degree = 360 + degree;
            }
            angles.add(degree);
        }
        Collections.sort(angles);
        int fast = 0;
        int slow = 0;
        int ans = 0;
        double currAngle = 0;
        int currCount = 0;

        //Circular Sliding window
        while (slow < angles.size()) {
            currAngle = angles.get(fast++ % angles.size()) -
                    angles.get(slow % angles.size());
            currAngle += fast > angles.size() ? 360 : 0;//adding 360 as we have made one full circle
            currCount++;

            while (currAngle > angle && slow < angles.size()) {
                currAngle -= angles.get(++slow % angles.size());
                currCount--;
            }
            ans = Math.max(ans, currCount);
        }
        return ans + sameLocationCount;
    }
}
