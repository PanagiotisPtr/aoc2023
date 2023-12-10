package com.panagiotispetridis.day10;

import java.util.HashMap;
import java.util.Scanner;
import com.panagiotispetridis.day10.Graph.Node;
import com.panagiotispetridis.day10.Graph.Type;

public class Parser {
    public static Input parse(Scanner scanner) {
        HashMap<Position, Node> nodes = new HashMap<>();

        int y = 1;
        int width = 0;
        int height = 0;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine().trim();
            int x = 1;
            for (var p : line.split("")) {
                Node n = null;
                Position position = new Position(x, y);
                // direction from is where you must be coming from to enter and direction to is what
                // direction you will have afterwards
                switch (p) {
                    case ".":
                        n = new Node(new Pair<>(Direction.NONE, Direction.NONE),
                                new Pair<>(Direction.NONE, Direction.NONE), position, Type.GROUND,
                                p);
                        break;
                    case "S":
                        n = new Node(new Pair<>(Direction.NONE, Direction.NONE),
                                new Pair<>(Direction.NONE, Direction.NONE), position, Type.START,
                                p);
                        break;
                    case "|":
                        n = new Node(new Pair<>(Direction.SOUTH, Direction.NORTH),
                                new Pair<>(Direction.SOUTH, Direction.NORTH), position, Type.PIPE,
                                p);
                        break;
                    case "-":
                        n = new Node(new Pair<>(Direction.EAST, Direction.WEST),
                                new Pair<>(Direction.EAST, Direction.WEST), position, Type.PIPE, p);
                        break;
                    case "L":
                        n = new Node(new Pair<>(Direction.SOUTH, Direction.WEST),
                                new Pair<>(Direction.EAST, Direction.NORTH), position, Type.PIPE,
                                p);
                        break;
                    case "J":
                        n = new Node(new Pair<>(Direction.SOUTH, Direction.EAST),
                                new Pair<>(Direction.WEST, Direction.NORTH), position, Type.PIPE,
                                p);
                        break;
                    case "7":
                        n = new Node(new Pair<>(Direction.EAST, Direction.NORTH),
                                new Pair<>(Direction.SOUTH, Direction.WEST), position, Type.PIPE,
                                p);
                        break;
                    case "F":
                        n = new Node(new Pair<>(Direction.WEST, Direction.NORTH),
                                new Pair<>(Direction.SOUTH, Direction.EAST), position, Type.PIPE,
                                p);
                        break;
                    default:
                        continue;
                }
                nodes.put(position, n);
                width = Math.max(width, x);
                x++;
            }
            height = Math.max(height, y);
            y++;
        }

        return new Input(new Graph(nodes, width, height));
    }
}

