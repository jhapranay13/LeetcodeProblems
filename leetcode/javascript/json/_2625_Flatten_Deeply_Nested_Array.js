/*
Given a multi-dimensional array arr and a depth n, return a flattened version of that array.

A multi-dimensional array is a recursive data structure that contains integers or other multi-dimensional arrays.

A flattened array is a version of that array with some or all of the sub-arrays removed and replaced with the actual elements in that sub-array. This flattening operation should only be done if the current depth of nesting is less than n. The depth of the elements in the first array are considered to be 0.

Please solve it without the built-in Array.flat method.



Example 1:

Input
arr = [1, 2, 3, [4, 5, 6], [7, 8, [9, 10, 11], 12], [13, 14, 15]]
n = 0
Output
[1, 2, 3, [4, 5, 6], [7, 8, [9, 10, 11], 12], [13, 14, 15]]

Explanation
Passing a depth of n=0 will always result in the original array. This is because the smallest possible depth of a subarray (0) is not less than n=0. Thus, no subarray should be flattened.
Example 2:

Input
arr = [1, 2, 3, [4, 5, 6], [7, 8, [9, 10, 11], 12], [13, 14, 15]]
n = 1
Output
[1, 2, 3, 4, 5, 6, 7, 8, [9, 10, 11], 12, 13, 14, 15]

Explanation
The subarrays starting with 4, 7, and 13 are all flattened. This is because their depth of 0 is less than 1. However [9, 10, 11] remains unflattened because its depth is 1.
Example 3:

Input
arr = [[1, 2, 3], [4, 5, 6], [7, 8, [9, 10, 11], 12], [13, 14, 15]]
n = 2
Output
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]

Explanation
The maximum depth of any subarray is 1. Thus, all of them are flattened.


Constraints:

0 <= count of numbers in arr <= 105
0 <= count of subarrays in arr <= 105
maxDepth <= 1000
-1000 <= each number <= 1000
0 <= n <= 1000


EDITORIAL

Overview:
You are given a multi-dimensional array arr and an integer n. Your task is to flatten the array arr by removing sub-arrays up to a depth of n. A flattened array should only contain the actual elements from the sub-arrays, and not the sub-arrays themselves.

Use Cases:
Data Processing:

When working with data in a nested format, such as JSON or XML, flattening the structure can simplify data processing tasks. For example, if you have a nested JSON response from an API and you only need certain fields, flattening the JSON can make it easier to extract and analyze the required data.
For example:
const people = [
{name: 'Mike', items: ['hammer', 'axe']}
{name: 'Steve', items: ['rock', 'stick']}
]

const allItems = people.map(d => d.items).flat()
Tree Traversal:

In computer science, trees are often used to represent hierarchical structures. When traversing a tree-like structure, flattening can be used to convert the tree into a linear representation, allowing easier navigation and manipulation of the data.
Recursive Algorithms:

Many algorithms involve recursive operations on data structures. Flattening nested arrays can be beneficial in such cases. For instance, when implementing algorithms like depth-first search or recursive backtracking, flattening can simplify the process by providing a linear view of the data.
Database Queries:

In database systems, nested structures can be stored as arrays or JSON objects. When querying the data, flattening the nested arrays can be useful to retrieve specific elements or perform aggregations across different levels of the structure.



*/

/**
 * @param {Array} arr
 * @param {number} depth
 * @return {Array}
 */
var flat = function (arr, n) {

    if (n == 0) {
        return arr;
    }
    let result = [];

    for (element of arr) {

        if (Array.isArray(element)) {
            let res = flat(element, n - 1);
            result.push(...res);
        } else {
            result.push(element);
        }
    }
    return result;
};

/**
 * @param {Array} arr
 * @param {number} depth
 * @return {Array}
 */
var flat = function (arr, n) {

    let flatten = (nums, l) => {
        if (l == 0) {
            return [...nums];
        }
        let temp = []

        for (const element of nums) {

            if (Array.isArray(element)) {
                let res = flatten(element, l - 1);
                temp.push(...res);
            } else {
                temp.push(element);
            }
        }
        return temp;
    };
    let result = flatten(arr, n);
    return result;
};

/**
 * @param {Array} arr
 * @param {number} depth
 * @return {Array}
 */
var flat = function (arr, n) {
    let result = [];

    let flatten = (nums, l) => {
        if (l == 0) {
            result.push(...nums);
            return;
        }

        for (const element of nums) {

            if (Array.isArray(element)) {
                flatten(element, l - 1);
            } else {
                result.push(element)
            }
        }
    };
    flatten(arr, n)
    return result;
};