package com.uraimo.javabuild;

import java.util.HashMap;

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


    private static boolean areArgsValid(String... args){
        boolean valid = true;

        if(args.length==0){
            return false;
        }

        for(String arg : args){
            if( (arg.startsWith("--"))&&(!commands.containsKey(arg)) ) {
                return false;
            }
        }

        return valid;
    }

    private static HashMap<String,String> commands = new HashMap<>();

    static{
        commands.put("--init","Creates a new project with a basic Build.java");
        commands.put("--clean","Clean the project from all the javabuild artifacts");
    }

    private static void printUsage(String error) {
        if (!error.isEmpty()) {
            System.out.println("Error: " + error);
        }
        System.out.println("Usage:\n");
        System.out.println("javabuild <commands>\n");
        System.out.println("Options:");
        for (String option : commands.keySet()) {
            System.out.println(option+"     "+ commands.get(option));
        }
    }


}
