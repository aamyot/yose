package com.alexandreamyot.yose.fire;

import java.util.List;

public class FireFighter {

    public static List<Move> extinguish(List<String> map) {
        Land land = Land.parse(map);

        List<Move> moves = Path.between(land.findPlane(), land.findWater());
        moves.addAll(Path.between(land.findWater(), land.findFire()));

        return moves;
    }

}
