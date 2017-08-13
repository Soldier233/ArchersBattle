package me.zhanshi123.archersbattle.managers;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class CooldownManager {
	private static CooldownManager cm=null;
	public CooldownManager()
	{
		cm=this;
	}
	public static CooldownManager getInstance()
	{
		return cm;
	}
	//Basic Methods End
	
	HashMap<Player,Long> data=new HashMap<Player,Long>();
	public void add(Player p)
	{
		if(data.containsKey(p))
		{
			data.remove(p);
		}
		data.put(p, System.currentTimeMillis());
	}
	public boolean exists(Player p)
	{
		return data.containsKey(p);
	}
	public long getLeft(Player p,long time)
	{
		if(!data.containsKey(p))
		{
			return 0;
		}
		else
		{
			long record=data.get(p);
			long now=System.currentTimeMillis();
			if((record+time)>now)
			{
				return (record+time)-now;
			}
			else
			{
				return 0;
			}
		}
	}
}
