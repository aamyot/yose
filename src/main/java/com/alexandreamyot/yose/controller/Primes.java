package com.alexandreamyot.yose.controller;

import com.alexandreamyot.yose.primes.PrimeFactors;
import com.google.gson.Gson;
import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.MimeTypes;

import java.util.List;

import static com.vtence.molecule.http.HttpStatus.OK;

public class Primes implements Application {

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.contentType(MimeTypes.JSON);
        response.status(OK);
        response.body(toJson(new PrimeResult(16, PrimeFactors.decompose(16))));
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
