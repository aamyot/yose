package com.alexandreamyot.yose.support.pages;

import static com.alexandreamyot.yose.support.Browser.element;
import static com.alexandreamyot.yose.support.Browser.navigateTo;
import static java.util.stream.IntStream.rangeClosed;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

public class MinesweeperPage {

    public MinesweeperPage go() {
        navigateTo("http://localhost:7001/minesweeper");
        return this;
    }

    public void displaysTitle(String title) {
        assertThat(element(id("title")).getText(), equalTo(title));
    }

    public void displaysGrid(int rows, int cols) {
        rangeClosed(1, rows).forEach(row -> rangeClosed(1, cols)
                .forEach(col -> assertThat(element(id(cellId(row, col))), is(notNullValue()))));
    }

    private String cellId(int row, int col) {
        return String.format("cell-%sx%s", row, col);
    }
}
