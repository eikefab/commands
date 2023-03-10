package com.github.eikefab.commands.impl;

import com.github.eikefab.commands.CommandParser;
import com.github.eikefab.commands.utils.UUIDChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerCommandParser implements CommandParser<Player> {

    @Override
    public Player from(Object arg) {
        if (arg == null) return null;

        final String nameOrId = arg.toString();

        if (UUIDChecker.is(nameOrId)) {
            return Bukkit.getPlayer(UUID.fromString(nameOrId));
        } else {
            return Bukkit.getPlayer(nameOrId);
        }
    }

    @Override
    public Object to(Player arg) {
        return arg.getUniqueId();
    }

}
