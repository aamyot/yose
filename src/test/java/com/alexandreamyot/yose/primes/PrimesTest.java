package com.alexandreamyot.yose.primes;


import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileReader;
import java.io.IOException;

import static java.nio.file.Paths.get;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.testinfected.hamcrest.dom.DomMatchers.hasUniqueSelector;

public class PrimesTest {

    @Test
    public void viewShouldIncludeAHtmlForm() {
        assertThat(view("primes"), hasUniqueSelector("form", hasUniqueSelector("#title"),
                                                             hasUniqueSelector("#invitation"),
                                                             hasUniqueSelector("input#number[type='text']"),
                                                             hasUniqueSelector("button#go"),
                                                             not(hasUniqueSelector("#result"))));
    }


    private Element view(String viewname) {
        try {
            FileReader reader = new FileReader(get("webapp/views/" + viewname + ".html").toFile());
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return documentBuilder.parse(new InputSource(reader)).getDocumentElement();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
