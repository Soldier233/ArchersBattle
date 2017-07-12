package net.mcmhsj.archersbattle.commands.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.mcmhsj.archersbattle.managers.ArenaManager;
import net.mcmhsj.archersbattle.managers.KitItemManager;
import net.mcmhsj.archersbattle.managers.WeaponItemManager;
import net.mcmhsj.archersbattle.utils.Utils;

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
			if(KitItemManager.isKitSelector(item))
			{
				//TODO add Kit
				inv.setItem(2,WeaponItemManager.getForbidden(3));
				inv.setItem(3,WeaponItemManager.getForbidden(4));
				inv.setItem(4,WeaponItemManager.getForbidden(5));
			}
		}
		e.setCancelled(true);
	}
}
