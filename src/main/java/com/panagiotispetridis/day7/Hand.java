package com.panagiotispetridis.day7;

import java.util.List;

public class Hand implements Comparable<Hand> {
    final private List<Integer> cards;

    final private int bid;

    final private HandType type;

    public Hand(List<Integer> cards, int bid) {
        this.type = HandType.getType(cards);
        this.cards = cards;
        this.bid = bid;
    }

    public int compareTo(Hand h) {
        if (this.type != h.type) {
            return this.type.getValue() - h.type.getValue();
        }

        for (int i = 0; i < Math.min(this.cards.size(), h.cards.size()); i++) {
            var c1 = this.cards.get(i);
            var c2 = h.cards.get(i);
            if (c1 != c2) {
                return c1 - c2;
            }

        }

        return 0;
    }

    public int getBid() {
        return this.bid;
    }

    public List<Integer> getCards() {
        return this.cards;
    }

    public HandType getType() {
        return this.type;
    }

}
