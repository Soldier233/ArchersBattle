package net.mcmhsj.archersbattle;

import org.bukkit.entity.Player;

import net.mcmhsj.archersbattle.managers.ArenaManager;

public class Utils
{
	public static boolean isInArena(Player p)
	{
		boolean in=false;
		for(Arena a:ArenaManager.getArenas())
		{
			if(a.checkPlayer(p))
			{
				in=true;
				break;
			}
		}
		return in;
	}
}
