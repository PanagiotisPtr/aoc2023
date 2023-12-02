package com.panagiotispetridis.day1;

import java.util.*;

public class Solver {
    final private TrieNode trie;

    public Solver(boolean includeNumberNames) {
        this.trie = this.buildTrie(includeNumberNames);
    }

    public Output solve(Input input) {
        int answer = 0;
        for (String line : input.data()) {
            var numbers = this.parseNumbers(line);
            int first = numbers.get(0);
            int last = numbers.get(numbers.size()-1);
            answer += first*10 + last;
        }

        return new Output(answer);
    }

    private List<Integer> parseNumbers(String line) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            var curr = this.trie;
            for (int j = i; j < line.length(); j++) {
                char c = line.charAt(j);
                if (curr.next(c) == null) {
                    break;
                }
                curr = curr.next(c);
            }
            var terminal = curr.getTerminal();
            if (terminal != null) {
                numbers.add(terminal.getValue());
            }
        }

        return numbers;
    }

    private TrieNode buildTrie(boolean includeNumberNames) {
        TrieNode root = new TrieNode('*');
        HashMap<String, Integer> map = this.valuesMap(includeNumberNames);

        for (var e : map.entrySet()) {
            var curr = root;
            for (int i = 0; i < e.getKey().length(); i++) {
                char c = e.getKey().charAt(i);

                var next = curr.next(c);
                if (next == null) {
                    next = new TrieNode(c);
                    curr.add(next);
                }
                curr = next;
            }
            curr.add(new TrieNode(e.getValue()));
        }

        return root;
    }

    private HashMap<String, Integer> valuesMap(boolean includeNumberNames) {
        HashMap<String, Integer> map = new HashMap<>();
        if (includeNumberNames) {
            map.put("one", 1);
            map.put("two", 2);
            map.put("three", 3);
            map.put("four", 4);
            map.put("five", 5);
            map.put("six", 6);
            map.put("seven", 7);
            map.put("eight", 8);
            map.put("nine", 9);
        }

        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("6", 6);
        map.put("7", 7);
        map.put("8", 8);
        map.put("9", 9);

        return map;
    }
}
