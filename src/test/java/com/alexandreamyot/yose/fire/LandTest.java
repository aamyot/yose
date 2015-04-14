package com.alexandreamyot.yose.fire;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class LandTest {

    @Test
    public void findsPlaneInALand() {
        Land land = Land.parse(asList("...", "P..", ".WF"));

        assertThat(land.findPlane(land), equalTo(new Point(0, 1)));
    }


}