package com.alexandreamyot.yose.controller;

import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static com.vtence.molecule.support.ResponseAssertions.assertThat;
import static java.util.Arrays.asList;
import static net.javacrumbs.jsonunit.JsonMatchers.jsonPartEquals;
import static org.hamcrest.Matchers.allOf;

public class PrimesTest {

    Request request = new Request();
    Response response = new Response();

    Primes primes;

    @Before
    public void thisHandler() {
        primes = new Primes();
    }

    @Test
    public void callsPrimeFactorsWithTheRequestedNumber() throws Exception {
        request.addParameter("number", "8");
        primes.handle(request, response);

        assertThat(response).hasStatus(HttpStatus.OK)
                            .hasContentType("application/json")
                            .hasBodyText(allOf(jsonPartEquals("number", "8"),
                                               jsonPartEquals("decomposition", asList(2, 2, 2))));
    }

}