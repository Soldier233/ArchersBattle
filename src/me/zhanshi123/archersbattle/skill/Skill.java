package me.zhanshi123.archersbattle.skill;

import org.bukkit.inventory.ItemStack;

public abstract class Skill {
	String skillName;
	ItemStack show,selector;
	/**
	*
	* @param 技能名
	* @param 实际选择后显示的物品
	* @param 技能选择器中显示的物品 
	* 
	* @author Soldier 
	*/
	public Skill(String skillName)
	{
		this.skillName=skillName;
	}
	
	public String getName()
	{
		return skillName;
	}
	public ItemStack getShow()
	{
		return show;
	}
	public ItemStack getSelector()
	{
		return selector;
	}
	public void setShow(ItemStack show)
	{
		this.show=show;
	}
	public void setSelector(ItemStack selector)
	{
		this.selector=selector;
	}
}
