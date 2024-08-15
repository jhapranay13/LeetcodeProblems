/*
Given a function fn, return a memoized version of that function.

A memoized function is a function that will never be called twice with the same inputs. Instead it will return a cached value.

You can assume there are 3 possible input functions: sum, fib, and factorial.

sum accepts two integers a and b and returns a + b.
fib accepts a single integer n and returns 1 if n <= 1 or fib(n - 1) + fib(n - 2) otherwise.
factorial accepts a single integer n and returns 1 if n <= 1 or factorial(n - 1) * n otherwise.


Example 1:

Input:
fnName = "sum"
actions = ["call","call","getCallCount","call","getCallCount"]
values = [[2,2],[2,2],[],[1,2],[]]
Output: [4,4,1,3,2]
Explanation:
const sum = (a, b) => a + b;
const memoizedSum = memoize(sum);
memoizedSum(2, 2); // "call" - returns 4. sum() was called as (2, 2) was not seen before.
memoizedSum(2, 2); // "call" - returns 4. However sum() was not called because the same inputs were seen before.
// "getCallCount" - total call count: 1
memoizedSum(1, 2); // "call" - returns 3. sum() was called as (1, 2) was not seen before.
// "getCallCount" - total call count: 2
Example 2:

Input:
fnName = "factorial"
actions = ["call","call","call","getCallCount","call","getCallCount"]
values = [[2],[3],[2],[],[3],[]]
Output: [2,6,2,2,6,2]
Explanation:
const factorial = (n) => (n <= 1) ? 1 : (n * factorial(n - 1));
const memoFactorial = memoize(factorial);
memoFactorial(2); // "call" - returns 2.
memoFactorial(3); // "call" - returns 6.
memoFactorial(2); // "call" - returns 2. However factorial was not called because 2 was seen before.
// "getCallCount" - total call count: 2
memoFactorial(3); // "call" - returns 6. However factorial was not called because 3 was seen before.
// "getCallCount" - total call count: 2
Example 3:

Input:
fnName = "fib"
actions = ["call","getCallCount"]
values = [[5],[]]
Output: [8,1]
Explanation:
fib(5) = 8 // "call"
// "getCallCount" - total call count: 1


Constraints:

0 <= a, b <= 105
1 <= n <= 10
0 <= actions.length <= 105
actions.length === values.length
actions[i] is one of "call" and "getCallCount"
fnName is one of "sum", "factorial" and "fib"

EDITORIAL

Overview
This question asks you to write a function that modifies a provided function such that the provided function will only be called if the arguments have not been passed before. If they have been passed before, it should return the previous output without calling the provided function. This type of optimization is called memoization and is an extremely important example of a higher-order function.

To give a concrete example of memoization, here is some code without memoization.

let callCount = 0;
const add = (a, b) => {
  callCount += 1;
  return a + b;
}

add(2, 2); // 4
console.log(callCount); // 1
add(2, 2); // 4
console.log(callCount); // 2
add(2, 2); // 4
console.log(callCount); // 3
As expected, callCount is incremented every time add is called.

However if we apply memoization:

let callCount = 0;
const add = (a, b) => {
  callCount += 1;
  return a + b;
};
const memoizedAdd = memoize(add);

memoizedAdd(2, 2); // 4
console.log(callCount); // 1
memoizedAdd(2, 2); // 4
console.log(callCount); // 1
memoizedAdd(2, 2); // 4
console.log(callCount); // 1
We can see that callCount was only incremented the first time memoizedAdd was called. Each subsequent time (2, 2) was passed, the memoization logic detected that those arguments were passed before and it instead immediately returned the cached value (4) without calling add.

Avoiding adding 2 numbers is obviously not much of an optimization, but you could imagine memoizing a more complex function could result in serious performance gains.

Pure Functions
It is important to note that memoization only works correctly for pure functions. A pure function is defined as function that always returns the same output given the same inputs and doesn't have any side-effects.

For example, suppose you attempted to memoize the impure function Date.now which returns the current time in milliseconds since the unix epoch.

const getCurrentTimeMemoized = memoize(Date.now);

getCurrentTimeMemoized(); // 1683784131157
getCurrentTimeMemoized(); // 1683784131157
getCurrentTimeMemoized(); // 1683784131157
getCurrentTimeMemoized correctly returns the current time the first time it is called. But each subsequent time, it incorrectly returns the same value.

Similarly, suppose you have a function with a side-effect like uploading data to a database.

function uploadRow(row) {
  // upload logic
}

const memoizedUpload = memoize(uploadRows);
memoizedUpload('Some Data'); // successful upload
memoizedUpload('Some Data'); // nothing happens
The first time memoizedUpload, data is correctly uploaded to the database, but each subsequent time, nothing will happen.

The fact you can only apply this optimization on pure functions is a good reason to try to make functions pure when possible.

Memoization Use-cases in Web Development
There are countless use-cases of memoization but we can discuss a few.

Caching Website Files
A large website often consists of many JavaScript files which are dynamically downloaded when a user visits different pages. A pattern is sometimes employed where the filename is based on a hash of the file's content. That way, when the web browser requests a filename that was already requested before, it can load the file locally from disk rather than have to download it again.

React Components
React is a highly popular library for building user interfaces, especially for single-page applications. One of its core principles is the idea of breaking down your application into separate components. Each of these components is responsible for rendering a distinct part of the app's HTML.

For example you might have a component like this:

const TitleComponent = (props) => {
  return <h1>{props.title}</h1>;
};
The above function will get called every time the parent component renders, even if title was not changed. Performance can be improved by calling React.memo on it, avoiding unnecessary renders.

const TitleComponent = React.memo((props) => {
  return <h1>{props.title}</h1>;
});
Now, TitleComponent will only re-render if the title has changed, thereby improving the performance of the application.

Caching API Calls
Suppose you had a function that sends a network request to an API to access key-value pairs in a database.

async function getValue(key) {
  // database request logic
}
const getValueMemoized = memoize(getValue);
Now getValueMemoized will only make a network request once for each key, potentially greatly improving performance. Something to note is that since getValue is async it will return a Promise rather than an actual value. For this use-case, this actually ideal because it will still only make a network request once even if it was called twice before the first request returned a value.

A potential downside of memoizing network requests is the risk of data staleness. If the value associated with a particular key in the database changes, the memoized function may still return the old cached result, leaving the user unaware of any updates.

A few ways to handle this:

Always send a request to the API asking if the value was changed.
Use a WebSocket to subscribe to changes in the values in the database.
Give the value Time Until Expiration so that at least the user won't see data that's TOO outdated.
You can read more about caching with HTTP here.

Memoization in Algorithms
A classic application of memoization is in dynamic programming where you break up a problem into sub-problems. The sub-problems can be represented as function calls, many of which have the same inputs passed to them over and over again, and thus are ripe for optimization.

A classic example of dynamic programming greatly improving efficiency is when calculating Fibonacci numbers.

function fib(n) {
  if (n <= 1) return n;
  return fib(n - 1) + fib(n - 2);
}
fib(100); // Takes years to compute
The code above is extremely inefficient, running in O(1.6n)O(1.6^n)O(1.6
n
 ) time (1.6 is the golden ratio).

But by never calling fib twice with the same inputs, we can can calculate Fibonacci numbers in O(n)O(n)O(n) time.

const cache = new Map();
function fib(n) {
  if (n <= 1) return n;
  if (cache.has(n)) {
    return cache.get(n);
  }
  const result = fib(n - 1) + fib(n - 2);
  cache.set(n, result);
  return result;
}
fib(100); // Solves almost instantly
Could we have just called used the first implementation of fib and then wrote memoizedFib = memoize(fib); on it to get the same performance optimization? Unfortunately, no. The original implementation of fib references itself (the un-memoized version of itself). So if you called memoizedFib(100), the cache would only have a single key added (100), and would still take years to compute. This is a fundamental limitation in JavaScript (Python doesn't have this issue).

Concerns of a Professional Implementation
Handling Arbitrary Inputs
There's a reason why only 3 specific functions, all with numeric inputs, were assumed to be passed as an argument in this problem. It's because numbers have unique string representations making the caching logic much simpler. If instead the function could have arbitrary inputs passed in, you would need a more complex approach then simply converting the inputs directly into strings. Consider solving Memoize II which requires a more generic approach.

Memory Management
Since you could keep calling the function with differently inputs indefinitely, you may eventually run out of memory. It's important to implement some mechanism to limit the size of the cache. One such approach is a Least Recently Used cache. You can read more about this at the bottom of the Memoize II Editorial.
*/

