package net.mcmhsj.archersbattle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.mcmhsj.archersbattle.Arena;
import net.mcmhsj.archersbattle.Utils;
import net.mcmhsj.archersbattle.managers.ArenaManager;

public class Commands implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length==0)
		{
			sendHelp(sender);
		}
		else
		{
			if(sender instanceof Player)
			{
				Player p=(Player) sender;
				switch(args[0])
				{
				case "join":
					if(Utils.isInArena(p))
					{
						p.sendMessage("§6§lArchersBattle §7>> §c你已经在一个游戏中了");
					}
					else
					{
						if(!args[1].equals(null))
						{
							String name=args[1];
							if(ArenaManager.getArena(name)!=null)
							{
								Arena arena=ArenaManager.getArena(name);
								arena.addPlayer(p);
								p.sendMessage("§6§lArchersBattle §7>> §a成功加入竞技场"+name);
							}
							else
							{
								p.sendMessage("§6§lArchersBattle §7>> §c竞技场不存在");
							}
						}
					}
					break;
				default:
					break;
				}
			}
		}
		return true;
	}
	private void sendHelp(CommandSender sender)
	{
		sender.sendMessage("§a- /ab join <竞技场名> §6加入某个竞技场");
	}
}
