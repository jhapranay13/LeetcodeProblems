/*
Write a generator function that returns a generator object which yields the fibonacci sequence.

The fibonacci sequence is defined by the relation Xn = Xn-1 + Xn-2.

The first few numbers of the series are 0, 1, 1, 2, 3, 5, 8, 13.



Example 1:

Input: callCount = 5
Output: [0,1,1,2,3]
Explanation:
const gen = fibGenerator();
gen.next().value; // 0
gen.next().value; // 1
gen.next().value; // 1
gen.next().value; // 2
gen.next().value; // 3
Example 2:

Input: callCount = 0
Output: []
Explanation: gen.next() is never called so nothing is outputted


Constraints:

0 <= callCount <= 50
*/
/**
 * @return {Generator<number>}
 */
var fibGenerator = function*() {
    let prev1 = 0;
    let prev2 = 1;

    while (true) {
        yield prev1;
        let temp = prev2;
        prev2 += prev1;
        prev1 = temp;
    }
};

/**
 * const gen = fibGenerator();
 * gen.next().value; // 0
 * gen.next().value; // 1
 */
 /*
The yield keyword pauses generator function execution and the value of the expression following the yield keyword is returned to the generator's caller. It can be thought of as a generator-based version of the return keyword.

yield can only be used directly within the generator function that contains it. It cannot be used within nested functions.

Calling a generator function constructs a Generator object. Each time the generator's next() method is called, the generator resumes execution, and runs until it reaches one of the following:

A yield expression. In this case, the generator pauses, and the next() method return an iterator result object with two properties: value and done. The value property is the value of the expression after the yield operator, and done is false, indicating that the generator function has not fully completed.
The end of the generator function. In this case, execution of the generator ends, and the next() method returns an iterator result object where the value is undefined and done is true.
A return statement. In this case, execution of the generator ends, and the next() method returns an iterator result object where the value is the specified return value and done is true.
A throw statement. In this case, execution of the generator halts entirely, and the next() method throws the specified exception.
Once paused on a yield expression, the generator's code execution remains paused until the generator's next() method is called again. If an optional value is passed to the generator's next() method, that value becomes the value returned by the generator's current yield operation. The first next() call does not have a corresponding suspended yield operation, so there's no way to get the argument passed to the first next() call.

If the generator's return() or throw() method is called, it acts as if a return or throw statement was executed at the paused yield expression. You can use try...catch...finally within the generator function body to handle these early completions. If the return() or throw() method is called but there's no suspended yield expression (because next() has not been called yet, or because the generator has already completed), then the early completions cannot be handled and always terminate the generator.
 */