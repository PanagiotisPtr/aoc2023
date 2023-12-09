package com.panagiotispetridis.day9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static Input parse(Scanner scanner) {
        Input result = new Input(new ArrayList<>());

        while (scanner.hasNextLine()) {
            List<Long> numbers = new ArrayList<>();
            for (var e : scanner.nextLine().trim().split(" ")) {
                try {
                    numbers.add(Long.parseLong(e));
                } catch (Exception ex) {
                    continue;
                }
            }
            result.sequences().add(new Sequence(numbers));
        }

        return result;
    }
}

