package com.panagiotispetridis.day8;

import java.math.BigInteger;
import java.util.List;

// Chinese Remainder Theorem (CRT)
public class CRT {

    public static record Congruence(BigInteger a, BigInteger m) {
    }

    public static Long solve(List<Congruence> congruences) {
        BigInteger M = BigInteger.valueOf(1);
        for (var congruence : congruences) {
            M = M.multiply(congruence.m());
        }

        BigInteger solution = BigInteger.valueOf(0);
        for (var congruence : congruences) {
            BigInteger a_i = congruence.a();
            BigInteger M_i = M.divide(congruence.m());
            BigInteger N_i = M_i.modInverse(congruence.m());
            solution = solution.add(a_i.multiply(M_i).multiply(N_i).mod(M)).mod(M);
        }

        return solution.longValue();
    }
}
