package com.panagiotispetridis.day5;

public record Range(Long dest, Long from, Long length) implements Comparable<Range> {

    public int compareTo(Range r) {
        return Long.compareUnsigned(this.from(), r.from());
    }

    public Range overlap(Range r) {
        var from = Math.max(this.from(), r.from());
        var to = Math.min(this.from() + this.length(), r.from() + r.length());

        if (from <= to) {
            return new Range(Long.valueOf(0), from, to - from);
        } else {
            return null;
        }
    }

    public boolean contains(Long val) {
        return Long.compareUnsigned(this.start(), val) <= 0
                && Long.compareUnsigned(this.end(), val) >= 0;
    }

    public boolean containsRange(Range r) {
        return this.contains(r.start()) && this.contains(r.end());
    }


    public Long start() {
        return this.from();
    }

    public Long end() {
        return this.from() + this.length() - 1;
    }

}
