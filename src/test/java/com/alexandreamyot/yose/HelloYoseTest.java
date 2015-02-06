package com.alexandreamyot.yose;

import com.jayway.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class HelloYoseTest {

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
    public void acceptsRequestAndReturns200() {
        Response response = given().get("http://localhost:7001");

        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.contentType(), equalTo("text/html"));
        assertThat(response.asString(), containsString("Hello Yose"));
    }

}
