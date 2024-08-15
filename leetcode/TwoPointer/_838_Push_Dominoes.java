package leetcode.TwoPointer;

/**
 *
 * There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
 *
 * After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
 *
 * When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
 *
 * For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
 *
 * You are given a string dominoes representing the initial state where:
 *
 * dominoes[i] = 'L', if the ith domino has been pushed to the left,
 * dominoes[i] = 'R', if the ith domino has been pushed to the right, and
 * dominoes[i] = '.', if the ith domino has not been pushed.
 * Return a string representing the final state.
 *
 *
 *
 * Example 1:
 *
 * Input: dominoes = "RR.L"
 * Output: "RR.L"
 * Explanation: The first domino expends no additional force on the second domino.
 * Example 2:
 *
 *
 * Input: dominoes = ".L.R...LR..L.."
 * Output: "LL.RR.LLRRLL.."
 *
 *
 * Constraints:
 *
 * n == dominoes.length
 * 1 <= n <= 10^5
 * dominoes[i] is either 'L', 'R', or '.'.
 *
 */

public class _838_Push_Dominoes {
    public String pushDominoes(String dominoes) {
        boolean[] leftToRight = new boolean[dominoes.length()];
        boolean[] rightToLeft = new boolean[dominoes.length()];
        char[] doms = dominoes.toCharArray();
        char prev = '.';

        for (int i = 0; i < doms.length; i++) {

            if (doms[i] == '.') {

                if (prev == 'R') {
                    leftToRight[i] = true;
                }
            } else {
                prev = doms[i];
            }
        }
        prev = '.';

        for (int i = doms.length - 1; i >= 0; i--) {

            if (doms[i] == '.') {

                if (prev == 'L') {
                    rightToLeft[i] = true;
                }
            } else {
                prev = doms[i];
            }
        }
        int i = 0;

        while (i < doms.length) {

            if (leftToRight[i] && !rightToLeft[i]) {
                doms[i++] = 'R';
            } else if (!leftToRight[i] && rightToLeft[i]) {
                doms[i++] = 'L';
            } else if (leftToRight[i] && rightToLeft[i]) {
                int j = i + 1;
                //checking the rangefor both to be true
                while (j < doms.length && leftToRight[j] && rightToLeft[j]) {
                    j++;
                }
                int left = i;
                int right = j - 1;

                while (left < right) {
                    doms[left++] = 'R';
                    doms[right--] = 'L';
                }
                i = j;
            } else {
                i++;
            }
        }
        return new String(doms);
    }
    //=============================================================================================
    public String pushDominoes1(String dominoes) {
        int size = dominoes.length();
        char[] dom = dominoes.toCharArray();
        int[] leftToRight = new int[size];
        int curr = -1;

        // Checking right falling dominoes and it's distance from Dominoes that are stable
        // closer the falling Domino greater than force
        for (int i = 0; i < size; i++) {

            if (dom[i] == 'R') {
                curr = i;
            } else if (dom[i] == 'L'){
                curr = -1;
            }
            // If curr == -1 then distance is max
            leftToRight[i] = curr == -1 ? Integer.MAX_VALUE : i - curr;
        }
        curr = -1;
        int[] rightToLeft = new int[size];

        for (int i = size - 1; i >= 0; i--) {

            if (dom[i] == 'L') {
                curr = i;
            } else if (dom[i] == 'R'){
                curr = -1;
            }
            // If curr == -1 then distance is max
            rightToLeft[i] = curr == -1 ? Integer.MAX_VALUE : curr - i;
        }

        for (int i = 0; i < size; i++) {

            if (dom[i] == '.') {
                int left = leftToRight[i];
                int right = rightToLeft[i];
                // checking whichEver is closer
                if (left == right) {
                    continue;
                } else if (left < right) {
                    dom[i] = 'R';
                } else {
                    dom[i] = 'L';
                }
            }
        }
        return new String(dom);
    }
}
