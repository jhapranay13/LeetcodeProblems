/*
Given a function fn, an array of arguments args, and an interval time t, return a cancel function cancelFn.

The function fn should be called with args immediately and then called again every t milliseconds until cancelFn is called at cancelTimeMs ms.



Example 1:

Input: fn = (x) => x * 2, args = [4], t = 35
Output:
[
   {"time": 0, "returned": 8},
   {"time": 35, "returned": 8},
   {"time": 70, "returned": 8},
   {"time": 105, "returned": 8},
   {"time": 140, "returned": 8},
   {"time": 175, "returned": 8}
]
Explanation:
const cancelTimeMs = 190;
const cancelFn = cancellable((x) => x * 2, [4], 35);
setTimeout(cancelFn, cancelTimeMs);

Every 35ms, fn(4) is called. Until t=190ms, then it is cancelled.
1st fn call is at 0ms. fn(4) returns 8.
2nd fn call is at 35ms. fn(4) returns 8.
3rd fn call is at 70ms. fn(4) returns 8.
4th fn call is at 105ms. fn(4) returns 8.
5th fn call is at 140ms. fn(4) returns 8.
6th fn call is at 175ms. fn(4) returns 8.
Cancelled at 190ms
Example 2:

Input: fn = (x1, x2) => (x1 * x2), args = [2, 5], t = 30
Output:
[
   {"time": 0, "returned": 10},
   {"time": 30, "returned": 10},
   {"time": 60, "returned": 10},
   {"time": 90, "returned": 10},
   {"time": 120, "returned": 10},
   {"time": 150, "returned": 10}
]
Explanation:
const cancelTimeMs = 165;
const cancelFn = cancellable((x1, x2) => (x1 * x2), [2, 5], 30)
setTimeout(cancelFn, cancelTimeMs)

Every 30ms, fn(2, 5) is called. Until t=165ms, then it is cancelled.
1st fn call is at 0ms
2nd fn call is at 30ms
3rd fn call is at 60ms
4th fn call is at 90ms
5th fn call is at 120ms
6th fn call is at 150ms
Cancelled at 165ms
Example 3:

Input: fn = (x1, x2, x3) => (x1 + x2 + x3), args = [5, 1, 3], t = 50
Output:
[
   {"time": 0, "returned": 9},
   {"time": 50, "returned": 9},
   {"time": 100, "returned": 9},
   {"time": 150, "returned": 9}
]
Explanation:
const cancelTimeMs = 180;
const cancelFn = cancellable((x1, x2, x3) => (x1 + x2 + x3), [5, 1, 3], 50)
setTimeout(cancelFn, cancelTimeMs)

Every 50ms, fn(5, 1, 3) is called. Until t=180ms, then it is cancelled.
1st fn call is at 0ms
2nd fn call is at 50ms
3rd fn call is at 100ms
4th fn call is at 150ms
Cancelled at 180ms


Constraints:

fn is a function
args is a valid JSON array
1 <= args.length <= 10
30 <= t <= 100
10 <= cancelTimeMs <= 500

EDITORIAL

Overview:
You are given a function fn, an array of arguments args, and an interval time t. You need to implement a function cancelFn that calls fn immediately with args and then schedules subsequent calls to fn every t milliseconds until cancelFn is called.

Use Cases:
Auto-Saving in Editing Applications: When working with text editors, document processors, or other content creation tools, it's common to have an auto-save feature that periodically saves changes. You can use interval cancellation to schedule auto-saving at regular intervals. If the user explicitly saves the document or exits the application, you can cancel the interval to prevent unnecessary saving operations.

Animation and Slideshow Timings: During development, you may want to create animations or slideshows that automatically transition between different states or images. Interval cancellation can be used to control the timing of these transitions. If the user interacts with the animation or slideshow, you can cancel the interval to pause or stop the automatic progression.

Note: For more complex or performance-critical animations, it's recommended to use the requestAnimationFrame method instead of setInterval, as it provides better performance and efficiency.

Time-based Reminders: Consider a task management application where users can set reminders for specific tasks. Interval cancellation can be used to trigger reminders at specified intervals. Once the user acknowledges the reminder or the task is completed, you can cancel the interval to stop further reminders.
Before going any further, we need to learn two concepts: setInterval and clearInterval.

setInterval:
The setInterval function is used to repeatedly execute a function or a code snippet with a fixed time delay between each call. It takes two arguments: the function or code snippet to be executed and the time delay specified in milliseconds.
setInterval(function, delay);
The function parameter represents the function or code snippet that will be executed at each interval.
The delay parameter specifies the time delay in milliseconds between each execution of the function.
When setInterval is called, it schedules the first execution of the specified function after the initial delay. Subsequent executions will occur repeatedly based on the specified delay.
setInterval returns an interval ID, which is a unique numeric value. This ID can be used later to identify and control the interval schedule. Also, note that setInterval is not totally precise.

To gain a deeper understanding, you can review the explanation provided in the Sleep editorial.

clearInterval:
The clearInterval function is used to cancel a timed, repeating action that was previously established by a call to setInterval. It takes the interval ID returned by setInterval as an argument.
clearInterval(intervalID);
The intervalID parameter represents the unique ID returned by the setInterval function when the interval was created.
By calling clearInterval with the appropriate interval ID, you can effectively stop the subsequent executions of the function specified in setInterval. It cancels the scheduled interval and prevents any further calls to the specified function.

*/

/**
 * @param {Function} fn
 * @param {Array} args
 * @param {number} t
 * @return {Function}
 */
var cancellable = function(fn, args, t) {
    fn(...args);
    let functionId = setInterval(() => {fn(...args);}, t);

    return () => {
        clearInterval(functionId);
    };
};

/**
 *  const result = [];
 *
 *  const fn = (x) => x * 2;
 *  const args = [4], t = 35, cancelTimeMs = 190;
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
 *  setTimeout(cancel, cancelTimeMs);
 *
 *  setTimeout(() => {
 *      console.log(result); // [
 *                           //     {"time":0,"returned":8},
 *                           //     {"time":35,"returned":8},
 *                           //     {"time":70,"returned":8},
 *                           //     {"time":105,"returned":8},
 *                           //     {"time":140,"returned":8},
 *                           //     {"time":175,"returned":8}
 *                           // ]
 *  }, cancelTimeMs + t + 15)
 */

 /**
  * @param {Function} fn
  * @param {Array} args
  * @param {number} t
  * @return {Function}
  */
 var cancellable = function(fn, args, t) {
     fn(...args);
     let isCancelled = false;
     const setIntefvalFn = () => {
         setTimeout(() => {
             if (isCancelled) {
                 return;
             }
             fn(...args);
             setIntefvalFn(); // recursive approach
         }, t);
     };
     setIntefvalFn();
     return () => {
         isCancelled = true;
     };
 };

 /**
  *  const result = [];
  *
  *  const fn = (x) => x * 2;
  *  const args = [4], t = 35, cancelTimeMs = 190;
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
  *  setTimeout(cancel, cancelTimeMs);
  *
  *  setTimeout(() => {
  *      console.log(result); // [
  *                           //     {"time":0,"returned":8},
  *                           //     {"time":35,"returned":8},
  *                           //     {"time":70,"returned":8},
  *                           //     {"time":105,"returned":8},
  *                           //     {"time":140,"returned":8},
  *                           //     {"time":175,"returned":8}
  *                           // ]
  *  }, cancelTimeMs + t + 15)
  */