package com.homework.Feed.Training.filereader;

import com.homework.Feed.Training.pojo.Associates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FileReader {
    @Value("${processing.file.folder}")
    private Path processingFolder;

    public void fileReader() {

        try {

            //For each file in folder, instantiate these variables
            Files.list(processingFolder).forEach(f -> {
                String fileName;
                BufferedReader br = null;
                List<String[]> associatesList= null;
                List<Associates> associatesPojo = null;
                InputStream stream;

                //1.get file name, 2.get file path, read / or \, get fileName, 3.read the stream with the br,
                //4. assign to associatesList: read lines, skip header, delimit with | character, put in list
                try {
                    fileName = f.getFileName().toString();
                    stream = new FileInputStream(processingFolder + File.separator + fileName);
                    br = new BufferedReader(new InputStreamReader(stream));
                    associatesList = br.lines().skip(1).map(s -> s.split(Pattern.quote("|"))).collect(Collectors.toList());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        //close br
                        br.close();

                        //stream associatesList, for each line print out to console
                        associatesList.stream().forEach(a -> System.out.println(Arrays.toString(a)));
                        //associatesList.stream().forEach(a ->);
                        System.out.println(associatesList.toString());
                        //associatesList.forEach(a -> log.info(a));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
