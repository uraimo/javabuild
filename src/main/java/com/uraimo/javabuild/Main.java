package com.uraimo.javabuild;

import com.uraimo.javabuild.commands.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Umberto Raimondi on 16/02/16.
 */
public class Main {


    public static void main(String... args) throws IOException{
        if(!new File("Build.java").exists()){
            System.out.print("Missing Build.java, init your project with javabuild --init, exiting.");
            System.exit(0);
        }


        if (!areArgsValid(args)) {
            printUsage("");
            System.exit(0);
        }

        HashMap<String, String[]> pargs = parseArguments(args);

        for (String arg : pargs.keySet()) {
            Command cmd = commandClasses.get(arg);
            cmd.execute(pargs.get(arg));
        }

    }


    private static boolean areArgsValid(String... args) {
        boolean valid = true;

        if (args.length == 0) {
            return false;
        }

        for (String arg : args) {
            if ((arg.startsWith("--")) && (!commands.containsKey(arg))) {
                return false;
            }
        }

        return valid;
    }

    private static HashMap<String, String[]> parseArguments(String... args) {
        HashMap<String, String[]> res = new HashMap<>();

        String key = null;
        List<String> values = new ArrayList<>();
        for (String arg : args) {
            if (arg.startsWith("--")) {
                if (key != null) {
                    res.put(key, values.toArray(new String[0]));
                }
                key = arg;
                values.clear();
            }
            values.add(arg);
        }
        res.put(key, values.toArray(new String[0]));
        return res;
    }

    private static HashMap<String, String> commands = new HashMap<>();
    private static HashMap<String, Command> commandClasses = new HashMap<>();


    static {
        commands.put("--init", "Creates a new project with a basic Build.java");
        commandClasses.put("--init", new Init());
        commands.put("--clean", "Clean the project from all the javabuild artifacts");
        commandClasses.put("--clean", new Clean());
        commands.put("--compile", "Compile the project");
        commandClasses.put("--compile", new Compile());
        commands.put("--jar", "Compile the project and package it in a jar");
        commandClasses.put("--jar", new CombinedCommand(new Compile(),new Jar()) );
    }

    private static void printUsage(String error) {
        if (!error.isEmpty()) {
            System.out.println("Error: " + error);
        }
        System.out.println("Usage:\n");
        System.out.println("javabuild <commands>\n");
        System.out.println("Options:");
        for (String option : commands.keySet()) {
            System.out.println(option + "     " + commands.get(option));
        }
    }


}
