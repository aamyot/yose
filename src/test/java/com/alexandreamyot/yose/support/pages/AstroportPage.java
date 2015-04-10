package com.alexandreamyot.yose.support.pages;

import java.util.stream.IntStream;

import static com.alexandreamyot.yose.support.Browser.element;
import static com.alexandreamyot.yose.support.Browser.navigateTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
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

    public void displaysGatesWithShipNames() {
        IntStream.rangeClosed(1, 3).forEach(this::displaysGateWithShipName);
    }

    private void displaysGateWithShipName(int id) {
        assertThat(element(id("gate-" + id)), notNullValue());
        assertThat(element(id("ship-" + id)), notNullValue());
    }
}
