package com.hrybrn.mahjong.core;

import java.util.ArrayList;
import java.util.List;

public class PlayerOrdering {
    private Player east;
    private Player south;
    private Player west;
    private Player north;

    public PlayerOrdering(Player east, Player south, Player west, Player north) {
        this.east = east;
        this.south = south;
        this.west = west;
        this.north = north;
    }

    public Player getEast() {
        return east;
    }

    public List<Player> getOrdering() {
        List<Player> players = new ArrayList<>();

        players.add(east);
        players.add(south);
        players.add(west);
        players.add(north);

        return players;
    }

    public void rotate() {
        Player placeholder = east;
        east = south;
        south = west;
        west = north;
        north = placeholder;
    }

    public void reverseRotate() {
        rotate();
        rotate();
        rotate();
    }

    @Override
    public Object clone() {
        return new PlayerOrdering(east, south, west, north);
    }
}
