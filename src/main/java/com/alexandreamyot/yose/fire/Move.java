package com.alexandreamyot.yose.fire;

public class Move {

    public final int dx;
    public final int dy;

    public Move(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public boolean equals(Object o) {
        Move point = (Move) o;

        return dx == point.dx && dy == point.dy;
    }

    @Override
    public String toString() {
        return String.format("Move(%s, %s)", dx, dy);
    }
}
