/*
 * Copyright (c) 2015 Justin W. Flory
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.justinwflory.playerlink;

import com.justinwflory.playerlink.PlayerLink.PlayerLinkType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author EDawg878 <EDawg878@gmail.com>
 */
public class PlayerLinkCommand implements CommandExecutor {

    private static final String BROADCAST = ChatColor.GREEN + "%s" + ChatColor.GREEN + " used " + ChatColor.ITALIC + "%s " + ChatColor.RESET + ChatColor.GREEN + "to get the %s link for %s";
    private static final String PREFIX = ChatColor.GOLD + "[" + ChatColor.YELLOW + "PL" + ChatColor.GOLD + "] " + ChatColor.AQUA + "%s";
    private static final String TITLE = ChatColor.GOLD + "=-=-=-=-> " + ChatColor.YELLOW + "%s's %s page!" + ChatColor.GOLD + " <-=-=-=-=";
    private static final long COOLDOWN = TimeUnit.DAYS.toMillis(1);

    private PlayerLink instance;
    private PlayerLinkType type;

    public PlayerLinkCommand(PlayerLink playerLink, PlayerLinkType type) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (instance.hasPermission(sender, type)) {
            sender.sendMessage(String.format(TITLE, instance.getServerName(), type.getName()));
            sender.sendMessage(String.format(PREFIX, type.getLink()));
            sender.sendMessage(String.format(TITLE, instance.getServerName(), type.getName()));
            if (sender instanceof Player && instance.isBroadcast(type)) {
                Player player = (Player) sender;
                if (Cooldowns.tryCooldown(player, type.getName(), COOLDOWN)) {
                    instance.getServer().broadcastMessage(String.format(BROADCAST, player.getDisplayName(), "/" + type.getName(), type.getName(), instance.getServerName()));
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "No permission");
        }
        return true;
    }

}
