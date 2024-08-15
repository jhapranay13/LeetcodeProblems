/*
Given an asynchronous function fn and a time t in milliseconds, return a new time limited version of the input function. fn takes arguments provided to the time limited function.

The time limited function should follow these rules:

If the fn completes within the time limit of t milliseconds, the time limited function should resolve with the result.
If the execution of the fn exceeds the time limit, the time limited function should reject with the string "Time Limit Exceeded".


Example 1:

Input:
fn = async (n) => {
  await new Promise(res => setTimeout(res, 100));
  return n * n;
}
inputs = [5]
t = 50
Output: {"rejected":"Time Limit Exceeded","time":50}
Explanation:
const limited = timeLimit(fn, t)
const start = performance.now()
let result;
try {
   const res = await limited(...inputs)
   result = {"resolved": res, "time": Math.floor(performance.now() - start)};
} catch (err) {
   result = {"rejected": err, "time": Math.floor(performance.now() - start)};
}
console.log(result) // Output

The provided function is set to resolve after 100ms. However, the time limit is set to 50ms. It rejects at t=50ms because the time limit was reached.
Example 2:

Input:
fn = async (n) => {
  await new Promise(res => setTimeout(res, 100));
  return n * n;
}
inputs = [5]
t = 150
Output: {"resolved":25,"time":100}
Explanation:
The function resolved 5 * 5 = 25 at t=100ms. The time limit is never reached.
Example 3:

Input:
fn = async (a, b) => {
  await new Promise(res => setTimeout(res, 120));
  return a + b;
}
inputs = [5,10]
t = 150
Output: {"resolved":15,"time":120}
Explanation:
​​​​The function resolved 5 + 10 = 15 at t=120ms. The time limit is never reached.
Example 4:

Input:
fn = async () => {
  throw "Error";
}
inputs = []
t = 1000
Output: {"rejected":"Error","time":0}
Explanation:
The function immediately throws an error.


Constraints:

0 <= inputs.length <= 10
0 <= t <= 1000
fn returns a promise

EDITORIAL

Overview
This question asks you to enhance an asynchronous function such that the promise it returns will automatically reject if the time limit elapses.

It is recommended you first read the Sleep Editorial as it covers topics on asynchronous programming not discussed here. You may also want to read up on the topic of functions that returns functions: Allow One Function Call Editorial and Memoize Editorial.

Use-cases for Time Limit
Long Running Processes
You may have code which repeats over and over again. A common example of this would be loading data into a cache and keeping it in sync with the data source.

async function repeatProcessIndefinitely() {
  while (true) {
    try {
      await someProcess();
    } catch (e) {
      console.error(e);
    }
  }
}
If someProcess were to ever never fulfill, the loop would get frozen and nothing would happen. Forcing someProcess to throw an error would unfreeze the process.

An important consideration is that code in someProcess can still continue executing even if the promise was rejected. So you might have multiple blocks of code executing in parallel. A better solution may be fix the underlying issue which caused the freeze or to implement proper cancellation. Consider solving Design Cancellable Function to implement true cancellation.

To force the promise someProcess() returns to reject after an hour:

const ONE_HOUR_IN_MS = 3600 * 1000;
const timeLimitedProcess = timeLimit(someProcess, ONE_HOUR_IN_MS);
Notify Users of Failure
Imagine a user requested to download a file which you expect should take less than 10 seconds to download. If the download is taking too long, rather than let the user wait, it may be better to just give up and show the user an error message.

Similar to the first use-case, this really should only be done as a last resort. It's likely better to implement a loading indicator and/or fix the underlying issue causing the slowness.

*/

/**
 * @param {Function} fn
 * @param {number} t
 * @return {Function}
 */
var timeLimit = function(fn, t) {

    return async function(...args) {
        return new Promise(async (resolve, reject) => {
            setTimeout(() => {
                reject("Time Limit Exceeded");
            }, t);
            let result;
            try {
                result = await fn(...args);
            } catch(err) {
                reject(err);
            }
            resolve(result);
        });
    }
};

/**
 * const limited = timeLimit((t) => new Promise(res => setTimeout(res, t)), 100);
 * limited(150).catch(console.log) // "Time Limit Exceeded" at t=100ms
 */

/**
 * @param {Function} fn
 * @param {number} t
 * @return {Function}
 */
var timeLimit = function(fn, t) {

    return async function(...args) {
        return new Promise(async (resolve, reject) => {
            let timeoutId = setTimeout(() => {
                reject("Time Limit Exceeded");
            }, t);
            let result;
            try {
                result = await fn(...args);
            } catch(err) {
                reject(err);
            } finally {
                clearTimeout(timeoutId);
            }
            resolve(result);
        });
    }
};

/**
 * const limited = timeLimit((t) => new Promise(res => setTimeout(res, t)), 100);
 * limited(150).catch(console.log) // "Time Limit Exceeded" at t=100ms
 */

/**
 * @param {Function} fn
 * @param {number} t
 * @return {Function}
 */
var timeLimit = function(fn, t) {

    return async function(...args) {
        return new Promise(async (resolve, reject) => {
            let timeoutId = setTimeout(() => {
                reject("Time Limit Exceeded");
            }, t);
            fn(...args).
                then(resolve).
                catch(reject).
                finally(() => clearTimeout(timeoutId));
        });
    }
};

/**
 * const limited = timeLimit((t) => new Promise(res => setTimeout(res, t)), 100);
 * limited(150).catch(console.log) // "Time Limit Exceeded" at t=100ms
 */

 /**
  * @param {Function} fn
  * @param {number} t
  * @return {Function}
  */
 var timeLimit = function(fn, t) {

     return async function(...args) {
         const timeLimitPromise = new Promise((resolve, reject) => {
             setTimeout(() => {reject("Time Limit Exceeded");}, t);
         });
         const functionPromise = fn(...args);
         return Promise.race([timeLimitPromise, functionPromise]);
     }
 };

 /**
  * const limited = timeLimit((t) => new Promise(res => setTimeout(res, t)), 100);
  * limited(150).catch(console.log) // "Time Limit Exceeded" at t=100ms
  */