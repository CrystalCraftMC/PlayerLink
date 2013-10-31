package com.crystalcraftmc.playerlink;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
	        if(args.length != 1)
	        {
	        	// If this hasn't happened, a value of false will be returned.
	            return false;
	        }
	        
	        else if(args[0].equalsIgnoreCase("reload"))
	        {
	        	// If the sender of the command has this permission...
	    		if(sender.hasPermission("playerlink.reload"))
	    		{
	    			plugin.reloadConfig();
	    			sender.sendMessage(ChatColor.GREEN + "Configuration reloaded!");
	    			return true;
	    		}
	    		
	    		// Otherwise, if the sender of the command doesn't have the permission...
	        	else
	    		{
	        		// ...do not let the command be run.
	    			sender.sendMessage("You do not have permission to use that command.");
	    		}
	        	
	        	// If this has happened, the function will return true. 
	            return true;
	        }
	        
	        else if(args[0].equalsIgnoreCase("help"))
	        {
	        	// If the sender of the command has this permission...
	    		if(sender.hasPermission("playerlink.help"))
	    		{
	    			sender.sendMessage(ChatColor.GOLD + "=-=-=-=-=-=-=-> " + ChatColor.YELLOW + "PlayerLink Commands" + ChatColor.GOLD + " <-=-=-=-=-=-=-=");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/playerlink help: " + ChatColor.GOLD + "Shows commands in the plugin.");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/website: " + ChatColor.GOLD + "Displays a link to the server website.");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/vote: " + ChatColor.GOLD + "Displays a link to a voting website for this server.");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/forums: " + ChatColor.GOLD + "Displays a link to the server forums.");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/shop: " + ChatColor.GOLD + "Displays a link to the server shop.");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/voice: " + ChatColor.GOLD + "Displays an IP to the voice server.");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/dynmap: " + ChatColor.GOLD + "Displays a link to the server DynMap.");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/wiki: " + ChatColor.GOLD + "Displays a link to the server wiki.");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/facebook: " + ChatColor.GOLD + "Displays a link to the server Facebook page.");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/twitter: " + ChatColor.GOLD + "Displays a link to the server Twitter page.");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/youtube: " + ChatColor.GOLD + "Displays a link to the server YouTube page.");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/google+: " + ChatColor.GOLD + "Displays a link to the server Google+ page.");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/instagram: " + ChatColor.GOLD + "Displays a link to the server Instagram page");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/link: " + ChatColor.GOLD + "Displays a link to whatever an admin has set.");
	        		sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/playerlink reload: " + ChatColor.GOLD + "Reload the configuration.");
	        		sender.sendMessage(ChatColor.GOLD + "=-=-=-=-=-=-=-> " + ChatColor.YELLOW + "PlayerLink Commands" + ChatColor.GOLD + " <-=-=-=-=-=-=-=");
	    		}
	    		
	    		// Otherwise, if the sender of the command doesn't have the permission...
	        	else
	    		{
	        		// ...do not let the command be run.
	    			sender.sendMessage("You do not have permission to use that command.");
	    		}
	        	
	        	// If this has happened, the function will return true. 
	            return true;
	        }
	    }
	    // If this hasn't happened, a value of false will be returned.
		return false;
	}
}