package me.zhanshi123.archersbattle.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.zhanshi123.archersbattle.managers.ArenaManager;
import me.zhanshi123.archersbattle.managers.ItemManager;
import me.zhanshi123.archersbattle.managers.SkillManager;
import me.zhanshi123.archersbattle.skill.skills.Sword;
import me.zhanshi123.archersbattle.utils.Utils;

public class InventoryListener implements Listener
{
	@EventHandler
	public void onClick(InventoryClickEvent e)
	{
		if(e.isCancelled())
			return;
		if(!(e.getWhoClicked() instanceof Player))
		{
			return;
		}
		if(!Utils.isInArena((Player)e.getWhoClicked()))
		{
			return;
		}
		if(e.getClickedInventory()==null)
		{
			return;
		}
		e.setCancelled(true);
	}
	@EventHandler
	public void onHeld(PlayerItemHeldEvent e)
	{
		if(e.isCancelled())
		{
			return;
		}
		if(!Utils.isInArena(e.getPlayer()))
		{
			return;
		}
		if(e.getNewSlot()==0)
		{
			return;
		}
		if(e.getNewSlot()>=2&&e.getNewSlot()<=4)
		{
			int slot=e.getNewSlot();
			Player p=e.getPlayer();
			PlayerInventory inv=p.getInventory();
			ItemStack item=inv.getItem(slot).clone();
			item.setAmount(1);
			if(SkillManager.getInstance().isSkillSelector(item))
			{
				if(SkillManager.getInstance().getSkillBySelector(item) instanceof Sword)
				{
					inv.setItem(0, SkillManager.getInstance().getSkillByName("³¤½£").getShow());
				}
				inv.setItem(2,ItemManager.getForbidden(3));
				inv.setItem(3,ItemManager.getForbidden(4));
				inv.setItem(4,ItemManager.getForbidden(5));
			}
		}
		e.setCancelled(true);
	}
	@EventHandler
	public void onDrop(PlayerDropItemEvent e)
	{
		if(e.isCancelled())
		{
			return;
		}
		if(!Utils.isInArena(e.getPlayer()))
		{
			return;
		}
		e.setCancelled(true);
	}
}
