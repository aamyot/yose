package com.alexandreamyot.yose.fire;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class Land {

    private final Character[][] cells;

    public Land(Character[][] cells) {
        this.cells = cells;
    }

    public static Land parse(List<String> map) {
        Character[][] cells = map.stream().map(line -> line.chars().mapToObj(c -> (char) c).toArray(Character[]::new)).toArray(Character[][]::new);

        return new Land(cells);
    }

    public Character[][] cells() {
        return cells;
    }

    public Point findPlane() {
        return find('P');
    }

    public Point findWater() {
        return find('W');
    }

    public Point findFire() {
        return find('F');
    }

    public Point find(Character what) {
        return rows().flatMap(row -> cols(row).filter(col -> cells[row][col] == what).map(col -> new Point(row, col))).findFirst().get();
    }

    private Stream<Integer> cols(Integer row) {
        return range(0, cells[row].length).boxed();
    }

    private Stream<Integer> rows() {
        return range(0, cells.length).boxed();
    }
}
