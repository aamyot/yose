package com.alexandreamyot.yose.challenges;

import com.alexandreamyot.yose.Yose;
import com.jayway.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static net.javacrumbs.jsonunit.JsonMatchers.jsonPartEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PrimeFactorsChallengeTest {

    private Yose server ;

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
    public void powerOfTwoChallenge() {
        Response response = given().get("http://localhost:7001/primeFactors?number=16");

        assertThat(response.contentType(), equalTo("application/json"));
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.asString(), allOf(jsonPartEquals("number", "16"),
                                              jsonPartEquals("decomposition", asList(2,2,2,2))));
    }
}
