

/*
Given an integer array arr and a mapping function fn, return a new array with a transformation applied to each element.

The returned array should be created such that returnedArray[i] = fn(arr[i], i).

Please solve it without the built-in Array.map method.



Example 1:

Input: arr = [1,2,3], fn = function plusone(n) { return n + 1; }
Output: [2,3,4]
Explanation:
const newArray = map(arr, plusone); // [2,3,4]
The function increases each value in the array by one.
Example 2:

Input: arr = [1,2,3], fn = function plusI(n, i) { return n + i; }
Output: [1,3,5]
Explanation: The function increases each value by the index it resides in.
Example 3:

Input: arr = [10,20,30], fn = function constant() { return 42; }
Output: [42,42,42]
Explanation: The function always returns 42.


Constraints:

0 <= arr.length <= 1000
-109 <= arr[i] <= 109
fn returns a number


EDITORIAL

Overview
This question is intended as an introduction to callbacks. A callback is defined as a function passed as an argument to another function. They are critical to understand as they are used frequently in almost any JavaScript codebase and are essential to writing reusable code.

Why Use Callbacks
The simple answer is they allow you to write code that can be reused across different use-cases.

Imagine you created an algorithm that sorts an array of numbers. Then you encounter a situation where you want to sort an array of people by their age. One option is to re-write the algorithm to handle arrays of people. However, a far better way is to have this function accept a callback that is expected to return a number. Then you can keep the core algorithm the same, and the user of the function simply passes person => person.age as the 2nd parameter.

Just about any generic algorithm can benefit from accepting callbacks. The standard JavaScript library and many 3rd party packages rely heavily on them. This particular question asks to reimplement the Array.map method, which is one of the most heavily used array methods in JavaScript. However, there are four small differences.

Array.map is a method on the Array prototype. This implementation accepts the array as the 1st argument.
The callback provided to Array.map passes a reference to the original array as the 3rd argument. This implementation's callback only accepts two arguments.
Array.map optionally allows you pass a thisArg as the 2nd parameter. If provided, the passed callback will be bound to that context (assuming the callback isn't an arrow function as they can't be bound).
Array.map is required to handle sparse arrays. For example, if you write code let arr = Array(100); arr[1] = 1;, Array.map will only look at index 1.
Performance Benchmarks
The following approaches include approximate benchmark results. You can test the results for yourself on this playground. Tests are done with a random array of 5 million integers and a callback that increments each number.
*/

/**
 * @param {number[]} arr
 * @param {Function} fn
 * @return {number[]}
 */
var map = function(arr, fn) {
    const result = []

    for (let i =0; i< arr.length; i++) {
        result[i] = fn(arr[i], i);
    }
    return result;
};


/**
 * @param {number[]} arr
 * @param {Function} fn
 * @return {number[]}
 */
var map = function(arr, fn) {
    const result = []

    for (const i in arr) {
        result[i] = fn(arr[i], Number(i));
    }
    return result;
};


/**
 * @param {number[]} arr
 * @param {Function} fn
 * @return {number[]}
 */
var map = function(arr, fn) {
    const result = []

    for (const i in arr) {
        result.push(fn(arr[i], Number(i)));
    }
    return result;
};