package com.panagiotispetridis.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Solver {
    public Solver() {}

    public Output solvePart1(Input input) {
        int answer = 0;

        for (int i = 0; i < input.grid().size(); i++) {
            for (int j = 0; j < input.grid().get(i).size(); j++) {
                var position = new Position(i,j);
                var c = input.getChar(position);
                if (this.isDigit(c)) {
                    continue;
                }
                if (c == '.') {
                    continue;
                }
                int sum = 0;
                for (var number : this.surroundingNumbers(input, position)) {
                    sum += number;
                }
                answer += sum;
            }
        }

        return new Output(answer);
    }

    public Output solvePart2(Input input) {
        int answer = 0;

        for (int i = 0; i < input.grid().size(); i++) {
            for (int j = 0; j < input.grid().get(i).size(); j++) {
                var position = new Position(i,j);
                if (input.getChar(position) == '*') {
                    var numbers = this.surroundingNumbers(input, position);
                    if (numbers.size() == 2) {
                        answer += numbers.get(0) * numbers.get(1);
                    }
                }
            }
        }

        return new Output(answer);
    }

    private List<Integer> surroundingNumbers(Input input, Position position) {
        List<Integer> numbers = new ArrayList<>(8);

        for (var neighbour : position.neighbours()) {
            if (!input.isValid(neighbour)) {
                continue;
            }
            if (!this.isDigit(input.getChar(neighbour))) {
                continue;
            }
            this.processNumber(input, neighbour).ifPresent(numbers::add);
        }

        return numbers;
    }

    private boolean isDigit(Character c) {
        return c >= '0' && c <= '9';
    }

    private Optional<Integer> processNumber(Input input, Position position) {
        StringBuilder sb = new StringBuilder();
        for (int j = position.j(); j >= 0; j--) {
            var currPosition = new Position(position.i(), j);
            var c = input.getChar(currPosition);
            if (!this.isDigit(c)) {
                break;
            }
            if (!input.visit(currPosition)) {
                return Optional.empty();
            }
            sb.append(c);
        }
        sb.reverse();
        for (int j = position.j()+1; j < input.grid().get(0).size(); j++) {
            var currPosition = new Position(position.i(), j);
            var c = input.getChar(currPosition);
            if (!this.isDigit(c)) {
                break;
            }
            if (!input.visit(currPosition)) {
                return Optional.empty();
            }
            sb.append(c);
        }

        try {
            return Optional.of(Integer.parseInt(sb.toString()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
