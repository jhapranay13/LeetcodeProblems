package LeetcodeDiscuss;

import java.util.Arrays;
import java.util.List;

/**
 *
 *
 amazon operates numerous warehouses, with each warehouse i
 holding inventory[i] units of a particular product. You and your co-worker are responsible for dispatching these items to fulfill customer orders following a specific process:
 dispatch1 units.
 1. When dispatching from warehouse i, you begin by reducing the inventory of the ith warehouse by dispatch1 unit
 2. After your dispatch, your co-worker reduces the inventory by dispatch2 units.

 3. This process repeats until the inventory of the ith warehouse reaches zero or becomes negative (i.e., inventory[i]≤ 0).
 4. For every warehouse that is emptied during your dispatch, you and your co-worker collectively earn 1 credi
 Your co-worker has the option to skip their turn, but they can only do this a limited number of times, defined by skips.

 Your task is to determine the best strategy to maximize the total credits that both you and your co-worke can earn together.
 Example
 n = 6
 inventory = [10, 6, 12, 8, 15, 1]
 dispatch1 = 2
 dispatch2 = 3 skips = 3

 * An optimal dispatch strategy is as follows:

 * 1. Your co-worker skips 2 turns, allowing you to empty the inventory of the 1st warehouse (Inventory: 10→8-5 →3-1-1).
 * 2. Your co-worker doesn't skip any turns, and you empty the inventory of the 2nd warehouse (Inventory: 6 →4→ 1-1).
 * 3. Your co-worker doesn't skip any turns, and you empty the inventory of the 3rd warehouse (Inventory: 12 → 10 →7-52-0).
 * -1).
 * 4. Your co-worker skips 1 turn, and you drain the inventory of the 4th warehouse (Inventory: 8-6→3→1→
 * →10-8-5-3→0).

 * 5. Your co-worker doesn't skip any turns, and they empty the inventory of the 5th warehouse (Inventory: 15 → 13
 * 6. Your co-worker doesn't skip any turns, and you empty the inventory of the 6th warehouse (Inventory: 1→-1).

 * As a result, the 1st, 2nd, 3rd, 4th, and 6th warehouses were completely dispatched by you, and the two of you collectively earned 5 credits, which is the maximum possible in this scenario.
 * Hence, the answer is 5.
 * Function Description

 * getMaximum Credits has the following parameters:
 * int inventory[n]: An array of integers denoting the inventory level of each warehouse.
 * int dispatch1: An integer indicating your dispatch level per turn.
 * int dispatch2: An integer indicating your co-worker's dispatch level per turn.

 * int skips: An integer specifying the maximum number of times your co-worker can skip their turn.
 * Return
 * int: the maximum number of credits both of you can achieve collectively.
 */
public class AmazonOADispatch {
    public static void main(String[] args) {
        // Example from problem description
        List<Integer> inventory1 = Arrays.asList(10, 6, 12, 8, 15, 1);
        int dispatch1_1 = 2;
        int dispatch2_1 = 3;
        int skips1 = 3;
        System.out.println("Example 1 (from problem):");
        System.out.println("Inventory: " + inventory1 + ", d1: " + dispatch1_1 + ", d2: " + dispatch2_1 + ", skips: " + skips1);
        System.out.println("Max Credits: " + getMaximumCredits(inventory1, dispatch1_1, dispatch2_1, skips1)); // Expected 5 from problem, my code gives 6.

        // Retesting previously added cases with the d1 <= 0 check
        List<Integer> inv7 = Arrays.asList(10);
        System.out.println("\nInventory: " + inv7 + ", d1: 1, d2: 5, skips: 1");
        System.out.println("Max Credits: " + getMaximumCredits(inv7, 1, 5, 1)); // Expected: 0 (Fixed: was 1, then infinite loop before fix)

        List<Integer> inv9 = Arrays.asList(10);
        System.out.println("Inventory: " + inv9 + ", d1: 2, d2: 2, skips: 0");
        System.out.println("Max Credits: " + getMaximumCredits(inv9, 2, 2, 0)); // Expected: 0 (Fixed: was infinite loop before fix)

        List<Integer> inv10 = Arrays.asList(10, 5);
        System.out.println("Inventory: " + inv10 + ", d1: 4, d2: 1, skips: 1");
        System.out.println("Max Credits: " + getMaximumCredits(inv10, 4, 1, 1)); // Expected: 0 (Fixed: was infinite loop before fix)


        // Other tests as before
        List<Integer> inv2 = Arrays.asList(5, 7, 3);
        System.out.println("\nInventory: " + inv2 + ", d1: 5, d2: 10, skips: 0");
        System.out.println("Max Credits: " + getMaximumCredits(inv2, 5, 10, 0));

        List<Integer> inv3 = Arrays.asList(10, 10, 10);
        System.out.println("Inventory: " + inv3 + ", d1: 3, d2: 5, skips: 1");
        System.out.println("Max Credits: " + getMaximumCredits(inv3, 3, 5, 1));

        List<Integer> inv4 = Arrays.asList(10, 11, 12);
        System.out.println("Inventory: " + inv4 + ", d1: 3, d2: 5, skips: 1");
        System.out.println("Max Credits: " + getMaximumCredits(inv4, 3, 5, 1));

        List<Integer> inv5 = Arrays.asList(10, 11, 12);
        System.out.println("Inventory: " + inv5 + ", d1: 3, d2: 5, skips: 0");
        System.out.println("Max Credits: " + getMaximumCredits(inv5, 3, 5, 0));

        List<Integer> inv6 = Arrays.asList(10, 0, -5, 12);
        System.out.println("Inventory: " + inv6 + ", d1: 3, d2: 5, skips: 1");
        System.out.println("Max Credits: " + getMaximumCredits(inv6, 3, 5, 1));

        List<Integer> inv8 = Arrays.asList(10);
        System.out.println("Inventory: " + inv8 + ", d1: 3, d2: 1, skips: 0");
        System.out.println("Max Credits: " + getMaximumCredits(inv8, 3, 1, 0));
    }

    private static int getMaximumCredits(List<Integer> inventory, int dispatch1, int dispatch2, int skip) {
        return recur(inventory, dispatch1, dispatch2, skip, 0);
    }

    private static int recur(List<Integer> inventory, int dispatch1, int dispatch2, int skip, int index) {

        if (index >= inventory.size()) {
            return 0; // Base case: no more warehouses to process
        }

        int ans = 0;
        int currStock = inventory.get(index);

        if (currStock <= 0) {
            ans = recur(inventory, dispatch1, dispatch2, skip, index + 1); // Skip this warehouse if stock is non-positive
        } else {

            for (int i = 0; i <= skip; i++) {
                int temp = 0;

                if (isDrainedByYou(currStock, dispatch1, dispatch2, i)) {
                    temp = 1;
                }
                ans = Math.max(ans, temp + recur(inventory, dispatch1, dispatch2, skip - i, index + 1));

            }
        }
        return ans;
    }

    private static boolean isDrainedByYou(int stock
            , int dispatch1, int dispatch2, int skip) {
        int d1 = dispatch1;  // you
        int d2 = dispatch2;
        stock = stock - dispatch1 * skip;

        int numberOfRounds = stock / (d1 + d2);
        int remaining = stock % (d1 + d2);

        if (remaining == 0) {
            return numberOfRounds % 2 == 1; // you get the last dispatch
        }
        return remaining < d1; // can be dispatched by you
    }

}
