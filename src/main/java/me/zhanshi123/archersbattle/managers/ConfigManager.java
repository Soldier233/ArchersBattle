package me.zhanshi123.archersbattle.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigManager {
    private static ConfigManager cm = null;
    FileConfiguration config;
    private Plugin p;

    public ConfigManager(Plugin plugin) {
        p = plugin;
        File f = new File(p.getDataFolder(), "config.yml");
        if (!f.exists()) {
            p.saveDefaultConfig();
            p.reloadConfig();
        }
        config = p.getConfig();
        cm = this;
    }

    public static ConfigManager getConfigManager() {
        return cm;
    }

    public String getMySQLAddress() {
        return config.getString("MySQL.address");
    }

    public String getMySQLUser() {
        return config.getString("MySQL.user");
    }

    public String getMySQLPassword() {
        return config.getString("MySQL.password");
    }

    public int getMaxLevel() {
        return config.getInt("Game.MaxLevel");
    }

}
