package com.uraimo.javabuild.commands;

import com.uraimo.javabuild.Defaults;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.Paths.*;

/**
 * Command that cleans the project removing target artifacts and dependencies
 *
 * Created by Umberto Raimondi on 16/02/16.
 */
public class Clean implements Command{

    /**
     * Clean the target and dependencies directory
     *
     * @param parameters
     * @throws IOException
     */
    @Override
    public void execute(String... parameters) throws IOException{
        SimpleFileVisitor<Path> deleteAll = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }

        };

        Path directory = get(Defaults.TARGETDIR);
        Files.walkFileTree(directory,deleteAll);

        directory = get(Defaults.DEPENDENCIESDIR);
        Files.walkFileTree(directory,deleteAll);
    }
}

