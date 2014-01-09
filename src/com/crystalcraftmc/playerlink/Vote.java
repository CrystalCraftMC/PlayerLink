package com.crystalcraftmc.playerlink;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vote implements CommandExecutor
{
	private static long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
	
	Main plugin;
	public Vote(Main plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		// Make the letter 'p' a variable for the command sender (or the player).
		Player p = (Player) sender;
		
		// If the sender of the command has this permission...
		if(p.hasPermission("playerlink.vote"))
		{
			// ...and the player typed /vote...
	    	if (cmd.getName().equalsIgnoreCase("vote"))
	    	{
	    		// ...and the sender of the command is NOT a player...
	    		if (!(sender instanceof Player))
	    		{
	    			// ...do not let the command be run.
	    			sender.sendMessage("This command can only be run by a player.");
	    		}
	    		
	    		p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + "Vote for " + plugin.getConfig().getString("server-name") + ChatColor.GOLD + " <-=-=-=-=");
	    		p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + plugin.getConfig().getString("vote.url"));
	    		p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + "Vote for " + plugin.getConfig().getString("server-name") + ChatColor.GOLD + " <-=-=-=-=");
	    		if (plugin.getConfig().getBoolean("vote.enable-broadcast"))
	    		{
	    			long last = plugin.getConfig().getLong("last-used.vote" + sender.getName(), 0L);
	    			long now = System.currentTimeMillis();
	    			if ((now - last) > DAY_IN_MILLIS)
	    			{
	    				Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/vote " + ChatColor.RESET + ChatColor.GREEN + "to get a voting link for " + (plugin.getConfig().getString("server-name")));
	    				plugin.getConfig().set("last-used.vote" + sender.getName(), now);
	    			}
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
		}
    	// If this hasn't happened, a value of false will be returned.
    	return false;
	}
}