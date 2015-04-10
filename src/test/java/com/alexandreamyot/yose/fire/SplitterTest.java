package com.alexandreamyot.yose.fire;

import com.alexandreamyot.yose.support.Splitter;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SplitterTest {

    @Test
    public void splitsAStringIntoChunksOfFixedLength() {
        assertThat(Splitter.split("1234", 2), equalTo(asList("12", "34")));
        assertThat(Splitter.split("123456789", 3), equalTo(asList("123", "456", "789")));
    }

}