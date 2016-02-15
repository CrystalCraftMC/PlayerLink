/*
 * Copyright 2015 Justin W. Flory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.justinwflory.playerlink;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public final class Main extends JavaPlugin {
    
    private final long DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1);
    
    @Override
    public void onEnable() {
        getLogger().info("PlayerLink has been enabled!");
        
        saveDefaultConfig();
        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }

        if (this.getConfig().getBoolean("auto-update")) {
            @SuppressWarnings("unused")
            Updater updater = new Updater(this, 54020, this.getFile(), Updater.UpdateType.DEFAULT, true);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
		String cmd = command.getName().toLowerCase();
		String serverName = this.getConfig().getString("server-name");
		String descriptor = this.getConfig().getString(cmd+".desc")
		
		if (cmd = "playerlink") {
-            if (args.length != 1) {
-                return false;
-            } else if (args[0].equalsIgnoreCase("reload") && p.hasPermission("playerlink.reload")) {
-                this.reloadConfig();
-                sender.sendMessage(ChatColor.GREEN + "Configuration reloaded!");
-                return true;
-            } else if (args[0].equalsIgnoreCase("help") && p.hasPermission("playerlink.help")) {
-                sender.sendMessage(ChatColor.GOLD + "=-=-=-=-=-=-=-> " + ChatColor.YELLOW + "PlayerLink Commands" + ChatColor.GOLD + " <-=-=-=-=-=-=-=");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/playerlink help: " + ChatColor.GOLD + "Shows commands in the plugin.");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/website: " + ChatColor.GOLD + "Displays a link to the server website.");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/vote: " + ChatColor.GOLD + "Displays a link to a voting website for this server.");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/forums: " + ChatColor.GOLD + "Displays a link to the server forums.");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/shop: " + ChatColor.GOLD + "Displays a link to the server shop.");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/voice: " + ChatColor.GOLD + "Displays an IP to the voice server.");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/dynmap: " + ChatColor.GOLD + "Displays a link to the server DynMap.");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/wiki: " + ChatColor.GOLD + "Displays a link to the server wiki.");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/facebook: " + ChatColor.GOLD + "Displays a link to the server Facebook page.");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/twitter: " + ChatColor.GOLD + "Displays a link to the server Twitter page.");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/youtube: " + ChatColor.GOLD + "Displays a link to the server YouTube page.");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/google+: " + ChatColor.GOLD + "Displays a link to the server Google+ page.");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/instagram: " + ChatColor.GOLD + "Displays a link to the server Instagram page");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/link: " + ChatColor.GOLD + "Displays a link to whatever an admin has set.");
-                sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.RED + "/playerlink reload: " + ChatColor.GOLD + "Reload the configuration.");
-                sender.sendMessage(ChatColor.GOLD + "=-=-=-=-=-=-=-> " + ChatColor.YELLOW + "PlayerLink Commands" + ChatColor.GOLD + " <-=-=-=-=-=-=-=");
-            }
-            return true;
		} else if (p.hasPermission("playerlink."+cmd)) {
			p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + serverName + "'s " + descriptor + "!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString(cmd+".url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + serverName + "'s " + descriptor + "!" + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean(cmd+".enable-broadcast")) {
                long last = this.getConfig().getLong("last-used."+cmd + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/"+cmd+" " + ChatColor.RESET + ChatColor.GREEN + "to get the " + descriptor + " for " + serverName);
                    this.getConfig().set("last-used."+cmd + sender.getName(), now);
                }
            }
			return true;
		} else {
            p.sendMessage("You do not have permission to use that command.");
        }
        return false;
    }

    @Override
    public void onDisable() {
        getLogger().info("PlayerLink has been disabled!");
    }
}
