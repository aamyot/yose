package com.alexandreamyot.yose.web;

import com.alexandreamyot.yose.primes.Decomposition;
import com.alexandreamyot.yose.primes.NotANumber;
import com.alexandreamyot.yose.primes.NumberIsTooBig;
import com.alexandreamyot.yose.primes.PrimesResult;
import com.google.gson.GsonBuilder;
import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.MimeTypes;

import java.util.ArrayList;
import java.util.List;

import static com.alexandreamyot.yose.primes.Pythagoras.primesOf;
import static com.vtence.molecule.http.HttpStatus.OK;
import static java.lang.Integer.parseInt;

public class Primes implements Application {

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.body(toJson(decompose(request)));
        response.contentType(MimeTypes.JSON);
        response.status(OK);
    }

    private List<PrimesResult> decompose(Request request) {
        List<PrimesResult> results = new ArrayList<>();

        List<String> inputs = request.parameters("number");
        for (String input : inputs) {
            if (NotANumber.check(input)) {
                results.add(new NotANumber(input));
            } else if (NumberIsTooBig.check(input)) {
                results.add(new NumberIsTooBig(input));
            } else {
                results.add(new Decomposition(input, primesOf(parseInt(input))));
            }
        }

        return results;
    }

    private String toJson(List<PrimesResult> results) {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(resultOf(results));
    }

    private Object resultOf(List<PrimesResult> results) {
        return results.size() > 1 ? results : results.get(0);
    }

}
