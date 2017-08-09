package me.zhanshi123.archersbattle.managers;

import java.util.HashMap;

import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import me.zhanshi123.archersbattle.Arena;
import me.zhanshi123.archersbattle.Main;
import me.zhanshi123.archersbattle.XpGen;

public class XpManager {
	HashMap<XpGen,Integer> data=new HashMap<XpGen,Integer>();
	public XpManager()
	{
		new BukkitRunnable()
		{
			public void run()
			{
				for(Arena arena:ArenaManager.getArenas())
				{
					if(arena.getPlayers().size()!=0)
					{
						for(XpGen gen:arena.getXpGenerators())
						{
							if(!data.containsKey(gen))
							{
								data.put(gen, 1);
							}
							else
							{
								int value=data.get(gen);
								data.remove(gen);
								data.put(gen, value+1);
							}
							if(data.get(gen)==gen.getInterval())
							{
								gen.getLocation().getWorld().spawnEntity(gen.getLocation(), EntityType.EXPERIENCE_ORB);
								data.remove(gen);
							}
						}
					}
				}
			}
		}.runTaskTimer(Main.getInstance(), 20L, 20L);
	}
}
