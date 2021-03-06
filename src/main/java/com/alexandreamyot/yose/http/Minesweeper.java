package com.alexandreamyot.yose.http;

import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.templating.Template;

import static com.vtence.molecule.http.HttpStatus.OK;

public class Minesweeper implements Application {

    public static final Object EMPTY_CONTEXT = new Object();

    private final Template view;

    public Minesweeper(Template view) {
        this.view = view;
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.status(OK);
        response.contentType("text/html");

        response.body(view.render(EMPTY_CONTEXT));
    }
}
