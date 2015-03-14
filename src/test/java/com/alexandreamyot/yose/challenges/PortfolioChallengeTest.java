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

public class PortfolioChallengeTest {

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
    public void includesALinkToMyContact() throws IOException {
        HttpResponse response = request.get("/");

        assertThat(response).hasBodyText(containsString("<a id=\"contact-me-link\" href=\"http://ca.linkedin.com/in/alexandreamyot\">Contact</a>"));
    }

    @Test
    public void includesALinkToThePingChallenge() throws IOException {
        HttpResponse response = request.get("/");

        assertThat(response).hasBodyText(containsString("<a id=\"ping-challenge-link\" href=\"/ping\">Ping</a>"));
    }

    @Test
    public void includesALinkToThePrimeFactorsChallenge() throws IOException {
        HttpResponse response = request.get("/");

        assertThat(response).hasBodyText(containsString("<a id=\"prime-factors-decomposition-link\" href=\"/primeFactors/ui\">Prime Factors</a>"));
    }
}
