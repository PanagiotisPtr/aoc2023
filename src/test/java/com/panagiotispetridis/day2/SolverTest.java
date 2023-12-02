package com.panagiotispetridis.day2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    @DisplayName("Part 1 -Testcase 1")
    void testcase1() {
        var input = Test.class.getResourceAsStream("/day2/part1/testcase_1.in");
        var output = Test.class.getResourceAsStream("/day2/part1/testcase_1.out");

        if (input == null) {
            fail("failed to find testcase input");
        }
        if (output == null) {
            fail("failed to find testcase output");
        }

        Scanner inputScanner = new Scanner(new InputStreamReader(input));
        Scanner outputScanner = new Scanner(new InputStreamReader(output));

        HashMap<String, Integer> totals = new HashMap<>();
        totals.put("red", 12);
        totals.put("green", 13);
        totals.put("blue", 14);
        Solver solver = new Solver(totals);
        Output actual = solver.solvePart1(Parser.parse(inputScanner));
        Output expected = new Output(
                Integer.parseInt(outputScanner.next())
        );

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Part 1 -Testcase 2")
    void testcase2() {
        var input = Test.class.getResourceAsStream("/day2/part1/testcase_2.in");
        var output = Test.class.getResourceAsStream("/day2/part1/testcase_2.out");

        if (input == null) {
            fail("failed to find testcase input");
        }
        if (output == null) {
            fail("failed to find testcase output");
        }

        Scanner inputScanner = new Scanner(new InputStreamReader(input));
        Scanner outputScanner = new Scanner(new InputStreamReader(output));

        HashMap<String, Integer> totals = new HashMap<>();
        totals.put("red", 12);
        totals.put("green", 13);
        totals.put("blue", 14);
        Solver solver = new Solver(totals);
        Output actual = solver.solvePart1(Parser.parse(inputScanner));
        Output expected = new Output(
                Integer.parseInt(outputScanner.next())
        );

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Part 2 -Testcase 1")
    void testcase3() {
        var input = Test.class.getResourceAsStream("/day2/part2/testcase_1.in");
        var output = Test.class.getResourceAsStream("/day2/part2/testcase_1.out");

        if (input == null) {
            fail("failed to find testcase input");
        }
        if (output == null) {
            fail("failed to find testcase output");
        }

        Scanner inputScanner = new Scanner(new InputStreamReader(input));
        Scanner outputScanner = new Scanner(new InputStreamReader(output));

        HashMap<String, Integer> totals = new HashMap<>();
        totals.put("red", 12);
        totals.put("green", 13);
        totals.put("blue", 14);
        Solver solver = new Solver(totals);
        Output actual = solver.solvePart2(Parser.parse(inputScanner));
        Output expected = new Output(
                Integer.parseInt(outputScanner.next())
        );

        assertEquals(actual, expected);
    }

}