package com.panagiotispetridis.day5;

import java.util.ArrayList;
import java.util.List;

public record RangeMap(List<Range> mappings) {

    public RangeMap {
        mappings.sort(Range::compareTo);
    }

    public long apply(Long val) {
        int pos = this.findPos(val);
        if (pos < 0 || pos >= this.mappings().size()) {
            return val;
        }

        var range = this.mappings().get(pos);
        if (Long.compareUnsigned(val, range.from() + range.length()) >= 0) {
            return val;
        }


        return range.dest() + (val - range.from());
    }

    private int findPos(Long val) {
        Range target = new Range(Long.valueOf(0), val, Long.valueOf(1));
        int left = 0, right = this.mappings().size();
        while (left < right) {
            int pos = (left + right) / 2;
            var curr = this.mappings().get(pos);
            if (target.compareTo(curr) == 0) {
                return pos;
            }
            if (target.compareTo(curr) > 0) {
                left = pos + 1;
            } else {
                right = pos;
            }
        }

        return left - 1;
    }


    public List<Range> splitRange(Range range) {
        List<Range> ranges = new ArrayList<>();

        for (int i = this.findPos(range.start()); i >= 0 && i < this.mappings().size(); i++) {
            var curr = this.mappings().get(i);
            if (curr.start() > range.end()) {
                break;
            }
            if (range.containsRange(curr)) {
                ranges.add(new Range(curr.dest(), curr.dest(), curr.length()));
                continue;
            }
            if (range.contains(curr.start()) && !range.contains(curr.end())) {
                ranges.add(new Range(curr.dest(), curr.dest(), range.end() - curr.start() + 1));
            }
            if (range.contains(curr.end()) && !range.contains(curr.start())) {
                ranges.add(new Range(curr.dest(), curr.dest() + range.start() - curr.start(),
                        curr.end() - range.start() + 1));
            }
        }

        // fix up edges
        if (ranges.size() == 0) {
            ranges.add(new Range(range.dest(), this.apply(range.start()), range.length()));
            return ranges;
        }

        return ranges;
    }

}
