package com.github.eikefab.commands.impl;

import com.github.eikefab.commands.CommandParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerCommandParser implements CommandParser<Player> {

    @Override
    public Player from(Object arg) {
        try {
            return Bukkit.getPlayer(UUID.fromString(arg.toString()));
        } catch (IllegalArgumentException exception) {
            return Bukkit.getPlayer(arg.toString());
        }
    }

    @Override
    public Object to(Player arg) {
        return arg.getUniqueId();
    }

}
