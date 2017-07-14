package net.mcmhsj.archersbattle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.mcmhsj.archersbattle.managers.ArenaManager;
import net.mcmhsj.archersbattle.messages.Messages;
import net.mcmhsj.archersbattle.utils.Utils;

public class Arena
{
	FileConfiguration file=new YamlConfiguration();
	File f;
	String worldName;
	List<Location> spawnLocations=new ArrayList<Location>();
	List<Player> players=new ArrayList<Player>();
	public Arena(String worldName)
	{
		this.worldName=worldName;
		f=new File(Main.getInstance().getDataFolder()+"/arenas/",worldName+".yml");
		if(!f.exists())
		{
			try {
				f.mkdir();
				BufferedWriter bw=new BufferedWriter(new FileWriter(f));
				bw.newLine();
				bw.close();
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
			Location loc=getRandomSpawnLocation();
			if(loc!=null)
			{
				p.teleport(loc);
			}
			else
			{
				p.sendMessage(Messages.prefix+Messages.SpawnLocationsNotFound);
			}
		}
	}
	public boolean checkPlayer(Player p)
	{
		return players.contains(p);
	}
	public void removePlayer(Player p)
	{
		players.remove(p);
		p.getInventory().clear();
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
				file.set("loc"+i, loc);
			}
			file.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Arena valueOf(String name)
	{
		Arena ar=null;
		for(Arena a:ArenaManager.getArenas())
		{
			if(a.getWorldName().equalsIgnoreCase(name))
			{
				ar=a;
				break;
			}
		}
		return ar;
	}
	public static boolean containsArena(String name)
	{
		boolean contains=false;
		for(Arena a:ArenaManager.getArenas())
		{
			if(a.getWorldName().equalsIgnoreCase(name))
			{
				contains=true;
				break;
			}
		}
		return contains;
	}
	public Location getRandomSpawnLocation()
	{
		Random random=new Random();
		if(spawnLocations.size()==0)
		{
			return null;
		}
		else
		{
			int index=random.nextInt(spawnLocations.size()+1);
			return spawnLocations.get(index);
		}
	}
}
