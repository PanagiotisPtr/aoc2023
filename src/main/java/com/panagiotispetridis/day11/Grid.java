package com.panagiotispetridis.day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public record Grid(List<List<Node>> data) {

    public static record Node(char c, long rowMultiplier, long colMultiplier) {
    }

    public static record Position(int i, int j) {
    }

    private static record PositionWithDistance(Position pos, long dist)
            implements Comparable<PositionWithDistance> {

        public int compareTo(PositionWithDistance pwd) {
            if (this.dist() > pwd.dist()) {
                return 1;
            }
            if (this.dist() < pwd.dist()) {
                return -1;
            }
            return 0;
        }
    }

    private List<PositionWithDistance> neighbours(PositionWithDistance pwd) {
        List<PositionWithDistance> neighbours = new ArrayList<>();

        if (!this.isValidPosition(pwd.pos())) {
            return neighbours;
        }
        var n = this.nodeAt(pwd.pos()).get();
        neighbours.add(new PositionWithDistance(new Position(pwd.pos().i() + 1, pwd.pos().j()),
                pwd.dist() + n.rowMultiplier()));
        neighbours.add(new PositionWithDistance(new Position(pwd.pos().i() - 1, pwd.pos().j()),
                pwd.dist() + n.rowMultiplier()));
        neighbours.add(new PositionWithDistance(new Position(pwd.pos().i(), pwd.pos().j() + 1),
                pwd.dist() + n.colMultiplier()));
        neighbours.add(new PositionWithDistance(new Position(pwd.pos().i(), pwd.pos().j() - 1),
                pwd.dist() + n.colMultiplier()));

        return neighbours;
    }

    public HashMap<Position, Long> calculateDistances(Position p) {
        var dist = new HashMap<Position, Long>();
        var visited = new HashSet<Position>();
        Queue<PositionWithDistance> queue = new PriorityQueue<PositionWithDistance>();

        queue.add(new PositionWithDistance(p, 0));
        while (!queue.isEmpty()) {
            var curr = queue.poll();
            if (visited.contains(curr.pos())) {
                continue;
            }
            visited.add(curr.pos());
            this.nodeAt(curr.pos()).ifPresent(n -> {
                if (n.c() == '#') {
                    dist.put(curr.pos(), curr.dist());
                }
            });
            for (var neighbour : this.neighbours(curr)) {
                if (!this.isValidPosition(neighbour.pos())) {
                    continue;
                }
                queue.add(neighbour);
            }
        }

        return dist;
    }

    public Optional<Node> nodeAt(Position position) {
        if (!this.isValidPosition(position)) {
            return Optional.empty();
        }

        return Optional.of(this.data().get(position.i()).get(position.j()));
    }

    public boolean isValidPosition(Position position) {
        return position.i() < this.data().size() && position.i() >= 0 && position.j() >= 0
                && position.j() < this.data().get(0).size();
    }

    public int getHeight() {
        return this.data().size();
    }

    public int getWidth() {
        return this.data().get(0).size();
    }
}
