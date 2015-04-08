package com.alexandreamyot.yose.challenges;

import com.alexandreamyot.yose.Yose;
import com.alexandreamyot.yose.support.pages.MinesweeperPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MinesweeperChallengeTest {

    Yose server;
    private MinesweeperPage minesweeperPage;

    @Before
    public void startServerAndNavigateToPage() throws IOException {
        server = new Yose(7001);
        server.start();

        minesweeperPage = new MinesweeperPage();
        minesweeperPage.go();
    }

    @After
    public void stopServer() throws IOException {
        server.stop();
    }

    @Test
    public void displaysTheGrid() throws IOException {
        minesweeperPage.displaysTitle("Minesweeper");
        minesweeperPage.displaysGrid(8, 8);
    }

    @Test
    public void handlesBomb() throws InterruptedException {
        minesweeperPage.load(new String[][]{
                {"empty", "empty"},
                {"empty", "bomb"}
        });
        minesweeperPage.clickOnCell(1, 1);

        minesweeperPage.cellIsTrapped(1, 1);
    }

    @Test
    public void handlesSafeCell() throws InterruptedException {
        minesweeperPage.load(new String[][]{
                {"empty", "empty", "bomb"},
                {"empty", "bomb", "empty"},
                {"empty", "empty", "empty"}
        });

        minesweeperPage.clickOnCell(1, 2);

        minesweeperPage.cellIsSafe(1, 2, "2");
    }

    @Test
    public void handlesSafeCellWithZeroBombAround() throws InterruptedException {
        minesweeperPage.load(new String[][]{
                {"empty", "empty", "bomb" , "empty"},
                {"empty", "bomb" , "empty", "empty"},
                {"empty", "empty", "empty", "empty"},
                {"empty", "empty", "empty", "empty"}
        });

        minesweeperPage.clickOnCell(3, 0);

        minesweeperPage.cellIsSafe(3, 0, "");
    }

    @Test
    public void handlesOpenFieldCell() {
        minesweeperPage.load(new String[][]{
                {"bomb" , "empty", "empty"},
                {"empty", "empty", "empty"},
                {"empty", "empty", "bomb"}
        });

        minesweeperPage.clickOnCell(2, 0);

        minesweeperPage.cellIsSafe(2, 0, "");
        minesweeperPage.cellIsSafe(1, 0, "1");
        minesweeperPage.cellIsSafe(1, 1, "2");
        minesweeperPage.cellIsSafe(2, 1, "1");
    }

    @Test
    public void safeModeChallenge() {
        minesweeperPage.load(new String[][]{
                {"bomb" , "empty", "empty"},
                {"empty", "empty", "empty"},
                {"empty", "empty", "bomb"}
        });
        minesweeperPage.activateSuspectMode();

        minesweeperPage.clickOnCell(0, 0);

        minesweeperPage.cellIsSuspect(0, 0);
    }

}
