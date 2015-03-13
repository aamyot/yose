package com.alexandreamyot.yose.support;

import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileReader;

import static java.nio.file.Paths.get;

public class BuildDocument {

    public static org.w3c.dom.Document document(String file) {
        try {
            FileReader reader = new FileReader(get(file).toFile());
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return db.parse(new InputSource(reader));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
