package com.github.eikefab.commands.utils;

import java.util.regex.Pattern;

public final class UUIDChecker {

    private static final Pattern UUID_PATTERN = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$");

    private UUIDChecker() {}

    public static boolean is(String text) {
        return UUID_PATTERN.matcher(text).matches();
    }

}
