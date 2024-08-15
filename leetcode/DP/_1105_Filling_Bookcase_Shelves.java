package leetcode.DP;

/**
 *
 * You are given an array books where books[i] = [thicknessi, heighti] indicates the thickness and height of the ith book. You are also given an integer shelfWidth.
 *
 * We want to place these books in order onto bookcase shelves that have a total width shelfWidth.
 *
 * We choose some of the books to place on this shelf such that the sum of their thickness is less than or equal to shelfWidth, then build another level of the shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down. We repeat this process until there are no more books to place.
 *
 * Note that at each step of the above process, the order of the books we place is the same order as the given sequence of books.
 *
 * For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.
 * Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
 *
 *
 *
 * Example 1:
 *
 *              ______
 *             |  1  |
 *             |_____|______________________
 *             |             |             |
 *             |             |             |
 *             |      2      |      3      |
 *             |             |             |
 *             |_____________|_____________|
 *                                   |     |
 *             ______________________|  7  |
 *            |   4  |    5  |   6   |     |
 *            |______|_______|_______|_____|
 *
 *
 * Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
 * Output: 6
 * Explanation:
 * The sum of the heights of the 3 shelves is 1 + 3 + 2 = 6.
 * Notice that book number 2 does not have to be on the first shelf.
 * Example 2:
 *
 * Input: books = [[1,3],[2,4],[3,2]], shelfWidth = 6
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= books.length <= 1000
 * 1 <= thicknessi <= shelfWidth <= 1000
 * 1 <= heighti <= 1000
 *
 */

public class _1105_Filling_Bookcase_Shelves {

    // Top Down approach
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int memo[] = new int[books.length];
        return recur(books, shelfWidth, 0, memo);
    }

    private int recur(int[][] books, int shelfWidth, int index, int[] memo) {

        if (index == books.length) {
            return 0;
        }

        if (memo[index] > 0) {
            return memo[index];
        }
        int ans = Integer.MAX_VALUE;
        int height = 0;
        int width = 0;

        for (int i = index; i < books.length; i++) {
            width += books[i][0];

            if (width > shelfWidth) {
                break;
            }
            height = Math.max(height, books[i][1]);
            ans = Math.min(ans, height + recur(books, shelfWidth, i + 1, memo));
        }
        return memo[index] = ans;
    }
    //=============================================================================================
    // Bottom up approach

    public int minHeightShelves1(int[][] books, int shelfWidth) {
        int memo[] = new int[books.length + 1];

        for (int index = books.length - 1; index >= 0; index--) {
            int ans = Integer.MAX_VALUE;
            int height = 0;
            int width = 0;

            for (int i = index; i < books.length; i++) {
                width += books[i][0];

                if (width > shelfWidth) {
                    break;
                }
                height = Math.max(height, books[i][1]);
                ans = Math.min(ans, height + memo[i + 1]);
            }
            memo[index] = ans;
        }
        return memo[0];
    }
}
