package com.alexandreamyot.yose;

import com.alexandreamyot.yose.controller.Home;
import com.alexandreamyot.yose.controller.Ping;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.middlewares.Failsafe;
import com.vtence.molecule.routing.DynamicRoutes;
import com.vtence.molecule.templating.JMustacheRenderer;
import com.vtence.molecule.templating.Templates;

import java.io.IOException;

import static com.alexandreamyot.yose.support.Resources.locationOf;
import static java.lang.Integer.parseInt;

public class Yose {

    private Templates templates = new Templates(new JMustacheRenderer().fromDir(locationOf("views")).extension("html"));

    private final WebServer server;

    public Yose(int port) {
        server = WebServer.create(port);
    }

    public void start() throws IOException {
        server.add(new Failsafe());

        server.start(new DynamicRoutes() {{
            get("/").to(new Home(templates.named("home")));
            get("/ping").to(new Ping());
        }});
    }

    public void stop() throws IOException {
        server.stop();
    }

    public static void main(String[] args) throws IOException {
        new Yose(port(args)).start();
    }

    private static int port(String[] args) {
        return args.length > 0 ? parseInt(args[0]) : 8888;
    }



}
