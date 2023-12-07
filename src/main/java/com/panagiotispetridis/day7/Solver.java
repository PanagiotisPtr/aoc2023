package com.panagiotispetridis.day7;

public class Solver {
    public Solver() {}

    public Output solvePart1(Input input) {
        long answer = 0;

        input.hands().sort(Hand::compareTo);
        int rank = 1;
        for (var hand : input.hands()) {
            answer += rank * hand.getBid();
            rank++;
        }

        return new Output(answer);
    }

    public Output solvePart2(Input input) {
        long answer = 0;

        input.hands().sort(Hand::compareTo);
        int rank = 1;
        for (var hand : input.hands()) {
            answer += rank * hand.getBid();
            rank++;
        }

        return new Output(answer);
    }

}
