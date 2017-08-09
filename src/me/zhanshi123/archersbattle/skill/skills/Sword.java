package me.zhanshi123.archersbattle.skill.skills;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.zhanshi123.archersbattle.managers.SkillManager;
import me.zhanshi123.archersbattle.skill.Skill;

public class Sword extends Skill{

	public Sword(String skillName) {
		super(skillName);
	}
	
	public void register()
	{
		ItemStack selector=new ItemStack(Material.STONE_SWORD);
		ItemMeta im=selector.getItemMeta();
		im.setDisplayName("§a长剑");
		im.setLore(Arrays.asList(new String[]{
			"§e§l§m            ",
				"§b想用近战来砍人?"
		}));
		selector.setItemMeta(im);
		this.setSelector(selector);
		
		ItemStack show=new ItemStack(Material.STONE_SWORD);
		im=show.getItemMeta();
		im.setDisplayName("§a长剑");
		im.spigot().setUnbreakable(true);
		show.setItemMeta(im);
		this.setShow(show);
		SkillManager.getInstance().register(this);
	}
}
