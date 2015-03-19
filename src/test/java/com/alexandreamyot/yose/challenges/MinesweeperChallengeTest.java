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
    public void loadsTheGrid() throws InterruptedException {
        MinesweeperPage minesweeperPage = new MinesweeperPage();
        minesweeperPage.go();
        minesweeperPage.load(new String[][]{
                {"empty", "empty"},
                {"empty", "bomb"}
        });
        minesweeperPage.clickOnCell(1, 1);
        minesweeperPage.cellHasClassName(1, 1, "lost");
    }

}
