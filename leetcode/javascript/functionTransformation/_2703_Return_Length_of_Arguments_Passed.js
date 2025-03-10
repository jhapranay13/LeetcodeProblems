/*
Write a function argumentsLength that returns the count of arguments passed to it.


Example 1:

Input: args = [5]
Output: 1
Explanation:
argumentsLength(5); // 1

One value was passed to the function so it should return 1.
Example 2:

Input: args = [{}, null, "3"]
Output: 3
Explanation:
argumentsLength({}, null, "3"); // 3

Three values were passed to the function so it should return 3.


Constraints:

args is a valid JSON array
0 <= args.length <= 100

EDITORIAL
Overview:
We need to count the number of arguments passed to function argumentsLength. Here the arguments are passsed in the form of rest parameters.

Rest Parameter:
In JavaScript, the rest parameter is a feature that allows a function to accept an indefinite number of arguments. It is denoted by three dots (...) followed by a parameter name. The rest parameter collects all the remaining arguments passed to a function into an array, even if the number of arguments is not known in advance. If no additional arguments are passed, the rest parameter will be an empty array.

Syntax:
function functionName(...args) {
  // Function body
}
The rest parameter (args in the above example) can have any valid identifier name, but it is conventionally named args or rest to indicate its purpose.
The rest parameter is placed as the last parameter in the function's parameter list.
You can have other parameters before the rest parameter, but the rest parameter must be the last one.

Accessing Arguments:
Inside the function, you can treat the rest parameter (args) as an ordinary array.
You can access the elements of the array using array indexing or array methods like forEach(), map(), filter(), etc.

Example:

function sum(...args) {
  let total = 0;
  for (let number of args) {
    total += number;
  }
  return total;
}

console.log(sum(1, 2, 3, 4)); // Output: 10
console.log(sum(5, 10, 15)); // Output: 30
console.log(sum()); // Output: 0 (no arguments passed)
The sum() function uses a rest parameter (args) to collect all the arguments passed to it. It then iterates over the args array and calculates the sum of all the elements. The rest parameter provides flexibility and eliminates the need to explicitly define a fixed number of parameters in the function declaration, making the code more concise and adaptable. It is important to note that the rest parameter only collects the additional arguments passed to the function and does not include arguments passed through named parameters or default parameter values.

Spread Operator / Rest Parameter:
After seeing the rest parameter you may feel that it is similar to spread operator but well it is not. The spread operator and the rest parameter both looks same because they both use the same syntax (...) in JavaScript, but they serve different purposes and are used in different contexts.

Let's explore the differences between the two:

The spread operator is used to unpack elements from an array or an iterable object (like a string or a set) into individual elements. It spreads the elements out. It's commonly used in situations where you need to combine or clone arrays, pass individual elements of an array as arguments to a function, or convert an iterable into an array. While the rest parameter is useful when you want to write functions that can accept a variable number of arguments i.e., It allows you to handle a dynamic number of function arguments without explicitly defining individual parameters.

To conclude let's take a example for spread syntax:

Example:

const array = [1, 2, 3];
console.log([...array]); // Output: [1, 2, 3]

const string = "hello";
console.log([...string]); // Output: ['h', 'e', 'l', 'l', 'o']

const set = new Set([1, 2, 3]);
console.log([...set]); // Output: [1, 2, 3]

*/

/**
 * @param {...(null|boolean|number|string|Array|Object)} args
 * @return {number}
 */
var argumentsLength = function(...args) {
	return args.length;
};

/**
 * argumentsLength(1, 2, 3); // 3
 */

/**
 * @param {...(null|boolean|number|string|Array|Object)} args
 * @return {number}
 */
var argumentsLength = function(...args) {
	return arguments.length;
};

/**
 * argumentsLength(1, 2, 3); // 3
 */

