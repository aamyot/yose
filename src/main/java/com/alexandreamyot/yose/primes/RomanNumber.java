package com.alexandreamyot.yose.primes;

import java.util.List;

public class RomanNumber implements PrimesResult {

    final String number;
    final List<String> decomposition;

    public RomanNumber(String number, List<String> decomposition) {
        this.number = number;
        this.decomposition = decomposition;
    }

    public static boolean check(String input) {
        return input.matches("[IVXLCDM]+");
    }
}
