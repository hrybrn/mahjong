package com.hrybrn.mahjong.games;

public class Main {
    public static void main(String[] args) throws Exception {
        Play play = new Aug31();

        play.run();
        play.printScores();
    }
}
