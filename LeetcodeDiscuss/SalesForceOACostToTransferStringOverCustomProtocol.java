package LeetcodeDiscuss;

/**
 *
 * A string must be transferred over network using custom protocol
 *
 * Protocol Details:-
 *
 * 1. Each pair of characters in the string is processed, forming string pairs like "ab", "bc"
 * and "ac" for the string "abc".
 * 2. If a pair contains the matching characters, "aa" it will require additional time.
 * 3. String can be split into substrings, and each substring can be processed independently before transfer.
 *
 *
 * Calculate min total extra time to transfer the string which is the sum of:-
 *
 * 1. The time taken to process each pair of characters with same chars in the string.
 * 2. The time taken to partition the string.
 *
 * Example s = "abcabcc"
 * sameTime = 1
 * partitionTime = 4
 *
 * Two optiomal ways to partition the string are:
 *
 * 1. abc | abcc  total extra time = extraTime(abc) + extraTime(abcc) + partitionTime
 * Extra time abc == 0  extra time abcc == 1  coz pair "cc" has same characters. partitionTime = 4
 * So 5
 *
 * 2. abcabcc total extra time = extraTime(abcabcc) + partitionTime
 * Extra time abcabcc == 5 coz pair "ccc" has 3 pairs aa and bb are another pairs.
 * 3 + 1 + 1 = 5
 *
 */

public class SalesForceOACostToTransferStringOverCustomProtocol {

