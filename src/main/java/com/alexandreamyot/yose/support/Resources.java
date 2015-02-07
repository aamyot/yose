package com.alexandreamyot.yose.support;

import java.io.File;
import java.net.URISyntaxException;

import static java.lang.Thread.currentThread;

public class Resources {

    public static File locationOf(String path) {
        try {
            return new File(currentThread().getContextClassLoader().getResource(path).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
