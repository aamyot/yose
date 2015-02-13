package com.alexandreamyot.yose.support;

import org.hamcrest.Matcher;

import java.util.List;

import static net.javacrumbs.jsonunit.JsonMatchers.jsonPartEquals;
import static org.hamcrest.Matchers.allOf;

public class PrimesResultMatchers {

    public static Matcher<String> validResponse(String number, List<Integer> primes) {
        return allOf(withNumber(number), jsonPartEquals("decomposition", primes));
    }

    public static Matcher<String> notANumber(String number) {
        return allOf(withNumber(number), jsonPartEquals("error", "not a number"));
    }

    public static Matcher<String> tooBigNumber(String number) {
        return allOf(withNumber(number), jsonPartEquals("error", "too big number (>1e6)"));
    }

    public static Matcher<String> withNumber(String number) {
        return jsonPartEquals("number", number);
    }

}
