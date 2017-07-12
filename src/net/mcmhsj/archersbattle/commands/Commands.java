package net.mcmhsj.archersbattle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.mcmhsj.archersbattle.Arena;
import net.mcmhsj.archersbattle.managers.ArenaManager;
import net.mcmhsj.archersbattle.messages.Messages;
import net.mcmhsj.archersbattle.utils.Utils;

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
