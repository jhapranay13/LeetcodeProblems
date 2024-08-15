package leetcode.StackAndQueues.Monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * You are given a 0-indexed integer array books of length n where books[i] denotes the number of books on the ith shelf of a bookshelf.
 *
 * You are going to take books from a contiguous section of the bookshelf spanning from l to r where 0 <= l <= r < n. For each index i in the range l <= i < r, you must take strictly fewer books from shelf i than shelf i + 1.
 *
 * Return the maximum number of books you can take from the bookshelf.
 *
 *
 *
 * Example 1:
 *
 * Input: books = [8,5,2,7,9]
 * Output: 19
 * Explanation:
 * - Take 1 book from shelf 1.
 * - Take 2 books from shelf 2.
 * - Take 7 books from shelf 3.
 * - Take 9 books from shelf 4.
 * You have taken 19 books, so return 19.
 * It can be proven that 19 is the maximum number of books you can take.
 * Example 2:
 *
 * Input: books = [7,0,3,4,5]
 * Output: 12
 * Explanation:
 * - Take 3 books from shelf 2.
 * - Take 4 books from shelf 3.
 * - Take 5 books from shelf 4.
 * You have taken 12 books so return 12.
 * It can be proven that 12 is the maximum number of books you can take.
 * Example 3:
 *
 * Input: books = [8,2,3,7,3,4,0,1,4,3]
 * Output: 13
 * Explanation:
 * - Take 1 book from shelf 0.
 * - Take 2 books from shelf 1.
 * - Take 3 books from shelf 2.
 * - Take 7 books from shelf 3.
 * You have taken 13 books so return 13.
 * It can be proven that 13 is the maximum number of books you can take.
 *
 *
 * Constraints:
 *
 * 1 <= books.length <= 105
 * 0 <= books[i] <= 105
 *
 */

public class _2355_Maximum_Number_of_Books_You_Can_Take {

    public long maximumBooks(int[] books) {
        Deque<Integer> mq = new LinkedList<>();
        long ans = 0;
        long curr = 0;

        for (int i = 0; i < books.length; i++) {

            while (!mq.isEmpty() && books[i] - books[mq.peek()] <= i - mq.peek()) {
                int index = mq.pop();
                curr -= sum(books[index], mq.isEmpty() ? index + 1: index - mq.peek());
            }
            curr += sum(books[i], mq.isEmpty() ? i + 1 : i - mq.peek());
            mq.push(i);
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public long sum(long num, long range) {

        if (range >= num) {
            return num * (num + 1) / 2;
        }
        long nextRange = num - range;
        return num * (num + 1) / 2 - (nextRange > 0 ? (nextRange * (nextRange + 1) / 2) : 0);
    }
}
