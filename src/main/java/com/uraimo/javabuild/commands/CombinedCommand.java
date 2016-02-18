package com.uraimo.javabuild.commands;

import java.io.IOException;

/**
 * Command that combines two commands and executes them sequentially
 *
 * Created by Umberto Raimondi on 18/02/16.
 */
public class CombinedCommand implements Command {

    private Command first=null;
    private Command second=null;

    public CombinedCommand(Command first,Command second){
        this.first=first;
        this.second=second;
    }


    /**
     * Execute the two combined commands, same parameters given to both
     *
     * @param parameters
     * @throws IOException
     */
    @Override
    public void execute(String... parameters) throws IOException {
        first.execute(parameters);
        second.execute(parameters);
    }
}
