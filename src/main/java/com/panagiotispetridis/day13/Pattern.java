package com.panagiotispetridis.day13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pattern {
    final private List<List<Character>> data;

    final private List<List<Character>> dataTransposed;

    public Pattern(List<List<Character>> data) {
        this.data = data;
        this.dataTransposed = this.transpose(data);
    }

    private List<List<Character>> transpose(List<List<Character>> data) {
        List<List<Character>> dataTransposed = new ArrayList<>();
        for (int i = 0; i < data.getFirst().size(); i++) {
            dataTransposed.add(new ArrayList<>(Collections.nCopies(data.size(), ' ')));
        }

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).size(); j++) {
                dataTransposed.get(j).set(i, data.get(i).get(j));
            }
        }

        return dataTransposed;
    }

    public List<List<Character>> getData() {
        return this.data;
    }

    public List<List<Character>> getDataTransposed() {
        return this.dataTransposed;
    }
}