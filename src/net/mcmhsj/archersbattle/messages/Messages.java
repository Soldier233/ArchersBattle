package net.mcmhsj.archersbattle.messages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.configuration.file.FileConfiguration;

import net.mcmhsj.archersbattle.Main;

public class Messages {
	FileConfiguration config;
	public static String prefix,AlreadyInArena,JoinedArena,AreanNotFound;
	public Messages()
	{
		File f=new File(Main.getInstance().getDataFolder(),"messages.yml");
		boolean first=!f.exists();
		if(first)
		{
			try {
				f.createNewFile();
			} catch (Exception e) {e.printStackTrace();}
		}
		BufferedReader br=null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF-8"));
			config.load(br);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(first)
		{
			config.set("prefix", "§6§lArchersBattle §7>>> §r");
			config.set("commands.AlreadyInArena","§c你已经在一个游戏中了");
			config.set("commands.JoinedArena","§a成功加入竞技场%arena%");
			config.set("commands.AreanNotFound", "§c竞技场不存在");
			try {
				config.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		prefix=config.getString("prefix");
		AlreadyInArena=config.getString("commands.AlreadyInArena");
		JoinedArena=config.getString("commands.JoinedArena");
		AreanNotFound=config.getString("commands.AreanNotFound");
	}
}
