package com.uraimo.javabuild.commands;

import java.io.IOException;

/**
 * Command interface, executes a command using the gven parameters
 *
 * Created by Umberto Raimondi on 16/02/16.
 */
public interface Command {
    public void execute(String... parameters) throws IOException;
}
