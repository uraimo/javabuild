package com.uraimo.javabuild.modules;

/**
 * Module interface, it represent a set of sources or a precompiled module that can be
 * built or linked.
 *
 * Created by Umberto Raimondi on 17/02/16.
 */
public interface Module {

    /**
     * Performs actions that precedes the build process
     */
    public void performPrebuildActions();
}
