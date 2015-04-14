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

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.bukkit.entity.Player;

public class Cooldowns {

    // https://gist.github.com/aadnk/5499140
    private static final Table<String, String, Long> cooldowns = HashBasedTable.create();

    /**
     * Retrieve the number of milliseconds left until a given cooldown expires.
     * <p>
     * Check for a negative value to determine if a given cooldown has expired.
     * <br>
     * Cooldowns that have never been defined will return
     * {@link Long#MIN_VALUE}.
     *
     * @param player - the player.
     * @param key - cooldown to locate.
     * @return Number of milliseconds until the cooldown expires.
     */
    public static long getCooldown(Player player, String key) {
        return calculateRemainder(cooldowns.get(player.getName(), key));
    }

    /**
     * Update a cooldown for the specified player.
     *
     * @param player - the player.
     * @param key - cooldown to update.
     * @param delay - number of milliseconds until the cooldown will expire
     * again.
     * @return The previous number of milliseconds until expiration.
     */
    public static long setCooldown(Player player, String key, long delay) {
        return calculateRemainder(
                cooldowns.put(player.getName(), key, System.currentTimeMillis() + delay));
    }

    /**
     * Determine if a given cooldown has expired. If it has, refresh the
     * cooldown. If not, do nothing.
     *
     * @param player - the player.
     * @param key - cooldown to update.
     * @param delay - number of milliseconds until the cooldown will expire
     * again.
     * @return TRUE if the cooldown was expired/unset and has now been reset,
     * FALSE otherwise.
     */
    public static boolean tryCooldown(Player player, String key, long delay) {
        if (getCooldown(player, key) <= 0) {
            setCooldown(player, key, delay);
            return true;
        }
        return false;
    }

    /**
     * Remove any cooldowns associated with the given player.
     *
     * @param player - the player we will reset.
     */
    public static void removeCooldowns(Player player) {
        cooldowns.row(player.getName()).clear();
    }

    private static long calculateRemainder(Long expireTime) {
        return expireTime != null ? expireTime - System.currentTimeMillis() : Long.MIN_VALUE;
    }
}