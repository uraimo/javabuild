package com.uraimo.javabuild.modules;

/**
 * A module that points to java sources in the usual source location
 *
 * Created by Umberto Raimondi on 17/02/16.
 */
public class SrcModule {

    public String name="";
    public String version="";
    public SrcModule[] dependencies= new SrcModule[0];

    public SrcModule(String name, String version, SrcModule[] dependencies){
        this.name=name;
        this.version=version;
        this.dependencies=dependencies;
    }


    public void performPrebuildActions(){
        //Nothing to do here
    }
}
