package com.hrybrn.mahjong.games;

import com.hrybrn.mahjong.core.Match;
import com.hrybrn.mahjong.core.Player;
import com.hrybrn.mahjong.core.PlayerOrdering;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public abstract class Play {
    protected Match match;
    private Map<String, Player> players;

    public Play(String east, String south, String west, String north) {
        players = new HashMap<>();
        addPlayer(east);
        addPlayer(south);
        addPlayer(west);
        addPlayer(north);

        PlayerOrdering playerOrdering = new PlayerOrdering(players.get(east), players.get(south), players.get(west), players.get(north));
        match = new Match(playerOrdering);
    }

    private void addPlayer(String name) {
        players.put(name, new Player(name));
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    protected Player getPlayer(String name) {
        return players.get(name);
    }

    public void printScores() {
        Map<Player, Integer> sortedScores = sortByValue(match.getPoints());
        sortedScores.forEach((p, i) -> {
            System.out.println(p.getName() + "\t" + i);
        });
    }
    public abstract void run() throws Exception;
}
