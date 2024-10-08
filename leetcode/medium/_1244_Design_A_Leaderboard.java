package leetcode.medium;

import java.util.*;

/**
 *
 *
 * Design a Leaderboard class, which has 3 functions:
 *
 * addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
 * top(K): Return the score sum of the top K players.
 * reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.
 * Initially, the leaderboard is empty.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
 * [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
 * Output:
 * [null,null,null,null,null,null,73,null,null,null,141]
 *
 * Explanation:
 * Leaderboard leaderboard = new Leaderboard ();
 * leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
 * leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
 * leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
 * leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
 * leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.top(1);           // returns 73;
 * leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
 * leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
 * leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
 * leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 *
 *
 * Constraints:
 *
 * 1 <= playerId, K <= 10000
 * It's guaranteed that K is less than or equal to the current number of players.
 * 1 <= score <= 100
 * There will be at most 1000 function calls.
 *
 */

public class _1244_Design_A_Leaderboard {
    // PriorityQueue Approach
    class Leaderboard {
        Map<Integer, Integer> scoreMap;
        PriorityQueue<Integer> q;

        public Leaderboard() {
            this.scoreMap = new HashMap<>();
            this.q = new PriorityQueue<>();
        }

        public void addScore(int playerId, int score) {
            scoreMap.put(playerId, scoreMap.getOrDefault(playerId, 0) + score);
        }

        public int top(int K) {
            int sum = 0;


            for (int key : scoreMap.keySet()) {
                q.offer(scoreMap.get(key));

                if (q.size() > K) {
                    q.poll();
                }
            }

            while (!q.isEmpty()) {
                sum += q.poll();
            }
            return sum;
        }

        public void reset(int playerId) {
            scoreMap.remove(playerId);
        }
    }

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 *
 */
    //=============================================================================================
    // TreeMap approach
class Leaderboard1 {
    TreeMap<Integer, Integer> scoreCount;
    Map<Integer, Integer> playerScore;

    public Leaderboard1() {
        this.scoreCount = new TreeMap<>(Collections.reverseOrder());
        this.playerScore = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        int currScore = playerScore.getOrDefault(playerId, 0);

        if (scoreCount.containsKey(currScore)) {

            if (scoreCount.get(currScore) == 1) {
                scoreCount.remove(currScore);
            } else {
                scoreCount.put(currScore, scoreCount.get(currScore) - 1);
            }
        }
        currScore += score;
        playerScore.put(playerId, currScore);
        scoreCount.put(currScore, scoreCount.getOrDefault(currScore, 0) + 1);
    }

    public int top(int K) {
        int count = 0;
        int sum = 0;

        outer:
        for (int key : scoreCount.keySet()) {
            int cnt = scoreCount.get(key);

            while (cnt-- > 0) {
                sum += key;
                count++;

                if (count == K) {
                    break outer;
                }
            }
        }
        return sum;
    }

    public void reset(int playerId) {
        TreeMap<Integer, Integer> temp1 = scoreCount;
        Map<Integer, Integer> temp2 = playerScore;

        int score = playerScore.remove(playerId);

        if (scoreCount.get(score) == 1) {
            scoreCount.remove(score);
        } else {
            scoreCount.put(score, scoreCount.get(score) - 1);
        }
    }
}

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard obj = new Leaderboard();
 * obj.addScore(playerId,score);
 * int param_2 = obj.top(K);
 * obj.reset(playerId);
 */
}
