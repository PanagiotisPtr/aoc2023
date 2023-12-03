package com.panagiotispetridis.day3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SolverTest {

    @Test
    @DisplayName("Part 1 -Testcase 1")
    void testcase1() {
        var input = Test.class.getResourceAsStream("/day3/part1/testcase_1.in");
        var output = Test.class.getResourceAsStream("/day3/part1/testcase_1.out");

        if (input == null) {
            fail("failed to find testcase input");
        }
        if (output == null) {
            fail("failed to find testcase output");
        }

        Scanner inputScanner = new Scanner(new InputStreamReader(input));
        Scanner outputScanner = new Scanner(new InputStreamReader(output));

        Solver solver = new Solver();
        Output actual = solver.solvePart1(Parser.parse(inputScanner));
        Output expected = new Output(
                Integer.parseInt(outputScanner.next())
        );

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Part 1 -Testcase 2")
    void testcase2() {
        var input = Test.class.getResourceAsStream("/day3/part1/testcase_2.in");
        var output = Test.class.getResourceAsStream("/day3/part1/testcase_2.out");

        if (input == null) {
            fail("failed to find testcase input");
        }
        if (output == null) {
            fail("failed to find testcase output");
        }

        Scanner inputScanner = new Scanner(new InputStreamReader(input));
        Scanner outputScanner = new Scanner(new InputStreamReader(output));

        Solver solver = new Solver();
        Output actual = solver.solvePart1(Parser.parse(inputScanner));
        Output expected = new Output(
                Integer.parseInt(outputScanner.next())
        );

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Part 2 -Testcase 1")
    void testcase3() {
        var input = Test.class.getResourceAsStream("/day3/part2/testcase_1.in");
        var output = Test.class.getResourceAsStream("/day3/part2/testcase_1.out");

        if (input == null) {
            fail("failed to find testcase input");
        }
        if (output == null) {
            fail("failed to find testcase output");
        }

        Scanner inputScanner = new Scanner(new InputStreamReader(input));
        Scanner outputScanner = new Scanner(new InputStreamReader(output));

        Solver solver = new Solver();
        Output actual = solver.solvePart2(Parser.parse(inputScanner));
        Output expected = new Output(
                Integer.parseInt(outputScanner.next())
        );

        assertEquals(actual, expected);
    }

}