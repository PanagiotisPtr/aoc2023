package com.panagiotispetridis.day5;

import java.util.TreeSet;

public record RangeMap(TreeSet<Range> mappings) {

    public long apply(Long val) {
        var range = this.mappings.floor(new Range(val, val, val));

        if (range == null) {
            return val;
        }
        if (Long.compareUnsigned(val, range.from() + range.length()) >= 0) {
            return val;
        }

        return range.dest() + (val - range.from());
    }

}
