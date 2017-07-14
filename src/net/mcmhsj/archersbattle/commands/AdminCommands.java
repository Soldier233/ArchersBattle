package net.mcmhsj.archersbattle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
			case "addspawn":
				if(!(sender instanceof Player))
				{
					sender.sendMessage(Messages.prefix+Messages.PlayersOnly);
					return true;
				}
				Player p=(Player) sender;
				worldName=args[1];
				if(args[1]==null)
				{
					sendHelp(sender);
					return true;
				}
				if(!Arena.containsArena(worldName))
				{
					sender.sendMessage(Messages.prefix+Messages.AreanNotFound);
					return true;
				}
				Arena a1=Arena.valueOf(worldName);
				a1.getSpawnLocations().add(p.getLocation());
				a1.saveFile();
				sender.sendMessage(Messages.prefix+Messages.SpawnAdded);
				break;
			case "list":
				sender.sendMessage(Messages.prefix+Messages.ArenasBelow);
				for(Arena a:ArenaManager.getArenas())
				{
					sender.sendMessage("§a - §b"+a.getWorldName()+" "+a.getPlayers().size());
				}
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
		sender.sendMessage("§a- /abadmin list §6查看加载的竞技场");
		sender.sendMessage("§a- /abadmin addspawn <世界名> §6在该竞技场内添加出生点");
	}
	
}
