package net.mcmhsj.archersbattle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.mcmhsj.archersbattle.Arena;
import net.mcmhsj.archersbattle.managers.ArenaManager;
import net.mcmhsj.archersbattle.messages.Messages;

public class AdminCommands implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.isOp())
		{
			return true;
		}
		if(args.length==0)
		{
			sendHelp(sender);
		}
		else
		{
			switch(args[0])
			{
			case "create":
				String worldName=args[1];
				if(args[1]==null)
				{
					sendHelp(sender);
					return true;
				}
				if(Arena.containsArena(worldName))
				{
					sender.sendMessage(Messages.prefix+Messages.ArenaAlreadyExists);
					return true;
				}
				Arena arena=new Arena(worldName);
				ArenaManager.addArena(arena);
				sender.sendMessage(Messages.prefix+Messages.ArenaCreated);
				break;
			case "addspawnpoint":
				break;
			case "list":
				
				break;
			default:
				sendHelp(sender);
				break;
			}
		}
		return true;
	}
	private void sendHelp(CommandSender sender)
	{
		sender.sendMessage("§a- /abadmin create <世界名> §6新建一个竞技场，同时竞技场名也为该世界名");
	}
	
}
