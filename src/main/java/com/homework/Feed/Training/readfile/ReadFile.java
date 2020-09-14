package com.homework.Feed.Training.readfile;

import com.homework.Feed.Training.archivefolder.ArchiveFolder;
import com.homework.Feed.Training.pojo.Associates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReadFile {

    @Autowired
    ArchiveFolder openProcessing;

    @Value("${processing.file.folder}")
    private Path processingFolder;

    @Value("${file.name.prefix}")
    private String prefix;

    public void readFile() {

        //this version uses openCsv
//        List<Associates> list = new ArrayList<>();
//        FileReader reader = null;

//        try {
//            reader =new FileReader(processingFolder +  prefix);
//            list = new CsvToBeanBuilder(reader).withSeparator("|").withType(Associates.class).build().parse();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        list.forEach(a -> {
//
//        });
//    }


        //this is the longhand version
        try {
            //For each file in folder, instantiate these variables
            Files.list(processingFolder).forEach(f -> {
                String fileName;
                BufferedReader br = null;
                List<String[]> associatesList = null;
                List<Associates> associatesPojo = new ArrayList<>();
                InputStream stream;
                Associates assoc;

                //1.get file name, 2.get file path, read / or \, get fileName, 3.read the stream with the br,
                //4. assign to associatesList: read lines, skip header, delimit with | character, put in list
                try {
                    fileName = f.getFileName().toString();
                    stream = new FileInputStream(processingFolder + File.separator + fileName);
                    br = new BufferedReader(new InputStreamReader(stream));
                    associatesList = br.lines().skip(1).map(s -> s.split(Pattern.quote("|"))).collect(Collectors.toList());

                    //stream associatesList, for each line print out to console
                    associatesList.stream().forEach(a -> System.out.println(Arrays.toString(a)));
                    //System.out.println(associatesList.toString());
                    //associatesList.forEach(a -> log.info(a));


                    for (String[] associate : associatesList) {
                        assoc = new Associates();
                        assoc.setEmployeeNbr(associate[0]);
                        assoc.setEffectiveDt(associate[1]);
                        assoc.setWorkCtry(associate[2]);
                        assoc.setFirstNm(associate[3]);
                        assoc.setLastNm(associate[4]);
                        assoc.setMiddleNm(associate[5]);

                        associatesPojo.add(assoc);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    //closes br, also closes port to make it available for later program runs
                    //calls the method that checks for files in processing and calls methods that moves files to archive
                    try {
                        br.close();
                        openProcessing.openProcessing();
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

