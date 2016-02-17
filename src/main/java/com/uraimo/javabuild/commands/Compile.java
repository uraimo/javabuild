package com.uraimo.javabuild.commands;

import com.uraimo.javabuild.Defaults;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.Paths.get;

/**
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
            System.out.format("Error on line %d in %s",
                    diagnostic.getLineNumber(),
                    diagnostic.getSource().toString());

        fileManager.close();

    }
}