    public static void main(String[] args) {
        // Example from problem description
        String s1 = "abcabcc";
        int sameTime1 = 1;
        int partitionTime1 = 4;
        System.out.println("Min extra time for \"" + s1 + "\": " + minTotalExtraTime(s1, sameTime1, partitionTime1)); // Expected: 5

        String s2 = "aaaa";
        int sameTime2 = 1;
        int partitionTime2 = 10;
        System.out.println("Min extra time for \"" + s2 + "\": " + minTotalExtraTime(s2, sameTime2, partitionTime2)); // Expected: 6

        String s3 = "ababa";
        int sameTime3 = 5;
        int partitionTime3 = 1;
        System.out.println("Min extra time for \"" + s3 + "\": " + minTotalExtraTime(s3, sameTime3, partitionTime3)); // Expected: 6

        String s4 = "xyz";
        int sameTime4 = 100;
        int partitionTime4 = 1;
        System.out.println("Min extra time for \"" + s4 + "\": " + minTotalExtraTime(s4, sameTime4, partitionTime4)); // Expected: 0

        String s5 = "";
        int sameTime5 = 10;
        int partitionTime5 = 5;
        System.out.println("Min extra time for \"" + s5 + "\": " + minTotalExtraTime(s5, sameTime5, partitionTime5)); // Expected: 0

        String s6 = "zzzaac"; // z:3, a:2, c:1
        // (z:3) C(3,2)=3. (a:2) C(2,2)=1. Total 4 pairs. Cost if one piece = 4 * sameTime
        int sameTime6 = 2;
        int partitionTime6 = 3;
        // zzzaac: (z:3, a:2) -> (3+1)*2 = 8
        // zzz | aac: (z:3) C(3,2)=3. Cost = 3*2=6. (a:2) C(2,2)=1. Cost = 1*2=2.
        // Total = 6 (for zzz) + 2 (for aac) + 3 (partition) = 11.
        // zzz | a | ac : (z:3) cost 6. (a:1) cost 0. (ac) cost 0. 6+0+0+3+3 = 12.
        // zzz | aa | c : (z:3) cost 6. (aa) cost 2. (c) cost 0. 6+2+0+3+3 = 14.

        // Let's trace dp[6] for "zzzaac", sameTime=2, partitionTime=3
        // dp[0] = 0

        // i=1 (z)
        //   j=0: s[0]=z. charCounts={z:1}. currentSubSameTimeCost=0. costForThisSub=0. dp[1] = min(MAX_VALUE, 0) = 0.
        // dp = [0, 0, M, M, M, M, M]

        // i=2 (zz)
        //   j=1: s[1]=z. charCounts={z:1}. currentSubSameTimeCost=0. costForThisSub=0. dp[2] = min(MAX_VALUE, dp[1]+0+3) = 3.
        //   j=0: s[0]=z. charCounts={z:2}. currentSubSameTimeCost=1. costForThisSub=2. dp[2] = min(3, 2) = 2.
        // dp = [0, 0, 2, M, M, M, M] (Cost of "zz" as one part is 2)

        // i=3 (zzz)
        //   j=2: s[2]=z. charCounts={z:1}. currentSubSameTimeCost=0. costForThisSub=0. dp[3] = min(MAX_VALUE, dp[2]+0+3) = 2+0+3=5.
        //   j=1: s[1]=z. charCounts={z:2}. currentSubSameTimeCost=1. costForThisSub=2. dp[3] = min(5, dp[1]+2+3) = min(5, 0+2+3) = 5.
        //   j=0: s[0]=z. charCounts={z:3}. currentSubSameTimeCost=3. costForThisSub=6. dp[3] = min(5, 6) = 5.
        // dp = [0, 0, 2, 5, M, M, M] (Cost of "zzz" as "zz|z" is 2+0+3=5)

        // i=4 (zzza)
        //   j=3: s[3]=a. charCounts={a:1}. currentSubSameTimeCost=0. costForThisSub=0. dp[4] = min(MAX_VALUE, dp[3]+0+3) = 5+0+3=8.
        //   j=2: s[2]=z, s[3]=a. charCounts={z:1,a:1}. currentSubSameTimeCost=0. costForThisSub=0. dp[4] = min(8, dp[2]+0+3) = 2+0+3=5.
        //   j=1: s[1]=z, s[2]=z, s[3]=a. charCounts={z:2,a:1}. currentSubSameTimeCost=1. costForThisSub=2. dp[4] = min(5, dp[1]+2+3) = 0+2+3=5.
        //   j=0: s[0]=z,s[1]=z,s[2]=z,s[3]=a. charCounts={z:3,a:1}. currentSubSameTimeCost=3. costForThisSub=6. dp[4] = min(5, 6) = 5.
        // dp = [0, 0, 2, 5, 5, M, M] (Cost of "zzza" as "zz|za" is 2+0+3=5)

        // i=5 (zzzaa)
        //   j=4: s[4]=a. charCounts={a:1}. currentSubSameTimeCost=0. costForThisSub=0. dp[5] = min(MAX_VALUE, dp[4]+0+3) = 5+0+3=8.
        //   j=3: s[3]=a,s[4]=a. charCounts={a:2}. currentSubSameTimeCost=1. costForThisSub=2. dp[5] = min(8, dp[3]+2+3) = 5+2+3=10.
        //   j=2: s[2]=z,s[3]=a,s[4]=a. charCounts={z:1,a:2}. currentSubSameTimeCost=1. costForThisSub=2. dp[5] = min(8, dp[2]+2+3) = 2+2+3=7.
        //   j=1: s[1]=z,s[2]=z,s[3]=a,s[4]=a. charCounts={z:2,a:2}. currentSubSameTimeCost=1+1=2. costForThisSub=4. dp[5] = min(7, dp[1]+4+3) = 0+4+3=7.
        //   j=0: s[0]=z,s[1]=z,s[2]=z,s[3]=a,s[4]=a. charCounts={z:3,a:2}. currentSubSameTimeCost=3+1=4. costForThisSub=8. dp[5] = min(7, 8) = 7.
        // dp = [0, 0, 2, 5, 5, 7, M] (Cost of "zzzaa" as "zz|zaa" is 2+2+3=7)

        // i=6 (zzzaac)
        //   j=5: s[5]=c. charCounts={c:1}. currentSubSameTimeCost=0. costForThisSub=0. dp[6] = min(MAX_VALUE, dp[5]+0+3) = 7+0+3=10.
        //   j=4: s[4]=a,s[5]=c. charCounts={a:1,c:1}. currentSubSameTimeCost=0. costForThisSub=0. dp[6] = min(10, dp[4]+0+3) = 5+0+3=8.
        //   j=3: s[3]=a,s[4]=a,s[5]=c. charCounts={a:2,c:1}. currentSubSameTimeCost=1. costForThisSub=2. dp[6] = min(8, dp[3]+2+3) = 5+2+3=10.
        //   j=2: s[2]=z,s[3]=a,s[4]=a,s[5]=c. charCounts={z:1,a:2,c:1}. currentSubSameTimeCost=1. costForThisSub=2. dp[6] = min(8, dp[2]+2+3) = 2+2+3=7.
        //   j=1: s[1]=z,s[2]=z,s[3]=a,s[4]=a,s[5]=c. charCounts={z:2,a:2,c:1}. currentSubSameTimeCost=1+1=2. costForThisSub=4. dp[6] = min(7, dp[1]+4+3) = 0+4+3=7.
        //   j=0: s[0]=z...s[5]=c. charCounts={z:3,a:2,c:1}. currentSubSameTimeCost=3+1=4. costForThisSub=8. dp[6] = min(7, 8) = 7.
        // dp = [0, 0, 2, 5, 5, 7, 7]
        System.out.println("Min extra time for \"" + s6 + "\": " + minTotalExtraTime(s6, sameTime6, partitionTime6)); // Expected: 7 (from "z|zzaac" or "zz|zaac" or "zzz|aac" - 7 seems correct)

        System.out.println(minTotalExtraTime("aababaabababbbaaaaabaabbababaaaaabaaaabbaaaaabababbbbbbaabbabbaabaabbabababaabaaaaabbbbbabbbbbabbbbbaaaabbbbbbababbaaaaaabbbaaabbbaababaabaaaaaabababaaabaaaabbaababababbbbaabaaaabbaabababbabbbabbabbbbbabbbaabbbabbbabaaabbaabbbbaabaaaaababbaaaababaabbbabbbabbbaaabaaabbbbaaaababbbabbbaabbbbbaabaaaaaaaabbbabbaabaaaaabbaaaabaabbabbababbababbaabbabbbababbabbabab",
                362, 874));
    }

