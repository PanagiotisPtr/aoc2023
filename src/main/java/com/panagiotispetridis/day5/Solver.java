package com.panagiotispetridis.day5;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    public Solver() {}

    public Output solvePart1(Input input) {
        long answer = Long.MAX_VALUE;

        for (var seed : input.seeds()) {
            for (var map : input.maps()) {
                seed = map.apply(seed);
            }

            if (Long.compareUnsigned(answer, seed) > 0) {
                answer = seed;
            }
        }

        return new Output(answer);
    }

    public Output solvePart2(Input input) {
        long answer = Long.MAX_VALUE;

        for (int i = 0; i < input.seeds().size(); i += 2) {
            long seed = input.seeds().get(i);
            long limit = input.seeds().get(i + 1);
            List<Range> ranges = new ArrayList<>();
            ranges.add(new Range(seed, seed, limit));

            for (var map : input.maps()) {
                List<Range> next = new ArrayList<>();
                for (var r : ranges) {
                    next.addAll(map.splitRange(r));
                }
                ranges = next;
            }
            for (var r : ranges) {
                if (Long.compareUnsigned(answer, r.start()) > 0) {
                    answer = r.start();
                }
            }
        }

        return new Output(answer);
    }

}
