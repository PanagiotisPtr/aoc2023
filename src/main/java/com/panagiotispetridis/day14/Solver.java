package com.panagiotispetridis.day14;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    public Solver() {}

    public Output solvePart1(Input input) {
        input.grid().moveLoadTop();
        input.grid().rotateClockwise();
        return new Output(input.grid().getLoadTop());
    }

    public Output solvePart2(Input input) {
        List<Long> loads = new ArrayList<>();
        loads.add(input.grid().getLoadTop());
        for (int cycle = 0; cycle < 1000; cycle++) {
            for (int i = 0; i < 4; i++) {
                input.grid().rotateClockwise();
                input.grid().moveLoadRight();
            }
            loads.add(input.grid().getLoadTop());
        }

        // a cycle exists in the loads. Need to find it and then solve the problem
        var pattern = this.findPattern(loads);
        var remainder = (1_000_000_000 - pattern.start()) % pattern.length();
        var last = pattern.numbers().getFirst();
        for (var i = 0; i <= remainder; i++) {
            last = pattern.numbers().get(i);
        }

        return new Output(last);
    }

    private Pattern findPattern(List<Long> loads) {
        for (var patternLength = 200; patternLength > 10; patternLength--) {
            for (var start = 200; start < 500; start++) {
                if (this.isPattern(loads, start, patternLength)) {
                    List<Long> numbers = new ArrayList<>();
                    for (var i = start; i < start+patternLength; i++) {
                        numbers.add(loads.get(i));
                    }
                    return new Pattern(start, patternLength, numbers);
                }
            }
        }

        return new Pattern(0,0,new ArrayList<>());
    }

    private boolean isPattern(List<Long> loads, int start, int length) {
        for (int i = 0; i < length; i++) {
            if (!loads.get(start+i).equals(loads.get(start+length+i))) {
                return false;
            }
            i++;
        }

        return true;
    }
}
