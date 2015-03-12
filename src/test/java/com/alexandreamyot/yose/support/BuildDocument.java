package com.alexandreamyot.yose.support;

import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class BuildDocument {

    public static org.w3c.dom.Document document(String xml) {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return db.parse(new InputSource(new StringReader(xml)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
