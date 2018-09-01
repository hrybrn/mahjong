package com.hrybrn.mahjong.core.unit;

import com.hrybrn.mahjong.core.Player;
import com.hrybrn.mahjong.core.PlayerOrdering;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerOrderingTest {
    private final String[] expectedOriginalNames = {"east", "south", "west", "north"};
    private final String[] expectedRotatedNames = {"south", "west", "north", "east"};


    PlayerOrdering playerOrdering;

    @Before
    public void setup() {
        Player east =  new Player("east");
        Player south =  new Player("south");
        Player west =  new Player("west");
        Player north =  new Player("north");
        playerOrdering = new PlayerOrdering(east, south, west, north);
    }

    @Test
    public void getEast() {
        assertEquals("east", playerOrdering.getEast().getName());
    }

    @Test
    public void getOrdering() {
        assertArrayEquals(expectedOriginalNames, playerOrdering.getOrdering().stream().map(Player::getName).toArray());
    }

    @Test
    public void rotate() {
        playerOrdering.rotate();
        assertArrayEquals(expectedRotatedNames, playerOrdering.getOrdering().stream().map(Player::getName).toArray());
    }
}