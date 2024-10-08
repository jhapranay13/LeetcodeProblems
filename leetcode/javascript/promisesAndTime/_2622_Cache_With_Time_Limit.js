/*
Write a class that allows getting and setting key-value pairs, however a time until expiration is associated with each key.

The class has three public methods:

set(key, value, duration): accepts an integer key, an integer value, and a duration in milliseconds. Once the duration has elapsed, the key should be inaccessible. The method should return true if the same un-expired key already exists and false otherwise. Both the value and duration should be overwritten if the key already exists.

get(key): if an un-expired key exists, it should return the associated value. Otherwise it should return -1.

count(): returns the count of un-expired keys.



Example 1:

Input:
actions = ["TimeLimitedCache", "set", "get", "count", "get"]
values = [[], [1, 42, 100], [1], [], [1]]
timeDelays = [0, 0, 50, 50, 150]
Output: [null, false, 42, 1, -1]
Explanation:
At t=0, the cache is constructed.
At t=0, a key-value pair (1: 42) is added with a time limit of 100ms. The value doesn't exist so false is returned.
At t=50, key=1 is requested and the value of 42 is returned.
At t=50, count() is called and there is one active key in the cache.
At t=100, key=1 expires.
At t=150, get(1) is called but -1 is returned because the cache is empty.
Example 2:

Input:
actions = ["TimeLimitedCache", "set", "set", "get", "get", "get", "count"]
values = [[], [1, 42, 50], [1, 50, 100], [1], [1], [1], []]
timeDelays = [0, 0, 40, 50, 120, 200, 250]
Output: [null, false, true, 50, 50, -1, 0]
Explanation:
At t=0, the cache is constructed.
At t=0, a key-value pair (1: 42) is added with a time limit of 50ms. The value doesn't exist so false is returned.
At t=40, a key-value pair (1: 50) is added with a time limit of 100ms. A non-expired value already existed so true is returned and the old value was overwritten.
At t=50, get(1) is called which returned 50.
At t=120, get(1) is called which returned 50.
At t=140, key=1 expires.
At t=200, get(1) is called but the cache is empty so -1 is returned.
At t=250, count() returns 0 because the cache is empty.


Constraints:

0 <= key, value <= 109
0 <= duration <= 1000
1 <= actions.length <= 100
actions.length === values.length
actions.length === timeDelays.length
0 <= timeDelays[i] <= 1450
actions[i] is one of "TimeLimitedCache", "set", "get" and "count"
First action is always "TimeLimitedCache" and must be executed immediately, with a 0-millisecond delay

EDITORIAL

Overview
This question asks you to implement a key-value store, but where each entry expires after a certain amount of time

It is recommended you first read the Sleep Editorial as it covers topics on asynchronous programming not discussed here.

Use-case for a Cache with a Time Limit
Imagine you are maintaining a cache of files from a database. You could load each file once and keep it in memory indefinitely. The issue is if a file is updated in the database, the cache will contains out-of-date data. Another option is to constantly re-download the files every time a file is accessed (or at least send a request asking if it changed). But this could be inefficient and slow, especially if the files change infrequently.

If it is acceptable for the data to sometimes be a little out of date, a good compromise is to give the data a Time Until Expiration. This provides a good balance between performance and having up-to-date data. This type of cache is most effective when the same key is accessed in rapid succession.

Here is some code showing how to use this type of cache for that purpose:

const cache = new TimeLimitedCache();

async function getFileWithCache(filename) {
  let content = cache.get(filename);
  if (content !== -1) return content;
  content = await loadFileContents(filename);
  const ONE_HOUR = 60 * 60 * 1000;
  cache.set(filename, content, ONE_HOUR);
  return content;
}
In the above code, getFileWithCache first tries to load the data from the cache. If there was a cache-hit, it immediately returns the result. Otherwise it downloads the data and populates the cache before returning the downloaded data.

*/

class TimeLimitedCache{
    cache = new Map();

    set = (key, value, duration) => {
        const val = this.cache.get(key);

        if (val) {
            clearTimeout(val.timeout);
        }
        const timeout = setTimeout(() => {
            this.cache.delete(key);
        }, duration);
        /*
        in this way object will be created like
        {
            value: value,
            timeout: timeout
        }
        */
        const setObj = {value, timeout};
        this.cache.set(key, setObj);
        return Boolean(val);
    };

    get(key) {
        return this.cache.has(key) ? this.cache.get(key).value : -1;
    }

    count() {
        return this.cache.size;
    }
};

/**
 * @param {number} key
 * @param {number} value
 * @param {number} duration time until expiration in ms
 * @return {boolean} if un-expired key already existed
 */


/**
 * @param {number} key
 * @return {number} value associated with key
 */


/**
 * @return {number} count of non-expired keys
 */


/**
 * const timeLimitedCache = new TimeLimitedCache()
 * timeLimitedCache.set(1, 42, 1000); // false
 * timeLimitedCache.get(1) // 42
 * timeLimitedCache.count() // 1
 */

 var TimeLimitedCache = function() {
     this.cache = new Map();
 };

 /**
  * @param {number} key
  * @param {number} value
  * @param {number} duration time until expiration in ms
  * @return {boolean} if un-expired key already existed
  */
 TimeLimitedCache.prototype.set = function(key, value, duration) {
     const val = this.cache.get(key);

     if (val) {
         clearTimeout(val.timeout);
     }
     const timeout = setTimeout(() => {
         this.cache.delete(key);
     }, duration);
     /*
     in this way object will be created like
     {
         value: value,
         timeout: timeout
     }
     */
     const setObj = {value, timeout};
     this.cache.set(key, setObj);
     return Boolean(val)
 };

 /**
  * @param {number} key
  * @return {number} value associated with key
  */
 TimeLimitedCache.prototype.get = function(key) {
     return this.cache.has(key) ? this.cache.get(key).value : -1;

 };

 /**
  * @return {number} count of non-expired keys
  */
 TimeLimitedCache.prototype.count = function() {
     return this.cache.size;
 };

 /**
  * const timeLimitedCache = new TimeLimitedCache()
  * timeLimitedCache.set(1, 42, 1000); // false
  * timeLimitedCache.get(1) // 42
  * timeLimitedCache.count() // 1
  */

  var TimeLimitedCache = function() {
      this.cache = {};
  };

  /**
   * @param {number} key
   * @param {number} value
   * @param {number} duration time until expiration in ms
   * @return {boolean} if un-expired key already existed
   */
  TimeLimitedCache.prototype.set = function(key, value, duration) {
      const val = this.cache[key];

      if (val) {
          clearTimeout(val.timeout);
      }
      const timeout = setTimeout(() => {
          delete this.cache[key];
      }, duration);
      /*
      in this way object will be created like
      {
          value: value,
          timeout: timeout
      }
      */
      const setObj = {value, timeout};
      this.cache[key] = setObj;
      return Boolean(val)
  };

  /**
   * @param {number} key
   * @return {number} value associated with key
   */
  TimeLimitedCache.prototype.get = function(key) {
      return this.cache[key] ? this.cache[key].value : -1;

  };

  /**
   * @return {number} count of non-expired keys
   */
  TimeLimitedCache.prototype.count = function() {
      return Object.keys(this.cache).length;
  };

  /**
   * const timeLimitedCache = new TimeLimitedCache()
   * timeLimitedCache.set(1, 42, 1000); // false
   * timeLimitedCache.get(1) // 42
   * timeLimitedCache.count() // 1
   */