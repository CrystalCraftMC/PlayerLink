package com.crystalcraftmc.playerlink;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin
{
	// When the plugin first starts...
	@Override
    public void onEnable()
	{
        // TODO Insert logic to be performed when the plugin is enabled
		getLogger().info("PlayerLink has been enabled!");
		
		// ...link plugin with online stats.
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e){
			// Failed to submit the stats :-(
		}
		
		// ...generate the config.yml file.
		this.saveDefaultConfig();
		
		// ...load the configuration file and copy the defaults into the plugin...
		this.getConfig().options().copyDefaults(true);
		
		// ...and save the configuration file.
        this.saveConfig();
        
        // ...see if the config file allows auto-updating...
        if (this.getConfig().getBoolean("auto-update"))
        {
        	// ...and if so, run the auto-update class.
        	@SuppressWarnings("unused")
			Updater updater = new Updater(this, "playerlink", this.getFile(), Updater.UpdateType.DEFAULT, true);
        }
        
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("website").setExecutor(new Website(this));
		
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("vote").setExecutor(new Vote(this));
		
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("shop").setExecutor(new Shop(this));
		
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("Facebook").setExecutor(new Facebook(this));
		
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("Twitter").setExecutor(new Twitter(this));
		
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("YouTube").setExecutor(new YouTube(this));
		
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("Instagram").setExecutor(new Instagram(this));
		
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("link").setExecutor(new Link(this));
		
		// This will throw a NullPointerException if you don't have the command defined in your plugin.yml file!
		getCommand("playerlink").setExecutor(new PlayerLinkCmd(this));
    }
 
    @Override
    public void onDisable()
    {
        // TODO Insert logic to be performed when the plugin is disabled
    	getLogger().info("PlayerLink has been disabled!");
    }
}

// GitHub.com test