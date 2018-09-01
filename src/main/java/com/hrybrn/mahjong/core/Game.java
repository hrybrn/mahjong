package com.hrybrn.mahjong.core;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class Game {
    private Date date;
    private HashMap<Player, Record> records;
    private Player winner;

    public Game() {
        date = new Date();
        records = new HashMap<>();
    }

    public Date getDate() {
        return date;
    }

    public void addRecord(Record record) throws TooManyRecordsException {
        if (records.size() == 4) {
            throw new TooManyRecordsException();
        }

        records.put(record.getPlayer(), record);
    }

    public class TooManyRecordsException extends Exception {}
    public class TooFewRecordsException extends Exception {}

    public void setWinner(Player winner) throws NotAValidWinnerException {
        if (this.records.containsKey(winner)) {
            this.winner = winner;
        } else {
            throw new NotAValidWinnerException(winner.getName());
        }
    }

    public Player getWinner() {
        return winner;
    }

    public class NotAValidWinnerException extends Exception {
        private NotAValidWinnerException(String playerName) {
            super (playerName + " is not a valid winner");
        }
    }

    private Map<Player, Integer> getScores() throws TooFewRecordsException {
        if (records.size() != 4) {
            throw new TooFewRecordsException();
        }

        return records.entrySet().parallelStream().collect(Collectors.toMap(Entry::getKey, (Entry<Player, Record> e) -> e.getValue().getScore()));
    }

    public Map<Player, Integer> calculateTally(PlayerOrdering ordering) throws TooFewRecordsException{
        Map<Player, Integer> scores = getScores();
        Player east = ordering.getEast();

        ConcurrentMap<Player, Integer> tally = new ConcurrentHashMap<>();
        scores.keySet().forEach((Player p) -> tally.put(p, 0));

        scores.entrySet().parallelStream().forEach((Map.Entry<Player, Integer> entry) -> {
            Player currentPlayer = entry.getKey();
            Integer score = entry.getValue();

            if (currentPlayer.equals(east)) {
                score *= 2;
            }

            final Integer scoreFinal = score;

            scores.keySet().parallelStream().filter((Player p) -> !p.equals(entry.getKey()) && !p.equals(winner )).forEach((Player p) -> {
                final int payment = p.equals(east) ? scoreFinal * 2 : scoreFinal;

                tally.computeIfPresent(p, (player, integer) -> integer - payment);
                tally.computeIfPresent(currentPlayer, (player, integer) -> integer + payment);
            });
        });

        return tally;
    }
}
