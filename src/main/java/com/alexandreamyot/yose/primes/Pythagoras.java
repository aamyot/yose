package com.alexandreamyot.yose.primes;

import java.util.ArrayList;
import java.util.List;

public class Pythagoras {

    public static List<Integer> primesOf(int number) {
        List<Integer> primes = new ArrayList<>();

        for (int prime = 2; number > 1; prime++) {
            for (;number % prime == 0; number /= prime) {
                primes.add(prime);
            }
        }
        return primes;
    }
}
