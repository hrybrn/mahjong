package com.hrybrn.mahjong.core;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Match {
    private Queue<Game> games;
    private PlayerOrdering initial;

    public Match(PlayerOrdering initial) {
        games = new ArrayDeque<>();

        this.initial = initial;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public void undoGame() throws NoSuchElementException {
        if (this.games.poll() == null) {
            throw new NoSuchElementException("No games left to undo");
        }
    }

    public Map<Player, Integer> getPoints() {
        return calculateScores(new ArrayDeque<>(this.games), (PlayerOrdering) initial.clone());
    }

    private Map<Player, Integer> calculateScores(Queue<Game> games, PlayerOrdering ordering) {
        Map<Player, Integer> overall = ordering.getOrdering().parallelStream().collect(Collectors.toMap(Function.identity(), p -> 0));
        while (!games.isEmpty()) {
            Game current = games.poll();

            try {
                Map<Player, Integer> tally = current.calculateTally(ordering);
                tally.entrySet().forEach((Map.Entry<Player, Integer> entry) -> {
                    overall.computeIfPresent(entry.getKey(), (player, integer) -> integer + entry.getValue());
                });
                if (!current.getWinner().equals(ordering.getEast())) {
                    ordering.rotate();
                }
            } catch(Game.TooFewRecordsException e) {
                e.printStackTrace();
                return Collections.emptyMap();
            }
        }

        return overall;
    }

    public PlayerOrdering getCurrentOrdering() {
        PlayerOrdering toAlter = (PlayerOrdering) initial.clone();
        calculateScores(new ArrayDeque<>(this.games), toAlter);

        return toAlter;
    }
}
