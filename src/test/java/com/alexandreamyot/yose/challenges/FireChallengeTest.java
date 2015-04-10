package com.alexandreamyot.yose.challenges;

import com.alexandreamyot.yose.Yose;
import com.vtence.molecule.testing.HttpRequest;
import com.vtence.molecule.testing.HttpResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.vtence.molecule.testing.HttpResponseAssert.assertThat;

public class FireChallengeTest {

    HttpRequest request = new HttpRequest(7001);
    Yose server;

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
    public void extinguishesFireWithAPlane() throws IOException {
        HttpResponse response = request.get("/fire/geek?width=3&map=...P...WF");

        assertThat(response).hasStatusCode(200)
                            .hasContentType("application/json")
                            .hasBodyText("{" +
                                            "\"map\":[" +
                                                "\"...\"," +
                                                "\"P..\"," +
                                                "\".WF\"" +
                                            "]," +
                                            "\"moves\":[" +
                                                "{\"dx\":0,\"dy\":1}," +
                                                "{\"dx\":1,\"dy\":0}," +
                                                "{\"dx\":1,\"dy\":0}" +
                                            "]" +
                                         "}");

    }
}
