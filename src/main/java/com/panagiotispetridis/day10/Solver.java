package com.panagiotispetridis.day10;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;
import java.util.function.BiFunction;
import com.panagiotispetridis.day10.Graph.Node;

public class Solver {
    public Solver() {}

    public Output solvePart1(Input input) {
        int answer = 0;
        var graph = input.graph();

        var startNodeOption = graph.getStartNode();
        if (startNodeOption.isEmpty()) {
            System.out.println("missing start node");
            return new Output(0);
        }
        var startNode = startNodeOption.get();

        Queue<Pair<Integer, Pair<Direction, Node>>> queue = new ArrayDeque<>();
        HashMap<Node, Integer> visited = new HashMap<>();
        var x = startNode.position().x();
        var y = startNode.position().y();

        visited.put(startNode, 0);
        graph.getNode(new Position(x + 1, y)).ifPresent(n -> {
            queue.add(new Pair<>(1, new Pair<>(Direction.EAST, n)));
        });
        graph.getNode(new Position(x - 1, y)).ifPresent(n -> {
            queue.add(new Pair<>(1, new Pair<>(Direction.WEST, n)));
        });
        graph.getNode(new Position(x, y + 1)).ifPresent(n -> {
            queue.add(new Pair<>(1, new Pair<>(Direction.SOUTH, n)));
        });
        graph.getNode(new Position(x, y - 1)).ifPresent(n -> {
            queue.add(new Pair<>(1, new Pair<>(Direction.NORTH, n)));
        });

        while (queue.size() > 0) {
            var pair = queue.poll();
            var direction = pair.second().first();
            var node = pair.second().second();
            if (visited.containsKey(node)) {
                visited.put(node, Math.min(pair.first(), visited.get(node)));
            } else {
                visited.put(node, pair.first());
            }
            graph.next(direction, node).ifPresent(p -> {
                if (!p.second().canVisit(p.first())) {
                    return;
                }
                queue.add(new Pair<>(pair.first() + 1, p));
            });
        }

        for (var e : visited.entrySet()) {
            answer = Math.max(answer, e.getValue());
        }

        return new Output(answer);
    }

    public Output solvePart2(Input input) {
        int answer = 0;
        var graph = input.graph();

        var startNodeOption = graph.getStartNode();
        if (startNodeOption.isEmpty()) {
            System.out.println("missing start node");
            return new Output(0);
        }
        var startNode = startNodeOption.get();

        List<Direction> startDirections =
                List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);

        HashSet<Node> loopNodes = null;
        for (var startDirection : startDirections) {
            var option = this.loop(graph, startDirection, startNode);
            if (option.isEmpty()) {
                continue;
            }
            loopNodes = option.get();
            break;
        }
        if (loopNodes == null) {
            System.out.println("did not find loop");
            return new Output(0);
        }

        BiFunction<Node, HashSet<Node>, HashSet<Node>> dfs = (node, visited) -> {
            Stack<Node> stack = new Stack<>();
            HashSet<Node> group = new HashSet<>();

            stack.add(node);
            var outOfBounds = false;
            while (stack.size() > 0) {
                var curr = stack.pop();
                if (visited.contains(curr)) {
                    continue;
                }
                visited.add(curr);
                group.add(curr);
                for (var n : input.graph().getNeighbours(curr)) {
                    if (n.isEmpty()) {
                        // means we got out of bounds, we don't care about this group
                        outOfBounds = true;
                        continue;
                    }
                    stack.add(n.get());
                }
            }

            if (outOfBounds) {
                return new HashSet<>();
            }

            return group;
        };

        HashSet<Node> visited = new HashSet<>();
        for (var n : loopNodes) {
            visited.add(n);
        }

        BiFunction<HashSet<Node>, HashSet<Node>, Boolean> isEnclosed = (group, loop) -> {
            if (group.size() == 0) {
                return false;
            }
            // not all edges flip the status of the inside/outside
            HashSet<String> skip = new HashSet<>();
            skip.add("L");
            skip.add("-");
            skip.add("J");
            for (int y = 1; y <= graph.getHeight(); y++) {
                var inside = false;
                for (int x = 1; x <= graph.getWidth(); x++) {
                    var opt = graph.getNode(new Position(x, y));
                    if (opt.isEmpty()) {
                        continue;
                    }
                    var node = opt.get();
                    if (loop.contains(node) && !skip.contains(node.value())) {
                        inside = !inside;
                        continue;
                    }
                    if (group.contains(node) && !inside) {
                        return false;
                    }
                }
            }

            return true;
        };

        for (int y = 1; y <= graph.getHeight(); y++) {
            for (int x = 1; x <= graph.getWidth(); x++) {
                var opt = graph.getNode(new Position(x, y));
                if (opt.isEmpty()) {
                    continue;
                }
                var node = opt.get();
                if (visited.contains(node)) {
                    continue;
                }
                var group = dfs.apply(opt.get(), visited);
                if (!isEnclosed.apply(group, loopNodes)) {
                    continue;
                }
                answer += group.size();
            }
        }

        return new Output(answer);
    }

    private Optional<HashSet<Node>> loop(Graph graph, Direction startDirection, Node startNode) {
        Stack<Pair<Direction, Node>> stack = new Stack<>();
        HashSet<Node> visited = new HashSet<>();
        var x = startNode.position().x();
        var y = startNode.position().y();

        switch (startDirection) {
            case EAST:
                graph.getNode(new Position(x + 1, y)).ifPresent(n -> {
                    stack.push(new Pair<>(Direction.EAST, n));
                });
                break;
            case WEST:
                graph.getNode(new Position(x - 1, y)).ifPresent(n -> {
                    stack.push(new Pair<>(Direction.WEST, n));
                });
                break;
            case SOUTH:
                graph.getNode(new Position(x, y + 1)).ifPresent(n -> {
                    stack.push(new Pair<>(Direction.SOUTH, n));
                });
                break;
            case NORTH:
                graph.getNode(new Position(x, y - 1)).ifPresent(n -> {
                    stack.push(new Pair<>(Direction.NORTH, n));
                });
                break;
            case NONE:
                break;
        }
        visited.add(startNode);
        HashSet<Node> loop = new HashSet<>();
        loop.add(startNode);
        while (stack.size() > 0) {
            var pair = stack.pop();
            var direction = pair.first();
            var node = pair.second();
            if (visited.contains(node)) {
                return Optional.of(loop);
            }
            if (!node.canVisit(direction)) {
                continue;
            }
            loop.add(node);
            graph.next(direction, node).ifPresent(p -> {
                stack.add(p);
            });
        }

        return Optional.empty();
    }

}
