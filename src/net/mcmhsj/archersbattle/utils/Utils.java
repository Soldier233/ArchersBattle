package net.mcmhsj.archersbattle.utils;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.mcmhsj.archersbattle.Arena;
import net.mcmhsj.archersbattle.managers.ArenaManager;
import net.mcmhsj.archersbattle.managers.WeaponItemManager;

public class Utils
{
	public static Arena getAreana(Player p)
	{
		Arena arena=null;
		for(Arena a:ArenaManager.getArenas())
		{
			for(Player player:a.getPlayers())
			{
				if(player.getName().equalsIgnoreCase(p.getName()))
				{
					arena=a;
				}
			}
		}
		return arena;
	}
	public static boolean isInArena(World w)
	{
		boolean in=false;
		for(Arena a:ArenaManager.getArenas())
		{
			if(a.getWorldName().equalsIgnoreCase(w.getName()))
			{
				in=true;
				break;
			}
		}
		return in;
	}
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
	public static void fillInventory(Player p)
	{
		PlayerInventory inv=p.getInventory();
		for(int i=0;i<=35;i++)
		{
			inv.setItem(i, WeaponItemManager.getForbidden(1));
		}
		p.updateInventory();
		for(int i=2;i<=4;i++)
		{
			inv.setItem(i, WeaponItemManager.getForbidden(i+1));
		}
		inv.setItem(9, WeaponItemManager.getArrow());
		inv.setItem(0, WeaponItemManager.getBow());
		p.updateInventory();
	}
}
