package leetcode.medium;

import java.util.*;

/**
 *
 * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
 *
 * You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.
 *
 * Return a list of all the recipes that you can create. You may return the answer in any order.
 *
 * Note that two recipes may contain each other in their ingredients.
 *
 *
 *
 * Example 1:
 *
 * Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
 * Output: ["bread"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * Example 2:
 *
 * Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 * Example 3:
 *
 * Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich","burger"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 * We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
 *
 *
 * Constraints:
 *
 * n == recipes.length == ingredients.length
 * 1 <= n <= 100
 * 1 <= ingredients[i].length, supplies.length <= 100
 * 1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
 * recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
 * All the values of recipes and supplies combined are unique.
 * Each ingredients[i] does not contain any duplicate values.
 *
 */

public class _2115_FindAllPossibleRecipesFromGivenSupplies {
    //=============================================================================================
    //Topological Sort Kahns Algorithm
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Set<String>> ingredientRecipeMap = new HashMap<>();
        Map<String, Integer> incoming = new HashMap<>();
        Set<String> recipeSet = new HashSet<>();
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            List<String> ingredientList = ingredients.get(i);

            for (String ingredient : ingredientList) {
                Set<String> set = ingredientRecipeMap.getOrDefault(ingredient, new HashSet<>());
                set.add(recipe);
                ingredientRecipeMap.put(ingredient, set);
            }
            incoming.put(recipe, ingredientList.size());//Number of incoming will be equal to the size of ingredientList
            recipeSet.add(recipe);
        }
        Deque<String> q = new LinkedList<>();
        //Adding all the values for suppiles in the q which are not in the incoming map because if the
        //entry is in the incoming map they have dependencies and cannot be created just yet.
        //Also checking if the supply is listed in the ingredient map if not we will never use it.

        for (String supply : supplies) {

            if (ingredientRecipeMap.containsKey(supply)) {
                q.offer(supply);
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                String type = q.poll();

                if (recipeSet.contains(type)) {
                    ans.add(type);
                }
                Set<String> nextTypeSet = ingredientRecipeMap.getOrDefault(type, new HashSet<>());

                for (String next : nextTypeSet) {

                    if (incoming.get(next) == 1) {
                        incoming.remove(next);
                        q.offer(next);
                    } else {
                        incoming.put(next, incoming.get(next) - 1);
                    }
                }
            }
        }
        return ans;
    }
}
