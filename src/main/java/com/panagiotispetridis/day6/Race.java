package com.panagiotispetridis.day6;

public record Race(Double time, Double distance) {
    public Long numberOfWaysToBeat() {
        var root = Math.sqrt(time * time - 4 * distance);

        var first = (-time + root) / (-2);
        var second = (-time - root) / (-2);

        if (first < second) {
            var tmp = first;
            first = second;
            second = tmp;
        }
        first = Math.ceil(first);
        second = Math.floor(second);

        long answer = Math.round(first - second) - 1;

        return answer;
    }
}
