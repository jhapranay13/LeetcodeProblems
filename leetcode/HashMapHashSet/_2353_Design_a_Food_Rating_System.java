package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 *
 * Design a food rating system that can do the following:
 *
 * Modify the rating of a food item listed in the system.
 * Return the highest-rated food item for a type of cuisine in the system.
 * Implement the FoodRatings class:
 *
 * FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system. The food items are described by foods, cuisines and ratings, all of which have a length of n.
 * foods[i] is the name of the ith food,
 * cuisines[i] is the type of cuisine of the ith food, and
 * ratings[i] is the initial rating of the ith food.
 * void changeRating(String food, int newRating) Changes the rating of the food item with the name food.
 * String highestRated(String cuisine) Returns the name of the food item that has the highest rating for the given type of cuisine. If there is a tie, return the item with the lexicographically smaller name.
 * Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["FoodRatings", "highestRated", "highestRated", "changeRating", "highestRated", "changeRating", "highestRated"]
 * [[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16], ["japanese"]]
 * Output
 * [null, "kimchi", "ramen", null, "sushi", null, "ramen"]
 *
 * Explanation
 * FoodRatings foodRatings = new FoodRatings(["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]);
 * foodRatings.highestRated("korean"); // return "kimchi"
 *                                     // "kimchi" is the highest rated korean food with a rating of 9.
 * foodRatings.highestRated("japanese"); // return "ramen"
 *                                       // "ramen" is the highest rated japanese food with a rating of 14.
 * foodRatings.changeRating("sushi", 16); // "sushi" now has a rating of 16.
 * foodRatings.highestRated("japanese"); // return "sushi"
 *                                       // "sushi" is the highest rated japanese food with a rating of 16.
 * foodRatings.changeRating("ramen", 16); // "ramen" now has a rating of 16.
 * foodRatings.highestRated("japanese"); // return "ramen"
 *                                       // Both "sushi" and "ramen" have a rating of 16.
 *                                       // However, "ramen" is lexicographically smaller than "sushi".
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 104
 * n == foods.length == cuisines.length == ratings.length
 * 1 <= foods[i].length, cuisines[i].length <= 10
 * foods[i], cuisines[i] consist of lowercase English letters.
 * 1 <= ratings[i] <= 108
 * All the strings in foods are distinct.
 * food will be the name of a food item in the system across all calls to changeRating.
 * cuisine will be a type of cuisine of at least one food item in the system across all calls to highestRated.
 * At most 2 * 104 calls in total will be made to changeRating and highestRated.
 *
 */

public class _2353_Design_a_Food_Rating_System {
    class FoodRatings {

        class Food implements Comparable<Food> {
            String name;
            int rating;

            public Food(String name, int rating) {
                this.name = name;
                this.rating = rating;
            }

            public int compareTo(Food food) {

                if (this.rating == food.rating) {
                    return this.name.compareTo(food.name);
                }
                return food.rating - this.rating;
            }
        }
        Map<String, PriorityQueue<Food>> cusineQueue = new HashMap<>();
        Map<String, Integer> foodRating = new HashMap<>();
        Map<String, String> foodCusine = new HashMap<>();

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {

            for (int i = 0; i < foods.length; i++) {
                String food = foods[i];
                String cusine = cuisines[i];
                int rating = ratings[i];
                Food obj = new Food(food, rating);
                foodRating.put(food, rating);
                PriorityQueue<Food> pq =
                        cusineQueue.getOrDefault(cusine, new PriorityQueue<>());
                pq.offer(obj);
                cusineQueue.put(cusine, pq);
                foodCusine.put(food, cusine);
            }
        }

        public void changeRating(String food, int newRating) {
            foodRating.put(food, newRating);
            String cusine = foodCusine.get(food);
            PriorityQueue<Food> pq = cusineQueue.get(cusine);
            pq.offer(new Food(food, newRating));
        }

        public String highestRated(String cuisine) {
            PriorityQueue<Food> pq = cusineQueue.get(cuisine);

            while(pq.peek().rating != foodRating.get(pq.peek().name)) {
                pq.poll();
            }
            return pq.peek().name;
        }
    }
    //=============================================================================================
    // TreeSet Solution

    class FoodRatings1 {

        class Food implements Comparable<Food> {
            String name;
            int rating;

            public Food(String name, int rating) {
                this.name = name;
                this.rating = rating;
            }

            public int compareTo(Food food) {

                if (this.rating == food.rating) {
                    return this.name.compareTo(food.name);
                }
                return food.rating - this.rating;
            }

            public boolean equals(Food food) {
                return this.name == food.name && this.rating == food.rating;
            }
        }
        Map<String, TreeSet<Food>> cusineQueue = new HashMap<>();
        Map<String, Integer> foodRating = new HashMap<>();
        Map<String, String> foodCusine = new HashMap<>();

        public FoodRatings1(String[] foods, String[] cuisines, int[] ratings) {

            for (int i = 0; i < foods.length; i++) {
                String food = foods[i];
                String cusine = cuisines[i];
                int rating = ratings[i];
                Food obj = new Food(food, rating);
                foodRating.put(food, rating);
                TreeSet<Food> set =
                        cusineQueue.getOrDefault(cusine, new TreeSet<Food>());
                set.add(obj);
                cusineQueue.put(cusine, set);
                foodCusine.put(food, cusine);
            }
        }

        public void changeRating(String food, int newRating) {
            int oldRating = foodRating.get(food);
            foodRating.put(food, newRating);
            String cusine = foodCusine.get(food);
            TreeSet<Food> set = cusineQueue.get(cusine);
            set.remove(new Food(food, oldRating));
            set.add(new Food(food, newRating));
        }

        public String highestRated(String cuisine) {
            TreeSet<Food> set = cusineQueue.get(cuisine);

            return set.first().name;
        }
    }

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
}