/**
 * @param {Function} fn
 * @return {Function}
 */
function memoize(fn) {
    const memo = new Map();

    return function(...args) {
        const key = JSON.stringify(args);

        if (memo.has(key)) {
            return memo.get(key);
        }
        let result = fn(...args);
        memo.set(key, result);
        return result;
    }
}


/**
 * let callCount = 0;
 * const memoizedFn = memoize(function (a, b) {
 *	 callCount += 1;
 *   return a + b;
 * })
 * memoizedFn(2, 3) // 5
 * memoizedFn(2, 3) // 5
 * console.log(callCount) // 1
 */

 /**
  * @param {Function} fn
  * @return {Function}
  */
 function memoize(fn) {
     const memoObj = {};

     return function(...args) {
         const key = JSON.stringify(args);

         if (memoObj[key] != undefined) {
             return memoObj[key];
         }
         let result = fn(...args);
         memoObj[key] = result;
         return result;
     }
 }


 /**
  * let callCount = 0;
  * const memoizedFn = memoize(function (a, b) {
  *	 callCount += 1;
  *   return a + b;
  * })
  * memoizedFn(2, 3) // 5
  * memoizedFn(2, 3) // 5
  * console.log(callCount) // 1
  */


/**
 * @param {Function} fn
 * @return {Function}
 */
function memoize(fn) {
    const memoObj = {};

    return function(...args) {
        const key = JSON.stringify(args);

        if (key in memoObj) {
            return memoObj[key];
        }
        let result = fn(...args);
        memoObj[key] = result;
        return result;
    }
}


/**
 * let callCount = 0;
 * const memoizedFn = memoize(function (a, b) {
 *	 callCount += 1;
 *   return a + b;
 * })
 * memoizedFn(2, 3) // 5
 * memoizedFn(2, 3) // 5
 * console.log(callCount) // 1
 */
