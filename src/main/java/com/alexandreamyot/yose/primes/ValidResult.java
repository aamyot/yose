package com.alexandreamyot.yose.primes;

import java.util.List;

import static java.lang.Integer.parseInt;

public class ValidResult implements PrimesResult {

    final int number;
    final List<Integer> decomposition;

    public ValidResult(String number, List<Integer> decomposition) {
        this.number = parseInt(number);
        this.decomposition = decomposition;
    }
}
