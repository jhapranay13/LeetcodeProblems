package leetcode.BinarySearch;

import java.util.*;

/**
 *
 * A social media company is trying to monitor activity on their site by analyzing the number of tweets that occur in select periods of time. These periods can be partitioned into smaller time chunks based on a certain frequency (every minute, hour, or day).
 *
 * For example, the period [10, 10000] (in seconds) would be partitioned into the following time chunks with these frequencies:
 *
 * Every minute (60-second chunks): [10,69], [70,129], [130,189], ..., [9970,10000]
 * Every hour (3600-second chunks): [10,3609], [3610,7209], [7210,10000]
 * Every day (86400-second chunks): [10,10000]
 * Notice that the last chunk may be shorter than the specified frequency's chunk size and will always end with the end time of the period (10000 in the above example).
 *
 * Design and implement an API to help the company with their analysis.
 *
 * Implement the TweetCounts class:
 *
 * TweetCounts() Initializes the TweetCounts object.
 * void recordTweet(String tweetName, int time) Stores the tweetName at the recorded time (in seconds).
 * List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) Returns a list of integers representing the number of tweets with tweetName in each time chunk for the given period of time [startTime, endTime] (in seconds) and frequency freq.
 * freq is one of "minute", "hour", or "day" representing a frequency of every minute, hour, or day respectively.
 *
 *
 * Example:
 *
 * Input
 * ["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
 * [[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]
 *
 * Output
 * [null,null,null,null,[2],[2,1],null,[4]]
 *
 * Explanation
 * TweetCounts tweetCounts = new TweetCounts();
 * tweetCounts.recordTweet("tweet3", 0);                              // New tweet "tweet3" at time 0
 * tweetCounts.recordTweet("tweet3", 60);                             // New tweet "tweet3" at time 60
 * tweetCounts.recordTweet("tweet3", 10);                             // New tweet "tweet3" at time 10
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return [2]; chunk [0,59] had 2 tweets
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return [2,1]; chunk [0,59] had 2 tweets, chunk [60,60] had 1 tweet
 * tweetCounts.recordTweet("tweet3", 120);                            // New tweet "tweet3" at time 120
 * tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return [4]; chunk [0,210] had 4 tweets
 *
 *
 * Constraints:
 *
 * 0 <= time, startTime, endTime <= 10^9
 * 0 <= endTime - startTime <= 10^4
 * There will be at most 104 calls in total to recordTweet and getTweetCountsPerFrequency.
 *
 */

public class _1348_Tweet_Counts_Per_Frequency {
    //TreeMap Approach
    class TweetCounts {
        Map<String, TreeMap<Integer, Integer>> tweetTimeMap;

        public TweetCounts() {
            this.tweetTimeMap = new HashMap<>();
        }

        public void recordTweet(String tweetName, int time) {

            if (!tweetTimeMap.containsKey(tweetName)) {
                tweetTimeMap.put(tweetName, new TreeMap<>());
            }
            tweetTimeMap.get(tweetName).put(time, tweetTimeMap.get(tweetName).getOrDefault(time, 0) + 1);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            List<Integer> ans = new ArrayList<>();

            if (!tweetTimeMap.containsKey(tweetName)) {
                return ans;
            }
            int timeInSeconds = 0;

            if (freq.equals("minute")) {
                timeInSeconds = 60;
            } else if (freq.equals("hour")) {
                timeInSeconds = 60 * 60;
            } else {
                timeInSeconds = 60 * 60 * 24;
            }

            for (int start = startTime; start <= endTime; start += timeInSeconds) {
                int count = 0;
                int end = Math.min(start + timeInSeconds - 1, endTime);

                for (int key : tweetTimeMap.get(tweetName).subMap(start, true, end, true).keySet()) {
                    count += tweetTimeMap.get(tweetName).get(key);
                }
                ans.add(count);
            }
            return ans;
        }
    }

/**
 * Your TweetCounts object will be instantiated and called as such:
 * TweetCounts obj = new TweetCounts();
 * obj.recordTweet(tweetName,time);
 * List<Integer> param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);
 *
 */
    //=============================================================================================
    //Binary Search Approach
class TweetCounts1 {
    Map<String, List<Integer>> tweetTimeMap;

    public TweetCounts1() {
        this.tweetTimeMap = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {

        if (!tweetTimeMap.containsKey(tweetName)) {
            tweetTimeMap.put(tweetName, new ArrayList<>());
        }
        tweetTimeMap.get(tweetName).add(time);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> ans = new ArrayList<>();

        if (!tweetTimeMap.containsKey(tweetName)) {
            return ans;
        }
        int timeInSeconds = 0;

        if (freq.equals("minute")) {
            timeInSeconds = 60;
        } else if (freq.equals("hour")) {
            timeInSeconds = 60 * 60;
        } else {
            timeInSeconds = 60 * 60 * 24;
        }
        List<Integer> times = tweetTimeMap.get(tweetName);
        Collections.sort(times);
        int start = startTime;

        while (start <= endTime) {
            int end = Math.min(start + timeInSeconds - 1, endTime);
            int indexJustGreaterThanEqualTo = bsJustGreaterThanEqualTo(times, start);
            int indexJustLessThanEqualTo = bsJustLessThanEqualTo(times, end);

            if (indexJustGreaterThanEqualTo != -1 && indexJustLessThanEqualTo != -1) {
                ans.add(indexJustLessThanEqualTo - indexJustGreaterThanEqualTo + 1);
            } else {
                ans.add(0);
            }
            start = end + 1;
        }
        return ans;
    }

    private int bsJustLessThanEqualTo(List<Integer> times, int target) {
        int ans = -1;
        int lo = 0;
        int hi = times.size() - 1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (times.get(pivot) <= target) {
                ans = pivot;
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }

    private int bsJustGreaterThanEqualTo(List<Integer> times, int target) {
        int ans = -1;
        int lo = 0;
        int hi = times.size() - 1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (times.get(pivot) < target) {
                lo = pivot + 1;
            } else {
                ans = pivot;
                hi = pivot - 1;
            }
        }
        return ans;
    }
}

/**
 * Your TweetCounts object will be instantiated and called as such:
 * TweetCounts obj = new TweetCounts();
 * obj.recordTweet(tweetName,time);
 * List<Integer> param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);
 */

}
