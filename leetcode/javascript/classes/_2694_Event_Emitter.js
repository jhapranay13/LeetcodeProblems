/*
Design an EventEmitter class. This interface is similar (but with some differences) to the one found in Node.js or the Event Target interface of the DOM. The EventEmitter should allow for subscribing to events and emitting them.

Your EventEmitter class should have the following two methods:

subscribe - This method takes in two arguments: the name of an event as a string and a callback function. This callback function will later be called when the event is emitted.
An event should be able to have multiple listeners for the same event. When emitting an event with multiple callbacks, each should be called in the order in which they were subscribed. An array of results should be returned. You can assume no callbacks passed to subscribe are referentially identical.
The subscribe method should also return an object with an unsubscribe method that enables the user to unsubscribe. When it is called, the callback should be removed from the list of subscriptions and undefined should be returned.
emit - This method takes in two arguments: the name of an event as a string and an optional array of arguments that will be passed to the callback(s). If there are no callbacks subscribed to the given event, return an empty array. Otherwise, return an array of the results of all callback calls in the order they were subscribed.


Example 1:

Input:
actions = ["EventEmitter", "emit", "subscribe", "subscribe", "emit"],
values = [[], ["firstEvent", "function cb1() { return 5; }"],  ["firstEvent", "function cb1() { return 6; }"], ["firstEvent"]]
Output: [[],["emitted",[]],["subscribed"],["subscribed"],["emitted",[5,6]]]
Explanation:
const emitter = new EventEmitter();
emitter.emit("firstEvent"); // [], no callback are subscribed yet
emitter.subscribe("firstEvent", function cb1() { return 5; });
emitter.subscribe("firstEvent", function cb2() { return 6; });
emitter.emit("firstEvent"); // [5, 6], returns the output of cb1 and cb2
Example 2:

Input:
actions = ["EventEmitter", "subscribe", "emit", "emit"],
values = [[], ["firstEvent", "function cb1(...args) { return args.join(','); }"], ["firstEvent", [1,2,3]], ["firstEvent", [3,4,6]]]
Output: [[],["subscribed"],["emitted",["1,2,3"]],["emitted",["3,4,6"]]]
Explanation: Note that the emit method should be able to accept an OPTIONAL array of arguments.

const emitter = new EventEmitter();
emitter.subscribe("firstEvent, function cb1(...args) { return args.join(','); });
emitter.emit("firstEvent", [1, 2, 3]); // ["1,2,3"]
emitter.emit("firstEvent", [3, 4, 6]); // ["3,4,6"]
Example 3:

Input:
actions = ["EventEmitter", "subscribe", "emit", "unsubscribe", "emit"],
values = [[], ["firstEvent", "(...args) => args.join(',')"], ["firstEvent", [1,2,3]], [0], ["firstEvent", [4,5,6]]]
Output: [[],["subscribed"],["emitted",["1,2,3"]],["unsubscribed",0],["emitted",[]]]
Explanation:
const emitter = new EventEmitter();
const sub = emitter.subscribe("firstEvent", (...args) => args.join(','));
emitter.emit("firstEvent", [1, 2, 3]); // ["1,2,3"]
sub.unsubscribe(); // undefined
emitter.emit("firstEvent", [4, 5, 6]); // [], there are no subscriptions
Example 4:

Input:
actions = ["EventEmitter", "subscribe", "subscribe", "unsubscribe", "emit"],
values = [[], ["firstEvent", "x => x + 1"], ["firstEvent", "x => x + 2"], [0], ["firstEvent", [5]]]
Output: [[],["subscribed"],["emitted",["1,2,3"]],["unsubscribed",0],["emitted",[7]]]
Explanation:
const emitter = new EventEmitter();
const sub1 = emitter.subscribe("firstEvent", x => x + 1);
const sub2 = emitter.subscribe("firstEvent", x => x + 2);
sub1.unsubscribe(); // undefined
emitter.emit("firstEvent", [5]); // [7]


Constraints:

1 <= actions.length <= 10
values.length === actions.length
All test cases are valid, e.g. you don't need to handle scenarios when unsubscribing from a non-existing subscription.
There are only 4 different actions: EventEmitter, emit, subscribe, and unsubscribe.
The EventEmitter action doesn't take any arguments.
The emit action takes between either 1 or 2 arguments. The first argument is the name of the event we want to emit, and the 2nd argument is passed to the callback functions.
The subscribe action takes 2 arguments, where the first one is the event name and the second is the callback function.
The unsubscribe action takes one argument, which is the 0-indexed order of the subscription made before.


EDITORIAL

Overview:
We are tasked with designing an EventEmitter class that allows for subscribing to events and emitting them. The EventEmitter should have the following two methods:

subscribe(eventName, callback): This method takes in the name of an event as a string and a callback function. The callback function will be called when the event is emitted. An event should be able to have multiple listeners for the same event. The callbacks should be called in the order they were subscribed. The subscribe method should return an object with an unsubscribe method that can be used to remove the callback from the list of subscriptions.
emit(eventName, args): This method takes in the name of an event as a string and an optional array of arguments. It should trigger the callbacks associated with the eventName, passing the provided arguments to each callback. If there are no callbacks subscribed to the given event, the method should return an empty array. Otherwise, it should return an array containing the results of all callback calls in the order they were subscribed.
Before going any further let us understand meaning of few terms:
Events and Event-driven Programming:

Events represent things that happen in a program. For example, when a user clicks a button, it triggers a "click" event.
Event-driven programming focuses on responding to events rather than following a fixed sequence of steps. It allows programs to react to user interactions and external changes.
Example: Imagine a game where the player's character moves when the arrow keys are pressed. The game uses events to detect key presses and update the character's position accordingly.
EventEmitter:

An EventEmitter is a tool or class that manages events in a program. It allows components to subscribe to events and receive notifications when those events occur.
Example: Think of an EventEmitter as a radio station. It broadcasts different types of shows (events), and listeners (components) can tune in to listen to specific shows they are interested in.
Subscriptions and Callbacks:

Subscriptions allow components to express their interest in specific events. They specify which events they want to listen to.
Callbacks, also known as event handlers, are functions that get executed when the subscribed event occurs.
Example: In a messaging app, a user can subscribe to the "newMessage" event to receive notifications when a new message is received. The callback function could display the message on the screen.
// Callback function for handling new messages
function handleMessageReceived(message) {
console.log("New message received:", message);
}

// Subscribe the callback function to the "newMessage" event
eventEmitter.subscribe("newMessage", handleMessageReceived);
Order of Callback Execution:

When multiple listeners subscribe to the same event, the callbacks are executed in the order they were subscribed.
Example: Imagine a social media app where users can like a post. Each like triggers the "postLiked" event, and all subscribed callbacks should execute in the order they were registered.
Unsubscribing from Events:

Subscriptions can be canceled or removed when components no longer want to receive event notifications.
Example: In a notification system, users may want to unsubscribe from email notifications after they have configured their preferences.
// Subscribe a callback function to an event and get the unsubscribe method
const subscription = eventEmitter.subscribe("eventName", callback);

// Unsubscribe from the event by calling the unsubscribe method
subscription.unsubscribe();
Event Arguments:

Events can carry additional information or data, known as event arguments, which are passed to the callback functions.
Example: In a weather app, the "weatherUpdate" event may include arguments such as temperature, humidity, and weather conditions. The callback function can use these arguments to update the UI.
// Callback function for handling weather updates
function handleWeatherUpdate(weatherData) {
console.log("Temperature:", weatherData.temperature);
console.log("Humidity:", weatherData.humidity);
}

// Subscribe the callback function to the "weatherUpdate" event
eventEmitter.subscribe("weatherUpdate", handleWeatherUpdate);
Return Values:

Callbacks can perform actions or computations and return values based on their functionality.
Example: In a calculator app, a callback function subscribed to the "calculate" event may receive arguments like numbers and an operation. It can perform the calculation and return the result.
// Callback function for handling calculations
function handleCalculation(numbers, operation) {
if (operation === "add") {
    return numbers.reduce((a, b) => a + b, 0);
} else if (operation === "multiply") {
    return numbers.reduce((a, b) => a * b, 1);
}
}

// Subscribe the callback function to the "calculate" event
eventEmitter.subscribe("calculate", handleCalculation);
Use Cases:
User Interface (UI) Interactions: In web development, an EventEmitter can be used to handle user interactions such as button clicks, form submissions, or menu selections. Components can subscribe to these events and perform appropriate actions or updates when the events are emitted.
// Create an EventEmitter instance
const eventEmitter = new EventEmitter();

// Subscribe to a button click event
eventEmitter.subscribe("buttonClick", () => {
console.log("Button clicked!");
});

// Emit the button click event
eventEmitter.emit("buttonClick");
Asynchronous Operations: When working with asynchronous operations like fetching data from an API or handling database queries, an EventEmitter can be used to notify components or modules about the completion or status of these operations. Subscribed callbacks can then handle the returned data or trigger subsequent actions.
// Create an EventEmitter instance
const eventEmitter = new EventEmitter();

// Simulate an asynchronous operation
function fetchData() {
setTimeout(() => {
    const data = "Some fetched data";
    // Emit the event with the fetched data
    eventEmitter.emit("dataFetched", data);
}, 2000);
}

// Subscribe to the dataFetched event
eventEmitter.subscribe("dataFetched", (data) => {
console.log("Data fetched:", data);
});

// Trigger the asynchronous operation
fetchData();
Custom Event-driven Systems: EventEmitters can be used to build custom event-driven systems for specific application needs. For example, in a game engine, an EventEmitter can be used to manage events like player movement, collision detection, or game state changes. Components, such as game objects or UI elements, can subscribe to these events and respond accordingly.
// Create an EventEmitter instance
const eventEmitter = new EventEmitter();

// Game state change event
eventEmitter.subscribe("gameStateChange", (newState) => {
console.log("Game state changed:", newState);
});

// Player movement event
eventEmitter.subscribe("playerMovement", (movement) => {
console.log("Player moved:", movement);
});

// Emit game events
eventEmitter.emit("gameStateChange", "start");
eventEmitter.emit("playerMovement", "left");
Logging and Error Handling: An EventEmitter can be utilized to handle logging and error events. Subscribed callbacks can capture error events, log them to a file or console, and perform error handling tasks such as sending error reports or displaying error messages to the user.
// Create an EventEmitter instance
const eventEmitter = new EventEmitter();

// Error event
eventEmitter.subscribe("error", (errorMessage) => {
console.error("Error occurred:", errorMessage);
});

// Log event
eventEmitter.subscribe("log", (message) => {
console.log("Log message:", message);
});

// Emit logging and error events
eventEmitter.emit("error", "Something went wrong!");
eventEmitter.emit("log", "Info: Application started.");
Event-driven Architectures: EventEmitters are a fundamental building block in event-driven architectures. They enable loose coupling and decoupling of components by allowing them to communicate through events. This promotes modularity and scalability in large-scale applications.

*/

