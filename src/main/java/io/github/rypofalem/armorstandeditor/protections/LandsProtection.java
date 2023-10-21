/*
 * ArmorStandEditor: Bukkit plugin to allow editing armor stand attributes
 * Copyright (C) 2016-2023  RypoFalem
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package io.github.rypofalem.armorstandeditor.protections;

import fr.iban.lands.LandManager;
import fr.iban.lands.LandsPlugin;
import fr.iban.lands.enums.Action;
import fr.iban.lands.land.Land;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class LandsProtection implements Protection {
    private final boolean landsEnabled;
    private LandManager landManager;

    public LandsProtection() {
        landsEnabled = Bukkit.getPluginManager().isPluginEnabled("Lands");

        if (landsEnabled)
            landManager = LandsPlugin.getInstance().getLandManager();
    }

    @Override
    public boolean checkPermission(Block block, Player player) {
        if (!landsEnabled || player.hasPermission("asedit.ignoreProtection.lands")) return true;

        Land land = landManager.getLandAt(block.getLocation());

        return land.isBypassing(player, Action.BLOCK_PLACE);
    }
}
