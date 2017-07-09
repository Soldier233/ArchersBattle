package net.mcmhsj.archersbattle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor{
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
			case "join":
				
				break;
			default:
				break;
			}
		}
		return true;
	}
	private void sendHelp(CommandSender sender)
	{
		sender.sendMessage("§a- /ab join <竞技场名> §6加入某个竞技场");
	}
}
