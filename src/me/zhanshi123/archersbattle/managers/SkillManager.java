package me.zhanshi123.archersbattle.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.zhanshi123.archersbattle.skill.Skill;

public class SkillManager {
	private static SkillManager sm=null;
	public SkillManager()
	{
		sm=this;
	}
	public static SkillManager getInstance()
	{
		return sm;
	}
	//Basic Methods End
	private List<Skill> skills=new ArrayList<Skill>();

	public List<Skill> getSkills()
	{
		return skills;
	}
	
	public void register(Skill skill)
	{
		skills.add(skill);
	}
	
	public Skill getSkillByName(String name)
	{
		Skill sr=null;;
		for(Skill skill:skills)
		{
			if(skill.getName().equalsIgnoreCase(name))
			{
				sr=skill;
				break;
			}
		}
		return sr;
	}
	
	public boolean isSkillSelector(ItemStack item)
	{
		boolean result=false;
		for(Skill s:skills)
		{
			if(s.getSelector().isSimilar(item))
			{
				result=true;
				break;
			}
		}
		return result;
	}
	
	public Skill getSkillBySelector(ItemStack item)
	{
		Skill result=null;
		for(Skill s:skills)
		{
			ItemStack skill=s.getSelector();
			if(skill.isSimilar(item))
			{
				result=s;
			}
		}
		return result;
	}
	
	public List<Skill> getRandomSkills()
	{
		return null;
	}
}
