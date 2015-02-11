package com.alexandreamyot.yose.primes;

public class NotANumber implements PrimesResult {

    final String number;
    final String error = "not a number";

    public NotANumber(String input) {
        this.number = input;
    }

    public static boolean check(String input) {
        return !input.matches("\\d+");
    }
}
