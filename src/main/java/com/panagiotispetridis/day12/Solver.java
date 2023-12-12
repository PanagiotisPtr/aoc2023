package com.panagiotispetridis.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solver {
    private record State(int sequenceLength, int springIndex, int brokenIndex){}

    final private HashMap<State, Long> cache;

    public Solver() {
        this.cache = new HashMap<>();
    }

    public Output solve(Input input) {
        long answer = 0;

        for (var cr : input.records()) {
            answer += this.DP(new State(0,0,0), cr);
            this.cache.clear();
        }

        return new Output(answer);
    }

    private boolean arraysAreEqual(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }

        for (int i = 0; i < b.size(); i++) {
            if (a.get(i) != b.get(i)) {
                return false;
            }
        }

        return true;
    }

    private long backtracking(int index, ConditionRecord cr) {
        if (index == cr.springs().size()) {
            List<Integer> groups = new ArrayList<>();
            var curr = 0;
            for (var c : cr.springs()) {
                if (c == '#') {
                    curr++;
                } else {
                    if (curr > 0) {
                        groups.add(curr);
                    }
                    curr = 0;
                }
            }
            if (curr > 0) {
                groups.add(curr);
            }

            return this.arraysAreEqual(groups, cr.broken()) ? 1 : 0;
        }

        if (cr.springs().get(index) != '?') {
            return backtracking(index+1, cr);
        }

        long answer = 0;
        cr.springs().set(index, '.');
        answer += backtracking(index, cr);
        cr.springs().set(index, '#');
        answer += backtracking(index, cr);
        cr.springs().set(index, '?');

        return answer;
    }

    private long DP(State s, ConditionRecord cr) {
        if (s.springIndex() == cr.springs().size()) {
            if (s.brokenIndex() == cr.broken().size() && s.sequenceLength() == 0) {
                return 1;
            }
            if (s.brokenIndex() == cr.broken().size()-1 && s.sequenceLength() == cr.broken().get(s.brokenIndex())) {
                return 1;
            }
            return 0;
        }
        if (this.cache.containsKey(s)) {
            return this.cache.get(s);
        }
        var expectedLength = 0;
        var inSequence = s.sequenceLength() > 0;
        if (s.brokenIndex() < cr.broken().size()) {
            expectedLength = cr.broken().get(s.brokenIndex());
        }
        var c = cr.springs().get(s.springIndex());
        switch (c) {
            case '.':
                if (inSequence && s.sequenceLength() != expectedLength) {
                    return 0;
                }
                var completeSequence = inSequence ? 1 : 0;
                return DP(
                        new State(0, s.springIndex()+1, s.brokenIndex() + completeSequence),
                        cr
                );
            case '#':
                if (inSequence && s.sequenceLength() == expectedLength) {
                    return 0;
                }
                return DP(
                        new State(s.sequenceLength() + 1, s.springIndex()+1, s.brokenIndex()),
                        cr
                );
        }

        // assume it's a #
        long answer = 0;
        boolean left = false;
        boolean right = false;
        if (s.sequenceLength() < expectedLength && expectedLength > 0) {
            left = true;
            answer += DP(new State(s.sequenceLength() + 1, s.springIndex() + 1, s.brokenIndex()), cr);
        }
        // assume it's a .
        if (!inSequence || s.sequenceLength() == expectedLength) {
            var newBrokenIndex = inSequence ? s.brokenIndex() + 1 : s.brokenIndex();
            right = true;
            answer += DP(new State(0, s.springIndex() + 1, newBrokenIndex), cr);
        }
        this.cache.put(s, answer);

        return answer;
    }

}
