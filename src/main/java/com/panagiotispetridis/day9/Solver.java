package com.panagiotispetridis.day9;

public class Solver {
    public Solver() {}

    public Output solvePart1(Input input) {
        long answer = 0;

        for (var sequence : input.sequences()) {
            sequence.predictForwards();
            answer += sequence.getSequence().get(sequence.getSequence().size() - 1);
        }

        return new Output(answer);
    }

    public Output solvePart2(Input input) {
        long answer = 0;

        for (var sequence : input.sequences()) {
            sequence.predictBackwards();
            answer += sequence.getSequence().get(0);
        }

        return new Output(answer);
    }

}
