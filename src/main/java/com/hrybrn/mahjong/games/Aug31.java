package com.hrybrn.mahjong.games;

import com.hrybrn.mahjong.core.*;

public class Aug31 extends Play {
    public Aug31() {
        super(DAD, HARRY, MUM, EM);
    }

    private static final String DAD = "Dad";
    private static final String HARRY = "Harry";
    private static final String MUM = "Mum";
    private static final String EM = "Em";

    public void run() throws Exception {
        Game game1 = new Game();
        game1.addRecord(new Record(getPlayer(DAD), 2));
        game1.addRecord(new Record(getPlayer(EM), 10));
        game1.addRecord(new Record(getPlayer(HARRY), 60));
        game1.addRecord(new Record(getPlayer(MUM), 14));
        game1.setWinner(getPlayer(HARRY));

        match.addGame(game1);

        Game game2 = new Game();
        game2.addRecord(new Record(getPlayer(DAD), 48));
        game2.addRecord(new Record(getPlayer(EM), 8));
        game2.addRecord(new Record(getPlayer(HARRY), 56));
        game2.addRecord(new Record(getPlayer(MUM), 48));
        game2.setWinner(getPlayer(HARRY));

        match.addGame(game2);

        Game game3 = new Game();
        game3.addRecord(new Record(getPlayer(DAD), 32));
        game3.addRecord(new Record(getPlayer(EM), 88));
        game3.addRecord(new Record(getPlayer(HARRY), 96));
        game3.addRecord(new Record(getPlayer(MUM), 10));
        game3.setWinner(getPlayer(DAD));

        match.addGame(game3);

        Game game4 = new Game();
        game4.addRecord(new Record(getPlayer(DAD), 12));
        game4.addRecord(new Record(getPlayer(EM), 128));
        game4.addRecord(new Record(getPlayer(HARRY), 416));
        game4.addRecord(new Record(getPlayer(MUM), 8));
        game4.setWinner(getPlayer(HARRY));

        match.addGame(game4);

        Game game5 = new Game();
        game5.addRecord(new Record(getPlayer(DAD), 10));
        game5.addRecord(new Record(getPlayer(EM), 12));
        game5.addRecord(new Record(getPlayer(HARRY), 56));
        game5.addRecord(new Record(getPlayer(MUM), 68));
        game5.setWinner(getPlayer(MUM));

        match.addGame(game5);
    }
}
