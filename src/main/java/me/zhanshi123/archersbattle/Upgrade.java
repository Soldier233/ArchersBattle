package me.zhanshi123.archersbattle;

import org.bukkit.entity.Player;

public class Upgrade {
    private Player player;
    private int level=1;

    public Upgrade( Player player, int level) {
        this.player = player;
        this.level = level;
    }


    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public Player getPlayer() {
        return player;
    }

    public int getLevel() {
        return level;
    }
}
