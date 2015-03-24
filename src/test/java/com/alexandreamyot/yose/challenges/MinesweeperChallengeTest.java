package com.alexandreamyot.yose.challenges;

import com.alexandreamyot.yose.Yose;
import com.alexandreamyot.yose.support.pages.MinesweeperPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MinesweeperChallengeTest {

    Yose server ;

    @Before
    public void startServer() throws IOException {
        server = new Yose(7001);
        server.start();
    }

    @After
    public void stopServer() throws IOException {
        server.stop();
    }

    @Test
    public void displaysTheGrid() throws IOException {
        MinesweeperPage minesweeperPage = new MinesweeperPage();
        minesweeperPage.go();
        minesweeperPage.displaysTitle("Minesweeper");
        minesweeperPage.displaysGrid(8, 8);
    }

    @Test
    public void handlesBomb() throws InterruptedException {
        MinesweeperPage minesweeperPage = new MinesweeperPage();
        minesweeperPage.go();
        minesweeperPage.load(new String[][]{
                {"empty", "empty"},
                {"empty", "bomb"}
        });
        minesweeperPage.clickOnCell(1, 1);
        minesweeperPage.cellHasClassName(1, 1, "lost");
    }

    @Test
    public void handlesSafeCell() throws InterruptedException {
        MinesweeperPage minesweeperPage = new MinesweeperPage();
        minesweeperPage.go();
        minesweeperPage.load(new String[][]{
                {"empty", "empty", "bomb"},
                {"empty", "bomb",  "empty"},
                {"empty", "empty", "empty"}
        });
        minesweeperPage.clickOnCell(1, 2);
        minesweeperPage.cellHasClassName(1, 2, "safe");
        minesweeperPage.cellContent(1, 2, "2");
    }

    @Test
    public void handlesSafeCellWithZeroBombAround() throws InterruptedException {
        MinesweeperPage minesweeperPage = new MinesweeperPage();
        minesweeperPage.go();
        minesweeperPage.load(new String[][]{
                {"empty", "empty", "bomb" , "empty"},
                {"empty", "bomb" , "empty", "empty"},
                {"empty", "empty", "empty", "empty"},
                {"empty", "empty", "empty", "empty"}
        });
        minesweeperPage.clickOnCell(3, 0);
        minesweeperPage.cellHasClassName(3, 0, "safe");
        minesweeperPage.cellContent(3, 0, "");
    }

}
