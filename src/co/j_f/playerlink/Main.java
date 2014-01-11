package co.j_f.playerlink;

import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin
{	
	@SuppressWarnings("unused")
	private FileConfiguration config;
	
	// When the plugin first starts...
	@Override
    public void onEnable()
	{
		// ...put a message in console confirming the plugin is enabled.
		getLogger().info("PlayerLink has been enabled!");
		
		// ...link the plugin with the online stats.
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e){
			// Failed to submit the stats :-(
		}
		
		// ...get the config.yml and generate it if it doesn't exist or has changed...
		config = getConfig();
		saveDefaultConfig();
        
        // ...see if the config file allows auto-updating...
        if (this.getConfig().getBoolean("auto-update"))
        {
        	// ...and if so, run the auto-update class.
        	@SuppressWarnings("unused")
        	Updater updater = new Updater(this, 54020, this.getFile(), Updater.UpdateType.DEFAULT, true);
        }
        
		getCommand("website").setExecutor(new Website(this));
		
		getCommand("vote").setExecutor(new Vote(this));
		
		getCommand("forums").setExecutor(new Forums(this));
		
		getCommand("shop").setExecutor(new Shop(this));
		
		getCommand("voice").setExecutor(new Voice(this));
		
		getCommand("map").setExecutor(new DynMap(this));
		
		getCommand("wiki").setExecutor(new Wiki(this));
		
		getCommand("Facebook").setExecutor(new Facebook(this));
		
		getCommand("Twitter").setExecutor(new Twitter(this));
		
		getCommand("YouTube").setExecutor(new YouTube(this));
		
		getCommand("Google+").setExecutor(new GooglePlus(this));
		
		getCommand("Instagram").setExecutor(new Instagram(this));
		
		getCommand("link").setExecutor(new Link(this));
		
		getCommand("playerlink").setExecutor(new PlayerLinkCmd(this));
    }
 
    @Override
    public void onDisable()
    {
        // TODO Insert logic to be performed when the plugin is disabled
    	getLogger().info("PlayerLink has been disabled!");
    }
}
