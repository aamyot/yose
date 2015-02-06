package com.alexandreamyot.yose;

import com.vtence.molecule.WebServer;
import com.vtence.molecule.http.MimeTypes;

import java.io.IOException;

import static com.vtence.molecule.http.HttpStatus.OK;

public class Yose {

    private final WebServer server;

    public Yose(int port) {
        server = WebServer.create(port);
    }

    public void start() throws IOException {
        server.start((request, response) -> {
            response.body(
                    "<html>" +
                    "<body>" +
                    "   <div>Hello Yose</div>" +
                    "</body>" +
                    "</html>"
            );
            response.contentType(MimeTypes.HTML);
            response.status(OK);
        });
    }

    public void stop() throws IOException {
        server.stop();
    }

    public static void main(String... args) throws IOException {
        new Yose(8080).start();
    }

}
