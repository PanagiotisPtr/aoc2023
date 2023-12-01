package com.panagiotispetridis.day1;

import java.util.ArrayList;
import java.util.List;

class TrieNode {
    final private char c;
    private Integer value;
    private boolean terminal;
    final private List<TrieNode> children;

    // child node
    TrieNode(char c) {
        this.c = c;
        this.value = 0;
        this.terminal = false;
        this.children = new ArrayList<>();
    }

    // Terminal node
    TrieNode(Integer value) {
        this.c = '*';
        this.value = value;
        this.terminal = true;
        this.children = new ArrayList<>();
    }

    char getChar() {
        return this.c;
    }

    List<TrieNode> getChildren() {
        return this.children;
    }

    void add(TrieNode n) {
        this.children.add(n);
    }

    TrieNode getTerminal() {
        for (var n : this.children) {
            if (n.terminal) {
                return n;
            }
        }

        return null;
    }

    Integer getValue() {
        return this.value;
    }

    TrieNode next(char c) {
        for (var n : this.children) {
            if (n.c == c) {
                return n;
            }
        }

        return null;
    }
}
