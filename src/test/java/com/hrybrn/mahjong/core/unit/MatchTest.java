package com.hrybrn.mahjong.core.unit;

import com.hrybrn.mahjong.core.Game;
import com.hrybrn.mahjong.core.Match;
import com.hrybrn.mahjong.core.Player;
import com.hrybrn.mahjong.core.PlayerOrdering;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MatchTest {
    Match match;

    @Before
    public void setup() {
        match = new Match(null);
    }

    @Test
    public void addGame() {
        match.addGame(new Game());
    }

    @Test
    public void undoGame() {
        match.addGame(new Game());
        match.undoGame();
        try {
            match.undoGame();
            fail();
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void getPoints() throws Game.TooFewRecordsException {
        Player a = new Player("a");
        Player b = new Player("b");
        Player c = new Player("c");
        Player d = new Player("d");
        PlayerOrdering ordering = new PlayerOrdering(a, b, c, d);

        match = new Match(ordering);

        Map<Player, Integer> points = match.getPoints();

        assertTrue(points.size() == 4);
        assertSame(points.get(a), 0);
        assertSame(points.get(b), 0);
        assertSame(points.get(c), 0);
        assertSame(points.get(d), 0);
    }
}