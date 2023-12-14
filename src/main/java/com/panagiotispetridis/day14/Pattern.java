package com.panagiotispetridis.day14;

import java.util.List;

public record Pattern(
        int start,
        int length,
        List<Long> numbers
) {
}
