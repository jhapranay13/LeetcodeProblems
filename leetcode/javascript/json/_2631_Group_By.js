/*
Write code that enhances all arrays such that you can call the array.groupBy(fn) method on any array and it will return a grouped version of the array.

A grouped array is an object where each key is the output of fn(arr[i]) and each value is an array containing all items in the original array with that key.

The provided callback fn will accept an item in the array and return a string key.

The order of each value list should be the order the items appear in the array. Any order of keys is acceptable.

Please solve it without lodash's _.groupBy function.



Example 1:

Input:
array = [
  {"id":"1"},
  {"id":"1"},
  {"id":"2"}
],
fn = function (item) {
  return item.id;
}
Output:
{
  "1": [{"id": "1"}, {"id": "1"}],
  "2": [{"id": "2"}]
}
Explanation:
Output is from array.groupBy(fn).
The selector function gets the "id" out of each item in the array.
There are two objects with an "id" of 1. Both of those objects are put in the first array.
There is one object with an "id" of 2. That object is put in the second array.
Example 2:

Input:
array = [
  [1, 2, 3],
  [1, 3, 5],
  [1, 5, 9]
]
fn = function (list) {
  return String(list[0]);
}
Output:
{
  "1": [[1, 2, 3], [1, 3, 5], [1, 5, 9]]
}
Explanation:
The array can be of any type. In this case, the selector function defines the key as being the first element in the array.
All the arrays have 1 as their first element so they are grouped together.
{
  "1": [[1, 2, 3], [1, 3, 5], [1, 5, 9]]
}
Example 3:

Input:
array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
fn = function (n) {
  return String(n > 5);
}
Output:
{
  "true": [6, 7, 8, 9, 10],
  "false": [1, 2, 3, 4, 5]
}
Explanation:
The selector function splits the array by whether each number is greater than 5.


Constraints:

0 <= array.length <= 105
fn returns a string


EDITORIAL

Overview
This question asks you to add the groupBy method to all arrays. Consider reading the Array Last Editorial for more information on adding methods by modifying the prototype object.

The groupBy operation accepts a callback function and returns a new object. The keys on this object are all the unique outputs of the callback function when it is applied to all the items in the array. Each key should have an associated array value. This array should contain all the values in the original array where the callback function returned that same key (sorted by the original order).

Note that the groupBy by method isn't built-in by default, so the example code will only run if the method is added to the Array prototype. Also you can see Lodash's popular implementation here.

To give a concrete example of groupBy in action:

const list = [
  { name: 'Alice', birthYear: 1990 },
  { name: 'Bob', birthYear: 1972 },
  { name: 'Jose', birthYear: 1999 },
  { name: 'Claudia', birthYear: 1974 },
  { name: 'Marcos', birthYear: 1995 }
]
const groupedByDecade = list.groupBy((person) =>  {
  const decade = Math.floor(person.birthYear / 10) * 10;
  return String(decade);
});
console.log(groupedByDecade);

{
  "1990": [
    { name: 'Alice', birthYear: 1990 },
    { name: 'Jose', birthYear: 1999 },
    { name: 'Marcos', birthYear: 1995 }
  ],
  "1970": [
    { name: 'Bob', birthYear: 1972 },
    { name: 'Claudia', birthYear: 1974 }
  ]
}

Use-cases for the Group Operation
Grouping a list is an extremely common thing to need to do front-end development and software engineering in general. Here are are few example use-cases.

Build Hierarchical Trees
If you want to build a tree of data, you can use perform groupBy on the list, and then groupBy on the values of the resulting object, and so on. This will result in a tree data structure that could be further used in algorithms where you need efficient lookup based on several keys. Or the tree could be used as an input to a tree visualization like some sort of expandable list.

Some example code that builds this tree:

function buildTree(list, keys, index = 0) {
  if (index >= keys.length) return list;
  const group = list.groupBy((item) => item[keys[index]]);
  Object.keys(group).forEach((key) => {
    const list = group[key]
    group[key] = buildTree(list, keys, index + 1);
  });
  return group;
}

buildTree([{a: 1, b: 2}, {a: 1, b: 3}], ['a', 'b']);

{
  "1": {
    "2": [{a: 1, b: 2}],
    "3": [{a: 1, b: 3}]
  }
}

Join Data on Two Lists
Frequently, you have multiple lists of data, but you need to efficiently merge them into one list for use by some algorithm or user interface. In the context of database, this is considered a join but you frequently need to do this in regular code as well.

The following examples shows how you could combine decades data with people data to create a decadesWithPeople variable.

const people = [
  { name: 'Alice', birthYear: 1990 },
  { name: 'Bob', birthYear: 1972 },
  { name: 'Jose', birthYear: 1999 },
  { name: 'Claudia', birthYear: 1974 },
  { name: 'Marcos', birthYear: 1995 }
];

const decades = [
  { start: 1970, theme: 'Disco',
  { start: 1980, theme: 'Arcades' },
  { start: 1990, theme: 'Beanie Babies' }
];

const groupedByDecade = list.groupBy((person) =>  {
  const decade = Math.floor(person.birthYear / 10) * 10;
  return String(decade);
});

const decadesWithPeople = decade.map((decade) => {
  return {
    ...decade,
    people: groupedByDecade[decade.start] || [],
  };
});
Categorical Bar Chart
The first step before you can can create a bar chart where each bar represents some category is to group the data by the category. For example, in the above example, you could calculate the average age of people by birth decade using the groupedByDecade variable.

*/

/**
 * @param {Function} fn
 * @return {Object}
 */
Array.prototype.groupBy = function(fn) {
    const result = {};

    for (let item of this) {
        const key = fn(item);
        result[key] ||= [];   // logical or assignment
        result[key].push(item);
    }
    return result;
};

/**
 * [1,2,3].groupBy(String) // {"1":[1],"2":[2],"3":[3]}
 */

 /**
  * @param {Function} fn
  * @return {Object}
  */
 Array.prototype.groupBy = function(fn) {
     const result = {};

     for (let item of this) {
         const key = fn(item);

         if (!result[key]) {
             result[key] = [];
         }
         result[key].push(item);
     }
     return result;
 };

 /**
  * [1,2,3].groupBy(String) // {"1":[1],"2":[2],"3":[3]}
  */