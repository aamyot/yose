package com.alexandreamyot.yose.http;

import com.alexandreamyot.yose.fire.FireFighter;
import com.alexandreamyot.yose.fire.Move;
import com.alexandreamyot.yose.fire.Solution;
import com.google.gson.Gson;
import com.vtence.molecule.Application;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;

import java.util.List;

import static com.alexandreamyot.yose.support.Splitter.split;
import static com.vtence.molecule.http.MimeTypes.JSON;
import static java.lang.Integer.parseInt;

public class Fire implements Application {

    @Override
    public void handle(Request request, Response response) throws Exception {
        int width = parseInt(request.parameter("width"));
        List<String> map = split(request.parameter("map"), width);

        response.contentType(JSON);
        response.body(toJson(new Solution(map, moves(map))));
    }

    public List<Move> moves(List<String> map) {
        return FireFighter.extinguish(map);
    }

    private String toJson(Solution solution) {
        return new Gson().toJson(solution);
    }

}
