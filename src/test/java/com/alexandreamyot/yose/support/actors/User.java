package com.alexandreamyot.yose.support.actors;

import com.alexandreamyot.yose.support.pages.PrimesPage;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class User {


    private PrimesPage page;

    public User openThePrimesUI() {
        page = new PrimesPage();
        page.go();
        return this;
    }

    public User enterANumber(String number) {
        page.number(number);
        return this;
    }

    public User submitToDecompose() {
        page.submit();
        return this;
    }

    public User andSeesThePrimesOfTheNumber(List<Integer> primes) {
        assertThat(page.result(), equalTo(expectedResult(page.number(), primes)));
        return this;
    }

    private String expectedResult(String number, List<Integer> primes) {
        return number + " = " + primes.stream().map(String::valueOf).collect(joining(" x "));
    }
}
