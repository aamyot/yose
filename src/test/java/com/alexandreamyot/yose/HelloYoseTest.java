package com.alexandreamyot.yose;

import com.jayway.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
    public void returns200ForAnyRequest() {
        Response response = given().get("http://localhost:7001");

        assertThat(response.statusCode(), equalTo(200));
    }

    @Test
    public void returnsHelloYoseInTheHtmlBody() {
        Response response = given().get("http://localhost:7001/hello");

        assertThat(response.asString(), containsString("Hello Yose"));
    }

}
