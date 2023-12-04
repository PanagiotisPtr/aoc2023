package com.panagiotispetridis.day4;

import java.util.HashSet;

public record Card(Integer ID, HashSet<Integer> winning, HashSet<Integer> hand) {
}
