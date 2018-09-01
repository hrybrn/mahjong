package com.hrybrn.mahjong.core;

public class Record {
    private int score;
    private Player player;

    public Record(Player player, int score) {
        this.player = player;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }
}
