package com.uraimo.javabuild;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Umberto Raimondi on 16/02/16.
 */
public class Main {

    private static String TARGETDIR = "target";

    public static void main(String... args) {
        if (args.length == 0) {
            printUsage("");
        }
    }

    private static HashMap<String,String> options = new HashMap<>();

    static{
        options.put("--init","Creates a new project with a basic Build.java");
    }

    private static void printUsage(String error) {
        if (error.isEmpty()) {
            System.out.println("Error: " + error);
        }
        System.out.println("Usage:\n");
        System.out.println("javabuild <commands>\n");
        System.out.println("Options:");
        for (String option : options.keySet()) {
            System.out.println(option+"     "+options.get(option));
        }
    }


}
