package leetcode.medium;

/**
 *
 * We can use run-length encoding (i.e., RLE) to encode a sequence of integers. In a run-length encoded array of even length encoding (0-indexed), for all even i, encoding[i] tells us the number of times that the non-negative integer value encoding[i + 1] is repeated in the sequence.
 *
 * For example, the sequence arr = [8,8,8,5,5] can be encoded to be encoding = [3,8,2,5]. encoding = [3,8,0,9,2,5] and encoding = [2,8,1,8,2,5] are also valid RLE of arr.
 * Given a run-length encoded array, design an iterator that iterates through it.
 *
 * Implement the RLEIterator class:
 *
 * RLEIterator(int[] encoded) Initializes the object with the encoded array encoded.
 * int next(int n) Exhausts the next n elements and returns the last element exhausted in this way. If there is no element left to exhaust, return -1 instead.
 *
 *
 * Example 1:
 *
 * Input
 * ["RLEIterator", "next", "next", "next", "next"]
 * [[[3, 8, 0, 9, 2, 5]], [2], [1], [1], [2]]
 * Output
 * [null, 8, 8, 5, -1]
 *
 * Explanation
 * RLEIterator rLEIterator = new RLEIterator([3, 8, 0, 9, 2, 5]); // This maps to the sequence [8,8,8,5,5].
 * rLEIterator.next(2); // exhausts 2 terms of the sequence, returning 8. The remaining sequence is now [8, 5, 5].
 * rLEIterator.next(1); // exhausts 1 term of the sequence, returning 8. The remaining sequence is now [5, 5].
 * rLEIterator.next(1); // exhausts 1 term of the sequence, returning 5. The remaining sequence is now [5].
 * rLEIterator.next(2); // exhausts 2 terms, returning -1. This is because the first term exhausted was 5,
 * but the second term did not exist. Since the last term exhausted does not exist, we return -1.
 *
 *
 * Constraints:
 *
 * 2 <= encoding.length <= 1000
 * encoding.length is even.
 * 0 <= encoding[i] <= 109
 * 1 <= n <= 109
 * At most 1000 calls will be made to next.
 *
 */

public class _900_RLEIterator {

    class RLEIterator {
        int[] encoding;
        int index;

        public RLEIterator(int[] encoding) {
            this.index = 0;
            this.encoding = encoding;
        }

        public int next(int n) {

            while (n > 0) {

                if (index + 1 >= encoding.length) {
                    return -1;
                }

                if (encoding[index] == 0) {
                    index += 2;
                    continue;
                }

                if (encoding[index] <= n) {
                    n -= encoding[index];
                    encoding[index] = 0;

                    if (n == 0) {
                        return encoding[index + 1];
                    }
                    index += 2;
                } else {
                    encoding[index] -= n;
                    return encoding[index + 1];
                }
            }
            return -1;
        }
    }
    //=============================================================================================
    //Another approach
    class RLEIterator1 {
        int index = 0;
        int[] encoding;
        int currCount = 0;

        public RLEIterator1(int[] encoding) {
            this.encoding = encoding;
        }

        public int next(int n) {

            while (index < encoding.length) {

                if (encoding[index] == 0) {
                    index += 2;
                    continue;
                }
                if (encoding[index] >= n) {
                    encoding[index] -= n;
                    return encoding[index + 1];
                }

                if (encoding[index] < n) {
                    n -= encoding[index];
                    encoding[index] = 0;
                    index += 2;
                }
            }
            return -1;
        }
    }

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(encoding);
 * int param_1 = obj.next(n);
 */
}
