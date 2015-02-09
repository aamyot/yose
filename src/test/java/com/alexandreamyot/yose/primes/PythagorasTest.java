package com.alexandreamyot.yose.primes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(value = Parameterized.class)
public class PythagorasTest {

    private final int number;
    private final List<Integer> primes;

    public PythagorasTest(int number, List<Integer> primes) {
        this.number = number;
        this.primes = primes;
    }

    @Parameterized.Parameters(name="Primes of {0} is {1}")
    public static Iterable<Object[]> data() {
        return asList(new Object[][] {
                {1, asList()},
                {2, asList(2)},
                {4, asList(2,2)},
                {16, asList(2,2,2,2)}
        });
    }

    @Test
    public void primesOf() {
        assertThat(new Pythagoras().primesOf(number), equalTo(primes));
    }
}
