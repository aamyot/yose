package com.alexandreamyot.yose;

import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.MimeTypes;

import static com.vtence.molecule.http.HttpStatus.OK;

public class HelloYose implements Application {

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.body(
            "<html>" +
            "<body>" +
            "   <div>Hello Yose</div>" +
            "</body>" +
            "</html>"
        );
        response.contentType(MimeTypes.HTML);
        response.status(OK);
    }
}
