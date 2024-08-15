/*
Given an object or an array, return if it is empty.

An empty object contains no key-value pairs.
An empty array contains no elements.
You may assume the object or array is the output of JSON.parse.



Example 1:

Input: obj = {"x": 5, "y": 42}
Output: false
Explanation: The object has 2 key-value pairs so it is not empty.
Example 2:

Input: obj = {}
Output: true
Explanation: The object doesn't have any key-value pairs so it is empty.
Example 3:

Input: obj = [null, false, 0]
Output: false
Explanation: The array has 3 elements so it is not empty.


Constraints:

obj is a valid JSON object or array
2 <= JSON.stringify(obj).length <= 105

EDITORIAL

Overview:
The task is to determine whether an input object or array is empty. An empty object should not contain any key-value pairs, while an empty array should not have any elements. The input is assumed to be the output of JSON.parse.

Before we go any further, let us first clarify a few points that you will hear a lot about:

JSON:
JSON (JavaScript Object Notation) is a popular data-interchange format that serves as a lightweight alternative to XML. It is widely used for transmitting and storing data in a structured format.
JSON consists of two main data structures: objects and arrays. The data is represented as a combination of key-value pairs, enclosed in curly braces {} for objects, and square brackets [] for arrays. The keys in an object must be strings, while the values can be any valid JSON data type, including objects and arrays.
Example of a JSON Object:

{
  "name": "Pavitr Prabhakar",
  "age": 17,
  "city": "Mumbattan"
}
Example of a JSON Array:

[
  "Peter",
  "Gwen",
  "Miles"
]
JSON.parse():
JSON.parse() is a built-in JavaScript function that converts a JSON string into a JavaScript object, arrayor a primitive value (such as a string, number, boolean, or null). It takes a valid JSON string as input and returns a corresponding JavaScript object, array or primitive value. This allows developers to work with JSON data in a native JavaScript format.

Example of using JSON.parse():

const jsonString = '{"name":"Pavitr Prabhakar","age"17,"city":"Mumbattan"}';
const parsedObject = JSON.parse(jsonString);
console.log(parsedObject.name); // Output: Pavitr Prabhakar
console.log(parsedObject.age); // Output: 17
console.log(parsedObject.city); // Output: Mumbattan
Objects in JavaScript:
Objects are used to store collections of key-value pairs. The keys of an object can be any value that can be converted to a string, and the corresponding values can be of any data type, including objects and arrays.

Example of using Objects:

const person = {
  name: "Pavitr Prabhakar",
  age: 17,
  city: "Mumbattan"
};
console.log(person.name); // Output: Pavitr Prabhakar
console.log(person.age); // Output: 17
console.log(person.city); // Output: Mumbattan
Now how to find length or size?
In JavaScript, the length or size property is used to determine the number of elements in an array or the number of key-value pairs in an object. For arrays, the length property returns the highest numeric index plus one. For objects, the length property is not available, so we need to use other methods like Object.keys() to get the number of key-value pairs.

Example of using length property:

const spiders = ["Peter", "Gwen", "Miles"];
console.log(spiders.length); // Output: 3

const person = {
  name: "Pavitr Prabhakar",
  age: 17,
  city: "Mumbattan"
};
console.log(Object.keys(person).length); // Output: 3
Approaches:
The first way is to use JSON.stringify to convert the input array/object to a string. If the array or object is empty, it returns a string with opening and closing braces or curly braces.

The second approach is to use Object.keys() as suggested above to obtain the length and then verify if it is empty or not.

The third approach is to just use a for loop iterator to check whether there is something to iterate, and if there is, it implies the object is not empty, and if there is nothing to iterate, it implies the object is empty.

*/

/**
 * @param {Object|Array} obj
 * @return {boolean}
 */
var isEmpty = function(obj) {
    return Object.keys(obj).length === 0
};

/**
 * @param {Object|Array} obj
 * @return {boolean}
 */
var isEmpty = function(obj) {
    /*
        {
        "name": "Pavitr Prabhakar",
        "age": 17,
        "city": "Mumbattan"
        }

        will stringify to {"name":"Pavitr Prabhakar","age":17,"city":"Mumbattan"}

    */
    return JSON.stringify(obj).length <= 2
};