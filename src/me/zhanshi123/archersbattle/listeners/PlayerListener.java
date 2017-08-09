package me.zhanshi123.archersbattle.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.zhanshi123.archersbattle.Arena;
import me.zhanshi123.archersbattle.messages.Messages;
import me.zhanshi123.archersbattle.utils.Utils;

public class PlayerListener implements Listener
{
	@EventHandler
	public void onSpawn(PlayerRespawnEvent e)
	{
		Player p=e.getPlayer();
		if(Utils.isInArena(p))
		{
			Arena arena=Utils.getAreana(p);
			Location loc=arena.getRandomSpawnLocation();
			if(loc==null)
			{
				p.sendMessage(Messages.prefix+Messages.SpawnLocationsNotFound);
			}
			else
			{
				e.setRespawnLocation(loc);
			}
		}
	}
	
	@EventHandler
	public void onLevelChange(PlayerLevelChangeEvent e)
	{
		if(!Utils.isInArena(e.getPlayer()))
		{
			return;
		}
		if(e.getNewLevel()==0)
		{
			return;
		}
		if(e.getNewLevel()>30)
		{
			e.getPlayer().setLevel(30);
		}
		else
		{
			Player p=e.getPlayer();
			Utils.flushSkillSelection(p);
		}
	}
}
