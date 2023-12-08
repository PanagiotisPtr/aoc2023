package com.panagiotispetridis.day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public record Graph(HashMap<String, List<String>> graph) {

    public List<String> neighbours(String node) {
        if (!graph.containsKey(node)) {
            return new ArrayList<>();
        }

        return graph.get(node);
    }

    public Cycle findCycle(String node, List<Character> instructions) {
        List<String> nodes = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();

        var curr = node;
        var i = 0;
        while (!visited.contains(curr)) {
            visited.add(curr);
            nodes.add(curr);
            curr = next(curr, instructions, i);
            i++;
        }

        List<String> before = new ArrayList<>();
        List<String> after = new ArrayList<>();
        i = 0;
        while (!nodes.get(i).equals(curr)) {
            before.add(nodes.get(i));
            i++;
        }
        while (i < nodes.size()) {
            after.add(nodes.get(i));
            i++;
        }


        return new Cycle(before, after);
    }

    private String next(String node, List<Character> instructions, int i) {
        var instruction = instructions.get(i % instructions.size());
        switch (instruction) {
            case 'L':
                return this.neighbours(node).get(0);
            default:
                return this.neighbours(node).get(1);
        }
    }

}
