package com.alexandreamyot.yose.http;

import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.MimeTypes;

import static com.vtence.molecule.http.HttpStatus.OK;

public class Ping implements Application {

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.contentType(MimeTypes.JSON);
        response.status(OK);
        response.body("{ \"alive\" : true }");
    }
}
