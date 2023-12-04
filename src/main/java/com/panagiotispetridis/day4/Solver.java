package com.panagiotispetridis.day4;

import java.util.HashMap;

public class Solver {
    public Solver() {}

    public Output solvePart1(Input input) {
        int answer = 0;

        for (var card : input.cards()) {
            int matches = 0;
            for (Integer number : card.hand()) {
                if (card.winning().contains(number)) {
                    matches++;
                }
            }
            if (matches > 0) {
                answer += 1 << (matches - 1);
            }
        }

        return new Output(answer);
    }

    public Output solvePart2(Input input) {
        int answer = 0;
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (var card : input.cards()) {
            counts.put(card.ID(), 1);
        }

        var cards = input.cards();
        for (int i = 0; i < cards.size(); i++) {
            var card = cards.get(i);
            int matches = 0;
            for (Integer number : card.hand()) {
                if (card.winning().contains(number)) {
                    matches++;
                }
            }
            while (matches > 0) {
                int wonCardID = card.ID() + matches;
                counts.put(wonCardID, counts.get(wonCardID) + counts.get(card.ID()));
                matches--;
            }
        }

        for (var entry : counts.entrySet()) {
            answer += entry.getValue();
        }

        return new Output(answer);
    }
}
