package com.panagiotispetridis.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum HandType {

    FIVE_OF_A_KIND(6), FOUR_OF_A_KIND(5), FULL_HOUSE(4), THREE_OF_A_KIND(3), TWO_PAIR(2), ONE_PAIR(
            1), HIGH_CARD(0),;

    final private int value;

    HandType(int val) {
        this.value = val;
    }

    public int getValue() {
        return this.value;
    }

    public static HandType getType(List<Integer> cards) {
        HashMap<Integer, Integer> counts = new HashMap<>();

        int jokers = 0;
        for (var c : cards) {
            counts.put(c, 0);
        }
        for (var c : cards) {
            if (c == 1) {
                jokers++;
                continue;
            }
            counts.put(c, counts.get(c) + 1);
        }

        List<Integer> vals = new ArrayList<>();
        for (var e : counts.entrySet()) {
            vals.add(e.getValue());
        }

        vals.sort((a, b) -> b - a);
        var best = vals.get(0) + jokers;
        if (best == 5) {
            return FIVE_OF_A_KIND;
        }
        var secondBest = vals.get(1);
        if (best == 4) {
            return FOUR_OF_A_KIND;
        }
        if (best == 3) {
            if (secondBest == 2) {
                return FULL_HOUSE;
            } else {
                return THREE_OF_A_KIND;
            }
        }
        if (best == 2) {
            if (secondBest == 2) {
                return TWO_PAIR;
            }
            return ONE_PAIR;
        }

        return HIGH_CARD;
    }
}
