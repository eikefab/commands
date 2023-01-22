package com.github.eikefab.commands;

import com.github.eikefab.commands.impl.OfflinePlayerCommandParser;
import com.github.eikefab.commands.impl.PlayerCommandParser;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public final class CommandParsers {

    private static final Map<Class<?>, CommandParser<?>> PARSER_MAP = new HashMap<>();

    private CommandParsers() {}

    static {
        add(Player.class, new PlayerCommandParser());
        add(OfflinePlayer.class, new OfflinePlayerCommandParser());

        add(Integer.class, (number) -> Integer.parseInt(number.toString()));
        add(Double.class, (number) -> Double.parseDouble(number.toString()));
        add(Float.class, (number) -> Float.parseFloat(number.toString()));
        add(Long.class, (number) -> Long.parseLong(number.toString()));
        add(Byte.class, (number) -> Byte.parseByte(number.toString()));
        add(Short.class, (number) -> Short.parseShort(number.toString()));

        add(Boolean.class, (value) -> Boolean.parseBoolean(value.toString()));
        add(String.class, Object::toString);
    }

    public static Map<Class<?>, CommandParser<?>> getParserMap() {
        return PARSER_MAP;
    }

    public static <T> CommandParser<T> get(Class<? extends T> clazz) {
        return (CommandParser<T>) PARSER_MAP.get(clazz);
    }

    public static <T> void add(Class<? extends T> clazz, CommandParser<T> parser) {
        PARSER_MAP.put(clazz, parser);
    }

}
