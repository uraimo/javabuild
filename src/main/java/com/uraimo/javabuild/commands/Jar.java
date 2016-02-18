package com.uraimo.javabuild.commands;

import com.uraimo.javabuild.Defaults;
import com.uraimo.javabuild.commands.util.ManifestBuilder;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import static java.nio.file.Paths.get;

/**
 * Jar command, it creates a jar file with the .class files in the target directory
 *
 * Created by Umberto Raimondi on 18/02/16.
 */
public class Jar implements Command {

    @Override
    public void execute(String... parameters) throws IOException {
        ArrayList<File> files=new ArrayList<>();

        // Collect all the java files
        Path directory = get(Defaults.TARGETJAVADIR);
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".class"))
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

        File output=new File(Defaults.TARGETDIR+"/test.jar");

        ManifestBuilder mb = new ManifestBuilder();

        createJarArchive(output,mb.getManifest(),files.toArray(new File[0]));
    }

    protected void createJarArchive(File archiveFile, Manifest man, File[] tobeJared) throws IOException {
        byte buffer[] = new byte[8192];
        // Open archive file
        FileOutputStream stream = new FileOutputStream(archiveFile);
        JarOutputStream out = new JarOutputStream(stream, man);

        for (int i = 0; i < tobeJared.length; i++) {
            JarEntry jarAdd = new JarEntry(tobeJared[i].getName());
            jarAdd.setTime(tobeJared[i].lastModified());
            out.putNextEntry(jarAdd);

            FileInputStream in = new FileInputStream(tobeJared[i]);
            while (true) {
                int nRead = in.read(buffer, 0, buffer.length);
                if (nRead <= 0)
                    break;
                out.write(buffer, 0, nRead);
            }
            in.close();
        }

        out.close();
        stream.close();
    }
}
