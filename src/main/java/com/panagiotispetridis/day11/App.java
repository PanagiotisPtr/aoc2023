package com.panagiotispetridis.day11;

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
        Solver solver = new Solver();

        Scanner scanner = new Scanner(System.in);
        Input input = null;
        if (part.equals("--part1")) {
            input = Parser.parse(scanner, 2);
        } else {
            input = Parser.parse(scanner, 1000000);
        }
        var output = solver.solve(input);

        System.out.printf("Answer: %d\n", output.result());
    }
}
