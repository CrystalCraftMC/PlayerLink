package com.crystalcraftmc.playerlink;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerLinkCmd implements CommandExecutor
{
	Main plugin;
	public PlayerLinkCmd(Main plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	    if(cmd.getName().equalsIgnoreCase("playerlink"))
	    {
	    	// Make the letter 'p' a variable for the command sender (or the player).
			Player p = (Player) sender;
			
	        if(args.length != 1)
	        {
	        	// If this hasn't happened, a value of false will be returned.
	            return false;
	        }
	        
	        else if(args[0].equalsIgnoreCase("reload"))
	        {
	        	// If the sender of the command has this permission...
	    		if(p.hasPermission("playerlink.reload"))
	    		{
	    			plugin.reloadConfig();
					p.sendMessage(ChatColor.GREEN + "Configuration Reloaded!");
	    		}
	    		
	    		// Otherwise, if the sender of the command doesn't have the permission...
	        	else
	    		{
	        		// ...do not let the command be run.
	    			p.sendMessage("You do not have permission to use that command.");
	    		}
	        	
	        	// If this has happened, the function will return true. 
	            return true;
	        }
	        
	        else if(args[0].equalsIgnoreCase("help"))
	        {
	        	// If the sender of the command has this permission...
	    		if(p.hasPermission("playerlink.help"))
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
	    		}
	    		
	    		// Otherwise, if the sender of the command doesn't have the permission...
	        	else
	    		{
	        		// ...do not let the command be run.
	    			p.sendMessage("You do not have permission to use that command.");
	    		}
	        	
	        	// If this has happened, the function will return true. 
	            return true;
	        }
	    }
	    // If this hasn't happened, a value of false will be returned.
		return false;
	}
}
