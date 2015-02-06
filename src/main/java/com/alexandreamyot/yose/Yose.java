package com.alexandreamyot.yose;

import com.alexandreamyot.yose.controller.Home;
import com.alexandreamyot.yose.controller.Ping;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.middlewares.Failsafe;
import com.vtence.molecule.routing.DynamicRoutes;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Yose {

    private final WebServer server;

    public Yose(int port) {
        server = WebServer.create(port);
    }

    public void start() throws IOException {
        server.add(new Failsafe());

        server.start(new DynamicRoutes() {{
            get("/").to(new Home());
            get("/ping").to(new Ping());
        }});
    }

    public void stop() throws IOException {
        server.stop();
    }

    public static void main(String[] args) throws IOException {
        new Yose(parseInt(args[0])).start();
    }

}
