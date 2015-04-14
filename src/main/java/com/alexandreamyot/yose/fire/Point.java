package com.alexandreamyot.yose.fire;

public class Point {

    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Point point = (Point) o;

        return x == point.x && y == point.y;
    }

    @Override
    public String toString() {
        return String.format("Point(%s, %s)", x, y);
    }
}
