package com.panagiotispetridis.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Parser {
    public static Input parse(Scanner scanner) {
        Input result = new Input(new ArrayList<>(), new ArrayList<>());

        if (!scanner.hasNextLine()) {
            return result;
        }
        List<Long> seeds = new ArrayList<>();
        var seedStrings = scanner.nextLine().split(":")[1].split(" ");
        for (var seed : seedStrings) {
            try {
                seeds.add(Long.parseUnsignedLong(seed));
            } catch (Exception e) {
                continue;
            }
        }
        result = new Input(seeds, new ArrayList<>());

        RangeMap currMap = null;
        // remove empty line
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.strip().length() == 0) {
                result.maps().add(currMap);
                continue;
            }

            if (line.contains("map")) {
                currMap = new RangeMap(new TreeSet<>());
                continue;
            }

            var parts = line.split(" ");
            if (parts.length != 3) {
                continue;
            }

            Long dest = Long.parseUnsignedLong(parts[0]);
            Long from = Long.parseUnsignedLong(parts[1]);
            Long len = Long.parseUnsignedLong(parts[2]);

            currMap.mappings().add(new Range(dest, from, len));
        }
        result.maps().add(currMap);

        return result;
    }
}

