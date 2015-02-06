package com.alexandreamyot.yose.controller;

import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;
import com.vtence.molecule.http.MimeTypes;

public class Home implements Application {

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.contentType(MimeTypes.HTML);
        response.body(
                "<html>" +
                "<body>" +
                "   <div>Hello Yose</div>" +
                "   <br/>" +
                "   <a id=\"repository-link\" href=\"https://github.com/aamyot/yose\">GitHub</a>" +
                "</body>" +
                "</html>"
        );
    }
}
