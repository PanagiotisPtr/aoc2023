package com.panagiotispetridis.day13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class Solver {
    public Solver() {}

    public Output solvePart1(Input input) {
        long answer = 0;

        for (var pattern : input.patterns()) {
            var vertical = this.reflectionPoint(pattern.getData());
            if (vertical.isPresent()) {
                answer += vertical.get();
                continue;
            }
            var horizontal = this.reflectionPoint(pattern.getDataTransposed());
            if (horizontal.isPresent()) {
                answer += 100L * horizontal.get();
            }
        }

        return new Output(answer);
    }

    public Output solvePart2(Input input) {
        long answer = 0;

        for (var pattern : input.patterns()) {
            var vertical = this.reflectionPointWithSmudge(pattern.getData());
            if (vertical.isPresent()) {
                answer += vertical.get();
                continue;
            }
            var horizontal = this.reflectionPointWithSmudge(pattern.getDataTransposed());
            if (horizontal.isPresent()) {
                answer += 100L * horizontal.get();
            }
        }

        return new Output(answer);
    }

    private Optional<Integer> reflectionPoint(List<List<Character>> data) {
        List<HashSet<Integer>> points = new ArrayList<>();

        for (var row : data) {
            points.add(this.getReflections(row));
        }

        return this.getReflection(points);
    }

    private boolean isValidReflection(int pos, List<Character> row) {
        int left = pos-1;
        int right = pos;
        while (left >= 0 && right < row.size()) {
            if (row.get(left) != row.get(right)) {
                return false;
            }
            left--;
            right++;
        }

        return true;
    }

    private void flip(List<Character> row, int index) {
        if (row.get(index) == '.') {
            row.set(index, '#');
        } else {
            row.set(index, '.');
        }
    }

    private HashSet<Integer> getReflections(List<Character> row) {
        HashSet<Integer> validReflections = new HashSet<>();
        for (int i = 1; i < row.size(); i++) {
            if (this.isValidReflection(i,row)) {
                validReflections.add(i);
            }
        }

        return validReflections;
    }

    private Optional<Integer> reflectionPointWithSmudge(List<List<Character>> data) {
        List<HashSet<Integer>> originals = new ArrayList<>();
        List<List<HashSet<Integer>>> mutated = new ArrayList<>();
        for (var row : data) {
            originals.add(this.getReflections(row));
            List<HashSet<Integer>> reflections = new ArrayList<>();
            for (int i = 0; i < row.size(); i++) {
                flip(row, i);
                reflections.add(this.getReflections(row));
                flip(row, i);
            }

            mutated.add(reflections);
        }

        var originalLine = this.getReflection(originals);
        for (int i = 0; i < mutated.size(); i++) {
            var o = originals.get(i);
            for (var m : mutated.get(i)) {
                originals.set(i, m);
                for (var result : this.getAllReflectionsFromPoints(originals)) {
                    if (originalLine.isEmpty()) {
                        return Optional.of(result);
                    }
                    if (!originalLine.get().equals(result)) {
                        return Optional.of(result);
                    }
                }
            }

            originals.set(i, o);
        }

        return Optional.empty();
    }

    private Optional<Integer> getReflection(List<HashSet<Integer>> points) {
        var result = this.getAllReflectionsFromPoints(points);
        if (result.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(result.getFirst());
    }

    private List<Integer> getAllReflectionsFromPoints(List<HashSet<Integer>> points) {
        var result = new HashSet<>(points.getFirst());
        for (var r : points) {
            List<Integer> toRemove = new ArrayList<>();
            for (var p : result) {
                if (!r.contains(p)) {
                    toRemove.add(p);
                }
            }
            toRemove.forEach(result::remove);
        }

        return new ArrayList<>(result);
    }
}
