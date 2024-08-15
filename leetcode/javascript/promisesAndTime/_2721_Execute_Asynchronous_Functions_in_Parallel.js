/*
Given an array of asynchronous functions functions, return a new promise promise. Each function in the array accepts no arguments and returns a promise. All the promises should be executed in parallel.

promise resolves:

When all the promises returned from functions were resolved successfully in parallel. The resolved value of promise should be an array of all the resolved values of promises in the same order as they were in the functions. The promise should resolve when all the asynchronous functions in the array have completed execution in parallel.
promise rejects:

When any of the promises returned from functions were rejected. promise should also reject with the reason of the first rejection.
Please solve it without using the built-in Promise.all function.



Example 1:

Input: functions = [
  () => new Promise(resolve => setTimeout(() => resolve(5), 200))
]
Output: {"t": 200, "resolved": [5]}
Explanation:
promiseAll(functions).then(console.log); // [5]

The single function was resolved at 200ms with a value of 5.
Example 2:

Input: functions = [
    () => new Promise(resolve => setTimeout(() => resolve(1), 200)),
    () => new Promise((resolve, reject) => setTimeout(() => reject("Error"), 100))
]
Output: {"t": 100, "rejected": "Error"}
Explanation: Since one of the promises rejected, the returned promise also rejected with the same error at the same time.
Example 3:

Input: functions = [
    () => new Promise(resolve => setTimeout(() => resolve(4), 50)),
    () => new Promise(resolve => setTimeout(() => resolve(10), 150)),
    () => new Promise(resolve => setTimeout(() => resolve(16), 100))
]
Output: {"t": 150, "resolved": [4, 10, 16]}
Explanation: All the promises resolved with a value. The returned promise resolved when the last promise resolved.


Constraints:

functions is an array of functions that returns promises
1 <= functions.length <= 10

EDITORIAL

Overview
In this problem, you are tasked with creating a JavaScript function named promiseAll, which simulates the behavior of JavaScript's built-in Promise.all() method without using it. The function takes an array of asynchronous functions as input, each returning a promise, and should return a new promise.

The returned promise resolves if and only if all the promises returned by the input functions resolve. In this case, the promise's resolved value should be an array containing the resolved values of all the promises in the same order as their corresponding functions in the input array. However, if any promise returned by an input function gets rejected, the returned promise should reject immediately, carrying the reason for the first promise rejection.

The problem description provides three key examples to illustrate the expected functionality. In the first example, there's a single function that resolves after a certain delay. The promise returned by our function should resolve with an array containing the value from this function. In the second example, one function rejects its promise before the other function has a chance to resolve. Consequently, the promise returned by our function should reject with the same reason as the first promise rejection. In the last example, all functions successfully resolve their promises, so the promise returned by our function should resolve with an array containing all resolved values, maintaining their original order.

Effectively solving this problem requires a good understanding of JavaScript promises and asynchronous programming. You should be familiar with how promises work, how to create new promises, and how to handle the resolution and rejection of promises.

For a comprehensive understanding of JavaScript's asynchronous programming, promises, async/await, and the event loop, we recommend checking out our Sleep editorial. If you're new to JavaScript promises, you may also find the MDN guide on using promises helpful.

Working with Promises in JavaScript
In our problem, we're dealing extensively with JavaScript Promises, a concept fundamental to asynchronous programming. A Promise in JavaScript represents a value that may not be immediately available but will be available in the future, or it will never be available due to an error. A Promise can be in one of three states: Pending, Fulfilled, or Rejected.

In the context of our problem, understanding these states is crucial. We're dealing with a series of functions that each return a promise. We always create a new promise, and the state of this new promise depends on the states of the promises in the input array. If all promises from the array are fulfilled, our new promise resolves with all their values. If any promise from the array is rejected, our new promise rejects with the reason of the first rejected promise.

To refresh your memory or for those who are new to JavaScript Promises, we recommend checking out the editorial Add two promises, part of the 30-day JavaScript plan. This tutorial provides a comprehensive explanation of Promises, their states, and their use in asynchronous JavaScript programming.

Promise.all()
Promise.all() is a built-in JavaScript method that takes an iterable of promises and returns a new promise that only fulfills when all the promises in the iterable have been fulfilled, or rejects as soon as one of the promises in the iterable rejects. The value of the Promise.all() promise is an array of the fulfilled values of the promises in the iterable, in the same order as the promises in the iterable.

let promise1 = Promise.resolve(3);
let promise2 = 42;
let promise3 = new Promise((resolve, reject) => {
  setTimeout(resolve, 100, 'foo');
});

Promise.all([promise1, promise2, promise3]).then((values) => {
  console.log(values); // [3, 42, "foo"]
});
As you can see, Promise.all() is perfect when you want to run multiple promises in parallel and wait for all of them to finish. It's a great way to group promises together and only deal with their results when all of them are ready.

However, the problem at hand asks to solve it without using Promise.all(). This pushes us to understand the inner workings of Promise.all() and emulate its behavior by manually handling promises, monitoring their state, and resolving or rejecting the final promise accordingly.

It's also worth mentioning that there is a potential pitfall with Promise.all() to be aware of: if any of the promises passed to it reject, Promise.all() will immediately reject with that reason, discarding all the other promises, even if they were about to fulfill. In other words, it's an "all or nothing" approach. This behavior is, in fact, what our problem expects us to emulate. For more detailed understanding, you can refer to the MDN documentation on Promise.all().

Use Cases of Promise.all() in JavaScript
Aggregating API Data
In a real-world application, you might need to fetch data from several different API endpoints before you can render a page or calculate some result. Rather than waiting for each request to complete before starting the next, Promise.all() allows you to make all the requests at once and then wait for all of them to complete.

let urls = [
  'https://api.github.com/users/github',
  'https://api.github.com/users/microsoft',
  'https://api.github.com/users/apple'
];

Promise.all(urls.map(url =>
        fetch(url).then(user => user.json())
)).then(users => {
  console.log(users.length); // 3
  console.log(users[0]); // {login: "github", ...}
});
In this example, we use Promise.all() to fetch user data from multiple GitHub accounts. This speeds up the data fetching process as all requests are made concurrently.

Database Transactions
In a database operation, you may need to perform multiple actions that should either all succeed or all fail. Promise.all() allows you to model this as a single promise that either fulfills when all the actions succeed or rejects as soon as one action fails.

let transaction = [
  UserModel.create({ name: 'Alice' }),
  AccountModel.create({ userId: 'Alice', balance: 100 })
];

Promise.all(transaction)
  .then(() => console.log('Transaction successful'))
  .catch(() => console.log('Transaction failed'));
In this example, we use Promise.all() to perform a transaction that involves creating a user and creating an account for the user. If any of these operations fail, Promise.all() will immediately reject, allowing us to easily roll back the transaction.

Running Tasks with Interdependencies
There may be scenarios where you have multiple async tasks that depend on each other. Promise.all() can be handy in such situations. You can start all tasks at once and then use the results array to access the results of each task in the correct order.

let task1 = fetch('/api/task1');
let task2 = fetch('/api/task2');

Promise.all([task1, task2])
        .then(results => {
          let result1 = results[0];
          let result2 = results[1];

          // do something with the results
        });
In this example, two network requests are made simultaneously using fetch. Once both complete, Promise.all() resolves with an array containing the results of both tasks in the order they were added. This can be very useful in situations where tasks have interdependencies, but can still be run concurrently.

*/

