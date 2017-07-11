package net.mcmhsj.archersbattle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Arena
{
	FileConfiguration file=new YamlConfiguration();
	File f;
	String worldName;
	List<Location> spawnLocations;
	List<Player> players;
	public Arena(String worldName)
	{
		this.worldName=worldName;
		f=new File(Main.getInstance().getDataFolder()+"/arenas/",worldName+".yml");
		if(!f.exists())
		{
			f.mkdir();
		}
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(f));
			bw.newLine();
			bw.close();
			file.load(f);
			file.set("world", worldName);
			file.save(f);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	public void addSpawnLoactions(Location loc)
	{
		spawnLocations.add(loc);
	}
	public void setSpawnLocations(List<Location> spawnLocations)
	{
		this.spawnLocations=spawnLocations;
	}
	public List<Location> getSpawnLocations()
	{
		return spawnLocations;
	}
	public void addPlayer(Player p)
	{
		if(!checkPlayer(p))
		{
			players.add(p);	
			Utils.fillInventory(p);
		}
	}
	public boolean checkPlayer(Player p)
	{
		return players.contains(p);
	}
	public void removePlayer(Player p)
	{
		players.remove(p);
	}
	public void setWorldName(String name)
	{
		this.worldName=name;
	}
	public String getWorldName()
	{
		return this.worldName;
	}
	public List<Player> getPlayers()
	{
		return players;
	}
	public void saveFile()
	{
		try {
			file.set("world", worldName);
			int i=0;
			for(Location loc:spawnLocations)
			{
				i++;
				file.set("Locations."+i, loc);
			}
			file.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
