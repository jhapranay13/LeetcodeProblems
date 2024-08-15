package leetcode.MeetInTheMiddle;

import java.util.*;

public class _2035_Partition_Array_Into_Two_Arrays_to_Minimize_Sum_Difference {

    //TLE
    public int minimumDifference(int[] nums) {
        int n = nums.length / 2;
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }
        Map<String, Integer> memo = new HashMap<>();
        return recur(nums, n, sum, 0, 0, 0, memo);
    }

    private int recur(int[] nums, int n, int sum, int index, int count, int currSum,
                      Map<String, Integer> memo) {

        if (n == count) {
            int sumOfOtherPart = sum - currSum;
            return Math.abs(sumOfOtherPart - currSum);
        }

        if (index == nums.length) {
            return Integer.MAX_VALUE;
        }
        String key = index + "|" + count + "|" + currSum;

        if(memo.containsKey(key)) {
            return memo.get(key);
        }
        int choose = recur(nums, n, sum, index + 1, count + 1, currSum + nums[index], memo);
        int dontChoose = recur(nums, n, sum, index + 1, count, currSum, memo);
        int ans = Math.min(choose, dontChoose);
        memo.put(key, ans);
        return ans;
    }
    //=============================================================================================
    //Need more understanding on Meet in middle technique
    public int minimumDifference1(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int n = nums.length/2;

        Map<Integer, List<Integer>> mp1 = new HashMap();
        Map<Integer, List<Integer>> mp2 = new HashMap();

        for(int i=0;i<(1<<n);i++){
            int setBits = 0, sum=0;
            for(int j=0;j<n;j++){
                if((i & (1<<j))!=0){
                    setBits++;
                    sum+= nums[j];
                }
            }
            if(!mp1.containsKey(setBits)){
                mp1.put(setBits,new ArrayList());
            }
            mp1.get(setBits).add(sum);

        }

        for(int i=0;i<(1<<n);i++){
            int setBits = 0, sum=0;
            for(int j=0;j<n;j++){
                if((i & (1<<j))!=0){
                    setBits++;
                    sum+= nums[n+j];
                }
            }
            if(!mp2.containsKey(setBits)){
                mp2.put(setBits,new ArrayList());
            }
            mp2.get(setBits).add(sum);

        }

        for(int i=0;i<=n;i++){
            if(mp1.containsKey(i)){
                Collections.sort(mp1.get(i));
            }

            if(mp2.containsKey(i)){
                Collections.sort(mp2.get(i));
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i=0;i<=n;i++){
            int a = 0, b=mp2.get(n-i).size()-1;
            while(a < mp1.get(i).size() && b>=0){
                int sum = mp1.get(i).get(a) + mp2.get(n-i).get(b);
                int diff = Math.abs(total - 2*sum);
                ans = Math.min(ans,diff);
                if(2*sum > total )b--;
                else a++;
            }


        }
        return ans;
    }

}
