package com.panagiotispetridis.day4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

public class Parser {
    public static Input parse(Scanner scanner) {
        Input result = new Input(new ArrayList<>());

        Function<String, Optional<Integer>> parseNumber = (s) -> {
            try {
                return Optional.of(Integer.parseInt(s));
            } catch (Exception e) {
                return Optional.empty();
            }
        };

        Function<String, Integer> parseCardID = (s) -> {
            int i = 0;
            while (i < s.length()) {
                if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    break;
                }
                i++;
            }

            return Integer.parseInt(s.substring(i));
        };

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            int cardID = parseCardID.apply(parts[0]);
            HashSet<Integer> winning = new HashSet<>();
            HashSet<Integer> hand = new HashSet<>();

            var sections = parts[1].split("\\|");
            for (var section : sections[0].split(" ")) {
                for (var number : section.split(" ")) {
                    parseNumber.apply(number).ifPresent(winning::add);
                }
            }
            for (var section : sections[1].split(" ")) {
                for (var number : section.split(" ")) {
                    parseNumber.apply(number).ifPresent(hand::add);
                }
            }

            result.cards().add(new Card(cardID, winning, hand));
        }
        result.cards().sort((a, b) -> a.ID() - b.ID());

        return result;
    }

}

