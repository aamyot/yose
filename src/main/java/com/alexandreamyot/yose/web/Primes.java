package com.alexandreamyot.yose.web;

import com.alexandreamyot.yose.primes.NotANumber;
import com.alexandreamyot.yose.primes.NumberIsTooBig;
import com.alexandreamyot.yose.primes.PrimesResult;
import com.alexandreamyot.yose.primes.ValidResult;
import com.google.gson.Gson;
import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.MimeTypes;

import java.util.List;

import static com.alexandreamyot.yose.primes.Pythagoras.primesOf;
import static com.vtence.molecule.http.HttpStatus.OK;
import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

public class Primes implements Application {

    @Override
    public void handle(Request request, Response response) throws Exception {
        List<PrimesResult> results = request.parameters("number").stream().map(this::decompose).collect(toList());
        response.body(toJson(results));
        response.contentType(MimeTypes.JSON);
        response.status(OK);
    }

    private PrimesResult decompose(String input) {
        if (NotANumber.check(input)) {
            return new NotANumber(input);
        } else if (NumberIsTooBig.check(input)) {
            return new NumberIsTooBig(input);
        }

        return new ValidResult(input, primesOf(parseInt(input)));
    }

    private String toJson(List<PrimesResult> results) {
        return new Gson().toJson(firstOrList(results));
    }

    private Object firstOrList(List<PrimesResult> results) {
        return results.size() > 1 ? results : results.get(0);
    }

}
