package com.alexandreamyot.yose.support;

import java.io.File;
import java.net.URISyntaxException;

public class Resources {

    public static File locationOf(String path) {
        try {
            return new File(Resources.class.getClassLoader().getResource(path).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
