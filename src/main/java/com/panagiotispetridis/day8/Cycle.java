package com.panagiotispetridis.day8;

import java.util.List;

// Cycle stores node before and after a cycle in a graph
public record Cycle(List<String> before, List<String> after) {
}
