package com.alexandreamyot.yose.controller;

import com.alexandreamyot.yose.primes.Pythagoras;
import com.google.gson.Gson;
import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.MimeTypes;

import java.util.List;

import static com.vtence.molecule.http.HttpStatus.OK;
import static java.lang.Integer.valueOf;

public class Primes implements Application {

    @Override
    public void handle(Request request, Response response) throws Exception {
        int number = valueOf(request.parameter("number"));
        response.body(toJson(new PrimeResult(number, new Pythagoras().primesOf(number))));
        response.contentType(MimeTypes.JSON);
        response.status(OK);
    }

    private String toJson(PrimeResult result) {
        return new Gson().toJson(result);
    }

    private class PrimeResult {
        final int number;
        final List<Integer> decomposition;

        public PrimeResult(int number, List<Integer> decomposition) {
            this.number = number;
            this.decomposition = decomposition;
        }
    }
}
