package com.panagiotispetridis.day11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import com.panagiotispetridis.day11.Grid.Node;

public class Parser {

    public static Input parse(Scanner scanner, long multiplier) {
        List<List<Character>> data = new ArrayList<>();
        HashSet<Integer> rowsWithGalaxies = new HashSet<>();
        HashSet<Integer> colsWithGalaxies = new HashSet<>();

        for (int i = 0; scanner.hasNextLine(); i++) {
            List<Character> row = new ArrayList<>();
            var line = scanner.nextLine();
            for (int j = 0; j < line.length(); j++) {
                var c = line.charAt(j);
                if (c == '#') {
                    rowsWithGalaxies.add(i);
                    colsWithGalaxies.add(j);
                }
                row.add(c);
            }
            data.add(row);
        }

        var grid = new Grid(new ArrayList<>());

        for (int i = 0; i < data.size(); i++) {
            List<Node> row = new ArrayList<>();
            for (int j = 0; j < data.get(i).size(); j++) {
                var colMultiplier = Long.valueOf(1);
                var rowMultiplier = Long.valueOf(1);
                if (!rowsWithGalaxies.contains(i)) {
                    rowMultiplier = Long.valueOf(multiplier);
                }
                if (!colsWithGalaxies.contains(j)) {
                    colMultiplier = Long.valueOf(multiplier);
                }
                row.add(new Node(data.get(i).get(j), rowMultiplier, colMultiplier));
            }
            grid.data().add(row);
        }

        return new Input(grid);
    }

}

