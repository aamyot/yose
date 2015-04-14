package com.alexandreamyot.yose.fire;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PointTest {

    @Test
    public void pointsAreEqualIfTheyHaveSameCoordinates() throws Exception {
        assertThat(new Point(0, 1), equalTo(new Point(0, 1)));
    }
}