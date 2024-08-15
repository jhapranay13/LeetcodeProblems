/*
Given an array of functions [f1, f2, f3, ..., fn], return a new function fn that is the function composition of the array of functions.

The function composition of [f(x), g(x), h(x)] is fn(x) = f(g(h(x))).

The function composition of an empty list of functions is the identity function f(x) = x.

You may assume each function in the array accepts one integer as input and returns one integer as output.



Example 1:

Input: functions = [x => x + 1, x => x * x, x => 2 * x], x = 4
Output: 65
Explanation:
Evaluating from right to left ...
Starting with x = 4.
2 * (4) = 8
(8) * (8) = 64
(64) + 1 = 65
Example 2:

Input: functions = [x => 10 * x, x => 10 * x, x => 10 * x], x = 1
Output: 1000
Explanation:
Evaluating from right to left ...
10 * (1) = 10
10 * (10) = 100
10 * (100) = 1000
Example 3:

Input: functions = [], x = 42
Output: 42
Explanation:
The composition of zero functions is the identity function


Constraints:

-1000 <= x <= 1000
0 <= functions.length <= 1000
all functions accept and return a single integer

EDITORIAL

Overview
Function composition is a concept in functional programming where the output of one function is used as the input of another function. In other words, it's the process of chaining two or more functions together so that the result of one function becomes the input to the next.

For example, let's say we have two functions, f(x) and g(x):


The composition of these two functions, denoted as (f ∘ g)(x)*, means applying the function g(x) first, and then using the result of g(x) as the input to f(x). In this case, (f ∘ g)(x) would be:


So, when we compose the functions f(x) and g(x), the resulting function (f ∘ g)(x) takes an input x, multiplies it by 3 (using g(x)), and then adds 2 to the result (using f(x)).


So, when we compose the functions f(x) and g(x), the resulting function (f ∘ g)(x) takes an input x, multiplies it by 3 (using g(x)), and then adds 2 to the result (using f(x)).

In this problem, you are given an array of functions and asked to create a single function that represents the function composition of the given array of functions.

The challenge here is to create a new function that evaluates the composition of the given functions in the correct order, from right to left. This requires understanding how to chain functions together and pass the output of one function as the input to the next.

In cases where the array of functions is empty, the composed function should act as the identity function, i.e., f(x) = x. In other words, the function should return whatever was passed into it without any modifications.

*The notation (f ∘ g)(x) is used in mathematics to represent function composition. It is read as "f composed with g" or "f of g." The small circle (∘) between f and g is the composition operator. This notation is used to indicate that the function f is applied to the result of applying the function g to the input x. In other words, you first apply g(x) and then use the result as the input to f(x).

*/

/**
 * @param {Function[]} functions
 * @return {Function}
 */
var compose = function(functions) {
	return function(x) {
        let result = x;

        for (const func of functions.reverse()) {
            result = func(result);
        }
        return result;
    }
};

/**
 * const fn = compose([x => x + 1, x => 2 * x])
 * fn(4) // 9
 */

 /**
  * @param {Function[]} functions
  * @return {Function}
  */
 var compose = function(functions) {
 	return function(x) {
         let result = x;

         for (const func of functions.reverse()) {
             result = func.call(this, result);
         }
         return result;
     }
 };

 /**
  * const fn = compose([x => x + 1, x => 2 * x])
  * fn(4) // 9
  */

  /**
   * @param {Function[]} functions
   * @return {Function}
   */
  var compose = function(functions) {
  	return function(x) {
          let result = x;

          for (const func of functions.reverse()) {
              func.bind(this);
              result = func(result);
          }
          return result;
      }
  };

  /**
   * const fn = compose([x => x + 1, x => 2 * x])
   * fn(4) // 9
   */