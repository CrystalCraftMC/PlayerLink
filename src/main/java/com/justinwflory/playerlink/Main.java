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

        if (cmd.getName().equalsIgnoreCase("website") && p.hasPermission("playerlink.website")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Website Address!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString("website.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Website Address!" + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean("website.enable-broadcast")) {
                long last = this.getConfig().getLong("last-used.website" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/website " + ChatColor.RESET + ChatColor.GREEN + "to get the website link for " + (this.getConfig().getString("server-name")));
                    this.getConfig().set("last-used.website" + sender.getName(), now);
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("vote") && p.hasPermission("playerlink.vote")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + "Vote for " + this.getConfig().getString("server-name") + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString("vote.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + "Vote for " + this.getConfig().getString("server-name") + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean("vote.enable-broadcast")) {
                long last = this.getConfig().getLong("last-used.vote" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/vote " + ChatColor.RESET + ChatColor.GREEN + "to get a voting link for " + (this.getConfig().getString("server-name")));
                    this.getConfig().set("last-used.vote" + sender.getName(), now);
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("forums") && p.hasPermission("playerlink.forums")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Forums!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString("forums.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Forums!" + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean("forums.enable-broadcast")) {
                long last = this.getConfig().getLong("last-used.forums" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/forums " + ChatColor.RESET + ChatColor.GREEN + "to get the forum link for " + (this.getConfig().getString("server-name")));
                    this.getConfig().set("last-used.forums" + sender.getName(), now);
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("shop") && p.hasPermission("playerlink.shop")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Server Shop!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString("shop.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Server Shop!" + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean("shop.enable-broadcast")) {
                long last = this.getConfig().getLong("last-used.shop" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/shop " + ChatColor.RESET + ChatColor.GREEN + "to get the server shop link for " + (this.getConfig().getString("server-name")));
                    this.getConfig().set("last-used.shop" + sender.getName(), now);
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("voice") && p.hasPermission("playerlink.voice")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Voice Server IP!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString("voice.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Voice Server IP!" + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean("voice.enable-broadcast")) {
                long last = this.getConfig().getLong("last-used.voice" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/voice " + ChatColor.RESET + ChatColor.GREEN + "to get the voice server IP for " + (this.getConfig().getString("server-name")));
                    this.getConfig().set("last-used.voice" + sender.getName(), now);
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("map") && p.hasPermission("playerlink.dynmap")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s DynMap page!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString("dynmap.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s DynMap page!" + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean("dynmap.enable-broadcast")) {
                long last = this.getConfig().getLong("last-used.dynmap" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/map " + ChatColor.RESET + ChatColor.GREEN + "to get the DynMap link for " + (this.getConfig().getString("server-name")));
                    this.getConfig().set("last-used.dynmap" + sender.getName(), now);
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("wiki") && p.hasPermission("playerlink.wiki")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Wiki!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString("wiki.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Wiki!" + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean("wiki.enable-broadcast")) {
                long last = this.getConfig().getLong("last-used.wiki" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/wiki " + ChatColor.RESET + ChatColor.GREEN + "to get the wiki link for " + (this.getConfig().getString("server-name")));
                    this.getConfig().set("last-used.wiki" + sender.getName(), now);
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("facebook") && p.hasPermission("playerlink.facebook")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Facebook Page!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString("facebook.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Facebook Page!" + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean("facebook.enable-broadcast")) {
                long last = this.getConfig().getLong("last-used.facebook" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/facebook " + ChatColor.RESET + ChatColor.GREEN + "to get the Facebook page for " + (this.getConfig().getString("server-name")));
                    this.getConfig().set("last-used.facebook" + sender.getName(), now);
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("twitter") && p.hasPermission("playerlink.twitter")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Twitter Page!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString("twitter.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Twitter Page!" + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean("twitter.enable-broadcast")) {
                long last = this.getConfig().getLong("last-used.twitter" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/twitter " + ChatColor.RESET + ChatColor.GREEN + "to get the Twitter page for " + (this.getConfig().getString("server-name")));
                    this.getConfig().set("last-used.twitter" + sender.getName(), now);
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("youtube") && p.hasPermission(("playerlink.youtube"))) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s YouTube Page!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString("youtube.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s YouTube Page!" + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean("youtube.enable-broadcast")) {
                long last = this.getConfig().getLong("last-used.youtube" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/youtube " + ChatColor.RESET + ChatColor.GREEN + "to get the YouTube page for " + (this.getConfig().getString("server-name")));
                    this.getConfig().set("last-used.youtube" + sender.getName(), now);
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("google+") && p.hasPermission("playerlink.googleplus")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Google+ page!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString("google+.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Google+ page!" + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean("google+.enable-broadcast")) {
                long last = this.getConfig().getLong("last-used.google+" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/google+ " + ChatColor.RESET + ChatColor.GREEN + "to get the Google+ page for " + (this.getConfig().getString("server-name")));
                    this.getConfig().set("last-used.google+" + sender.getName(), now);
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("instagram") && p.hasPermission("playerlink.instagram")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Instagram Page!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString("instagram.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s Instagram Page!" + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean("instagram.enable-broadcast")) {
                long last = this.getConfig().getLong("last-used.instagram" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/instagram " + ChatColor.RESET + ChatColor.GREEN + "to get the Instagram page for " + (this.getConfig().getString("server-name")));
                    this.getConfig().set("last-used.instagram" + sender.getName(), now);
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("link") && p.hasPermission("playerlink.link")) {
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s " + this.getConfig().getString("link.name") + "!" + ChatColor.GOLD + " <-=-=-=-=");
            p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + this.getConfig().getString("link.url"));
            p.sendMessage(ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + this.getConfig().getString("server-name") + "'s " + this.getConfig().getString("link.name") + "!" + ChatColor.GOLD + " <-=-=-=-=");
            if (this.getConfig().getBoolean("link.enable-broadcast")) {
                long last = this.getConfig().getLong("last-used.link" + sender.getName(), 0L);
                long now = System.currentTimeMillis();
                if ((now - last) > DAY_IN_MILLIS) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.GREEN + " used " + ChatColor.ITALIC + "/link " + ChatColor.RESET + ChatColor.GREEN + "to get the " + (this.getConfig().getString("link.name")) + " link for " + (this.getConfig().getString("server-name")));
                    this.getConfig().set("last-used.link" + sender.getName(), now);
                }
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("playerlink")) {
            if (args.length != 1) {
                return false;
            } else if (args[0].equalsIgnoreCase("reload") && p.hasPermission("playerlink.reload")) {
                this.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Configuration reloaded!");
                return true;
            } else if (args[0].equalsIgnoreCase("help") && p.hasPermission("playerlink.help")) {
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
