package com.panagiotispetridis.day3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static Input parse(Scanner scanner) {
        Input result = new Input(new ArrayList<>(), new HashSet<>());

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Character> row = new ArrayList<>(line.length());

            for (var c : line.toCharArray()) {
                row.add(c);
            }

            result.grid().add(row);
        }

        return result;
    }
}