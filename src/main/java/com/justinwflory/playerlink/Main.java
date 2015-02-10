/*
 * Copyright 2015 Justin W. Flory
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.justinwflory.playerlink;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("PlayerLink has been enabled!");
        getLogger().info("------oooooo------o---------");
        getLogger().info("-----o------o-----o---------");
        getLogger().info("-----o------o-----o---------");
        getLogger().info("-----ooooooo------o---------");
        getLogger().info("-----o------------o---------");
        getLogger().info("-----o------------o---------");
        getLogger().info("-----o------------oooooooo--");

        try {
            File database = new File(getDataFolder(), "config.yml");
            if (!database.exists()) saveDefaultConfig();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        // Submit plugin metrics
        try {
            Metrics metrics = new Metrics(this);
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
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
		cmd = cmd.getName().toLowerCase();
		serverName = this.getConfig().getString("server-name");
		descriptor = this.getConfig().getString(cmd+".desc")
		
		if (p.hasPermission("playerlink."+cmd)) {
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

    private static long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
}
