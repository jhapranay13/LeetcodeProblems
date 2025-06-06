package LeetcodeDiscuss;


import java.util.*;

/**
 * You work for a company with more than a million employees when a pandemic hit.
 *
 * The virus is likely spread when people are together in a room.
 *
 * You need to create a system that can do contact tracing so that the company can warn employees to stay home if they were exposed.
 *
 * You have records of when someone entered and exit a room in the following format: time (int), username (string), room (string), enter/exit.
 *
 * For example:
 *
 * 0, Navi, meetingRoomA, enter
 *
 * 5, jeff, meetingRoomA, enter
 *
 * 10, Navi, meetingRoomA, exit
 *
 * 15, jeff, meetingRoomA, exit
 *
 * Given a username of someone who had the disease,
 *
 * list out the usernames of everyone who stayed in the same room with them.
 *
 * Input: Jeff
 *
 * Outpu: Navi
 */
public class AmazonOAContactTracer {
    // Represents a single log entry
    static class LogEntry implements Comparable<LogEntry> {
        int time;
        String username;
        String room;
        String eventType; // "enter" or "exit"

        public LogEntry(int time, String username, String room, String eventType) {
            this.time = time;
            this.username = username;
            this.room = room;
            this.eventType = eventType;
        }

        @Override
        public int compareTo(LogEntry other) {
            // Sort by time primarily
            if (this.time != other.time) {
                return Integer.compare(this.time, other.time);
            }
            // If times are the same, prioritize 'exit' over 'enter' for more accurate
            // snapshot of occupants before new entries.
            // This is a subtle point, but can prevent a person from being counted
            // as an 'in-room' contact if they exit at the exact same time as the infected
            // person enters, but conceptually they left *before* the infected person settled in.
            // However, for strict 'any overlap', it might not be strictly necessary, but often safer.
            if (this.eventType.equals("exit") && other.eventType.equals("enter")) {
                return -1;
            }
            if (this.eventType.equals("enter") && other.eventType.equals("exit")) {
                return 1;
            }
            return 0; // Maintain original order for same time, same event
        }

        @Override
        public String toString() {
            return "{" +
                    "time=" + time +
                    ", username='" + username + '\'' +
                    ", room='" + room + '\'' +
                    ", eventType='" + eventType + '\'' +
                    '}';
        }
    }

    /**
     * Lists all usernames of employees who shared a room with the infected person.
     *
     * @param logs The raw log entries as an array of strings.
     * @param infectedUsername The username of the person who tested positive.
     * @return A Set of usernames who were in the same room as the infected person.
     */
    public Set<String> getContacts(String[] logs, String infectedUsername) {
        List<LogEntry> parsedLogs = new ArrayList<>();
        for (String log : logs) {
            String[] parts = log.split(", ");
            parsedLogs.add(new LogEntry(
                    Integer.parseInt(parts[0]),
                    parts[1],
                    parts[2],
                    parts[3]
            ));
        }

        // Sort logs chronologically
        Collections.sort(parsedLogs);

        // Map to track current occupants in each room
        // Key: room name, Value: Set of usernames currently in that room
        Map<String, Set<String>> roomOccupancy = new HashMap<>();

        // Set to store unique contacts
        Set<String> infectedContacts = new HashSet<>();

        for (LogEntry entry : parsedLogs) {
            String currentRoom = entry.room;
            String currentUsername = entry.username;
            String currentEventType = entry.eventType;

            // Get or create the set of occupants for the current room
            Set<String> occupants = roomOccupancy.computeIfAbsent(currentRoom, k -> new HashSet<>());

            if (currentEventType.equals("enter")) {
                // Scenario 1: Infected person enters, and others are already there.
                if (currentUsername.equals(infectedUsername)) {
                    // Add everyone already in the room (except self) to contacts
                    occupants.stream()
                            .filter(user -> !user.equals(infectedUsername))
                            .forEach(infectedContacts::add);
                }
                // Scenario 2: Another person enters, and the infected person is already there.
                else if (occupants.contains(infectedUsername)) {
                    // Add the entering user to contacts
                    infectedContacts.add(currentUsername);
                }
                // Add the current user to the room occupants
                occupants.add(currentUsername);

            } else { // eventType is "exit"
                // Simply remove the user from the room
                occupants.remove(currentUsername);
            }
        }

        // Ensure the infected person themselves is not listed as a contact
        infectedContacts.remove(infectedUsername);

        return infectedContacts;
    }

    public static void main(String[] args) {
        AmazonOAContactTracer tracer = new AmazonOAContactTracer();

        String[] logs1 = {
                "0, Navi, meetingRoomA, enter",
                "5, jeff, meetingRoomA, enter",
                "10, Navi, meetingRoomA, exit",
                "15, jeff, meetingRoomA, exit"
        };
        String infected1 = "jeff";
        Set<String> contacts1 = tracer.getContacts(logs1, infected1);
        System.out.println("Infected: " + infected1 + ", Contacts: " + contacts1); // Expected: [Navi]

        System.out.println("---");

        String[] logs2 = {
                "0, Alice, kitchen, enter",
                "2, Bob, kitchen, enter",
                "3, Carol, kitchen, enter",
                "5, Alice, kitchen, exit",
                "7, Bob, kitchen, exit",
                "8, David, kitchen, enter",
                "10, Carol, kitchen, exit"
        };
        String infected2 = "Bob";
        Set<String> contacts2 = tracer.getContacts(logs2, infected2);
        System.out.println("Infected: " + infected2 + ", Contacts: " + contacts2); // Expected: [Alice, Carol, David]
        // Explanation for contacts2:
        // Bob enters at 2. Alice and Carol are already there. -> Alice, Carol are contacts.
        // Bob exits at 7. David enters at 8. Bob doesn't interact with David.
        // Re-check: Alice: (0-5), Bob: (2-7), Carol: (3-10), David: (8-?)
        // Bob is in room from 2 to 7.
        // During 2-5: Alice is in room. Bob enters at 2. Alice is already there. -> Alice is contact.
        // During 3-7: Carol is in room. Bob enters at 2. Carol enters at 3. Bob is already there. -> Carol is contact.
        // During 8-?: David enters. Bob has already left. David is NOT a contact.
        // Output will be [Alice, Carol]

        System.out.println("---");

        String[] logs3 = {
                "0, A, R1, enter",
                "1, B, R1, enter",
                "2, C, R2, enter",
                "3, A, R1, exit",
                "4, D, R1, enter",
                "5, E, R2, enter",
                "6, B, R1, exit",
                "7, F, R2, enter"
        };
        String infected3 = "B";
        Set<String> contacts3 = tracer.getContacts(logs3, infected3);
        System.out.println("Infected: " + infected3 + ", Contacts: " + contacts3); // Expected: [A, D]
        // Explanation for contacts3:
        // B enters R1 at 1. A is already there. -> A is a contact.
        // A exits R1 at 3.
        // D enters R1 at 4. B is still there. -> D is a contact.
        // B exits R1 at 6.
        // C, E, F are in R2, B is never in R2.
    }
}
