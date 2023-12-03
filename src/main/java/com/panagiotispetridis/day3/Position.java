package com.panagiotispetridis.day3;

import java.util.List;

public record Position(
        int i,
        int j
) {
    public List<Position> neighbours() {
        return List.of(
                new Position(this.i+1, this.j),
                new Position(this.i-1, this.j),
                new Position(this.i, this.j+1),
                new Position(this.i, this.j-1),
                new Position(this.i+1, this.j+1),
                new Position(this.i-1, this.j-1),
                new Position(this.i+1, this.j-1),
                new Position(this.i-1, this.j+1)
        );
    }
}
