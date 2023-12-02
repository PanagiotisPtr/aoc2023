package com.panagiotispetridis.day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static Input parse(Scanner scanner) {
        Input result = new Input(new ArrayList<>());

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            int gameID = Integer.parseInt(parts[0].split(" ")[1]);
            List<HashMap<String, Integer>> draws = new ArrayList<>();

            for (var draw : parts[1].split(";")) {
                HashMap<String, Integer> entry = new HashMap<>();
                for (var pair : draw.split(",")) {
                    String[] data = pair.split(" ");
                    entry.put(data[2], Integer.parseInt(data[1]));
                }
                draws.add(entry);
            }

            result.games().add(new Game(gameID, draws));
        }

        return result;
    }
}