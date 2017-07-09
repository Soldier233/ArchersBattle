package net.mcmhsj.archersbattle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.mcmhsj.archersbattle.Arena;

public class AdminCommands implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
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
				Arena arena=new Arena(worldName);
				
				break;
			default:
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
