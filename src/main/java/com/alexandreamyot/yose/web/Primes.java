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

import static com.alexandreamyot.yose.primes.Pythagoras.primesOf;
import static com.vtence.molecule.http.HttpStatus.OK;
import static java.lang.Integer.parseInt;

public class Primes implements Application {

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.body(toJson(process(request)));
        response.contentType(MimeTypes.JSON);
        response.status(OK);
    }

    private PrimesResult process(Request request) {
        String input = request.parameter("number");
        if (NotANumber.check(input)) {
            return new NotANumber(input);
        } else if (NumberIsTooBig.check(input)) {
            return new NumberIsTooBig(input);
        } else {
            return new Decomposition(input, primesOf(parseInt(input)));
        }
    }

    private String toJson(Object result) {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(result);
    }

}
