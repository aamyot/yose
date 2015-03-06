package com.alexandreamyot.yose.challenges;

import com.alexandreamyot.yose.Yose;
import com.vtence.molecule.testing.HttpRequest;
import com.vtence.molecule.testing.HttpResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

import static com.vtence.molecule.testing.HttpResponseAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.AllOf.allOf;

public class PrimeFactorsChallengeTest {

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
    public void decomposesANumberIntoPrimeFactors() throws IOException {
        HttpResponse response = request.get("/primeFactors?number=16");

        assertThat(response).hasStatusCode(200)
                            .hasContentType("application/json")
                            .hasBodyText("{\"number\":16,\"decomposition\":[2,2,2,2]}");
    }

    @Test
    public void returnsANotANumberMessageForAString() throws IOException {
        HttpResponse response = request.get("/primeFactors?number=any-string");

        assertThat(response).hasStatusCode(200)
                            .hasContentType("application/json")
                            .hasBodyText("{\"number\":\"any-string\",\"error\":\"not a number\"}");
    }

    @Test
    public void returnsNumberIsTooBigMessageForANumberGreatherThan1e6() throws IOException {
        HttpResponse response = request.get("/primeFactors?number=1000001");

        assertThat(response).hasStatusCode(200)
                            .hasContentType("application/json")
                            .hasBodyText("{\"number\":1000001,\"error\":\"too big number (\\u003e1e6)\"}");
    }

    @Test
    public void decomposesAListOfNumbers() throws IOException {
        HttpResponse response = request.get("/primeFactors?number=300&number=any-string&number=4");

        assertThat(response).hasStatusCode(200)
                            .hasContentType("application/json")
                            .hasBodyText(equalTo(
                                    "[" +
                                        "{\"number\":300,\"decomposition\":[2,2,3,5,5]}," +
                                        "{\"number\":\"any-string\",\"error\":\"not a number\"}," +
                                        "{\"number\":4,\"decomposition\":[2,2]}" +
                                    "]"));
    }

    @Test
    public void pageIncludesAnHtmlFormForDecomposingANumber() throws IOException {
        HttpResponse response = request.get("/primeFactors/ui");

        assertThat(response).hasStatusCode(200)
                            .hasContentType("text/html")
                            .hasBodyText(allOf(
                                    containsString("id=\"title\""),
                                    containsString("id=\"invitation\""),
                                    containsString("<input type=\"text\" id=\"number\""),
                                    containsString("<button id=\"go\"")));
    }

    @Test
    @Ignore
    public void displaysTheResultOfTheInput() {
        WebDriver webDriver = new FirefoxDriver();
        Runtime.getRuntime().addShutdownHook(new Thread(webDriver::quit));

        webDriver.navigate().to("http://localhost:7001/primeFactors/ui");
        WebElement input = webDriver.findElement(By.id("number"));
        input.clear();
        input.sendKeys("24");
        input.submit();

        Assert.assertThat(webDriver.findElement(By.id("number")).getText(), equalTo("24"));
    }

}
