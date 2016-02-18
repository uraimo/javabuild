package com.uraimo.javabuild.commands.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Manifest;

/**
 * Created by Umberto Raimondi on 18/02/16.
 */
public class ManifestBuilder {

    private String version="";
    private String createdBy="";
    private String mainClass="";
    private String[] classpath = new String[0];


    public Manifest getManifest(){
        Manifest man=new Manifest();
        try {
            InputStream is = new ByteArrayInputStream(genManifest().getBytes());
            man = new Manifest(is);
        }catch (IOException ex){
            //NOP
        }
        return man;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public String[] getClasspath() {
        return classpath;
    }

    public void setClasspath(String[] classpath) {
        this.classpath = classpath;
    }

    private String genManifest(){
        String res="";
        //TODO: verify max line length 70
        if(!version.isEmpty()){
            res+="Manifest-Version: "+version+"\n";
        }
        if(!version.isEmpty()){
            res+="Created-By: "+createdBy+"\n";
        }
        if(!mainClass.isEmpty()){
            res+="Created-By: "+createdBy+"\n";
        }
        if(classpath.length>0){
            res+="Class-Path: ";
            for(String pkg : classpath){
                res+=" "+pkg;
            }
        }
        return res;
    }
}
