package com.crystalcraftmc.playerlink;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor
{
	Main plugin;
	public Reload(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		// Make the letter 'p' a variable for the command sender (or the player).
		Player p = (Player) sender;
				
		// If the sender of the command has this permission...
		if(p.hasPermission("playerlink.reload"))
		{
			// ...and the player typed /plreload...
			if(cmd.getName().equalsIgnoreCase("plreload"))
			{
				plugin.reloadConfig();
				p.sendMessage(ChatColor.GREEN + "Configuration Reloaded!");
			}
			
			// If this has happened, the function will return true. 
    		return true;
    	}
    	
    	// Otherwise, if the sender of the command doesn't have the permission...
    	else
		{
    		// ...do not let the command be run.
			p.sendMessage("You do not have permission to use that command.");
		}
		
		// If this hasn't happened, a value of false will be returned.
		return false;
	}
}	
