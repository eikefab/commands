package com.github.eikefab.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CommandContext {

    private final CommandSender sender;
    private final List<Object> args;

    protected CommandContext(CommandSender sender, List<Object> args) {
        this.sender = sender;
        this.args = args;
    }

    public CommandSender getSender() {
        return sender;
    }

    public List<Object> getArgs() {
        return args;
    }

    public Object getArg(int index) {
        if (index > args.size()) return null;

        return args.get(index);
    }

    public <T> T getArg(int index, Class<? extends T> clazz) {
        if (index > (args.size() - 1)) return null;

        return CommandParsers.get(clazz).from(args.get(index));
    }

    public void sendMessage(String text, Object... values) {
        if (text == null) return;

        sender.sendMessage(String.format(colorize(text), values));
    }

    public void sendMessage(String[] args) {
        Arrays.stream(args).forEach(this::sendMessage);
    }

    public void sendMessage(List<String> args) {
        args.forEach(this::sendMessage);
    }

    public boolean hasPermission(String permission) {
        if (permission == null || permission.isEmpty() || permission.isBlank()) return true;

        return sender.hasPermission(permission);
    }

    public Player getPlayer() {
        if (!(sender instanceof Player)) return null;

        return (Player) sender;
    }

    public String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }


}
