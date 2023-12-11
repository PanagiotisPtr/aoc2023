package com.panagiotispetridis.day11;

import java.util.concurrent.atomic.AtomicLong;
import com.panagiotispetridis.day11.Grid.Position;

public class Solver {
    public Solver() {}

    public Output solve(Input input) {
        var answer = new AtomicLong(0);

        var grid = input.grid();
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                var pos = new Position(i, j);
                grid.nodeAt(pos).ifPresent(n -> {
                    if (n.c() != '#') {
                        return;
                    }
                    for (var e : grid.calculateDistances(pos).entrySet()) {
                        answer.addAndGet(e.getValue());
                    }
                });
            }
        }

        // we are counting everything twice (galaxy1 to galaxy2 *and* galaxy2 to galaxy1)
        return new Output(answer.get() / 2);
    }

}
