package leetcode.Greedy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * There is a forest with an unknown number of rabbits. We asked n rabbits "How many rabbits have the same color as you?" and collected the answers in an integer array answers where answers[i] is the answer of the ith rabbit.
 *
 * Given the array answers, return the minimum number of rabbits that could be in the forest.
 *
 *
 *
 * Example 1:
 *
 * Input: answers = [1,1,2]
 * Output: 5
 * Explanation:
 * The two rabbits that answered "1" could both be the same color, say red.
 * The rabbit that answered "2" can't be red or the answers would be inconsistent.
 * Say the rabbit that answered "2" was blue.
 * Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
 * The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.
 * Example 2:
 *
 * Input: answers = [10,10,10]
 * Output: 11
 *
 *
 * Constraints:
 *
 * 1 <= answers.length <= 1000
 * 0 <= answers[i] < 1000
 *
 */

public class _781_Rabbits_in_Forest {
    public int numRabbits(int[] answers) {
        int ans = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int answer : answers) {

            //Unique color
            if (answer == 0) {
                ans++;
                continue;
            }
            // If a rabbit says 1 then one another rabbit will answer 1.
            // If 3rd rabbit also says 1 then it means that it is off different color
            // So adding that and starting from one again.
            if (!freqMap.containsKey(answer)) {
                ans += answer + 1;
                freqMap.put(answer, 1);
            } else {
                int freq = freqMap.get(answer);
                int temp = answer + 1;

                if (freq > answer) {
                    ans += temp;
                    freqMap.put(answer, 1);
                } else {
                    freqMap.put(answer, freqMap.get(answer) + 1);
                }
            }
        }
        return ans;
    }
    //=============================================================================================
    /*
    Breakdown:
        Two rabbits say “1 other like me” → group size = 2

        So we need 1 group of size 2 → covers 2 rabbits

        One rabbit says “2 others like me” → group size = 3

        We need 1 group of size 3 → covers 1 rabbit (but we must assume 2 more exist)
     */
    public int numRabbits1(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;

        for (int answer : answers) {
            // Count how many times this answer appears
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }

        for (int k : map.keySet()) {
            int count = map.get(k);
            int groupSize = k + 1;

            // Number of groups needed for this answer
            int groups = (int) Math.ceil(count / (double) groupSize);

            total += groups * groupSize;
        }

        return total;
    }
}
