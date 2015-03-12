package com.alexandreamyot.yose.primes;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class IsTooBig implements PrimesResult {

    final int number;
    final String error = "too big number (>1e6)";

    public IsTooBig(String input) {
        number = parseInt(input);
    }

    public static boolean check(String input) {
        return valueOf(input) > 1_000_000;
    }
}