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

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/**
 *
 * @author EDawg878 <EDawg878@gmail.com>
 */
public class PlayerLink extends JavaPlugin implements Listener, CommandExecutor {

    public enum PlayerLinkType {

        WEBSITE("website", "http://minecraft.net", true),
        VOTE("vote", "http://minecraft-server-list.com", true),
        FORUMS("forums", "http://crystalcraftmc.com/forum", true),
        SHOP("shop", "http://buycraft.net", true),
        VOICE("voice", "http://mumble.info", true),
        DYNMAP("dynmap", "http://dev.bukkit.org/bukkit-plugins/dynmap", true),
        WIKI("wiki", "http://spigotmc.org/wiki", true),
        FACEBOOK("facebook", "http://facebook.com", true),
        TWITTER("twitter", "http://twitter.com", true),
        YOUTUBE("youtube", "http://youtube.com", true),
        GOOGLE_PLUS("google+", "http://plus.google.com", true),
        INSTAGRAM("instagram", "http://instagram.com", true);

        @Getter
        private String name, link;
        @Getter
        private boolean broadcast;

         PlayerLinkType(String s1, String s2, boolean b) {
        }

        public String getName() {
            return name;
        }

        public String getLink() {
            return link;
        }

        public boolean isBroadcast() {
            return broadcast;
        }
    }

    @Override
    public void onEnable() {
        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }

        setupConfig();
        registerCommands();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("playerlink.reload")) {
                    reloadConfig();
                    sender.sendMessage(ChatColor.GREEN + "Configuration reloaded!");
                } else {
                    sender.sendMessage(ChatColor.RED + "No permission");
                }
                return true;
            }
        }
        sender.sendMessage(ChatColor.RED + "Invalid usage");
        return false;
    }

    private void setupConfig() {
        FileConfiguration config = getConfig();
        config.addDefault("server-name", "A Minecraft Server");
        for (PlayerLinkType type : PlayerLinkType.values()) {
            String key = type.getName();
            ConfigurationSection section = config.getConfigurationSection(key);
            if (section == null) {
                section = config.createSection(key);
            }
            section.addDefault("url", type.getLink());
            section.addDefault("enable-broadcast", type.isBroadcast());
        }
        config.options().copyDefaults(true);
        saveConfig();
    }

    public String getLink(PlayerLinkType type) {
        return getConfig().getString(type.getName() + ".url", type.getLink());
    }

    public boolean isBroadcast(PlayerLinkType type) {
        return getConfig().getBoolean(type.getName() + ".enable-broadcast", type.isBroadcast());
    }

    public boolean hasPermission(CommandSender sender, PlayerLinkType type) {
        if (type == PlayerLinkType.GOOGLE_PLUS) {
            return sender.hasPermission("playerlink.googleplus");
        }
        return sender.hasPermission("playerlink." + type.getName());
    }

    public String getServerName() {
        return getConfig().getString("server-name");
    }

    private void registerCommands() {
        getCommand("playerlink").setExecutor(this);
        for (PlayerLinkType type : PlayerLinkType.values()) {
            getCommand(type.getName()).setExecutor(new PlayerLinkCommand(this, type));

        }
    }

    public void onPlayerQuit(PlayerQuitEvent event) {
        Cooldowns.removeCooldowns(event.getPlayer());
    }
}
