/*
Given a function fn, an array of arguments args, and a timeout t in milliseconds, return a cancel function cancelFn.

After a delay of cancelTimeMs, the returned cancel function cancelFn will be invoked.

setTimeout(cancelFn, cancelTimeMs)
Initially, the execution of the function fn should be delayed by t milliseconds.

If, before the delay of t milliseconds, the function cancelFn is invoked, it should cancel the delayed execution of fn. Otherwise, if cancelFn is not invoked within the specified delay t, fn should be executed with the provided args as arguments.



Example 1:

Input: fn = (x) => x * 5, args = [2], t = 20
Output: [{"time": 20, "returned": 10}]
Explanation:
const cancelTimeMs = 50;
const cancelFn = cancellable((x) => x * 5, [2], 20);
setTimeout(cancelFn, cancelTimeMs);

The cancellation was scheduled to occur after a delay of cancelTimeMs (50ms), which happened after the execution of fn(2) at 20ms.
Example 2:

Input: fn = (x) => x**2, args = [2], t = 100
Output: []
Explanation:
const cancelTimeMs = 50;
const cancelFn = cancellable((x) => x**2, [2], 100);
setTimeout(cancelFn, cancelTimeMs);

The cancellation was scheduled to occur after a delay of cancelTimeMs (50ms), which happened before the execution of fn(2) at 100ms, resulting in fn(2) never being called.
Example 3:

Input: fn = (x1, x2) => x1 * x2, args = [2,4], t = 30
Output: [{"time": 30, "returned": 8}]
Explanation:
const cancelTimeMs = 100;
const cancelFn = cancellable((x1, x2) => x1 * x2, [2,4], 30);
setTimeout(cancelFn, cancelTimeMs);

The cancellation was scheduled to occur after a delay of cancelTimeMs (100ms), which happened after the execution of fn(2,4) at 30ms.


Constraints:

fn is a function
args is a valid JSON array
1 <= args.length <= 10
20 <= t <= 1000
10 <= cancelTimeMs <= 1000

EDITORIAL

Overview:
We need to implement a function cancellable that executes a given function (let's call it fn) after a specified delay (t milliseconds), unless a cancel function (cancelFn) is called before the delay expires. The cancel function should prevent the execution of the delayed function.

In other words, we have a task fn we want to do, but we want to wait for a bit (t milliseconds) before doing it. However, if we change our mind and want to cancel this task before the wait time is up, we can use a cancel function (cancelFn). If we don't cancel, the task will happen after the delay.

Closures:
In JavaScript, a closure is a combination of a function and the lexical environment within which that function was declared. The lexical environment consists of the variables, functions, and scopes available at the time of the closure's creation.

Working:

When a function is defined inside another function, a closure is created. The inner function retains a reference to the variables and scope of its outer function.
When the outer function finishes executing and returns, the closure is still intact with its captured variables and scope chain.
The closure allows the inner function to access and manipulate the variables of its outer function, even if the outer function's execution has been completed.
This behavior is possible because the closure maintains a reference to its outer function's variables and scope chain, preventing them from being garbage collected.
For a more detailed explanation of closures, check out the Counter editorial.

In the context of the problem, closures are used to maintain a reference to the timer variable even after the function that creates the closure has returned. This allows the cancelFn function to access and modify the timer variable, effectively canceling the execution of the delayed function.

setTimeout:
setTimeout is a built-in function in JavaScript that allows you to schedule the execution of a function after a specified delay. It can take an infinite number of arguments but usually, its first two arguments are always a function to be executed and a delay time in milliseconds.

Note: setTimeout is a variadic function that can accept an infinite number of arguments.

Here's an example of how to use setTimeout:

function delayedFunction() {
  console.log("Delayed function executed!");
}

const delay = 2000;

const timerId = setTimeout(delayedFunction, delay);

// To cancel the execution before the delay expires:
clearTimeout(timerId);
Working:

When setTimeout is called, it starts a timer and sets it to run after the specified delay.
After the delay expires, the JavaScript event loop puts the specified function in the execution queue.
Once the call stack is empty, the function is executed, and any associated code inside it is run.
If the setTimeout function is canceled before the delay expires, the scheduled function will not be executed.
For a deeper understanding of setTimeout, refer to the following editorials: Cache With Time Limit Editorial, Debounce Editorial, and Throttle Editorial.

In the context of the problem, setTimeout is used inside the cancellable function to schedule the execution of the delayed function (fn) after the specified delay (t).

Overall, closures and setTimeout work together in this problem to create a cancelable delayed function execution mechanism. The closure preserves the reference to the timeoutId variable, and setTimeout schedules the execution of the function after the specified delay.
*/

/**
 * @param {Function} fn
 * @param {Array} args
 * @param {number} t
 * @return {Function}
 */
var cancellable = function(fn, args, t) {
    let cancelled = false;

    setTimeout(() => {
        if (!cancelled) {
            fn(...args);
        }
    }, t);
    return () => {cancelled = true};
};

/**
 *  const result = [];
 *
 *  const fn = (x) => x * 5;
 *  const args = [2], t = 20, cancelTimeMs = 50;
 *
 *  const start = performance.now();
 *
 *  const log = (...argsArr) => {
 *      const diff = Math.floor(performance.now() - start);
 *      result.push({"time": diff, "returned": fn(...argsArr)});
 *  }
 *
 *  const cancel = cancellable(log, args, t);
 *
 *  const maxT = Math.max(t, cancelTimeMs);
 *
 *  setTimeout(cancel, cancelTimeMs);
 *
 *  setTimeout(() => {
 *      console.log(result); // [{"time":20,"returned":10}]
 *  }, maxT + 15)
 */

/**
 * @param {Function} fn
 * @param {Array} args
 * @param {number} t
 * @return {Function}
 */
var cancellable = function(fn, args, t) {

    let processId = setTimeout(() => {
        fn(...args);
    }, t);
    return () => {clearTimeout(processId)};
};

/**
 *  const result = [];
 *
 *  const fn = (x) => x * 5;
 *  const args = [2], t = 20, cancelTimeMs = 50;
 *
 *  const start = performance.now();
 *
 *  const log = (...argsArr) => {
 *      const diff = Math.floor(performance.now() - start);
 *      result.push({"time": diff, "returned": fn(...argsArr)});
 *  }
 *
 *  const cancel = cancellable(log, args, t);
 *
 *  const maxT = Math.max(t, cancelTimeMs);
 *
 *  setTimeout(cancel, cancelTimeMs);
 *
 *  setTimeout(() => {
 *      console.log(result); // [{"time":20,"returned":10}]
 *  }, maxT + 15)
 */

/**
 * @param {Function} fn
 * @param {Array} args
 * @param {number} t
 * @return {Function}
 */
var cancellable = function(fn, args, t) {

    let processId = setTimeout(() => {
        fn.apply(null, args);
    }, t);
    return () => {clearTimeout(processId)};
};

/**
 *  const result = [];
 *
 *  const fn = (x) => x * 5;
 *  const args = [2], t = 20, cancelTimeMs = 50;
 *
 *  const start = performance.now();
 *
 *  const log = (...argsArr) => {
 *      const diff = Math.floor(performance.now() - start);
 *      result.push({"time": diff, "returned": fn(...argsArr)});
 *  }
 *
 *  const cancel = cancellable(log, args, t);
 *
 *  const maxT = Math.max(t, cancelTimeMs);
 *
 *  setTimeout(cancel, cancelTimeMs);
 *
 *  setTimeout(() => {
 *      console.log(result); // [{"time":20,"returned":10}]
 *  }, maxT + 15)
 */
