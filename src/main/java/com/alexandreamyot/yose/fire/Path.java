package com.alexandreamyot.yose.fire;

import java.util.LinkedList;
import java.util.List;

public class Path {
    public static List<Move> between(Point a, Point b) {

        List<Move> move = new LinkedList<>();
        for (int i = 0; i < b.x - a.x; i++) {
            move.add(new Move(0, 1));
        }

        for (int i = 0; i < b.y - a.y; i++) {
            move.add(new Move(1, 0));
        }

        return move;
    }
}
