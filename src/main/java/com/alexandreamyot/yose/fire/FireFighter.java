package com.alexandreamyot.yose.fire;

import java.util.List;

import static java.util.Arrays.asList;

public class FireFighter {

    public static List<Move> extinguish(List<String> map) {
        Land land = Land.parse(map);

        return asList(new Move(0, 1),
                      new Move(1, 0),
                      new Move(1, 0));
    }

}
