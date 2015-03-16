package com.alexandreamyot.yose.primes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;

public class Pythagoras {

    public static List<Integer> primesOf(int number) {
        return IntStream.rangeClosed(2, number)
                        .filter(candidate -> number % candidate == 0)
                        .mapToObj(subPrimesOf(number))
                        .findFirst().orElse(emptyList());
    }

    private static IntFunction<List<Integer>> subPrimesOf(int number) {
        return candidate -> {
            List<Integer> primes = new ArrayList<>();
            primes.add(candidate);
            primes.addAll(primesOf(number / candidate));
            return primes;
        };
    }
}
