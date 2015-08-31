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
