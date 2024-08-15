/*
Given an integer array arr and a filtering function fn, return a filtered array filteredArr.

The fn function takes one or two arguments:

arr[i] - number from the arr
i - index of arr[i]
filteredArr should only contain the elements from the arr for which the expression fn(arr[i], i) evaluates to a truthy value. A truthy value is a value where Boolean(value) returns true.

Please solve it without the built-in Array.filter method.



Example 1:

Input: arr = [0,10,20,30], fn = function greaterThan10(n) { return n > 10; }
Output: [20,30]
Explanation:
const newArray = filter(arr, fn); // [20, 30]
The function filters out values that are not greater than 10
Example 2:

Input: arr = [1,2,3], fn = function firstIndex(n, i) { return i === 0; }
Output: [1]
Explanation:
fn can also accept the index of each element
In this case, the function removes elements not at index 0
Example 3:

Input: arr = [-2,-1,0,1,2], fn = function plusOne(n) { return n + 1 }
Output: [-2,0,1,2]
Explanation:
Falsey values such as 0 should be filtered out


Constraints:

0 <= arr.length <= 1000
-109 <= arr[i] <= 109

EDITORIAL

Overview
This question asks you to write a function that filters elements from an array based on the output of a callback function. Alongside map and reduce, it is one of the most commonly used and important functions in JavaScript.

It is recommended you first read the editorial for map as that editorial includes a discussion on callbacks not included here.

Truthy and Falsy
In this question, you are asked to remove all values from an array that aren't truthy (i.e. remove all falsy values). But what does that mean? JavaScript has true boolean values of true and false. But you are actually allowed to put any value inside an if statement. That value will be coerced into a boolean based on it's "truthiness".

All values are considered truthy except the following:

false
All forms of zero, meaning 0, -0 (output of 0/-1), and 0n (output of BigInt(0))
NaN ("Not a Number", one way to get it is with 0/0)
"" (empty string)
null
undefined
Why does this language feature exist?
The short answer is it can be convenient. Imagine you have a textfield which edits a variable userInput which is initially null.

Rather than writing:

if (userInput !== null && userInput !== "") {
  // uploadToDatabase(userInput)
}
You can shorten this to:

if (userInput) {
  // uploadToDatabase(userInput)
}
However, it is easy to not think carefully about your code and create bugs by not being explicit about what values are valid. For example, zero or an empty string might be completely valid inputs and the above code will result in a bug.

Truthiness and Logical Operators
It is not uncommon to see code like this in a JavaScript codebase:

const stringVal = textInput || "Default Value";
To an experienced JavaScript developer, this makes perfect sense. But developers from other backgrounds might find this very confusing. Why is a logical operator returning a string?

This is because, in JavaScript, logical operators don't return booleans; they return one of the two operands provided to them. At first this is confusing, but it is actually quite elegant and allows you to write very terse code.

The OR operator || returns the first value if the first value is truthy (without evaluating the 2nd value). Otherwise it returns the second value.
The AND operator && returns the first value if the first value is falsy (without evaluating the 2nd value). Otherwise it returns the 2nd value.
The Nullish Coalescing operator ?? is identical to || except it only treats null and undefined as falsy.
An easy way to remember this is by knowing the logical operator will return the last value it needed to evaluate. For example, OR is immediately true if the first value is true, thus it will return the first value iff it is truthy.

The reason this is elegant is because for true booleans, this algorithm actually works exactly as you would expect. Try it out for yourself! However you can also use them to write short code for non-boolean operations. And even if you don't use these operators for that purpose yourself, it's important to understand them for reading other's code.

A common use-case is for choosing the first truthy value from a list:

let val;
if (a) {
  val = a;
} else if (b) {
  val = b;
} else {
  val = c;
}
can be replaced with:

const val = a || b || c;
You could also conditionally execute some code:

if (a && b) {
  func();
}
can be replaced with:

a && b && func();
Built-in Array.filter
This question asks you to reimplement the Array.filter method, which is one of the most heavily used array methods in JavaScript. However there are four small differences between your implementation and the standard library.

Array.filter is a method on the Array prototype. This implementation is a function that accepts the array as the 1st argument.
The callback passed to Array.filter has a reference to the original array passed as the 3rd argument. This implementation's callback only accepts two arguments.
Array.filter optionally allows you pass a thisArg as the 2nd parameter. If provided, the passed callback will be bound to that context (assuming the callback isn't an arrow function as they can't be bound).
Array.filter handles sparse arrays. For example, if you write code let arr = Array(100); arr[1] = 10;, Array.filter will only look at index 1 and the empty indices will automatically be filtered out.
*/

/**
 * @param {number[]} arr
 * @param {Function} fn
 * @return {number[]}
 */
var filter = function(arr, fn) {
    return arr.filter(fn);
};


/**
 * @param {number[]} arr
 * @param {Function} fn
 * @return {number[]}
 */
var filter = function(arr, fn) {
    const result = [];

    for (const i in arr) {

        if (fn(arr[i], Number(i))) {
            result.push(arr[i]);
        }
    }
    return result;
};