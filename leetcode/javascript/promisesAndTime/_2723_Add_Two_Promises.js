/*
Given two promises promise1 and promise2, return a new promise. promise1 and promise2 will both resolve with a number. The returned promise should resolve with the sum of the two numbers.


Example 1:

Input:
promise1 = new Promise(resolve => setTimeout(() => resolve(2), 20)),
promise2 = new Promise(resolve => setTimeout(() => resolve(5), 60))
Output: 7
Explanation: The two input promises resolve with the values of 2 and 5 respectively. The returned promise should resolve with a value of 2 + 5 = 7. The time the returned promise resolves is not judged for this problem.
Example 2:

Input:
promise1 = new Promise(resolve => setTimeout(() => resolve(10), 50)),
promise2 = new Promise(resolve => setTimeout(() => resolve(-12), 30))
Output: -2
Explanation: The two input promises resolve with the values of 10 and -12 respectively. The returned promise should resolve with a value of 10 + -12 = -2.


Constraints:

promise1 and promise2 are promises that resolve with a number

EDITORIAL

Overview:
The problem requires creating a new promise that resolves with the sum of two numbers obtained from two given promises, promise1 and promise2.

This editorial will serve as an excellent prerequisite.

Promises:
In Javascript promises represent the eventual completion (or failure) of an asynchronous operation and allow us to work with the results when they become available. They are a way to handle asynchronous code in a more organized and structured manner. Promises have three states: pending, fulfilled, or rejected.

Pending: The initial state of a promise. It represents that the asynchronous operation is still ongoing and hasn't completed yet.
Fulfilled: The state of a promise when the asynchronous operation is successfully completed. It means that the promised result or value is available.
Rejected: The state of a promise when the asynchronous operation encounters an error or fails. It means that the promised result cannot be obtained.
Promises provide methods like .then() and .catch() to handle the resolved values or errors.
Whenever we use promises we usually come accross async/await keywords. Let's discuss those keywords also:
The async and await keywords are used to simplify working with promises and make asynchronous code appear more like synchronous code.

async: async is used to define an asynchronous function. It ensures that the function always returns a promise. When the async keyword is used before a function declaration or function expression, it becomes an asynchronous function.
Note: Non-promises returned from async functions are automatically wrapped in promises.

await: await is used to pause the execution of an asynchronous function until a promise is resolved. It can only be used inside an async function. When await is used before a promise, it waits for the promise to be resolved or rejected. If resolved it proceeds to the next line of code and if the awaited promise is rejected, an exception is thrown.
Using await within an async function allows you to write asynchronous code in a more sequential and readable manner, without the need for explicit promise chaining using .then().
When a scenerio arises where you need to perform multiple asynchronous operations concurrently and wait for all of them to complete before proceeding. This is where the Promise.all() method is used prominently. Let's deep dive into it.

The Promise.all() method is used to handle multiple promises concurrently. It takes an array (or an iterable) of promises as input and returns a new promise that resolves when all the promises in the input array have resolved.
Note: Promise.all() not necessarily only takes promises as input, it can also take just array of numbers and it'll resolve it - for example:

 await Promise.all([1,2,Promise.resolve(3), Promise.resolve(4)]).then((value) => {
    console.log(value)
  }, (error) => {
    console.log(error)
  })
The Promise.all() method waits for all the promises to settle (either fulfilled or rejected).
If all the promises are fulfilled, the returned promise is fulfilled, and the resolved values of the input promises are available as an array in the same order as the input promises.
If any of the promises are rejected, the returned promise is rejected with the reason of the first rejected promise. For example the below code will resolve with 'error', even though 3 other items resolved correctly.
await Promise.all([1, 2, Promise.resolve(3), Promise.reject('error')]).then(
  value => {
    console.log(value);
  },
  error => {
    console.log(error);
  }
);
Using Promise.all() allows for efficient parallel execution of multiple asynchronous operations and enables us to work with the combined results once they are all available.
For example when an application needs to fetch data from multiple APIs simultaneously, Promise.all() can be used to initiate all the requests in parallel and wait for all the responses to arrive. Once all the promises are fulfilled, the application can process the combined data.
*/

/**
 * @param {Promise} promise1
 * @param {Promise} promise2
 * @return {Promise}
 */
var addTwoPromises = async function(promise1, promise2) {
    return await promise1 + await promise2;
};

/**
 * addTwoPromises(Promise.resolve(2), Promise.resolve(2))
 *   .then(console.log); // 4
 */

 var addTwoPromises = async function(promise1, promise2) {
     return new Promise(async (resolve, reject) => {
         try{
             let val1 = await promise1;
             let val2 = await promise2;
             resolve(val1 + val2);
         } catch(error) {
             reject(error);
         }
     });
 };

 var addTwoPromises = async function(promise1, promise2) {
     let val1 = await promise1.then((result) => {
         return result;
     }).catch((error) => {throw error});
     let val2 = await promise2.then((result) => {
         return result;
     }).catch((error) => {throw error});
     return val1 + val2;
 };

 /**
  * @param {Promise} promise1
  * @param {Promise} promise2
  * @return {Promise}
  */
 var addTwoPromises = async function(promise1, promise2) {
     let result = promise1.then((val1) => {
         return promise2.then((val2) => {
             return val1 + val2;
         });
     });
     return result;
 };

 /**
  * addTwoPromises(Promise.resolve(2), Promise.resolve(2))
  *   .then(console.log); // 4
  */
/**
 * @param {Promise} promise1
 * @param {Promise} promise2
 * @return {Promise}
 */
var addTwoPromises = async function(promise1, promise2) {
    let promises = [...arguments];

    return new Promise((resolve, reject) => {
        let result = 0;
        let count = promises.length;

        promises.forEach(async (promise) => {
            try{
                const holder = await promise; // if don't do it results can be unpredicatbale due to multi threading
                result += holder;
                count--;

                if (count === 0) {
                    resolve(result);
                }
            } catch(error) {
                reject(error);
            }
        });
    });
};

/**
 * addTwoPromises(Promise.resolve(2), Promise.resolve(2))
 *   .then(console.log); // 4
 */
/**
 * @param {Promise} promise1
 * @param {Promise} promise2
 * @return {Promise}
 */
var addTwoPromises = async function(promise1, promise2) {
    try {
        const promises = [...arguments];

        const results = await Promise.all([...promises]);
        let result = 0;

        for (let res of results) {
            const resHolder = res;
            result += Number(resHolder);
        }
        return result;
    } catch(error) {
        throw error;
    }
};

/**
 * addTwoPromises(Promise.resolve(2), Promise.resolve(2))
 *   .then(console.log); // 4
 */

 /**
  * @param {Promise} promise1
  * @param {Promise} promise2
  * @return {Promise}
  */
 var addTwoPromises = async function(promise1, promise2) {
     try {
         const promises = [...arguments];

         const results = await Promise.all([...promises]);
         let result = 0;
         // iterates over values. For in is for iterable String Properties
         for (let res of results) {
             const resHolder = res;
             result += Number(resHolder);
         }
         return result;
     } catch(error) {
         throw error;
     }
 };

 /**
  * addTwoPromises(Promise.resolve(2), Promise.resolve(2))
  *   .then(console.log); // 4
  */