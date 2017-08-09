package me.zhanshi123.archersbattle;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.zhanshi123.archersbattle.commands.AdminCommands;
import me.zhanshi123.archersbattle.commands.Commands;
import me.zhanshi123.archersbattle.listeners.InventoryListener;
import me.zhanshi123.archersbattle.listeners.PlayerListener;
import me.zhanshi123.archersbattle.listeners.WorldListener;
import me.zhanshi123.archersbattle.managers.ArenaManager;
import me.zhanshi123.archersbattle.managers.ConfigManager;
import me.zhanshi123.archersbattle.managers.Database;
import me.zhanshi123.archersbattle.managers.ItemManager;
import me.zhanshi123.archersbattle.managers.SkillManager;
import me.zhanshi123.archersbattle.messages.Messages;
import me.zhanshi123.archersbattle.skill.skills.Sword;


public class Main extends JavaPlugin
{
	private static Main instance;
	public static Main getInstance()
	{
		return instance;
	}
	public void loadArenas()
	{
		File f=new File(getDataFolder()+"/arenas/");
		for(File a:f.listFiles())
		{
			if(a.getName().endsWith(".yml"))
			{
				FileConfiguration config=new YamlConfiguration();
				try {
					config.load(a);
					Arena arena=new Arena(config.getString("world"));
					List<Location> locations=new ArrayList<Location>();
					List<XpGen> xpgens=new ArrayList<XpGen>();
					if(config.isConfigurationSection("spawnLocations"))
					{
						ConfigurationSection locs=config.getConfigurationSection("spawnLocations");
						Set<String> keys=locs.getKeys(false);
						for(String x:keys)
						{
							locations.add((Location)config.get("spawnLocations."+x));
						}
						arena.setSpawnLocations(locations);
					}
					if(config.isConfigurationSection("xpGenerators"))
					{
						ConfigurationSection gens=config.getConfigurationSection("xpGenerators");
						Set<String> keys=gens.getKeys(false);
						for(String x:keys)
						{
							String path="xpGenerators."+x;
							int interval=config.getInt(path+".interval");
							Location loc=(Location)config.get(path+".location");
							XpGen gen=new XpGen(loc,interval);
							xpgens.add(gen);
						}
						arena.setXpGenerators(xpgens);
					}
					ArenaManager.addArena(arena);
				} catch (IOException | InvalidConfigurationException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void onEnable()
	{
		Bukkit.getConsoleSender().sendMessage("§6§lArchersBattle §7>>> §a弓箭手大作战正在加载中..");
		instance=this;
		Bukkit.getPluginCommand("ab").setExecutor(new Commands());
		Bukkit.getPluginCommand("abadmin").setExecutor(new AdminCommands());
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
		Bukkit.getPluginManager().registerEvents(new WorldListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		new SkillManager();
		loadSkills();
		loadArenas();
		ItemManager.init();
		new Messages();
		new ConfigManager(this);
		Database db=null;
		try {
			db=new Database(DriverManager.getConnection(ConfigManager.getConfigManager().getMySQLAddress(),
					ConfigManager.getConfigManager().getMySQLUser(), 
					ConfigManager.getConfigManager().getMySQLPassword()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!db.init())
		{
			Bukkit.getConsoleSender().sendMessage("§6§lArchersBattle §7>>> §c数据库初始化失败，停止加载");
			setEnabled(false);
		}
		Bukkit.getConsoleSender().sendMessage("§6§lArchersBattle §7>>> §a加载完成!加载了"+SkillManager.getInstance().getSkills().size()+"个技能");
	}
	public void onDisable()
	{
		List<Arena> arenas=ArenaManager.getArenas();
		for(Arena arena:arenas)
		{
			arena.saveFile();
			for(Player p:arena.getPlayers())
			{
				arena.removePlayer(p);
				p.sendMessage(Messages.prefix+Messages.LeavedArena);
			}
		}
		Bukkit.getConsoleSender().sendMessage("§6§lArchersBattle §7>>> §a竞技场保存完成!");
	}
	
	public void loadSkills()
	{
		new Sword("长剑").register();
	}
}
