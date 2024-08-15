package leetcode.hard;

/**
 *
 *You are given several boxes with different colors represented by different positive numbers.
 *
 * You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points.
 *
 * Return the maximum points you can get.
 *
 *
 *
 * Example 1:
 *
 * Input: boxes = [1,3,2,2,2,3,4,3,1]
 * Output: 23
 * Explanation:
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 * ----> [1, 3, 3, 3, 1] (1*1=1 points)
 * ----> [1, 1] (3*3=9 points)
 * ----> [] (2*2=4 points)
 * Example 2:
 *
 * Input: boxes = [1,1,1]
 * Output: 9
 * Example 3:
 *
 * Input: boxes = [1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= boxes.length <= 100
 * 1 <= boxes[i] <= 100
 *
 */

public class _546_RemoveBoxes {public int removeBoxes(int[] boxes) {
    int[][][] memo = new int[boxes.length][boxes.length][boxes.length];
    return recur(boxes, 0, boxes.length - 1, 0, memo);
}

    private int recur(int[] boxes, int lo, int hi, int countOfBoxesRemoved, int[][][] memo) {

        if (lo > hi) {
            return 0;
        }

        if (memo[lo][hi][countOfBoxesRemoved] > 0) {
            return memo[lo][hi][countOfBoxesRemoved];
        }
        int templo = lo;
        int tempCount = countOfBoxesRemoved;
        //removing from the left
        while (lo + 1 <= hi && boxes[lo] == boxes[lo + 1]) {
            lo++;
            countOfBoxesRemoved++;
        }
        //countOfBoxesRemoved == 0 here coz no left boxes are attatched since we removed it
        int ans = recur(boxes, lo + 1, hi, 0, memo) +
                (countOfBoxesRemoved + 1) * (countOfBoxesRemoved + 1);

        for (int i = lo + 1; i <= hi; i++) {
            //Combining boxes lo and i by removing boxes from between
            if (boxes[i] == boxes[lo]) {
                ans = Math.max(ans, recur(boxes, lo + 1, i - 1, 0, memo) + // removing from middle
                        recur(boxes, i, hi, countOfBoxesRemoved + 1, memo));
                //if right side is removed then we will have countOfBoxesRemoved + 1 boxes
                //with the same colour;
            }
        }
        return memo[templo][hi][tempCount] = ans;
    }
}
