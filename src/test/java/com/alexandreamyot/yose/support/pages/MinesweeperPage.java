package com.alexandreamyot.yose.support.pages;

import static com.alexandreamyot.yose.support.Browser.click;
import static com.alexandreamyot.yose.support.Browser.element;
import static com.alexandreamyot.yose.support.Browser.execute;
import static com.alexandreamyot.yose.support.Browser.navigateTo;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class MinesweeperPage {

    public MinesweeperPage go() {
        navigateTo("http://localhost:7001/minesweeper");
        return this;
    }

    public MinesweeperPage load(String[][] grid) {
        execute("document.grid = " + convertToJs(grid) + ";");
        execute("load();");
        return this;
    }

    private String convertToJs(String[][] grid) {
        return "[" +
                range(0, grid.length).mapToObj(row ->
                    "[" + range(0, grid[row].length).mapToObj(col -> "'" + grid[row][col] + "'").collect(joining(",")) + "]").collect(joining(",")) +
                "]";
    }

    public void displaysTitle(String title) {
        assertThat(element(id("title")).getText(), equalTo(title));
    }

    public void displaysGrid(int rows, int cols) {
        range(0, rows).forEach(row ->
            range(0, cols).forEach(col ->
                assertThat(element(id(cellId(row, col))), is(notNullValue()))));
    }

    private String cellId(int row, int col) {
        return String.format("cell-%sx%s", row+1, col+1);
    }

    public MinesweeperPage clickOnCell(int row, int col) {
        click(id(cellId(row, col)));
        return this;
    }

    public void cellHasClassName(int row, int col, String cssClass) {
        assertThat(element(id(cellId(row, col))).getAttribute("class"), equalTo(cssClass));

    }

    public void cellContent(int row, int col, String content) {
        assertThat(element(id(cellId(row, col))).getText(), equalTo(content));
    }

    public void cellIsSafe(int row, int col, String content) {
        cellHasClassName(row, col, "safe");
        cellContent(row, col, content);
    }

    public void cellIsTrapped(int row, int col) {
        cellHasClassName(row, col, "lost");
        cellContent(row, col, "");
    }

    public void cellIsSuspect(int row, int col) {
        cellHasClassName(row, col, "suspect");
        cellContent(row, col, "");
    }

    public void activateSuspectMode() {
        element(cssSelector("input#suspect-mode[type=checkbox]")).click();
    }
}
