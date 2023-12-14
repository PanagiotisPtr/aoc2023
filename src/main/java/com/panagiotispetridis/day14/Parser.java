package com.panagiotispetridis.day14;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static Input parse(Scanner scanner) {
        List<List<Character>> data = new ArrayList<>();

        while (scanner.hasNextLine()) {
            var line = scanner.nextLine().trim();
            List<Character> chars = new ArrayList<>();
            for (var c : line.toCharArray()) {
                chars.add(c);
            }
            data.add(chars);
        }

        return new Input(new Grid(data));
    }
}

