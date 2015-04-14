package com.alexandreamyot.yose.fire;

import java.util.List;

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

    public Point findPlane(Land land) {

        range(0, cells.length).boxed()
                              .flatMap(row -> range(0, cells[row].length).boxed().map(col -> new Point(row, col)))
                              .forEach(System.out::println);

        return new Point(0, 1);
    }



}
