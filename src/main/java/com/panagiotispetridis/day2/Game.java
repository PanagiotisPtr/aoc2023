package com.panagiotispetridis.day2;

import java.util.HashMap;
import java.util.List;

public record Game(
        Integer ID,
        List<HashMap<String, Integer>> draws
) {}
