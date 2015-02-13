package com.alexandreamyot.yose.challenges;

import com.alexandreamyot.yose.Yose;
import com.jayway.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.alexandreamyot.yose.support.PrimesResultMatchers.notANumber;
import static com.alexandreamyot.yose.support.PrimesResultMatchers.tooBigNumber;
import static com.alexandreamyot.yose.support.PrimesResultMatchers.validResponse;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.json.JsonPath.from;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PrimeFactorsChallengeTest {

    private Yose server;

    @Before
    public void startServer() throws IOException {
        server = new Yose(7001);
        server.start();
    }

    @After
    public void stopServer() throws IOException {
        server.stop();
    }

    @Test
    public void decomposesANumberIntoPrimeFactors() {
        Response response = given().get("http://localhost:7001/primeFactors?number=16");

        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.contentType(), equalTo("application/json"));
        assertThat(response.asString(), validResponse("16", asList(2, 2, 2, 2)));
    }

    @Test
    public void returnsANotANumberMessageForAString() {
        Response response = given().get("http://localhost:7001/primeFactors?number=any-string");

        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.contentType(), equalTo("application/json"));
        assertThat(response.asString(), notANumber("any-string"));
    }

    @Test
    public void returnsNumberIsTooBigMessageForANumberGreatherThan1e6() {
        Response response = given().get("http://localhost:7001/primeFactors?number=1000001");

        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.contentType(), equalTo("application/json"));
        assertThat(response.asString(), tooBigNumber("1000001"));
    }

    @Test
    public void decomposesAListOfNumbers() {
        Response response = given().get("http://localhost:7001/primeFactors?number=16&number=1000001&number=any-string&number=4");

        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.contentType(), equalTo("application/json"));
        assertThat(json(response, "[0]"), validResponse("16", asList(2, 2, 2, 2)));
        assertThat(json(response, "[1]"), tooBigNumber("1000001"));
        assertThat(json(response, "[2]"), notANumber("any-string"));
        assertThat(json(response, "[3]"), validResponse("4", asList(2, 2)));
    }

    private <T> T json(Response response, String path) {
        return from(response.asString()).get(path);
    }

}
