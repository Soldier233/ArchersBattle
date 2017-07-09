package net.mcmhsj.archersbattle.managers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WeaponItemManager {
	static ItemStack forbidden=new ItemStack(Material.STAINED_GLASS_PANE,1,(short)14);
	static ItemStack arrow= new ItemStack(Material.ARROW);
	public static void init()
	{
		ItemMeta im=forbidden.getItemMeta();
		im.setDisplayName(" ");
		forbidden.setItemMeta(im);
		im=arrow.getItemMeta();
		im.setDisplayName("¼ý¡á");
		arrow.setItemMeta(im);
	}
	public static ItemStack getArrow()
	{
		return arrow;
	}
	public static ItemStack getForbidden(int i)
	{
		forbidden.setAmount(i);
		return forbidden;
	}
}
