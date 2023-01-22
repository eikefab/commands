package com.github.eikefab.commands;

public interface CommandParser<T> {

    T from(Object arg);
    Object to(T arg);

}