/**
 * @param {Array<Function>} functions
 * @return {Promise<any>}
 */
var promiseAll = function(functions) {
    return new Promise((resolve, reject) => {
        if (functions.length === []) {
            resolve([]);
        }
        const holder = new Array(functions.length).fill(null);
        let count = 0;

        functions.forEach(async (element, index) => {
            try {
                const result = await element();
                holder[index] = result;
                count++;

                if (count == functions.length) {
                    resolve(holder);
                }
            } catch(error) {
                reject(error);
            }
        });
    });
};

/**
 * const promise = promiseAll([() => new Promise(res => res(42))])
 * promise.then(console.log); // [42]
 */

 /**
  * @param {Array<Function>} functions
  * @return {Promise<any>}
  */
 var promiseAll = function(functions) {
     return new Promise((resolve, reject) => {
         if (functions.length === []) {
             resolve([]);
         }
         const holder = new Array(functions.length).fill(null);
         let count = 0;

         functions.forEach((element, index) => {
             element().then( (result) => {
                     holder[index] = result;
                     count++;

                     if (count === functions.length) {
                         resolve(holder);
                     }
                 }
             ).catch((error) => {reject(error)});
         });
     });
 };

 /**
  * const promise = promiseAll([() => new Promise(res => res(42))])
  * promise.then(console.log); // [42]
  */