package leetcode.Strings;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 *
 * Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.
 *
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 *
 * Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.
 *
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 *
 *
 *
 * Example 1:
 *
 * Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
 * Output: 1
 * Example 2:
 *
 * Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 0 <= bank.length <= 10
 * startGene.length == endGene.length == bank[i].length == 8
 * startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
 *
 */

public class _433_Minimum_Genetic_Mutation {
    public int minMutation(String startGene, String endGene, String[] bank) {

        if (startGene.equals(endGene)) {
            return 0;
        }
        Set<String> holder = new HashSet<>();

        for (String str : bank) {
            holder.add(str);
        }
        Deque<String> q = new LinkedList<>();
        q.offer(startGene);
        Set<String> v = new HashSet<>();
        v.add(startGene);
        int step = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                String str = q.poll();

                if (str.equals(endGene)) {
                    return step;
                }

                for (int i = 0; i < 26; i++) {
                    char ch = (char)(i + 'A');

                    for (int j = 0; j < str.length(); j++) {

                        if (ch == str.charAt(j)) {
                            continue;
                        }
                        String left = str.substring(0, j);
                        String right = str.substring(j + 1);
                        String fullString = left + ch + right;

                        if (holder.contains(fullString) && !v.contains(fullString)) {
                            q.offer(fullString);
                            v.add(fullString);
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
