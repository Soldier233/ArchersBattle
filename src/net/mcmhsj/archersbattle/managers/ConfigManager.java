package net.mcmhsj.archersbattle.managers;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {
	private Plugin p;
	FileConfiguration config;
	private static ConfigManager cm=null;
	public ConfigManager(Plugin plugin)
	{
		p=plugin;
		File f=new File(p.getDataFolder(),"config.yml");
		if(!f.exists())
		{
			p.saveDefaultConfig();
			p.reloadConfig();
		}
		config=p.getConfig();
		cm=this;
	}
	public static ConfigManager getConfigManager()
	{
		return cm;
	}
	
	public String getMySQLAddress()
	{
		return config.getString("MySQL.address");
	}
	
	public String getMySQLUser()
	{
		return config.getString("MySQL.user");
	}
	
	public String getMySQLPassword()
	{
		return config.getString("MySQL.password");
	}
}
