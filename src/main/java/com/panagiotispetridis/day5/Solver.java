package com.panagiotispetridis.day5;

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
            for (long j = 0; j < limit; j++) {
                long tmp = seed + j;
                for (var map : input.maps()) {
                    tmp = map.apply(tmp);
                }

                if (Long.compareUnsigned(answer, tmp) > 0) {
                    tmp = seed + j;
                    for (var map : input.maps()) {
                        tmp = map.apply(tmp);
                    }
                    answer = tmp;
                }
            }
        }

        return new Output(answer);
    }

}
