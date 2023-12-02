package com.panagiotispetridis.day2;

import java.util.HashMap;

public class Solver {
    final private HashMap<String, Integer> totals;
    public Solver(HashMap<String, Integer> totals) {
        this.totals = totals;
    }

    public Output solvePart1(Input input) {
        int answer = 0;

        for (var game : input.games()) {
            if (this.isPossible(game)) {
                answer += game.ID();
            }
        }

        return new Output(answer);
    }

    public Output solvePart2(Input input) {
        int answer = 0;

        for (var game : input.games()) {
            answer += this.getPower(game);
        }

        return new Output(answer);
    }

    private boolean isPossible(Game game) {
        for (var draw : game.draws()) {
            for (var entry : draw.entrySet()) {
                if (!this.totals.containsKey(entry.getKey())) {
                    return false;
                }
                if (this.totals.get(entry.getKey()) < entry.getValue()) {
                    return false;
                }
            }
        }

        return true;
    }

    private int getPower(Game game) {
        int power = 1;
        HashMap<String, Integer> maximums = new HashMap<>();
        for (var draw : game.draws()) {
            for (var entry : draw.entrySet()) {
                if (!maximums.containsKey(entry.getKey())) {
                    maximums.put(entry.getKey(), entry.getValue());
                }
                maximums.put(entry.getKey(),Math.max(
                        maximums.get(entry.getKey()),
                        entry.getValue()
                ));
            }
        }
        for (var entry : maximums.entrySet()) {
            power *= entry.getValue();
        }

        return power;
    }
}
