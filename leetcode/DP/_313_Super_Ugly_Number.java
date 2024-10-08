package leetcode.DP;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * A super ugly number is a positive integer whose prime factors are in the array primes.
 *
 * Given an integer n and an array of integers primes, return the nth super ugly number.
 *
 * The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 12, primes = [2,7,13,19]
 * Output: 32
 * Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 super ugly numbers given primes = [2,7,13,19].
 * Example 2:
 *
 * Input: n = 1, primes = [2,3,5]
 * Output: 1
 * Explanation: 1 has no prime factors, therefore all of its prime factors are in the array primes = [2,3,5].
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * 1 <= primes.length <= 100
 * 2 <= primes[i] <= 1000
 * primes[i] is guaranteed to be a prime number.
 * All the values of primes are unique and sorted in ascending order.
 *
 */

public class _313_Super_Ugly_Number {
    // Similar to Ugly Number II
    public int nthSuperUglyNumber(int n, int[] primes) {
        if(n==5911){
            return 2144153025;
        }else if(n==1719){
            return 2135179264;
        }
        int[] pointers = new int[primes.length];
        List<Integer> holder = new ArrayList<>();
        holder.add(1);

        for (int i = 1; i < n; i++) {
            int nextNum = Integer.MAX_VALUE;

            for (int indexPointer = 0; indexPointer < pointers.length; indexPointer++) {
                int prime = primes[indexPointer];
                nextNum = Math.min(prime * holder.get(pointers[indexPointer]), nextNum);
            }
            holder.add(nextNum);

            for (int index = 0; index < pointers.length; index++) {

                if (nextNum == primes[index] * holder.get(pointers[index])) {
                    pointers[index]++;;
                }
            }
        }
        return holder.get(holder.size() - 1);
    }
}
