package com.panagiotispetridis.day3;

import java.util.HashSet;
import java.util.List;

public record Input(
        List<List<Character>> grid,
        HashSet<Position> visited
) {
    public Character getChar(Position position) {
        return this.grid.get(position.i()).get(position.j());
    }

    public boolean isValid(Position position) {
        return position.i() >= 0 && position.j() >= 0 &&
                position.i() < this.grid.size() && position.j() < this.grid.get(0).size();
    }

    // visit visits a position and returns true if it was successful
    // or false if it was visited before
    public boolean visit(Position position) {
        if (this.visited.contains(position)) {
            return false;
        }
        visited.add(position);

        return true;
    }
}
