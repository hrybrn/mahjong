package com.hrybrn.mahjong.core;

import java.util.UUID;

public class Player {
    private UUID uuid;
    private String name;

    public Player(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Player)) {
            return false;
        }

        return uuid.equals(((Player) other).uuid) && name.equals(((Player) other).name);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode() + name.hashCode();
    }
}
