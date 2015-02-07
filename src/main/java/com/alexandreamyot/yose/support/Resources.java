package com.alexandreamyot.yose.support;

import java.io.File;

public class Resources {

    public static File locationOf(String path) {
        return new File(Resources.class.getClassLoader().getResource(path).getFile());
    }
}
