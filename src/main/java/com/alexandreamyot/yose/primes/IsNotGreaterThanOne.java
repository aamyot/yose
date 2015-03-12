package com.alexandreamyot.yose.primes;

import static java.lang.Integer.parseInt;

public class IsNotGreaterThanOne implements PrimesResult {

    final int number;
    final String error;

    public IsNotGreaterThanOne(String input) {
        this.number = parseInt(input);
        this.error = input + " is not an integer > 1";
    }

    public static boolean check(String input) {
        return parseInt(input) < 1;
    }
}
