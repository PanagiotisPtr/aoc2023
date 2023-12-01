package com.panagiotispetridis.day1;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static Input parse(Scanner scanner) {
        Input result = new Input(new ArrayList<>());

        while (scanner.hasNextLine()) {
            result.data().add(scanner.nextLine());
        }

        return result;
    }
}
