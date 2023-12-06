package com.panagiotispetridis.day6;

public class Solver {

    public Output solvePart1(Input input) {
        long answer = 1;

        for (var race : input.races()) {
            answer *= race.numberOfWaysToBeat();
        }

        return new Output(answer);
    }

    public Output solvePart2(Input input) {
        return new Output(input.races().get(0).numberOfWaysToBeat());
    }

}
