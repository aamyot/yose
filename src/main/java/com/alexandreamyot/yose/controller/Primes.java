package com.alexandreamyot.yose.controller;

import com.google.gson.Gson;
import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.MimeTypes;

import java.util.List;

import static com.alexandreamyot.yose.primes.Pythagoras.primesOf;
import static com.vtence.molecule.http.HttpStatus.OK;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class Primes implements Application {

    @Override
    public void handle(Request request, Response response) throws Exception {
        String input = request.parameter("number");
        if (notANumber(input)) {
            response.body(toJson(new NotANumber(input)));
        } else if (numberIsTooBig(input)) {
            response.body(toJson(new NumberIsTooBig(input)));
        } else {
            response.body(toJson(new Decomposition(input, primesOf(parseInt(input)))));
        }
        response.contentType(MimeTypes.JSON);
        response.status(OK);
    }

    private boolean notANumber(String input) {
        return !input.matches("\\d+");
    }

    private boolean numberIsTooBig(String input) {
        return valueOf(input) > 1_000_000;
    }

    private String toJson(Object result) {
        return new Gson().toJson(result);
    }

    private class Decomposition {
        final int number;
        final List<Integer> decomposition;

        public Decomposition(String number, List<Integer> decomposition) {
            this.number = parseInt(number);
            this.decomposition = decomposition;
        }
    }

    private class NotANumber {
        final String number;
        final String error = "not a number";

        public NotANumber(String input) {
            this.number = input;
        }

    }

    private class NumberIsTooBig {
        final int number;
        final String error = "too big number (>1e6)";

        public NumberIsTooBig(String input) {
            number = parseInt(input);
        }
    }
}
