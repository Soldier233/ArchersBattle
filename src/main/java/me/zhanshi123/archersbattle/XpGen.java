package me.zhanshi123.archersbattle;

import org.bukkit.Location;

public class XpGen {
    int interval = 10;
    Location loc = null;

    public XpGen(Location loc, int interval) {
        this.interval = interval;
        this.loc = loc;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setLocation(Location loc) {
        this.loc = loc;
    }

    public Location getLocation() {
        return loc;
    }

    public int getInterval() {
        return interval;
    }
}
