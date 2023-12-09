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

    public List<TargetNode> getTargetNodes(String node, List<Character> instructions) {
        List<String> nodes = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();

        var curr = node;
        var i = 0;
        // we care about seeing the same node on the same instruction
        while (!visited.contains(curr + i)) {
            visited.add(curr + i);
            nodes.add(curr + i);
            curr = next(curr, instructions, i);
            i++;
            i %= instructions.size();
        }
        // save position in curr
        curr = curr + i;

        System.out.printf("nodes: ");
        for (var n : nodes) {
            System.out.printf("%s ", n);
        }
        System.out.println();

        List<String> before = new ArrayList<>();
        List<String> after = new ArrayList<>();
        i = 0;
        List<TargetNode> endNodes = new ArrayList<>();
        var dist = 0;
        // curr now points to the start of the cycle
        while (!nodes.get(dist).equals(curr)) {
            var n = nodes.get(dist);
            before.add(n);
            if (n.charAt(2) == 'Z') {
                endNodes.add(new TargetNode(n, Long.valueOf(dist), Long.valueOf(0)));
            }
            i++;
            i %= instructions.size();
            dist++;
        }
        var period = Long.valueOf(nodes.size() - dist);
        while (dist < nodes.size()) {
            var n = nodes.get(dist);
            after.add(nodes.get(dist));
            if (n.charAt(2) == 'Z') {
                endNodes.add(new TargetNode(n, Long.valueOf(dist), period));
            }
            i++;
            i %= instructions.size();
            dist++;
        }

        return endNodes;
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
