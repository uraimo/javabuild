package com.uraimo.javabuild.modules;

/**
 * Created by Umberto Raimondi on 17/02/16.
 */
public class GitModule {
    public String gitPath="";
    public String gitTag="";


    public GitModule(String gitPath,
                     String gitTag){
        this.gitPath=gitPath;
        this.gitTag=gitTag;
    }

    public void performPrebuildActions(){
        //Clone from git
        //Compile
        //Jar and place it under dependencies
    }

}
