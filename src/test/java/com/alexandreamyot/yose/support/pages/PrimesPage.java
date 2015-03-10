package com.alexandreamyot.yose.support.pages;

import static com.alexandreamyot.yose.support.Browser.element;
import static com.alexandreamyot.yose.support.Browser.input;
import static com.alexandreamyot.yose.support.Browser.navigateTo;
import static org.openqa.selenium.By.id;

public class PrimesPage {

    public PrimesPage go() {
        navigateTo("http://localhost:7001/primeFactors/ui");
        return this;
    }


    public PrimesPage number(String number) {
        input(id("number"), number);
        return this;
    }

    public PrimesPage submit() {
        element(id("go")).click();
        return this;
    }

    public String number() {
        return element(id("number")).getAttribute("value");
    }

    public String result() {
        return element(id("result")).getText();
    }
}
