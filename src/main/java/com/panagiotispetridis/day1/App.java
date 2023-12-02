package com.panagiotispetridis.day1;

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
        Solver solver = new Solver(part.equals("--part2"));

        Scanner scanner = new Scanner(System.in);
        Input input = Parser.parse(scanner);
        Output output = solver.solve(input);

        System.out.printf("Answer: %d\n", output.result());
    }
}
