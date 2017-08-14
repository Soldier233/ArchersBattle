package me.zhanshi123.archersbattle.utils;

import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.zhanshi123.archersbattle.Arena;
import me.zhanshi123.archersbattle.managers.ArenaManager;
import me.zhanshi123.archersbattle.managers.ItemManager;
import me.zhanshi123.archersbattle.managers.SkillManager;
import me.zhanshi123.archersbattle.skill.Skill;

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
	public static boolean isArenaWorld(World w)
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
			inv.setItem(i, ItemManager.getForbidden(1));
		}
		for(int i=2;i<=4;i++)
		{
			inv.setItem(i, ItemManager.getForbidden(i+1));
		}
		flushSkillSelection(p);
		p.updateInventory();		
		p.setLevel(0);
	}
	public static void flushSkillSelection(Player p)
	{
		PlayerInventory inv=p.getInventory();
		List<Skill> skills=SkillManager.getInstance().getRandomSkills();
		int i=2;
		for(Skill s:skills)
		{
			ItemStack item=s.getSelector();
			item.setAmount(i+1);
			inv.setItem(i,item );
			i++;
		}
	}
}
