package com.panagiotispetridis.day8;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import com.panagiotispetridis.day8.CRT.Congruence;

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

        BiFunction<String, Long, String> advance = (node, limit) -> {
            Long steps = Long.valueOf(0);
            String curr = node;
            while (steps < limit) {
                Long position = steps % input.instructions().size();
                // this is safe because we mod
                var instruction = input.instructions().get(position.intValue());
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

            return curr;
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
         *
         * If the moduli/coefficient **are coprime** then the solution can be found from CRT if the
         * moduli are **not coprime** then the (possible) answer is the LCM of the moduli
         */
        List<Congruence> congruences = new ArrayList<>();
        List<Long> pathLengths = new ArrayList<>();
        for (var node : startNodes) {
            System.out.println("at node: " + node);
            var D = pathLength.apply(node);
            var targetNodes = input.graph().getTargetNodes(node, input.instructions());

            System.out.println("found target nodes: ");
            for (var n : targetNodes) {
                System.out.printf("node: %s\tposition: %d\tperiod: %d\n", n.node(), n.dist(),
                        n.period());
                if (n.period() == 0) {
                    continue;
                }
                long remainder = n.dist() == n.period() ? 0 : n.dist();
                congruences.add(new Congruence(BigInteger.valueOf(remainder),
                        BigInteger.valueOf(n.period())));
            }
            System.out.println();

            pathLengths.add(D);
        }

        System.out.println("equations: ");
        int a = 1;
        for (var e : congruences) {
            System.out.printf("K = %d + %d * N_%d\n", e.a(), e.m(), a++);
        }
        System.out.println();

        long answer = 0;
        try {
            System.out.println("trying with CRT");
            answer = CRT.solve(congruences);
        } catch (Exception e) {
            System.out.println("moduli are not coprime, answer is LCM");
            List<Long> moduli = new ArrayList<>();
            for (var c : congruences) {
                moduli.add(c.m().longValue());
            }
            answer = this.lcm(moduli);
        }

        // validate
        // for (var n : startNodes) {
        // var end = advance.apply(n, answer);
        // System.out.printf("node %s ended at %s\n", n, end);
        // }

        return new Output(answer);
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
