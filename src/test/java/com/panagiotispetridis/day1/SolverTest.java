package com.panagiotispetridis.day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    @DisplayName("Part 1 -Testcase 1")
    void testcase1() {
        var input = Test.class.getResourceAsStream("/day1/part1/testcase_1.in");
        var output = Test.class.getResourceAsStream("/day1/part1/testcase_1.out");

        if (input == null) {
            fail("failed to find testcase input");
        }
        if (output == null) {
            fail("failed to find testcase output");
        }

        Scanner inputScanner = new Scanner(new InputStreamReader(input));
        Scanner outputScanner = new Scanner(new InputStreamReader(output));

        Solver solver = new Solver(false);
        Output actual = solver.solve(Parser.parse(inputScanner));
        Output expected = new Output(
                Integer.parseInt(outputScanner.next())
        );

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Part 1 - Testcase 2")
    void testcase2() {
        var input = Test.class.getResourceAsStream("/day1/part1/testcase_2.in");
        var output = Test.class.getResourceAsStream("/day1/part1/testcase_2.out");

        if (input == null) {
            fail("failed to find testcase input");
        }
        if (output == null) {
            fail("failed to find testcase output");
        }

        Scanner inputScanner = new Scanner(new InputStreamReader(input));
        Scanner outputScanner = new Scanner(new InputStreamReader(output));

        Solver solver = new Solver(false);
        Output actual = solver.solve(Parser.parse(inputScanner));
        Output expected = new Output(
                Integer.parseInt(outputScanner.next())
        );

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Part 2 - Testcase 1")
    void testcase3() {
        var input = Test.class.getResourceAsStream("/day1/part2/testcase_1.in");
        var output = Test.class.getResourceAsStream("/day1/part2/testcase_1.out");

        if (input == null) {
            fail("failed to find testcase input");
        }
        if (output == null) {
            fail("failed to find testcase output");
        }

        Scanner inputScanner = new Scanner(new InputStreamReader(input));
        Scanner outputScanner = new Scanner(new InputStreamReader(output));

        Solver solver = new Solver(true);
        Output actual = solver.solve(Parser.parse(inputScanner));
        Output expected = new Output(
                Integer.parseInt(outputScanner.next())
        );

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Part 2 - Testcase 2")
    void testcase4() {
        var input = Test.class.getResourceAsStream("/day1/part2/testcase_2.in");
        var output = Test.class.getResourceAsStream("/day1/part2/testcase_2.out");

        if (input == null) {
            fail("failed to find testcase input");
        }
        if (output == null) {
            fail("failed to find testcase output");
        }

        Scanner inputScanner = new Scanner(new InputStreamReader(input));
        Scanner outputScanner = new Scanner(new InputStreamReader(output));

        Solver solver = new Solver(true);
        Output actual = solver.solve(Parser.parse(inputScanner));
        Output expected = new Output(
                Integer.parseInt(outputScanner.next())
        );

        assertEquals(actual, expected);
    }
}