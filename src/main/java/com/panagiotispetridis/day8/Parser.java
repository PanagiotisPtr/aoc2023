package com.panagiotispetridis.day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static Input parse(Scanner scanner) {
        HashMap<String, List<String>> g = new HashMap<>();
        List<Character> instructions = new ArrayList<>();

        for (var c : scanner.nextLine().toCharArray()) {
            instructions.add(c);
        }
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            var line = scanner.nextLine().trim();
            var parts = line.split("=");
            var from = parts[0].trim();
            var dest = parts[1].trim();
            dest = dest.substring(1, dest.length() - 1);

            g.put(from, new ArrayList<>());
            for (var d : dest.split(",")) {
                d = d.trim();
                g.get(from).add(d);
            }
        }

        return new Input(instructions, new Graph(g));
    }
}

