package com.alexandreamyot.yose.support.actors;

import com.alexandreamyot.yose.support.pages.PrimesPage;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class User {


    private PrimesPage page;

    public User opensThePrimesUI() {
        page = new PrimesPage();
        page.go();
        return this;
    }

    public User entersANumber(String number) {
        page.number(number);
        return this;
    }

    public User submitsToDecompose() {
        page.submit();
        return this;
    }

    public void andSeesThePrimesOfTheNumber(List<Integer> primes) {
        assertThat(page.result(), equalTo(expectedResult(page.number(), primes)));
    }

    private String expectedResult(String number, List<Integer> primes) {
        return number + " = " + primes.stream().map(String::valueOf).collect(joining(" x "));
    }

    public void andSeesTheErrorMessage(String error) {
        assertThat(page.result(), equalTo(error));
    }
}
