package com.crystalcraftmc.playerlink;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PLHelp implements CommandExecutor
{
	Main plugin;
	public PLHelp(Main plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		// Make the letter 'p' a variable for the command sender (or the player).
		Player p = (Player) sender;
		
		// If the player typed /plhelp, then do the following...
    	if (cmd.getName().equalsIgnoreCase("plhelp"))
    	{
    		p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + "PlayerLink Commands" + ChatColor.GOLD + " <-=-=-=-=");
    		p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.GOLD + "Version: " + ChatColor.RED + "1.1");
    		p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/PlayerLink: " + ChatColor.GOLD + "Shows commands in the plugin.");
    		p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/website: " + ChatColor.GOLD + "Displays a link to the server website.");
    		p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/vote: " + ChatColor.GOLD + "Displays a link to a voting website for this server.");
    		p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/facebook: " + ChatColor.GOLD + "Displays a link to the server Facebook page.");
    		p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/twitter: " + ChatColor.GOLD + "Displays a link to the server Twitter page.");
    		p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/youtube: " + ChatColor.GOLD + "Displays a link to the server YouTube page.");
    		p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/link: " + ChatColor.GOLD + "Displays a link to whatever an admin has set.");
    		p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + "PlayerLink Commands" + ChatColor.GOLD + " <-=-=-=-=");
    	
    		// If this has happened, the function will return true. 
    		return true;
    	}	
    	// If this hasn't happened, a value of false will be returned.
    	return false;
	}
}

