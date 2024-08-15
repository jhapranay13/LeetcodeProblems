package leetcode.Strings;

import java.util.*;

/**
 *
 * A transaction is possibly invalid if:
 *
 * the amount exceeds $1000, or;
 * if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
 * You are given an array of strings transaction where transactions[i] consists of comma-separated values representing the name, time (in minutes), amount, and city of the transaction.
 *
 * Return a list of transactions that are possibly invalid. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
 * Output: ["alice,20,800,mtv","alice,50,100,beijing"]
 * Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
 * Example 2:
 *
 * Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
 * Output: ["alice,50,1200,mtv"]
 * Example 3:
 *
 * Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
 * Output: ["bob,50,1200,mtv"]
 *
 *
 * Constraints:
 *
 * transactions.length <= 1000
 * Each transactions[i] takes the form "{name},{time},{amount},{city}"
 * Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
 * Each {time} consist of digits, and represent an integer between 0 and 1000.
 * Each {amount} consist of digits, and represent an integer between 0 and 2000.
 *
 */

public class _1169_Invalid_Transactions {
    public List<String> invalidTransactions(String[] transactions) {
        Map<String, List<String[]>> nameholder = new HashMap<>();
        Set<String[]> ansHolder = new HashSet<>();

        for (String transaction : transactions) {
            String[] split = transaction.split(",");
            List<String[]> list = nameholder.getOrDefault(split[0], new ArrayList<>());
            list.add(split);
            nameholder.put(split[0], list);

            if (Integer.parseInt(split[2]) > 1000) {
                ansHolder.add(split);
            }
        }

        for (String name : nameholder.keySet()) {
            List<String[]> list = nameholder.get(name);
            Collections.sort(list, (a, b) -> Integer.parseInt(a[1]) - Integer.parseInt(b[1]));

            for (int i = 0; i < list.size(); i++) {

                for (int j = 0; j < i; j++) {
                    String prevTime = list.get(i)[1];
                    String currTime = list.get(j)[1];
                    String prevCity = list.get(i)[3];
                    String currCity = list.get(j)[3];
                    String prevName = list.get(i)[0];
                    String currName = list.get(j)[0];

                    if (Math.abs(Integer.parseInt(currTime) - Integer.parseInt(prevTime)) <= 60 &&
                            !prevCity.equals(currCity) && prevName.equals(currName) ){
                        ansHolder.add(list.get(j));
                        ansHolder.add(list.get(i));
                    }
                }
            }
        }
        List<String> ans = new ArrayList<>();

        for (String[] split : ansHolder) {
            StringBuilder strHolder = new StringBuilder();

            for (String str : split) {

                if (!strHolder.isEmpty()) {
                    strHolder.append(",");
                }
                strHolder.append(str);
            }
            ans.add(strHolder.toString());
        }
        return ans;
    }
}
