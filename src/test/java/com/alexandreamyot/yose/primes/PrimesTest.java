package com.alexandreamyot.yose.primes;


import org.junit.Test;

import static com.alexandreamyot.yose.support.BuildDocument.document;
import static org.junit.Assert.assertThat;
import static org.testinfected.hamcrest.dom.DomMatchers.hasUniqueSelector;

public class PrimesTest {

    @Test
    public void viewShouldIncludeAHtmlForm() {
        assertThat(document("primes").getDocumentElement(), hasUniqueSelector("form", hasUniqueSelector("#title"),
                                                                                      hasUniqueSelector("#invitation"),
                                                                                      hasUniqueSelector("input#number[type='text']"),
                                                                                      hasUniqueSelector("button#go"),
                                                                                      hasUniqueSelector("#result"),
                                                                                      hasUniqueSelector("#results")));
    }
}
