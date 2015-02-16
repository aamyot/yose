package com.alexandreamyot.yose.primes;


import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.junit.Assert.assertThat;
import static org.testinfected.hamcrest.dom.DomMatchers.hasUniqueSelector;

public class PrimesTest {

    @Test
    public void uiShouldIncludeAHtmlForm() {
        String html = view("primes");
        assertThat(loadDocumentElement(html), hasUniqueSelector("form", hasUniqueSelector("#title"),
                                                                        hasUniqueSelector("#invitation"),
                                                                        hasUniqueSelector("input#number[type='text']"),
                                                                        hasUniqueSelector("button#go")));
    }

    private Element loadDocumentElement(String html) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return documentBuilder.parse(new InputSource(new StringReader(html))).getDocumentElement();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String view(String name) {
        try {
            ;
            return new String(readAllBytes(get(Paths.get("src/main/webapp/views/" + name + ".html").toUri())));
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }
}
