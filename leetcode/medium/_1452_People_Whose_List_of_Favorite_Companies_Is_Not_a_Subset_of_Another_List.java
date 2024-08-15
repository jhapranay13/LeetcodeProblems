package leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 *
 * Given the array favoriteCompanies where favoriteCompanies[i] is the list of favorites companies for the ith person (indexed from 0).
 *
 * Return the indices of people whose list of favorite companies is not a subset of any other list of favorites companies. You must return the indices in increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
 * Output: [0,1,4]
 * Explanation:
 * Person with index=2 has favoriteCompanies[2]=["google","facebook"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] corresponding to the person with index 0.
 * Person with index=3 has favoriteCompanies[3]=["google"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] and favoriteCompanies[1]=["google","microsoft"].
 * Other lists of favorite companies are not a subset of another list, therefore, the answer is [0,1,4].
 * Example 2:
 *
 * Input: favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
 * Output: [0,1]
 * Explanation: In this case favoriteCompanies[2]=["facebook","google"] is a subset of favoriteCompanies[0]=["leetcode","google","facebook"], therefore, the answer is [0,1].
 * Example 3:
 *
 * Input: favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
 * Output: [0,1,2,3]
 *
 *
 * Constraints:
 *
 * 1 <= favoriteCompanies.length <= 100
 * 1 <= favoriteCompanies[i].length <= 500
 * 1 <= favoriteCompanies[i][j].length <= 20
 * All strings in favoriteCompanies[i] are distinct.
 * All lists of favorite companies are distinct, that is, If we sort alphabetically each list then favoriteCompanies[i] != favoriteCompanies[j].
 * All strings consist of lowercase English letters only.
 *
 */

public class _1452_People_Whose_List_of_Favorite_Companies_Is_Not_a_Subset_of_Another_List {
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Set<String>> fcSet = new ArrayList<>();

        for (List<String> person : favoriteCompanies) {
            Set<String> set = new HashSet<>();

            for (String company : person) {
                set.add(company);
            }
            fcSet.add(set);
        }
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < fcSet.size(); i++) {
            boolean isSubSet = false;

            for (int j = 0; j < fcSet.size(); j++) {
                Set<String> set1 = fcSet.get(i);
                Set<String> set2 = fcSet.get(j);

                if (i != j && set2.size() > set1.size() && set2.containsAll(set1)) {
                    isSubSet = true;
                    break;
                }
            }

            if (!isSubSet) {
                ans.add(i);
            }
        }
        return ans;
    }
}
