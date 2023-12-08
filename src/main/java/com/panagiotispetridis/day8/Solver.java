package com.panagiotispetridis.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Solver {
    public Solver() {}

    public Output solvePart1(Input input) {
        int steps = 0;

        String curr = "AAA";
        while (!curr.equals("ZZZ")) {
            var instruction = input.instructions().get(steps % input.instructions().size());
            switch (instruction) {
                case 'L':
                    curr = input.graph().neighbours(curr).get(0);
                    break;
                default:
                    curr = input.graph().neighbours(curr).get(1);
                    break;
            }
            steps++;
        }

        return new Output(steps);
    }

    public Output solvePart2(Input input) {
        List<String> startNodes = new ArrayList<>();
        for (var node : input.graph().graph().keySet()) {
            if (node.charAt(node.length() - 1) == 'A') {
                startNodes.add(node);
            }
        }

        Function<String, Long> pathLength = node -> {
            int steps = 0;
            String curr = node;
            while (curr.charAt(curr.length() - 1) != 'Z') {
                var instruction = input.instructions().get(steps % input.instructions().size());
                switch (instruction) {
                    case 'L':
                        curr = input.graph().neighbours(curr).get(0);
                        break;
                    default:
                        curr = input.graph().neighbours(curr).get(1);
                        break;
                }
                steps++;
            }

            return Long.valueOf(steps);
        };

        /*
         * For each one of the start points we know that there will be a cycle (otherwise there'd be
         * no solution)
         *
         * The cycle can be at any point (see Graph.findCycle)
         *
         * In general though the path from a start node XXA to an end node XXZ will be:
         *
         * P = D + L * N
         *
         * Where D is the length of the path from the node XXA to XXZ and L is the length of the
         * cycle.
         *
         * If you draw it out you should see it. eg. XXA -> XXB -> XXC -> XXZ -> XXD -> XXB
         *
         * D = 3 L = 4
         *
         * We will be at Z at steps: 3, 7, 11, ...
         *
         * So I'll build a system of equations A_i = D_i + L_i * N_i
         *
         * And then set them all equal to K. Now I just need to find K.
         *
         * There's some smart way to find this using the Chinese Remainder Theorem
         * (https://cp-algorithms.com/algebra/chinese-remainder-theorem.html) but I can't come up
         * with it so I'll just brute force this part (there are very few starting points)
         */

        /*
         * The notes above are wrong but I'll leave them there as I think it's an interesting idea.
         * * cycles without taking int consideration the instructions. We might go through the same
         * node but with a different instruction so it's not a proper cycle. Instead I've simplified
         * it where each full path is a cycle (this is an assumption but seems to work)
         */
        List<Long> pathLengths = new ArrayList<>();
        for (var node : startNodes) {
            var D = pathLength.apply(node);
            pathLengths.add(D);
        }

        return new Output(this.lcm(pathLengths));
    }

    private long lcm(List<Long> numbers) {
        long ans = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++)
            ans = (((numbers.get(i) * ans)) / (gcd(numbers.get(i), ans)));

        return ans;
    }

    private long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

}
