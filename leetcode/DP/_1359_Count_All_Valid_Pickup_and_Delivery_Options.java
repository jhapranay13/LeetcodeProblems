package leetcode.DP;

/**
 *
 * Given n orders, each order consist in pickup and delivery services.
 *
 * Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 1
 * Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
 * Example 2:
 *
 * Input: n = 2
 * Output: 6
 * Explanation: All possible orders:
 * (P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
 * This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
 * Example 3:
 *
 * Input: n = 3
 * Output: 90
 *
 *
 * Constraints:
 *
 * 1 <= n <= 500
 *
 */

public class _1359_Count_All_Valid_Pickup_and_Delivery_Options {
    public int countOrders(int n) {
        long[][] memo = new long[n + 1][n + 1];
        return (int)recur(n, 0, memo);
    }
    private int mod = 1000000007;

    private long recur(int pickup, int delivery, long[][] memo) {

        if (pickup == 0 && delivery == 0) {
            return 1;
        }

        if (pickup < 0 || delivery < 0) {
            return 0;
        }

        if (memo[pickup][delivery] > 0) {
            return memo[pickup][delivery];
        }
        long pickupRes = pickup * recur(pickup - 1, delivery + 1, memo) % mod;
        long deliveryRes = delivery * recur(pickup, delivery - 1, memo) % mod;
        return memo[pickup][delivery] = (pickupRes + deliveryRes) % mod;
    }
}
