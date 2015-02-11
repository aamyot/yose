package com.alexandreamyot.yose;

import com.alexandreamyot.yose.web.Home;
import com.alexandreamyot.yose.web.Ping;
import com.alexandreamyot.yose.web.Primes;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.middlewares.Failsafe;
import com.vtence.molecule.middlewares.FileServer;
import com.vtence.molecule.middlewares.StaticAssets;
import com.vtence.molecule.routing.DynamicRoutes;
import com.vtence.molecule.templating.JMustacheRenderer;
import com.vtence.molecule.templating.Templates;

import java.io.File;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Yose {

    private final WebServer server;
    private final File webroot;
    private final Templates templates;

    public Yose(int port) {
        this.server = WebServer.create(port);
        this.webroot = new File("src/main/webapp");
        this.templates = new Templates(new JMustacheRenderer().fromDir(new File(webroot, "views")).extension("html"));
    }

    public void start() throws IOException {
        server.add(new Failsafe())
              .add(staticAssets())
              .start(routes());
    }

    public void stop() throws IOException {
        server.stop();
    }

    private DynamicRoutes routes() {
        return new DynamicRoutes() {{
            get("/").to(new Home(templates.named("home")));
            get("/ping").to(new Ping());
            get("/primeFactors").to(new Primes());
        }};
    }

    private StaticAssets staticAssets() {
        return new StaticAssets(new FileServer(webroot)).serve("/css");
    }

    public static void main(String[] args) throws IOException {
        new Yose(port(args)).start();
    }

    private static int port(String[] args) {
        return args.length > 0 ? parseInt(args[0]) : 8080;
    }
}
