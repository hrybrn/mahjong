package com.hrybrn.mahjong.core.unit;

import com.hrybrn.mahjong.core.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void getName() {
        Player player = new Player("Harry");
        assertEquals("Harry", player.getName());
    }

    @Test
    public void equals() {
        Player playerA = new Player("Harry");
        Player playerB = new Player("Em");

        assertEquals(playerA, playerA);
        assertNotEquals(playerA, playerB);
    }
}