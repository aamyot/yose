package com.alexandreamyot.yose.web;

import com.alexandreamyot.yose.primes.NotANumber;
import com.alexandreamyot.yose.primes.NumberIsTooBig;
import com.alexandreamyot.yose.primes.PrimesResult;
import com.alexandreamyot.yose.primes.ValidResult;
import com.google.gson.Gson;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.MimeTypes;
import com.vtence.molecule.templating.Template;

import java.util.List;

import static com.alexandreamyot.yose.primes.Pythagoras.primesOf;
import static com.vtence.molecule.http.HttpStatus.OK;
import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

public class Primes {

    public static final Object EMPTY_CONTEXT = new Object();

    private final Template view;

    public Primes(Template view) {
        this.view = view;
    }


    public void list(Request request, Response response) throws Exception {
        List<PrimesResult> results = request.parameters("number").stream().map(this::decompose).collect(toList());
        response.body(toJson(results));
        response.contentType(MimeTypes.JSON);
    }


    public void ui(Request request, Response response) throws Exception {
        response.status(OK);
        response.contentType(MimeTypes.HTML);
        response.body(view.render(EMPTY_CONTEXT));
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
