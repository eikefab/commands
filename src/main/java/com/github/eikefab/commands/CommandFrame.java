package com.github.eikefab.commands;

import java.util.ArrayList;
import java.util.List;

public interface CommandFrame {

    String name();

    default CommandType type() {
        return CommandType.BOTH;
    }

    default List<String> aliases() {
        return new ArrayList<>();
    }

    default String permission() {
        return null;
    }

    default String permissionMessage() {
        return null;
    }

    void handle(CommandContext context);

}
