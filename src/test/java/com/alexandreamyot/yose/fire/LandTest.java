package com.alexandreamyot.yose.fire;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class LandTest {

    private Land land;

    @Before
    public void thisLand() throws Exception {
        land = Land.parse(asList("...", "P..", ".WF"));
    }

    @Test
    public void parsesAListOfStringIntoALand() {
        assertThat(Land.parse(asList("...", "P..", ".WF")).cells(), equalTo(new Character[][]{
                                                                                    {'.', '.', '.'},
                                                                                    {'P', '.', '.'},
                                                                                    {'.', 'W', 'F'}
                                                                            }));
    }

    @Test
    public void findsPlane() {
        assertThat(land.findPlane(), equalTo(new Point(1, 0)));
    }

    @Test
    public void findsWater() {
        assertThat(land.findWater(), equalTo(new Point(2, 1)));
    }

    @Test
    public void findsFire() {
        assertThat(land.findFire(), equalTo(new Point(2, 2)));
    }

}