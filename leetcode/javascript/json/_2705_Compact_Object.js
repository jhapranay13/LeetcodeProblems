/*
Given an object or array obj, return a compact object. A compact object is the same as the original object, except with keys containing falsy values removed. This operation applies to the object and any nested objects. Arrays are considered objects where the indices are keys. A value is considered falsy when Boolean(value) returns false.

You may assume the obj is the output of JSON.parse. In other words, it is valid JSON.



Example 1:

Input: obj = [null, 0, false, 1]
Output: [1]
Explanation: All falsy values have been removed from the array.
Example 2:

Input: obj = {"a": null, "b": [false, 1]}
Output: {"b": [1]}
Explanation: obj["a"] and obj["b"][0] had falsy values and were removed.
Example 3:

Input: obj = [null, 0, 5, [0], [false, 16]]
Output: [5, [], [16]]
Explanation: obj[0], obj[1], obj[3][0], and obj[4][0] were falsy and removed.


Constraints:

obj is a valid JSON object
2 <= JSON.stringify(obj).length <= 106

EDITORIAL

Overview
In this problem, you are required to implement a JavaScript function compactObject, which receives a JSON object as input and returns a "compact" version of the object. The term "compact" refers to the original object but with all keys associated with falsy values removed. This removal process is applied not just to the top-level object but also to any nested objects or arrays. In the context of this problem, arrays are treated as objects where the indices are keys. A value is considered falsy when Boolean(value) returns false.

Three examples are given to illustrate the behavior of the function. The first example demonstrates how all falsy values in an array (which is a special type of object) are removed. The second example shows the removal of a key-value pair from an object due to a falsy value. The third example, a bit more complex, demonstrates the removal of falsy values in a nested structure involving both objects and arrays.

Mastering this problem demands a firm grasp on JavaScript's object and array manipulation, particularly in terms of iterating through them and modifying their content. Moreover, it is crucial to understand what constitutes a falsy value in JavaScript.

For a comprehensive understanding of JavaScript's objects and arrays, recursion, and value comparison, we also recommend trying out the following problems:

JSON Deep Equal
Convert Object to JSON String
Differences Between Two Objects
For beginners who are new to recursion, we recommend studying our Recursion I card for a detailed introduction and more practice problems.

Handling Falsy Values:
In JavaScript, a value is considered falsy if it converts to false when evaluated in a boolean context. This includes false, 0, -0, '' and "" (empty string), null, undefined, and NaN.

In our problem, we want to remove keys that have a falsy value. To do this, we use a check if(!obj) return false; in our function. This concise check effectively captures all falsy values, allowing us to ignore them in the output.

This condition also correctly handles null. Despite typeof null in JavaScript returning object due to historical reasons, null is indeed a falsy value. It's a primitive value that represents the absence of any object value. Therefore, in our context, keys with a null value will also be ignored.

Use Cases of compactObject
The compactObject function can be a powerful tool in JavaScript applications that involve processing and manipulating JSON data. Its primary function is to prune an object (or an array, considered an object with indices as keys in JavaScript) of keys that have falsy values, including nested keys. Here are some general areas where such functionality might be useful:

Data Cleaning: In many real-world applications, data often comes from various sources in different formats, sometimes with unnecessary keys or keys with falsy values. Using compactObject can help clean this data before further processing. For instance, if we have a nested object such as var obj = { key1: "", key2: { key3: null, key4: "value" }}, compactObject will return { key2: { key4: "value" }}, effectively eliminating the empty or null values.

API Response Processing: When working with responses from third-party APIs, it's not uncommon to find keys with falsy values that could potentially lead to issues if not handled properly. compactObject can be used to remove these keys, ensuring the API response is cleaner and more predictable for subsequent operations.

UI Rendering: Before rendering data to the UI, it can be beneficial to remove any keys with falsy values to create a cleaner user interface. For example, consider a UI component that takes an object to display a user profile. If the object includes fields with null or undefined values, it could lead to blank spaces or errors in the UI. By using compactObject, we can remove these fields before passing the object to the UI component.

Optimizing Storage: When storing data, using compactObject to remove keys with falsy values can help optimize the storage utilization by ensuring only meaningful data is saved.

It's important to note that the usage of compactObject highly depends on the specific requirements and context of your application. There might be cases where preserving keys with falsy values is necessary. Therefore, always consider your specific use case before deciding to apply this function.

*/

/**
 * @param {Object|Array} obj
 * @return {Object|Array}
 */
var compactObject = function(obj) {

    let dfs = function(param) {

        if (!param) {
            return false;
        }

        if (typeof param !== 'object') {
            return param;
        }

        if (Array.isArray(param)) {
            const holder = [];

            for (const elem of param) {
                const ret = dfs(elem);

                if (ret) {
                    holder.push(ret);
                }
            }
            return holder;
        }
        const result = {};

        for (const key in param) {
            const ret = dfs(param[key]);

            if (ret) {
                result[key] = ret;
            }
        }
        return result;
    };
    return dfs(obj);
};


/**
 * @param {Object|Array} obj
 * @return {Object|Array}
 */
var compactObject = function(obj) {

    let dfs = function(param) {

        if (!param) {
            return false;
        }

        if (!(param instanceof Object)) {
            return param;
        }

        if (Array.isArray(param)) {
            const holder = [];

            for (const elem of param) {
                const ret = dfs(elem);

                if (ret) {
                    holder.push(ret);
                }
            }
            return holder;
        }
        const result = {};

        for (const key in param) {
            const ret = dfs(param[key]);

            if (ret) {
                result[key] = ret;
            }
        }
        return result;
    };
    return dfs(obj);
};