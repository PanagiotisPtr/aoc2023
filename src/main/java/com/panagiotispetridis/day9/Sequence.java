package com.panagiotispetridis.day9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Sequence {
    List<LinkedList<Long>> data;

    public Sequence(List<Long> l) {
        this.data = new ArrayList<>();
        LinkedList<Long> linkedList = new LinkedList<>();
        for (var e : l) {
            linkedList.add(e);
        }
        this.data.add(linkedList);

        this.init();
    }

    private void init() {
        var done = false;

        while (!done) {
            done = true;
            var lastIdx = this.data.size() - 1;
            var prev = this.data.get(lastIdx).get(0);
            LinkedList<Long> next = new LinkedList<>();
            for (int i = 1; i < this.data.get(lastIdx).size(); i++) {
                var curr = this.data.get(lastIdx).get(i);
                if (curr - prev != 0) {
                    done = false;
                }
                next.add(curr - prev);
                prev = curr;
            }
            this.data.add(next);
        }
    }

    public void predictForwards() {
        this.data.get(this.data.size() - 1).addLast(Long.valueOf(0));
        for (int i = this.data.size() - 2; i > -1; i--) {
            var prev = this.data.get(i + 1);
            var curr = this.data.get(i);

            curr.addLast(curr.getLast() + prev.getLast());
        }
    }

    public void predictBackwards() {
        this.data.get(this.data.size() - 1).addFirst(Long.valueOf(0));
        for (int i = this.data.size() - 2; i > -1; i--) {
            var prev = this.data.get(i + 1);
            var curr = this.data.get(i);

            curr.addFirst(curr.getFirst() - prev.getFirst());
        }
    }

    public List<Long> getSequence() {
        return this.data.get(0);
    }
}
