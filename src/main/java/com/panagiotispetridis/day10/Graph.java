package com.panagiotispetridis.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Graph {
    final private HashMap<Position, Node> nodes;
    final private int width;
    final private int height;

    public enum Type {
        START, GROUND, PIPE
    }

    public record Node(Pair<Direction, Direction> from, Pair<Direction, Direction> to,
            Position position, Type type, String value) {
        public boolean canVisit(Direction d) {
            return this.from().first() == d || this.from().second() == d;
        }
    }

    public Graph(HashMap<Position, Node> nodes, int width, int height) {
        this.nodes = nodes;
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Optional<Pair<Direction, Node>> next(Direction d, Node n) {
        if (n.type() != Type.PIPE) {
            return Optional.empty();
        }
        Direction nextDirection = null;
        if (n.from().first() == d) {
            nextDirection = n.to().first();
        }
        if (n.from().second() == d) {
            nextDirection = n.to().second();
        }
        if (nextDirection == null) {
            return Optional.empty();
        }

        Position position = null;
        switch (nextDirection) {
            case NORTH:
                position = new Position(n.position().x(), n.position().y() - 1);
                break;
            case SOUTH:
                position = new Position(n.position().x(), n.position().y() + 1);
                break;
            case EAST:
                position = new Position(n.position().x() + 1, n.position().y());
                break;
            case WEST:
                position = new Position(n.position().x() - 1, n.position().y());
                break;
            default:
                return Optional.empty();
        }
        if (!this.nodes.containsKey(position)) {
            return Optional.empty();
        }
        var nextNode = this.nodes.get(position);

        return Optional.of(new Pair<>(nextDirection, nextNode));
    }

    public Optional<Node> getStartNode() {
        for (var e : this.nodes.entrySet()) {
            if (e.getValue().type() == Type.START) {
                return Optional.of(e.getValue());
            }
        }

        return Optional.empty();
    }

    public Optional<Node> getNode(Position position) {
        if (this.nodes.containsKey(position)) {
            return Optional.of(this.nodes.get(position));
        }

        return Optional.empty();
    }

    public List<Optional<Node>> getNeighbours(Node n) {
        List<Optional<Node>> neighbours = new ArrayList<>();
        var x = n.position().x();
        var y = n.position().y();

        neighbours.add(this.getNode(new Position(x + 1, y)));
        neighbours.add(this.getNode(new Position(x - 1, y)));
        neighbours.add(this.getNode(new Position(x, y + 1)));
        neighbours.add(this.getNode(new Position(x, y - 1)));

        return neighbours;
    }
}
