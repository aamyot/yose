package com.alexandreamyot.yose.challenges;

import com.alexandreamyot.yose.Yose;
import com.vtence.molecule.testing.HttpRequest;
import com.vtence.molecule.testing.HttpResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.vtence.molecule.testing.HttpResponseAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class StartChallengeTest {

    HttpRequest request = new HttpRequest(7001);
    Yose server ;

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
    public void homeContainsTheStringHelloYose() throws IOException {
        HttpResponse response = request.get("http://localhost:7001");

        assertThat(response).hasStatusCode(200);
        assertThat(response).hasContentType("text/html");
        assertThat(response).hasBodyText(containsString("Hello Yose"));
    }

    @Test
    public void homeIncludesALinkToAGitHubRepository() throws IOException {
        HttpResponse response = request.get("http://localhost:7001");

        assertThat(response).hasBodyText(containsString("<a id=\"repository-link\" href=\"https://github.com/aamyot/yose\">GitHub</a>"));
    }

    @Test
    public void respondsAliveToAPing() throws IOException {
        HttpResponse response = request.get("http://localhost:7001/ping");

        assertThat(response).hasStatusCode(200);
        assertThat(response).hasContentType("application/json");
        assertThat(response).hasBodyText("{ \"alive\" : true }");
    }


}
