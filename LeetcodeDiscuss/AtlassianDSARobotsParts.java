package LeetcodeDiscuss;

import java.util.*;

/**
 *
 * You're working in a robotics factory. Each robot has a unique name, and each robot requires a standard set of parts
 * (e.g., "sensors", "case", "speaker", etc.) to be fully assembled.
 *
 * All parts in the factory bin are labeled in the format:
 * <robot_name>_<part_name>
 *
 * Example:
 * "Rosie_claw", "Optimus_speaker", etc.
 *
 * You are given:
 *
 * A list of strings representing the available parts in the factory bin.
 * A string representing the comma-separated list of required parts needed to fully build one robot.
 * Task:
 * Implement a function that returns the list of robot names for which we can build at least one complete robot
 * from the available parts.
 *
 * Function Signature:
 * public static String[] get_robots(String[] all_parts, String required_parts)
 *
 * Sample Input:
 *
 * String[] all_parts = {
 * "Rosie_claw",
 * "Rosie_sensors",
 * "Dustie_case",
 * "Optimus_sensors",
 * "Rust_sensors",
 * "Rosie_case",
 * "Rust_case",
 * "Optimus_speaker",
 * "Rosie_wheels",
 * "Rosie_speaker",
 * "Dustie_case",
 * "Dustie_arms",
 * "Rust_claw",
 * "Dustie_case",
 * "Dustie_speaker",
 * "Optimus_case",
 * "Optimus_wheels",
 * "Rust_legs",
 * "Optimus_sensors"
 * };
 *
 * String required_parts_1 = "sensors,case,speaker,wheels";
 * String required_parts_2 = "sensors,case,speaker,wheels,claw";
 * String required_parts_3 = "sensors,case,screws";
 *
 * Expected Output:
 *
 * get_robots(all_parts, required_parts_1) => ["Optimus", "Rosie"]
 * get_robots(all_parts, required_parts_2) => ["Rosie"]
 * get_robots(all_parts, required_parts_3) => []
 *
 * (Note: The output order does not matter.)
 *
 * Constraints:
 *
 * N = number of parts in all_parts
 * P = number of parts in required_parts
 * Each part string is guaranteed to be in "<robot_name>_<part_name>" format
 * Robot names are case-sensitive
 * A robot may appear multiple times in the list with different parts
 *
 */
public class AtlassianDSARobotsParts {

    public static void main(String[] args) {
        // Example Usage
        String[] allParts1 = {"Rosie_claw", "Optimus_speaker", "Rosie_sensors", "Optimus_case", "Rosie_case", "Rosie_speaker"};
        String requiredParts1 = "sensors,case,speaker";
        String[] robots1 = get_robots(allParts1, requiredParts1);
        System.out.println("Robots that can be built (Example 1): " + Arrays.toString(robots1)); // Expected: [Rosie]

        String[] allParts2 = {"Voltron_head", "Voltron_arm", "Gundam_leg", "Voltron_leg", "Gundam_arm", "Voltron_torso", "Gundam_head", "Gundam_torso"};
        String requiredParts2 = "head,arm,leg,torso";
        String[] robots2 = get_robots(allParts2, requiredParts2);
        System.out.println("Robots that can be built (Example 2): " + Arrays.toString(robots2)); // Expected: [Voltron, Gundam]

        String[] allParts3 = {"Bender_eye", "Bender_hand", "Wall-E_track", "Bender_body"};
        String requiredParts3 = "eye,hand,body,wheel";
        String[] robots3 = get_robots(allParts3, requiredParts3);
        System.out.println("Robots that can be built (Example 3): " + Arrays.toString(robots3)); // Expected: []

        String[] allParts4 = {"KITT_chassis", "KITT_engine", "KITT_tire", "KITT_dashboard"};
        String requiredParts4 = "chassis,engine";
        String[] robots4 = get_robots(allParts4, requiredParts4);
        System.out.println("Robots that can be built (Example 4): " + Arrays.toString(robots4)); // Expected: [KITT]

        String[] allParts5 = {};
        String requiredParts5 = "sensor";
        String[] robots5 = get_robots(allParts5, requiredParts5);
        System.out.println("Robots that can be built (Example 5): " + Arrays.toString(robots5)); // Expected: []

        String[] allParts6 = {"RobotA_part1", "RobotA_part2", "RobotB_part1"};
        String requiredParts6 = "part1,part2";
        String[] robots6 = get_robots(allParts6, requiredParts6);
        System.out.println("Robots that can be built (Example 6): " + Arrays.toString(robots6)); // Expected: [RobotA]

    }

    public static String[] get_robots(String[] all_parts, String required_parts) {
        Map<String, Set<String>> robotPartsMap = new java.util.HashMap<>();

        for (String part : all_parts) {
            String[] splitPart = part.split("_");

            String robotName = splitPart[0];
            String partName = splitPart[1];

            robotPartsMap.putIfAbsent(robotName, new java.util.HashSet<>());
            robotPartsMap.get(robotName).add(partName);
        }
        Map<Set<String>, List<String>> partKeyRobotsMap = new java.util.HashMap<>();

        for (Map.Entry<String, Set<String>> robotParts : robotPartsMap.entrySet()) {
            Set<String> parts = robotParts.getValue();
            String robotName = robotParts.getKey();
            List<String> robots = partKeyRobotsMap.getOrDefault(parts, new ArrayList<>());
            robots.add(robotName);
            partKeyRobotsMap.put(parts, robots);
        }
        String[] requiredPartsArray = required_parts.split(",");
        Set<String> requiredPartsSet = new java.util.HashSet<>(Arrays.asList(requiredPartsArray));

        for (String requiredPart : requiredPartsArray) {
            requiredPartsSet.add(requiredPart.trim());
        }

        for (Set<String> key : partKeyRobotsMap.keySet()) {

            if (key.containsAll(requiredPartsSet)) {
                List<String> robots = partKeyRobotsMap.get(key);
                return robots.toArray(new String[0]);
            }
        }
        return new String[0];
    }
}
