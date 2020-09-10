package com.homework.Feed.Training.filemover;

import com.homework.Feed.Training.filereader.FileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Component
public class FileMover {
    @Autowired
    FileReader fileReader;

    @Value("${input.file.folder}")
    private Path associateFolder;

    @Value("${processing.file.folder}")
    private Path associateProcessingFolder;


    public void folderOpener() {
//        try {
//            Long countFiles = Files.walk(associateFolder).count();
//            if(countFiles <= 1) {
//                log.debug("No files in folder");
//            } else {
//                fileMover();
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
        fileReader.fileReader();
    }

    public void fileMover() {
        try {
            Files.createDirectories(associateProcessingFolder);
            Files.walk(associateFolder).filter(f -> f.toString().endsWith(".csv"))
                    .forEach(f -> {
                        Path destination = associateProcessingFolder.resolve(associateFolder.relativize(f));

                        try {
                            Files.createDirectories(destination.getParent());
                            Files.move(f, destination);
                            fileReader.fileReader();
                        } catch (IOException i) {
                            i.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
