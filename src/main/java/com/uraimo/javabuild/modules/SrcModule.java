package com.uraimo.javabuild.modules;

/**
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


    public void performPrebuildActions(){}
}