    private static int minTotalExtraTime(String str, int sameTime, int partitionTime) {
        int[][] memo = new int[str.length()][str.length()];
        return recur(str, sameTime, partitionTime, 0, str.length() - 1, memo);
    }

    private static int recur(String str, int sameTime, int partitionTime, int lo, int hi, int[][] memo) {

        if (hi - lo < 2) {
            return 0; // No characters to process
        }

        if(memo[lo][hi] != 0) {
            return memo[lo][hi]; // Return cached result
        }
        int ans = 0;
        int frequency[] = new int[26]; // Assuming lowercase letters a-z

        for (int i = lo; i <= hi; i++) {
            char ch = str.charAt(i);
            frequency[ch - 'a']++;
        }
        int sameTimeCost = 0;

        for (int freq : frequency) {

            if (freq > 0) {
                sameTimeCost += (freq * (freq - 1) / 2) * sameTime; // Cost for same characters
            }
        }
        int minPartitionTime = Integer.MAX_VALUE;// Cost for partitioning

        for (int i = lo; i < hi; i++) {
            int left = recur(str, sameTime, partitionTime, lo, i, memo);
            int right = recur(str, sameTime, partitionTime, i + 1, hi, memo);
            int partitionTimeCost = left + right + partitionTime;
            minPartitionTime = Math.min(minPartitionTime, partitionTimeCost);
        }
        ans = Math.min(sameTimeCost, minPartitionTime);
        return memo[lo][hi] = ans;
    }
}
