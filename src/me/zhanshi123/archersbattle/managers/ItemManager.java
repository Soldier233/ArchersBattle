package me.zhanshi123.archersbattle.managers;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
	static ItemStack forbidden=new ItemStack(Material.STAINED_GLASS_PANE,1,(short)14);
	static ItemStack arrow= new ItemStack(Material.ARROW);
	static ItemStack bow= new ItemStack(Material.BOW);
	public static void init()
	{
		ItemMeta im=forbidden.getItemMeta();
		im.setDisplayName(" ");
		forbidden.setItemMeta(im);
		im=arrow.getItemMeta();
		im.setDisplayName("¼ý¡á");
		arrow.setItemMeta(im);
		im=bow.getItemMeta();
		im.spigot().setUnbreakable(true);
		im.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		bow.setItemMeta(im);
		
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
	public static ItemStack getBow()
	{
		return bow;
	}
}
