package com.hrybrn.mahjong.core.unit;

import com.hrybrn.mahjong.core.Game;
import com.hrybrn.mahjong.core.Player;
import com.hrybrn.mahjong.core.PlayerOrdering;
import com.hrybrn.mahjong.core.Record;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {
    private Game game;

    private static Player a;
    private static Player b;
    private static Player c;
    private static Player d;
    private static Player e;

    @BeforeClass
    public static void classSetup() {
        a = new Player("A");
        b = new Player("B");
        c = new Player("C");
        d = new Player("D");
        e = new Player("E");
    }

    @Before
    public void setup() {
        game = new Game();
    }

    @Test
    public void addRecords() throws Exception {
        game.addRecord(new Record(a, 10));
        game.addRecord(new Record(b, 10));
        game.addRecord(new Record(c, 10));
        game.addRecord(new Record(d, 10));
    }

    @Test
    public void tooManyRecords() throws Exception {
        game.addRecord(new Record(a, 10));
        game.addRecord(new Record(b, 10));
        game.addRecord(new Record(c, 10));
        game.addRecord(new Record(d, 10));
        try {
            game.addRecord(new Record(e, 10));
            fail();
        } catch (Game.TooManyRecordsException e) {
        }
    }

    @Test
    public void tooFewRecords() throws Exception {
        game.addRecord(new Record(a, 10));
        game.addRecord(new Record(b, 10));
        game.addRecord(new Record(c, 10));

        try {
            game.calculateTally(null);
            fail();
        } catch (Game.TooFewRecordsException e) {
        }
    }

    @Test
    public void setWinner() throws Exception {
        game.addRecord(new Record(a, 10));
        game.setWinner(a);

        assertEquals(a, game.getWinner());
    }

    @Test
    public void setInvalidWinner() {
        try {
            game.setWinner(a);
            fail();
        } catch (Exception e) {
        }
    }
}