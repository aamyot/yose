package com.alexandreamyot.yose.web;

import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.MimeTypes;
import com.vtence.molecule.templating.Template;

import static com.vtence.molecule.http.HttpStatus.OK;

public class Home implements Application {

    public static final Object EMPTY_CONTEXT = new Object();

    private final Template view;

    public Home(Template view) {
        this.view = view;
    }

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.status(OK);
        response.contentType(MimeTypes.HTML);
        response.body(view.render(EMPTY_CONTEXT));
    }
}
