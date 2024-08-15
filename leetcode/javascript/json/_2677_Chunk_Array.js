/*
Given an array arr and a chunk size size, return a chunked array. A chunked array contains the original elements in arr, but consists of subarrays each of length size. The length of the last subarray may be less than size if arr.length is not evenly divisible by size.

You may assume the array is the output of JSON.parse. In other words, it is valid JSON.

Please solve it without using lodash's _.chunk function.



Example 1:

Input: arr = [1,2,3,4,5], size = 1
Output: [[1],[2],[3],[4],[5]]
Explanation: The arr has been split into subarrays each with 1 element.
Example 2:

Input: arr = [1,9,6,3,2], size = 3
Output: [[1,9,6],[3,2]]
Explanation: The arr has been split into subarrays with 3 elements. However, only two elements are left for the 2nd subarray.
Example 3:

Input: arr = [8,5,3,2,6], size = 6
Output: [[8,5,3,2,6]]
Explanation: Size is greater than arr.length thus all elements are in the first subarray.
Example 4:

Input: arr = [], size = 1
Output: []
Explanation: There are no elements to be chunked so an empty array is returned.


Constraints:

arr is a valid JSON array
2 <= JSON.stringify(arr).length <= 105
1 <= size <= arr.length + 1

Editorial

Overview:
The task is to take an array arr and a chunk size size as input, and return a chunked array. The chunked array should contain subarrays of length size, formed from the elements of the original array arr. The last subarray may have a length less than size if the length of arr is not evenly divisible by size.

Use Cases:
Pagination:

When implementing pagination in a web application, you often need to split a large list of items into smaller chunks or pages. The chunking operation allows you to divide the items into manageable portions, making it easier to display and navigate through the data.
In the example usage, let's say we have an array of 10 items and want to display 3 items per page. We specify the current page number as 2. The function will be called with these parameters, and the resulting chunked array (representing the items for the second page) should be logged to the console.
function paginateArray(array, pageSize, pageNumber) {
// Calculate the starting index of the current page
const startIndex = (pageNumber - 1) * pageSize;

// Create a chunk of the array based on the page size
const chunkedArray = array.slice(startIndex, startIndex + pageSize);

return chunkedArray;
}

// Example usage
const data = [
'Racoon 1', 'Racoon 2', 'Racoon 3', 'Racoon 4', 'Racoon 5',
'Racoon 6', 'Racoon 7', 'Racoon 8', 'Racoon 9', 'Racoon 10'
];

const pageSize = 3; // Number of items per page
const pageNumber = 2; // Current page number

const result = paginateArray(data, pageSize, pageNumber);
console.log(result);
Parallel Processing:

In parallel computing or distributed systems, data is often divided into chunks and processed simultaneously by multiple processors or nodes. Chunking the data allows for efficient distribution and parallel execution of tasks, improving overall performance.
Image Processing:

In image processing applications, large images are often divided into smaller blocks or tiles to enable processing and analysis at a more granular level. Each tile can be independently processed, allowing for parallelization and efficient utilization of computational resources.
Data Analysis and Aggregation:

When dealing with large datasets, it can be beneficial to divide the data into smaller chunks for analysis and aggregation purposes. This approach allows for parallel or distributed processing, enabling faster data processing and efficient resource utilization.
File Upload and Transfer:

When uploading or transferring large files, the data is typically sent in smaller chunks to handle potential network limitations and ensure reliable delivery. The receiving end can process each chunk independently and reassemble them to reconstruct the original file.

*/

/**
 * @param {Array} arr
 * @param {number} size
 * @return {Array}
 */
var chunk = function(arr, size) {

    if (arr.length == 0) {
        return [];
    }
    let temp = [];
    let ans = [];

    for (let i = 0; i < arr.length; i++) {
        temp.push(arr[i]);

        if ((i + 1) % size == 0) {
            ans.push(temp);
            temp = [];
        }
    }

    if (temp.length > 0) {
        ans.push(temp);
    }
    return ans;
};

/**
 * @param {Array} arr
 * @param {number} size
 * @return {Array}
 */
var chunk = function(arr, size) {
    // slice(optional start parameter, optional end parameter)
    if (arr.length == 0) {
        return [];
    }
    let ans = [];

    for (let i = 0; i < arr.length; i += size) {
        ans.push(arr.slice(i, i + size));
    }
    return ans;
};

/**
 * @param {Array} arr
 * @param {number} size
 * @return {Array}
 */
var chunk = function(arr, size) {
    // slice(optional start parameter, optional end parameter) does not alter array. Shallow copy
    // splice(start, optional delete count, optional items to add) alters the array adding value array.splice(1, 0, val) will add one to 1st index
    if (arr.length == 0) {
        return [];
    }
    let ans = [];

    while (arr.length !== 0) {
        ans.push(arr.splice(0, size));
    }
    return ans;
};
