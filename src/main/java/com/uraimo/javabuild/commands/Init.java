package com.uraimo.javabuild.commands;

import com.uraimo.javabuild.Defaults;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Init commands, create a basic javabuild module
 *
 * Created by Umberto Raimondi on 16/02/16.
 */
public class Init implements Command {

    public void execute(String... parameters) throws IOException {
        File srcDir=new File(Defaults.SRCDIR);
        srcDir.mkdirs();

        File testDir=new File(Defaults.TESTDIR);
        testDir.mkdirs();

        File depsDir=new File(Defaults.DEPENDENCIESDIR);
        testDir.mkdirs();

        FileWriter fw= new FileWriter("Build.java");
        fw.write("import com.uraimo.javabuild.*\n\n" +
                "public class Build {\n" +
                "    public SrcModule origin = new SrcModule(\n" +
                "                        \"MyProject\",\n" +
                "                        \"0.0.1\",\n" +
                "                        new Module[]{\n" +
                "                        });\n"+
                "}\n");
        fw.close();
    }

}
