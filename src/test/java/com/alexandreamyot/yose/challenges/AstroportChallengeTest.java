package com.alexandreamyot.yose.challenges;

import com.alexandreamyot.yose.Yose;
import com.alexandreamyot.yose.support.pages.AstroportPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AstroportChallengeTest {

    Yose server;
    private AstroportPage astroportPagePage;

    @Before
    public void startServerAndNavigateToPage() throws IOException {
        server = new Yose(7001);
        server.start();

        astroportPagePage = new AstroportPage();
        astroportPagePage.go();
    }

    @After
    public void stopServer() throws IOException {
        server.stop();
    }

    @Test
    public void displaysTheNameOfTheAstroport() {
        astroportPagePage.displaysTheAstroportName("AstroYUL");
    }

    @Test
    public void displaysGatesWithShipNames() {
        astroportPagePage.displaysGatesWithShipNames();
    }
}
