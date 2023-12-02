package com.panagiotispetridis.day2;

import java.util.HashMap;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("exactly 1 parameter expected: --part1 or --part2");
            return;
        }
        if (!args[0].equals("--part1") && !args[0].equals("--part2")) {
            System.out.println("exactly 1 parameter expected: --part1 or --part2");
            return;
        }
        String part = args[0];
        HashMap<String, Integer> totals = new HashMap<>();
        totals.put("red", 12);
        totals.put("green", 13);
        totals.put("blue", 14);
        Solver solver = new Solver(totals);

        Scanner scanner = new Scanner(System.in);
        Input input = Parser.parse(scanner);
        Output output = null;
        if (part.equals("--part1")) {
            output = solver.solvePart1(input);
        } else {
            output = solver.solvePart2(input);
        }

        System.out.printf("Answer: %d\n", output.result());
    }
}
