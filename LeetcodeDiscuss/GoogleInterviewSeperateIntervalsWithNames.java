package LeetcodeDiscuss;

import java.util.*;

/**
 *
 *
 * This is like merge interval question but the result needs to have a list of names in the interval too. I'm not quite sure how the list of names is done. Can someone help with the approach?
 *
 * Input
 * Foo 10 30
 * Bar 15 45
 *
 * Output
 * 10 15 Foo
 * 15 30 Foo, Bar
 * 30 45 Bar
 *
 */
public class GoogleInterviewSeperateIntervalsWithNames {

    public static void main(String[] args) {
        List<String> input = Arrays.asList(
                "Foo 10 30",
                "Bar 15 45"
        );

        System.out.println("--- Example 1 (Line Sweep) ---");
        List<String> output = processRangesLineSweep(input);
        for (String s : output) {
            System.out.println(s);
        }
        // Expected:
        // 10 15 Foo
        // 15 30 Foo, Bar
        // 30 45 Bar

        System.out.println("\n--- Example 2 (Line Sweep) ---");
        List<String> input2 = Arrays.asList(
                "Alpha 0 10",
                "Beta 5 15",
                "Gamma 8 12"
        );
        List<String> output2 = processRangesLineSweep(input2);
        for (String s : output2) {
            System.out.println(s);
        }
        // Expected:
        // 0 5 Alpha
        // 5 8 Alpha, Beta
        // 8 10 Alpha, Beta, Gamma
        // 10 12 Beta, Gamma
        // 12 15 Beta

        System.out.println("\n--- Example 3 (Line Sweep - No Overlap) ---");
        List<String> input3 = Arrays.asList(
                "A 1 5",
                "B 6 10"
        );
        List<String> output3 = processRangesLineSweep(input3);
        for (String s : output3) {
            System.out.println(s);
        }
        // Expected:
        // 1 5 A
        // 6 10 B

        System.out.println("\n--- Example 4 (Line Sweep - Nested Intervals) ---");
        List<String> input4 = Arrays.asList(
                "Outer 0 20",
                "Inner 5 15"
        );
        List<String> output4 = processRangesLineSweep(input4);
        for (String s : output4) {
            System.out.println(s);
        }
        // Expected:
        // 0 5 Outer
        // 5 15 Inner, Outer
        // 15 20 Outer
    }

    private static List<String> processRangesLineSweep(List<String> input) {
        List<String[]> lines = new ArrayList<>();

        for (String str : input) {
            String[] parts = str.split(" ");
            String name = parts[0];
            int start = Integer.parseInt(parts[1]);
            int end = Integer.parseInt(parts[2]);
            lines.add(new String[]{name, String.valueOf(start), "1"});
            lines.add(new String[]{name, String.valueOf(end), "-1"});
        }
        Collections.sort(lines, (a, b) -> {
            int startA = Integer.parseInt(a[1]);
            int startB = Integer.parseInt(b[1]);

            if (startA == startB) {

                if (a[2] == "1" && b[2] == "-1") {
                    return -1; // Start event comes before end event
                } else if (a[2] == "-1" && b[2] == "1") {
                    return 1; // End event comes after start event
                } else {
                    return 0; // Both are either start or end events, keep original order
                }
            }
            return Integer.compare(startA, startB);
        });
        List<String> result = new ArrayList<>();
        int prev = -1;
        Set<String> currNames = new HashSet<>();

        for (String[] lineData : lines) {
            String name = lineData[0];
            int point = Integer.parseInt(lineData[1]);
            int type = Integer.parseInt(lineData[2]); // 1 for start, -1 for end

            if (prev != -1 && prev != point) {

                StringBuilder namesAtPrev = new StringBuilder();
                for (String n : currNames) {
                    if (namesAtPrev.length() > 0) {
                        namesAtPrev.append(", ");
                    }
                    namesAtPrev.append(n);
                }
                result.add(prev + " " + point + " " + namesAtPrev.toString());
            }

            if (type == 1) {
                currNames.add(name);
            } else {
                currNames.remove(name);
            }
            prev = point;
        }
        return result;
    }
}
