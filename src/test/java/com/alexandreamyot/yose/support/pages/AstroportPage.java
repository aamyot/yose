package com.alexandreamyot.yose.support.pages;

import static com.alexandreamyot.yose.support.Browser.element;
import static com.alexandreamyot.yose.support.Browser.navigateTo;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

public class AstroportPage {

    public AstroportPage go() {
        navigateTo("http://localhost:7001/astroport");
        return this;
    }

    public void displaysTheAstroportName(String name) {
        assertThat(element(id("astroport-name")).getText(), equalTo(name));
    }
}
