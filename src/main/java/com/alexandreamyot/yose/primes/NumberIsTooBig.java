package com.alexandreamyot.yose.primes;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class NumberIsTooBig implements PrimesResult {

    final int number;
    final String error = "too big number (>1e6)";

    public NumberIsTooBig(String input) {
        number = parseInt(input);
    }

    public static boolean check(String input) {
        return valueOf(input) > 1_000_000;
    }
}