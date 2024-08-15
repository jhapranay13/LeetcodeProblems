/*
Given a function fn, return a curried version of that function.

A curried function is a function that accepts fewer or an equal number of parameters as the original function and returns either another curried function or the same value the original function would have returned.

In practical terms, if you called the original function like sum(1,2,3), you would call the curried version like csum(1)(2)(3), csum(1)(2,3), csum(1,2)(3), or csum(1,2,3). All these methods of calling the curried function should return the same value as the original.



Example 1:

Input:
fn = function sum(a, b, c) { return a + b + c; }
inputs = [[1],[2],[3]]
Output: 6
Explanation:
The code being executed is:
const curriedSum = curry(fn);
curriedSum(1)(2)(3) === 6;
curriedSum(1)(2)(3) should return the same value as sum(1, 2, 3).
Example 2:

Input:
fn = function sum(a, b, c) { return a + b + c; }
inputs = [[1,2],[3]]
Output: 6
Explanation:
curriedSum(1, 2)(3) should return the same value as sum(1, 2, 3).
Example 3:

Input:
fn = function sum(a, b, c) { return a + b + c; }
inputs = [[],[],[1,2,3]]
Output: 6
Explanation:
You should be able to pass the parameters in any way, including all at once or none at all.
curriedSum()()(1, 2, 3) should return the same value as sum(1, 2, 3).
Example 4:

Input:
fn = function life() { return 42; }
inputs = [[]]
Output: 42
Explanation:
currying a function that accepts zero parameters should effectively do nothing.
curriedLife() === 42


Constraints:

1 <= inputs.length <= 1000
0 <= inputs[i][j] <= 105
0 <= fn.length <= 1000
inputs.flat().length == fn.length
function parameters explicitly defined
If fn.length > 0 then the last array in inputs is not empty
If fn.length === 0 then inputs.length === 1

EDITORIAL

Overview
Currying is a powerful technique in functional programming that transforms a function with multiple arguments into a sequence of functions. It allows you to create flexible and reusable code by enabling partial application of function arguments. In this article, we will discuss the concept and implementation of currying in JavaScript.

Example:

Suppose we have a function sum that takes three arguments and returns their sum:


We can create a curried version of this function, curriedSum. Now, we can call curriedSum in various ways, all of which should return the same result as the original sum function:


Currying in JavaScript has several practical applications that can help improve code readability, maintainability, and reusability. Here are some practical use cases of currying:

Reusable utility functions: Currying can help create reusable utility functions that can be easily customized for specific use cases.
Currying allows you to create a function that returns another function with a partially applied argument. In this case, we have a curried add function that takes two arguments, a and b. When you call add with a single argument, it returns a new function that takes the second argument b and adds it to the initially provided a.

Here's the example with more explanation:


Event handling: In event-driven programming, currying can be used to create event handlers with specific configurations, while keeping the core event handling function generic.


Customizing API calls: Currying can help create more specific API calls based on a generic API call function.


Higher-order functions and functional composition: Currying enables the creation of higher-order functions that can be composed to create more complex functionality.


Currying is a valuable concept in functional programming that enables you to write more flexible and reusable code. Mastering currying will help you create cleaner and more efficient solutions for a wide range of programming problems.

*/

/**
 * @param {Function} fn
 * @return {Function}
 */
var curry = function(fn) {

    return function curried(...args) {

        if (args.length >= fn.length) {
            return fn.apply(this, args);
        }
        return curried.bind(this, ...args);
    }
};

/**
 * function sum(a, b) { return a + b; }
 * const csum = curry(sum);
 * csum(1)(2) // 3
 */

 /**
  * @param {Function} fn
  * @return {Function}
  */
 var curry = function(fn) {

     return function curried(...args) {
         if(args.length >= fn.length) {
             return fn(...args);
         }

         return (...nextArgs) => curried(...args, ...nextArgs);
     };
 };

 /**
  * function sum(a, b) { return a + b; }
  * const csum = curry(sum);
  * csum(1)(2) // 3
  */