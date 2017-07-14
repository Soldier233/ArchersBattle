package net.mcmhsj.archersbattle.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import net.mcmhsj.archersbattle.Arena;
import net.mcmhsj.archersbattle.messages.Messages;
import net.mcmhsj.archersbattle.utils.Utils;

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
}
