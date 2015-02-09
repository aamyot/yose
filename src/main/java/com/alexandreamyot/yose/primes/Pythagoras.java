package com.alexandreamyot.yose.primes;

import java.util.ArrayList;
import java.util.List;

public class Pythagoras {

    public List<Integer> primesOf(int number) {
        List<Integer> primes = new ArrayList<>();
        if (number > 1) {
            int prime = 2;
            while (number % prime == 0) {
                primes.add(prime);
                number /= prime;
            }
        }
        return primes;
    }
}
