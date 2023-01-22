package com.github.eikefab.commands;

public interface CommandParser<T> {

    T from(Object arg);
    default Object to(T arg) {
        return arg;
    }

}
