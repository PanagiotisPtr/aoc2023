package com.panagiotispetridis.day5;

public record Range(Long dest, Long from, Long length) implements Comparable<Range> {

    public int compareTo(Range r) {
        return Long.compareUnsigned(this.from(), r.from());
    }

}
