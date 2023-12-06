package com.panagiotispetridis.day6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    public static Input parsePart1(Scanner scanner) {
        Input result = new Input(new ArrayList<>());

        List<Double> times = new ArrayList<>();
        List<Double> distances = new ArrayList<>();

        String line = scanner.nextLine();
        String[] timesStrings = line.split(":")[1].trim().split(" ");
        for (var s : timesStrings) {
            try {
                times.add(Double.parseDouble(s));
            } catch (Exception e) {
                continue;
            }
        }

        line = scanner.nextLine();
        String[] distancesStrings = line.split(":")[1].trim().split(" ");
        for (var s : distancesStrings) {
            try {
                distances.add(Double.parseDouble(s));
            } catch (Exception e) {
                continue;
            }
        }

        for (int i = 0; i < times.size(); i++) {
            var time = times.get(i);
            var distance = distances.get(i);

            result.races().add(new Race(time, distance));
        }

        return result;
    }

    public static Input parsePart2(Scanner scanner) {
        Input result = new Input(new ArrayList<>());

        String line = scanner.nextLine();
        String time = line.split(":")[1].trim().replaceAll(" ", "");

        line = scanner.nextLine();
        String distance = line.split(":")[1].trim().replaceAll(" ", "");

        result.races().add(new Race(Double.parseDouble(time), Double.parseDouble(distance)));

        return result;
    }

}

