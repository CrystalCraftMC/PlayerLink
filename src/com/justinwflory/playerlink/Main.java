/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Justin W. Flory
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.justinwflory.playerlink;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Main extends JavaPlugin {
    @SuppressWarnings("unused")
    private FileConfiguration config;

    @Override
    public void onEnable() {
        getLogger().info("PlayerLink has been enabled!");

        // Submit plugin metrics
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }

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

        // Generate the config.yml
        config = getConfig();
        saveDefaultConfig();

        if (this.getConfig().getBoolean("auto-update")) {
            @SuppressWarnings("unused")
            Updater updater = new Updater(this, 54020, this.getFile(), Updater.UpdateType.DEFAULT, true);
        }
    }

    Player p = (Player) sender;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("website") && sender.hasPermission("playerlink.website")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + plugin.getConfig().getString("server-name") + "'s Website Address!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + plugin.getConfig().getString("website.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + plugin.getConfig().getString("server-name") + "'s Website Address!" + ChatColor.GOLD + " <-=-=-=-=");
            if (plugin.getConfig().getBoolean("website.enable-broadcast")) {
                long last = plugin.getConfig().getLong("last-used.website" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/website " + ChatColor.RESET + ChatColor.GREEN + "to get the website link for " + (plugin.getConfig().getString("server-name")));
                    plugin.getConfig().set("last-used.website" + sender.getName(), now);
                }
            }
            return true;
        } else {
            p.sendMessage("You do not have permission to use that command.");
        }
        return false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("vote") && p.hasPermission("playerlink.vote")) {
                p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + "Vote for " + plugin.getConfig().getString("server-name") + ChatColor.GOLD + " <-=-=-=-=");
                p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + plugin.getConfig().getString("vote.url"));
                p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + "Vote for " + plugin.getConfig().getString("server-name") + ChatColor.GOLD + " <-=-=-=-=");
                if (plugin.getConfig().getBoolean("vote.enable-broadcast")) {
                    long last = plugin.getConfig().getLong("last-used.vote" + sender.getName(), 0L);
                    long now = System.currentTimeMillis();
                    if ((now - last) > DAY_IN_MILLIS) {
                        Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/vote " + ChatColor.RESET + ChatColor.GREEN + "to get a voting link for " + (plugin.getConfig().getString("server-name")));
                        plugin.getConfig().set("last-used.vote" + sender.getName(), now);
                    }
                }
                return true;
            } else {
            p.sendMessage("You do not have permission to use that command.");
        }
        return false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("forums") && p.hasPermission("playerlink.forums")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + plugin.getConfig().getString("server-name") + "'s Forums!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + plugin.getConfig().getString("forums.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + plugin.getConfig().getString("server-name") + "'s Forums!" + ChatColor.GOLD + " <-=-=-=-=");
            if (plugin.getConfig().getBoolean("forums.enable-broadcast")) {
                long last = plugin.getConfig().getLong("last-used.forums" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/forums " + ChatColor.RESET + ChatColor.GREEN + "to get the forum link for " + (plugin.getConfig().getString("server-name")));
                    plugin.getConfig().set("last-used.forums" + sender.getName(), now);
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
