package com.github.eikefab.commands;

import java.util.HashMap;
import java.util.Map;

public final class CommandParsers {

    private static final Map<Class<?>, CommandParser<?>> PARSER_MAP = new HashMap<>();

    private CommandParsers() {}

    static {

    }

    public static Map<Class<?>, CommandParser<?>> getParserMap() {
        return PARSER_MAP;
    }

    public static <T> CommandParser<T> get(Class<? extends T> clazz) {
        return (CommandParser<T>) PARSER_MAP.get(clazz);
    }

    public static <T> void addParser(Class<? extends T> clazz, CommandParser<T> parser) {
        PARSER_MAP.put(clazz, parser);
    }

}
