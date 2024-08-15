package leetcode.DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * In a project, you have a list of required skills req_skills, and a list of people. The ith person people[i] contains a list of skills that the person has.
 *
 * Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill. We can represent these teams by the index of each person.
 *
 * For example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
 * Return any sufficient team of the smallest possible size, represented by the index of each person. You may return the answer in any order.
 *
 * It is guaranteed an answer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
 * Output: [0,2]
 * Example 2:
 *
 * Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * 1 <= req_skills.length <= 16
 * 1 <= req_skills[i].length <= 16
 * req_skills[i] consists of lowercase English letters.
 * All the strings of req_skills are unique.
 * 1 <= people.length <= 60
 * 0 <= people[i].length <= 16
 * 1 <= people[i][j].length <= 16
 * people[i][j] consists of lowercase English letters.
 * All the strings of people[i] are unique.
 * Every skill in people[i] is a skill in req_skills.
 * It is guaranteed a sufficient team exists.
 *
 */

public class _1125_Smallest_Sufficient_Team {
    // DP Top Down
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> skillIndexMap = new HashMap<>();

        for (int i = 0; i < req_skills.length; i++) {
            skillIndexMap.put(req_skills[i], i);
        }
        int[] peopleSkillBitMask = new int[people.size()];

        for (int i = 0; i < people.size(); i++) {
            List<String> personSkill = people.get(i);
            int mask = 0;

            for (String skill : personSkill){
                mask |= 1 << skillIndexMap.get(skill); //setting bit at that index indicating skills that person has
            }
            peopleSkillBitMask[i] = mask;
        }
        int endState = (1 << req_skills.length) - 1; //setting all bits indicating all skills
        Map<String, Long> memo = new HashMap<>();
        long state = recur(peopleSkillBitMask, endState ,0, 0, memo);
        List<Integer> holder = new ArrayList<>();
        int index = 0;
        System.out.println(Long.toBinaryString(state));

        while (state > 0) {
            long ans = state & 1;

            if (ans == 1) {
                holder.add(index);
            }
            index++;
            state >>= 1;
        }
        int[] ans = new int[holder.size()];

        for (int i = 0; i < holder.size(); i++) {
            ans[i] = holder.get(i);
        }
        return ans;
    }

    private long recur(int[] peopleSkillBitMask, int endState, int index, int mask, Map<String, Long> memo) {

        if (mask == endState) {
            return 0;
        }

        if (index == peopleSkillBitMask.length) {
            return Integer.MAX_VALUE;
        }
        String key = index + "|" + mask;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        long ans = 0;
        //Setting the bit to signify including of skills
        long include = recur(peopleSkillBitMask, endState, index + 1, mask | peopleSkillBitMask[index], memo);
        long exclude = recur(peopleSkillBitMask, endState, index + 1, mask, memo);
        // Setting the bit as indicator that it has been included or excluded
        // Using Long as 1 <= people.length <= 60
        if (Long.bitCount(include) < Long.bitCount(exclude)) {
            ans =  include | (1L << index);
        } else {
            ans = exclude;
        }
        memo.put(key, ans);
        return ans;
    }
    //=============================================================================================
    // Backtracking Accepted
    public int[] smallestSufficientTeam1(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> skillIndexMap = new HashMap<>();

        for (int i = 0; i < req_skills.length; i++) {
            skillIndexMap.put(req_skills[i], i);
        }
        int[] peopleSkillBitMask = new int[people.size()];

        for (int i = 0; i < people.size(); i++) {
            List<String> personSkill = people.get(i);
            int mask = 0;

            for (String skill : personSkill){
                mask |= 1 << skillIndexMap.get(skill); //setting bit at that index;
            }
            peopleSkillBitMask[i] = mask;
        }
        int endState = (1 << req_skills.length) - 1; //setting all bits
        recur1(peopleSkillBitMask, new ArrayList<>(), endState ,0, 0);
        int[] ans = new int[res.size()];

        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
    private List<Integer> res = new ArrayList<>();

    private void recur1(int[] peopleSkillBitMask, List<Integer> holder,
                       int endState, int index, int mask) {

        if (mask == endState) {

            if (res.size() == 0 || res.size() > holder.size()) {
                res = new ArrayList<>(holder);
            }
            return;
        }

        if (index == peopleSkillBitMask.length) {
            return;
        }

        if (res.size() != 0 && holder.size() >= res.size()) {
            return;
        }

        for (int i = index; i < peopleSkillBitMask.length; i++) {
            int nextMask = mask | peopleSkillBitMask[i];

            if (nextMask == mask) {
                continue;
            }
            holder.add(i);
            recur1(peopleSkillBitMask, holder, endState, i + 1, nextMask);
            holder.remove(holder.size() - 1);
        }
    }
    //=============================================================================================
    // Backtracking TLE
    public int[] smallestSufficientTeam2(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> skillIndexMap = new HashMap<>();

        for (int i = 0; i < req_skills.length; i++) {
            skillIndexMap.put(req_skills[i], i);
        }
        int[] peopleSkillBitMask = new int[people.size()];

        for (int i = 0; i < people.size(); i++) {
            List<String> personSkill = people.get(i);
            int mask = 0;

            for (String skill : personSkill){
                mask |= 1 << skillIndexMap.get(skill); //setting bit at that index;
            }
            peopleSkillBitMask[i] = mask;
        }
        int endState = (1 << req_skills.length) - 1; //setting all bits
        recur(peopleSkillBitMask, new ArrayList<>(), endState ,0, 0);
        int[] ans = new int[res.size()];

        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
   // private List<Integer> res = new ArrayList<>();

    private void recur(int[] peopleSkillBitMask, List<Integer> holder,
                       int endState, int index, int mask) {

        if (mask == endState) {

            if (res.size() == 0 || res.size() > holder.size()) {
                res = new ArrayList<>(holder);
            }
            return;
        }

        if (index == peopleSkillBitMask.length) {
            return;
        }

        if (res.size() != 0 && holder.size() >= res.size()) {
            return;
        }
        holder.add(index);
        int include = mask | peopleSkillBitMask[index];
        recur(peopleSkillBitMask, holder, endState, index + 1, include);
        holder.remove(holder.size() - 1);
        //excluding
        recur(peopleSkillBitMask, holder, endState, index + 1, mask);
    }
}
