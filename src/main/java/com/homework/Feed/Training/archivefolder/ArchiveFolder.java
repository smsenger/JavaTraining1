package com.homework.Feed.Training.archivefolder;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


@Slf4j
@Component
public class ArchiveFolder {

    @Value("${processing.file.folder}")
    private Path associateProcessingFolder;

    @Value("${archive.file.folder}")
    private Path archiveFolder;


    public void openProcessing() {
        try {
            Long countFiles = Files.walk(associateProcessingFolder).count();
            if(countFiles <= 1) {
                log.debug("No files in folder");
            } else {
                archiveMove();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void archiveMove() {
        try {
            Files.createDirectories(archiveFolder);
            Files.walk(associateProcessingFolder).filter(f -> f.toString().endsWith(".csv"))
                    .forEach(f -> {
                        Path destination = archiveFolder.resolve(associateProcessingFolder.relativize(f));

                        try {
                            Files.createDirectories(destination.getParent());
                            Files.move(f, destination);
                            log.debug("File moved to archive folder");
                        } catch (IOException i) {
                            i.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
