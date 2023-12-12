package com.panagiotispetridis.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static Input parse(Scanner scanner, boolean unfold) {
        Input result = new Input(new ArrayList<>());

        while (scanner.hasNextLine()) {
            List<Character> springs = new ArrayList<>();
            List<Integer> broken = new ArrayList<>();
            var line = scanner.nextLine().trim();
            var parts = line.split(" ");
            for (int i = 0; i < parts[0].length(); i++) {
                var c = parts[0].charAt(i);
                springs.add(c);
            }
            for (var n : parts[1].trim().split(",")) {
                try {
                    broken.add(Integer.parseInt(n));
                } catch (Exception e) {
                    continue;
                }
            }

            if (!unfold) {
                result.records().add(new ConditionRecord(springs, broken));
            } else {
                List<Character> unfoldedSprings = new ArrayList<>();
                List<Integer> unfoldedBroken = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    unfoldedSprings.addAll(springs);
                    unfoldedSprings.add('?');
                    unfoldedBroken.addAll(broken);
                }
                unfoldedSprings.removeLast();
                result.records().add(new ConditionRecord(unfoldedSprings, unfoldedBroken));
            }
        }

        return result;
    }
}