class EventEmitter {

    constructor() {
        this.events = {};
    }

    /**
     * @param {string} eventName
     * @param {Function} callback
     * @return {Object}
     */
    subscribe(eventName, callback) {
        // nullish coalescing operator  if the left side is null then return right side
        this.events[eventName] = this.events[eventName] ?? [];
        this.events[eventName].push(callback);

        return {
            unsubscribe: () => {
                // filter creates shallow copy
                this.events[eventName] = this.events[eventName].filter((fn) => {

                    if (fn == callback) {
                        return false;
                    }
                    return true;
                });

                if (this.events[eventName].length === 0) {
                    // delete a property from object
                    delete this.events[eventName];
                }
            }
        };
    }

    /**
     * @param {string} eventName
     * @param {Array} args
     * @return {Array}
     */
    emit(eventName, args = []) {

        if (!this.events[eventName]) {
            return [];
        }
        return this.events[eventName].map((fn) => {
            return fn(...args);
        });
    }
}

/**
 * const emitter = new EventEmitter();
 *
 * // Subscribe to the onClick event with onClickCallback
 * function onClickCallback() { return 99 }
 * const sub = emitter.subscribe('onClick', onClickCallback);
 *
 * emitter.emit('onClick'); // [99]
 * sub.unsubscribe(); // undefined
 * emitter.emit('onClick'); // []
 */

 class EventEmitter {

     constructor() {
         this.events = new Map();
     }

     /**
      * @param {string} eventName
      * @param {Function} callback
      * @return {Object}
      */
     subscribe(eventName, callback) {

         if (!this.events.has(eventName)) {
             this.events.set(eventName, []);
         }
         this.events.get(eventName).push(callback);

         return {
             unsubscribe: () => {

                 if (this.events.get(eventName).indexOf(callback) !== -1) {
                     let index = this.events.get(eventName).indexOf(callback);
                     this.events.get(eventName).splice(index, 1); // modify the array by delteing one element starting from index
                 }
             }
         };
     }

     /**
      * @param {string} eventName
      * @param {Array} args
      * @return {Array}
      */
     emit(eventName, args = []) {

         if (!this.events.has(eventName)) {
             return [];
         }
         return this.events.get(eventName).map((fn) => {
             return fn(...args);
         });
     }
 }

 /**
  * const emitter = new EventEmitter();
  *
  * // Subscribe to the onClick event with onClickCallback
  * function onClickCallback() { return 99 }
  * const sub = emitter.subscribe('onClick', onClickCallback);
  *
  * emitter.emit('onClick'); // [99]
  * sub.unsubscribe(); // undefined
  * emitter.emit('onClick'); // []
  */