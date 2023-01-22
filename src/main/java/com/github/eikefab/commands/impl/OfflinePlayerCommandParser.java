package com.github.eikefab.commands.impl;

import com.github.eikefab.commands.CommandParser;
import com.github.eikefab.commands.utils.UUIDChecker;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class OfflinePlayerCommandParser implements CommandParser<OfflinePlayer> {

    @Override
    public OfflinePlayer from(Object arg) {
        if (arg == null) return null;

        final String nameOrId = arg.toString();

        if (UUIDChecker.is(nameOrId)) {
            return Bukkit.getOfflinePlayer(UUID.fromString(nameOrId));
        } else {
            return Bukkit.getOfflinePlayer(nameOrId);
        }
    }

    @Override
    public Object to(OfflinePlayer arg) {
        return arg;
    }

}
