package com.hrybrn.mahjong.core.integration;

import com.hrybrn.mahjong.core.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MatchIntegrationTest {
    Match match;
    PlayerOrdering playerOrdering;

    private static Player a;
    private static Player b;
    private static Player c;
    private static Player d;

    @BeforeClass
    public static void classSetup() {
        a = new Player("A");
        b = new Player("B");
        c = new Player("C");
        d = new Player("D");
    }

    @Before
    public void setup() {
        playerOrdering = new PlayerOrdering(a, b, c, d);
        match = new Match(playerOrdering);
    }

    @Test
    public void checkBasicRound() throws Exception {
        Game game = new Game();
        game.addRecord(new Record(a, 24));
        game.addRecord(new Record(b, 0));
        game.addRecord(new Record(c, 0));
        game.addRecord(new Record(d, 0));
        game.setWinner(a);

        match.addGame(game);

        Map<Player, Integer> points = match.getPoints();

        assertEquals(144, (Object) points.get(a));
        assertEquals(-48, (Object) points.get(b));
        assertEquals(-48, (Object) points.get(c));
        assertEquals(-48, (Object) points.get(d));
    }

    @Test
    public void checkEastWinningRound() throws Exception {
        Game game = new Game();
        game.addRecord(new Record(a, 24));
        game.addRecord(new Record(b, 0));
        game.addRecord(new Record(c, 0));
        game.addRecord(new Record(d, 0));
        game.setWinner(a);

        match.addGame(game);
        match.addGame(game);

        Map<Player, Integer> points = match.getPoints();

        assertEquals(288, (Object) points.get(a));
        assertEquals(-96, (Object) points.get(b));
        assertEquals(-96, (Object) points.get(c));
        assertEquals(-96, (Object) points.get(d));
    }

    @Test
    public void checkNotEastWinningRound() throws Exception {
        Game game = new Game();
        game.addRecord(new Record(a, 0));
        game.addRecord(new Record(b, 24));
        game.addRecord(new Record(c, 0));
        game.addRecord(new Record(d, 0));
        game.setWinner(b);

        match.addGame(game);

        Map<Player, Integer> points = match.getPoints();

        assertEquals(-48, (Object) points.get(a));
        assertEquals(96, (Object) points.get(b));
        assertEquals(-24, (Object) points.get(c));
        assertEquals(-24, (Object) points.get(d));
    }

    @Test
    public void checkNotEastWinningRoundButWinningSecond() throws Exception {
        Game game = new Game();
        game.addRecord(new Record(a, 0));
        game.addRecord(new Record(b, 24));
        game.addRecord(new Record(c, 0));
        game.addRecord(new Record(d, 0));
        game.setWinner(b);

        match.addGame(game);
        match.addGame(game);

        Map<Player, Integer> points = match.getPoints();

        assertEquals(-96, (Object) points.get(a));
        assertEquals(240, (Object) points.get(b));
        assertEquals(-72, (Object) points.get(c));
        assertEquals(-72, (Object) points.get(d));
    }
}
