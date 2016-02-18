package com.uraimo.javabuild.modules;

/**
 * Module that points to sources in a GIT repository
 *
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
        //TODO: Missing implementation
        //Clone from git
        //Compile
        //Jar and place it under dependencies
    }

}
