package net.mcmhsj.archersbattle.commands.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import net.mcmhsj.archersbattle.utils.Utils;

public class WorldListener implements Listener
{
	@EventHandler
	public void onBreak(BlockBreakEvent e)
	{
		if(e.isCancelled())
		{
			return;
		}
		if(!Utils.isInArena(e.getPlayer()))
		{
			return;
		}
		if(!Utils.isInArena(e.getBlock().getWorld()))
		{
			return;
		}
		if(e.getPlayer().isOp())
		{
			return;
		}
		e.setCancelled(true);
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent e)
	{
		if(e.isCancelled())
		{
			return;
		}
		if(!Utils.isInArena(e.getPlayer()))
		{
			return;
		}
		if(!Utils.isInArena(e.getBlock().getWorld()))
		{
			return;
		}
		if(e.getPlayer().isOp())
		{
			return;
		}
		e.setCancelled(true);
	}
}
