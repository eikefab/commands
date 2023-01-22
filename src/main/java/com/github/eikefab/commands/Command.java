package com.github.eikefab.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Command implements CommandFrame {

    public static String mustBeConsoleMessage = "You need to be at console to execute this.";
    public static String mustBePlayerMessage = "You need to be at game to execute this.";

    private final Map<String, Command> subCommands;

    public Command() {
        this.subCommands = new HashMap<>();
    }

    protected Map<String, Command> getSubCommands() {
        return subCommands;
    }

    public Command addSubCommand(Command subCommand) {
        subCommands.put(subCommand.name(), subCommand);

        subCommand.aliases().forEach((name) -> subCommands.put(name, subCommand));

        return this;
    }

    public void register(JavaPlugin plugin) {
        final String name = name();
        final String permission = permission();
        final String permissionMessage = permissionMessage();
        final CommandType type = type();

        final PluginCommand command = plugin.getCommand(name);

        if (command == null) return;

        command.setAliases(aliases());

        if (permission != null) {
            command.setPermission(permission);
        }

        if (permissionMessage != null) {
            command.setPermissionMessage(permissionMessage);
        }

        command.setExecutor(
            (sender, cmd, lb, args) -> {
                final int argLength = args.length;

                if (argLength == 0 || !subCommands.containsKey(args[0])) {
                    if (!validateCommandType(sender, type)) {
                        return true;
                    }

                    final CommandContext context = new CommandContext(
                            sender,
                            argLength == 0 ?
                            ImmutableList.of() :
                            ImmutableList.of(args)
                    );

                    handle(context);

                    return true;
                }

                final Command subCommand = subCommands.get(args[0]);
                final CommandContext context = new CommandContext(
                        sender,
                        argLength > 1 ?
                        ImmutableList.of(
                                Arrays.copyOfRange(
                                    args,
                                    1,
                                    argLength
                                )
                        ) :
                        ImmutableList.of()
                );

                if (!validateCommandType(sender, subCommand.type())) {
                    return true;
                }

                if (!context.hasPermission(subCommand.permission())) {
                    context.sendMessage(subCommand.permissionMessage());

                    return true;
                }

                subCommand.handle(context);

                return true;
            }
        );
    }

    private boolean validateCommandType(CommandSender sender, CommandType type) {
        if (type != CommandType.BOTH) {
            if (sender instanceof Player) {
                if (type == CommandType.CONSOLE) {
                    sender.sendMessage(mustBeConsoleMessage);

                    return false;
                }
            } else {
                if (type == CommandType.PLAYER) {
                    sender.sendMessage(mustBePlayerMessage);

                    return false;
                }
            }
        }

        return true;
    }

}
