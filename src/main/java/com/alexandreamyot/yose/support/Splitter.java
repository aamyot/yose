package com.alexandreamyot.yose.support;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class Splitter {

    public static List<String> split(String map, int width) {
        return range(0, width).mapToObj(index -> map.substring(index * width, index * width + width))
                              .collect(toList());
    }
}
