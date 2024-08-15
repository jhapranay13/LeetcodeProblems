package leetcode.Arrays;

import java.util.*;

/**
 *
 * You are given two string arrays username and website and an integer array timestamp. All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]] indicates that the user username[i] visited the website website[i] at time timestamp[i].
 *
 * A pattern is a list of three websites (not necessarily distinct).
 *
 * For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and ["luffy", "luffy", "luffy"] are all patterns.
 * The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.
 *
 * For example, if the pattern is ["home", "away", "love"], the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
 * Similarly, if the pattern is ["leetcode", "love", "leetcode"], the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
 * Also, if the pattern is ["luffy", "luffy", "luffy"], the score is the number of users x such that x visited "luffy" three different times at different timestamps.
 * Return the pattern with the largest score. If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.
 *
 *
 *
 * Example 1:
 *
 * Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
 * Output: ["home","about","career"]
 * Explanation: The tuples in this example are:
 * ["joe","home",1],["joe","about",2],["joe","career",3],["james","home",4],["james","cart",5],["james","maps",6],["james","home",7],["mary","home",8],["mary","about",9], and ["mary","career",10].
 * The pattern ("home", "about", "career") has score 2 (joe and mary).
 * The pattern ("home", "cart", "maps") has score 1 (james).
 * The pattern ("home", "cart", "home") has score 1 (james).
 * The pattern ("home", "maps", "home") has score 1 (james).
 * The pattern ("cart", "maps", "home") has score 1 (james).
 * The pattern ("home", "home", "home") has score 0 (no user visited home 3 times).
 * Example 2:
 *
 * Input: username = ["ua","ua","ua","ub","ub","ub"], timestamp = [1,2,3,4,5,6], website = ["a","b","a","a","b","c"]
 * Output: ["a","b","a"]
 *
 *
 * Constraints:
 *
 * 3 <= username.length <= 50
 * 1 <= username[i].length <= 10
 * timestamp.length == username.length
 * 1 <= timestamp[i] <= 109
 * website.length == username.length
 * 1 <= website[i].length <= 10
 * username[i] and website[i] consist of lowercase English letters.
 * It is guaranteed that there is at least one user who visited at least three websites.
 * All the tuples [username[i], timestamp[i], website[i]] are unique.
 *
 */

public class _1152_AnalyzeUserWebsiteVisistPattern {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<Object[]> list = new ArrayList<>();

        for (int i = 0; i < username.length; i++) {
            list.add(new Object[] {username[i], website[i], timestamp[i]});
        }
        Collections.sort(list, (x, y) -> {return (int)x[2] - (int)y[2];});
        Map<String, List<String>> userWebsiteMap = new HashMap<>();

        for (Object[] obj : list) {
            String name = (String)obj[0];
            String web = (String)obj[1];
            List<String> ws = userWebsiteMap.getOrDefault(name, new ArrayList<>());
            ws.add(web);
            userWebsiteMap.put(name, ws);
        }
        Map<String, Set<List<String>>> user3WebsiteMap = new HashMap<>();
        initUser3WebsiteMap(userWebsiteMap, user3WebsiteMap);
        Map<List<String>, Integer> seqScore = new HashMap<>();

        for (String key : user3WebsiteMap.keySet()) {
            Set<List<String>> seqSet = user3WebsiteMap.get(key);

            for (List<String> seq : seqSet) {
                int count = seqScore.getOrDefault(seq, 0);
                seqScore.put(seq, ++count);
            }
        }
        int maxScore = 0;
        List<String> ans = null;

        for (List<String> seq : seqScore.keySet()) {
            int score = seqScore.get(seq);

            if (score == maxScore) {
                if (ans.toString().compareTo(seq.toString()) > 0) {
                    ans = seq;
                }
            } else if (maxScore < score) {
                ans = seq;
                maxScore = score;
            }
        }
        return ans;
    }

    private void initUser3WebsiteMap(Map<String, List<String>> userWebsiteMap, Map<String, Set<List<String>>>
            user3WebsiteMap) {
        for (String key : userWebsiteMap.keySet()) {
            List<String> list = userWebsiteMap.get(key);

            if (list.size() < 3) {
                continue;
            }
            Set<List<String>> seqSet = new HashSet<>();
            initSeqSet(list, seqSet);
            user3WebsiteMap.put(key, seqSet);
        }
    }

    private void initSeqSet(List<String> list, Set<List<String>> seqSet) {

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    List<String> holder = new ArrayList<>();
                    holder.add(list.get(i));
                    holder.add(list.get(j));
                    holder.add(list.get(k));
                    seqSet.add(holder);
                }
            }
        }
    }
}
