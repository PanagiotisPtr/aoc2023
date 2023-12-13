package com.panagiotispetridis.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static Input parse(Scanner scanner) {
        Input result = new Input(new ArrayList<>());

        List<List<Character>> curr = new ArrayList<>();
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                result.patterns().add(new Pattern(curr));
                curr = new ArrayList<>();
                continue;
            }

            List<Character> tmp = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                tmp.add(line.charAt(i));
            }
            curr.add(tmp);
        }
        result.patterns().add(new Pattern(curr));

        return result;
    }
}

