package net.mcmhsj.archersbattle.managers;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitItemManager {

	public static ItemStack getChangjian()
	{
		ItemStack item=new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta im=item.getItemMeta();
		im.setDisplayName("³¤½£");
		item.setItemMeta(im);
		return item;
	}
	public static ItemStack getGong()
	{
		ItemStack item = new ItemStack(Material.BOW);
		ItemMeta im=item.getItemMeta();
		im.setDisplayName("¹­");
		item.setItemMeta(im);
		return item;
	}
	public static boolean isKitSelector(ItemStack item)
	{
		String[] kitselector=new String[]
				{
						"³¤½£",
						"¹­",
				};
		List<String> list=Arrays.asList(kitselector);
		if(list.contains(item.getItemMeta().getDisplayName()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
