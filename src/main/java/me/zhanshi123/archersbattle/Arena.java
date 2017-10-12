package me.zhanshi123.archersbattle;

import me.zhanshi123.archersbattle.managers.ArenaManager;
import me.zhanshi123.archersbattle.messages.Messages;
import me.zhanshi123.archersbattle.utils.Utils;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    FileConfiguration file = new YamlConfiguration();
    File f;
    String worldName;
    List<Location> spawnLocations = new ArrayList<Location>();
    List<Player> players = new ArrayList<Player>();
    List<XpGen> xpGenerators = new ArrayList<XpGen>();

    public Arena(String worldName) {
        this.worldName = worldName;
        f = new File(Main.getInstance().getDataFolder() + "/arenas/", worldName + ".yml");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            file.load(f);
            file.set("world", worldName);
            file.save(f);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void addSpawnLoactions(Location loc) {
        spawnLocations.add(loc);
    }

    public void setSpawnLocations(List<Location> spawnLocations) {
        this.spawnLocations = spawnLocations;
    }

    public List<Location> getSpawnLocations() {
        return spawnLocations;
    }

    public void addXpGenerators(XpGen g) {
        xpGenerators.add(g);
    }

    public void setXpGenerators(List<XpGen> list) {
        this.xpGenerators = list;
    }

    public List<XpGen> getXpGenerators() {
        return xpGenerators;
    }

    public void addPlayer(Player p) {
        if (!checkPlayer(p)) {
            players.add(p);
            Utils.fillInventory(p);
            Location loc = getRandomSpawnLocation();
            if (loc != null) {
                p.teleport(loc);
            } else {
                p.sendMessage(Messages.prefix + Messages.SpawnLocationsNotFound);
            }
        }
    }

    public boolean checkPlayer(Player p) {
        return players.contains(p);
    }

    public void removePlayer(Player p) {
        players.remove(p);
        p.getInventory().clear();
    }

    public void setWorldName(String name) {
        this.worldName = name;
    }

    public String getWorldName() {
        return this.worldName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void saveFile() {
        try {
            file.set("world", worldName);
            {
                int i = 0;
                for (Location loc : spawnLocations) {
                    i++;
                    file.set("spawnLocations." + i, loc);
                }
            }
//----------------------------------------------------------
            {
                int i = 0;
                for (XpGen g : xpGenerators) {
                    i++;
                    file.set("xpGenerators." + i + ".interval", g.getInterval());
                    file.set("xpGenerators." + i + ".location", g.getLocation());
                }
            }
            file.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getRandomSpawnLocation() {
        Random random = new Random();
        if (spawnLocations.size() == 0) {
            return null;
        } else {
            int index = random.nextInt(spawnLocations.size());
            return spawnLocations.get(index);
        }
    }


    //static methods below
    public static Arena valueOf(String name) {
        Arena ar = null;
        for (Arena a : ArenaManager.getArenas()) {
            if (a.getWorldName().equalsIgnoreCase(name)) {
                ar = a;
                break;
            }
        }
        return ar;
    }

    public static boolean containsArena(String name) {
        boolean contains = false;
        for (Arena a : ArenaManager.getArenas()) {
            if (a.getWorldName().equalsIgnoreCase(name)) {
                contains = true;
                break;
            }
        }
        return contains;
    }
}
