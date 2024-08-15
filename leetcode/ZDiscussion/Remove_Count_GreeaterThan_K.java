package leetcode.ZDiscussion;

import java.util.Deque;
import java.util.LinkedList;

public class Remove_Count_GreeaterThan_K {

    public static void main(String[] args) {
        String str = "aabbbaa";
        int k = 2;
       /*String str = "aabbbaac";
        int k = 2;
        */
        /*String str = "aabbbbaacde";
        int k = 3;*/
        /*String str = "caabbbbcccccaadffggglmn";
        int k = 3;*/
        System.out.println(getString(str, k));
    }

    public static String getString(String str, int k) {
        Deque<Integer> indexStack = new LinkedList<>();
        Deque<Integer> countStack = new LinkedList<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int count = 0;


            if (!indexStack.isEmpty() && str.charAt(indexStack.peek()) == ch) {
                count = countStack.peek();
            } else if(!indexStack.isEmpty()) {
                int tempCount = countStack.peek();
                checkOperstion(countStack, indexStack, str, k);

                if (!indexStack.isEmpty() && str.charAt(indexStack.peek()) == ch) {
                    count = countStack.peek();
                }
            }
            count++;
            countStack.push(count);
            indexStack.push(i);
        }
        checkOperstion(countStack, indexStack, str, k);
        StringBuilder ans = new StringBuilder();

        while (!indexStack.isEmpty()){
            ans.insert(0, str.charAt(indexStack.pop()));
        }
        return ans.toString();
    }

    private static void checkOperstion(Deque<Integer> countStack, Deque<Integer> indexStack,
                                       String str, int k) {
        int tempCount = countStack.peek();

        if (tempCount > k) {
            char tempChar = str.charAt(indexStack.peek());

            while(!indexStack.isEmpty() && tempChar == str.charAt(indexStack.peek())) {
                indexStack.pop();
                countStack.pop();
            }
        }
    }
}
