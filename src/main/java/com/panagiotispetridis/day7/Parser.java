package com.panagiotispetridis.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static Input parsePart1(Scanner scanner) {
        Input result = new Input(new ArrayList<>());

        while (scanner.hasNextLine()) {
            var line = scanner.nextLine().trim();
            var parts = line.split(" ");
            var bid = Integer.parseInt(parts[1]);
            List<Integer> cards = new ArrayList<>();

            for (var card : parts[0].split("")) {
                var value = 0;
                switch (card) {
                    case "A":
                        value = 14;
                        break;
                    case "K":
                        value = 13;
                        break;
                    case "Q":
                        value = 12;
                        break;
                    case "J":
                        value = 11;
                        break;
                    case "T":
                        value = 10;
                        break;
                    default:
                        value = Integer.parseInt(card);
                }
                cards.add(value);
            }

            result.hands().add(new Hand(cards, bid));
        }

        return result;
    }

    public static Input parsePart2(Scanner scanner) {
        Input result = new Input(new ArrayList<>());

        while (scanner.hasNextLine()) {
            var line = scanner.nextLine().trim();
            var parts = line.split(" ");
            var bid = Integer.parseInt(parts[1]);
            List<Integer> cards = new ArrayList<>();

            for (var card : parts[0].split("")) {
                var value = 0;
                switch (card) {
                    case "A":
                        value = 14;
                        break;
                    case "K":
                        value = 13;
                        break;
                    case "Q":
                        value = 12;
                        break;
                    case "J":
                        value = 1;
                        break;
                    case "T":
                        value = 10;
                        break;
                    default:
                        value = Integer.parseInt(card);
                }
                cards.add(value);
            }

            result.hands().add(new Hand(cards, bid));
        }

        return result;
    }
}

