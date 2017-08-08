package me.zhanshi123.archersbattle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zhanshi123.archersbattle.Arena;
import me.zhanshi123.archersbattle.managers.ArenaManager;
import me.zhanshi123.archersbattle.messages.Messages;
import me.zhanshi123.archersbattle.utils.Utils;

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
						p.sendMessage(Messages.prefix+Messages.AlreadyInArena);
					}
					else
					{
						if(!args[1].equals(null))
						{
							String name=args[1];
							if(Arena.valueOf(name)!=null)
							{
								Arena arena=Arena.valueOf(name);
								arena.addPlayer(p);
								p.sendMessage((Messages.prefix+Messages.JoinedArena).replace("%arena%", arena.getWorldName()));
							}
							else
							{
								p.sendMessage(Messages.prefix+Messages.AreanNotFound);
							}
						}
					}
					break;
				case "leave":
					if(!Utils.isInArena(p))
					{
						p.sendMessage(Messages.prefix+Messages.NotInArena);
					}
					else
					{
						Arena arena=Utils.getAreana(p);
						arena.removePlayer(p);
						p.sendMessage(Messages.prefix+Messages.LeavedArena);
					}
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
		sender.sendMessage("§a- /ab leave §6离开竞技场");
	}
}
