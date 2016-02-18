package com.uraimo.javabuild.commands;

import com.uraimo.javabuild.Defaults;

import javax.tools.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.Paths.get;

/**
 * Compile command, compiles everything in the source directory and outputs
 * the result to the target directory
 *
 * Created by Umberto Raimondi on 17/02/16.
 */
public class Compile implements Command{

    @Override
    public void execute(String... parameters) throws IOException {
        ArrayList<File> files=new ArrayList<>();

        // Collect all the java files
        Path directory = get(Defaults.SRCDIR);
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".java"))
                    files.add(new File(file.toString()));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

        });

        if(files.size()==0)
            return;

        // Create the target directory
        File targetdir=new File(Defaults.TARGETJAVADIR);
        targetdir.mkdirs();

        // Specify the target directory as base output directory
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        List<String> options = Arrays.asList( new String[] { "-d", Defaults.TARGETJAVADIR} );

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> compilationUnits =
                fileManager.getJavaFileObjectsFromFiles(files);

        compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits).call();

        for (Diagnostic diagnostic : diagnostics.getDiagnostics())
            System.out.println(stringifyDiagnostic(diagnostic));

        fileManager.close();
    }


    private String stringifyDiagnostic(Diagnostic diag){
        String res="";

        res += stringifyKind(diag.getKind())+" (Line "+diag.getLineNumber()+"): "+diag.getMessage(null)+"\n";

        if(diag.getStartPosition()>0) {
            File source = new File(((JavaFileObject) diag.getSource()).toUri());
            try {
                FileReader fr = new FileReader(source);
                BufferedReader bfr = new BufferedReader(fr);
                for(int i=0;i<diag.getLineNumber()-1;i++){
                    bfr.readLine();
                }
                String errorLine = bfr.readLine();

                res+=errorLine+"\n";
                res+=generateMarker((int) diag.getColumnNumber())+"\n";
            } catch (IOException ex) {
                ex.printStackTrace();
                //NOP
            }
        }

        return res;
    }

    private String stringifyKind(Diagnostic.Kind kind){
        switch(kind){
            case ERROR:
                return "Error";
            case WARNING:
            case MANDATORY_WARNING:
                return "Warning";
            case NOTE:
                return "Info";
            default:
                return "Info";
        }
    }


    private String generateMarker(int pos){
        String res="";
        for(int i=0;i<pos;i++){
            res+=" ";
        }
        res+="^";
        return res;
    }
}
