package com.panagiotispetridis.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        List<Range> mappings = null;
        // remove empty line
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.strip().length() == 0) {
                result.maps().add(new RangeMap(mappings));
                continue;
            }

            if (line.contains("map")) {
                mappings = new ArrayList<>();
                continue;
            }

            var parts = line.split(" ");
            if (parts.length != 3) {
                continue;
            }

            Long dest = Long.parseUnsignedLong(parts[0]);
            Long from = Long.parseUnsignedLong(parts[1]);
            Long len = Long.parseUnsignedLong(parts[2]);

            mappings.add(new Range(dest, from, len));
        }
        result.maps().add(new RangeMap(mappings));

        return result;
    }
}

