package com.alexandreamyot.yose.fire;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PathTest {

    @Test
    public void calculatesTheShortestPathBetweenTwoPoints() {
        assertThat(Path.between(new Point(1, 0), new Point(2, 1)), equalTo(asList(new Move(0, 1), new Move(1, 0))));
    }

}