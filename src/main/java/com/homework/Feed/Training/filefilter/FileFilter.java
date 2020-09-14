package com.homework.Feed.Training.filefilter;

import com.homework.Feed.Training.pojo.Associates;
import com.homework.Feed.Training.readfile.ReadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Component
public class FileFilter {
    @Autowired
    ReadFile readFile;

    public void fileFilter(List<Associates> list) {
// THIS WILL GO THROUGH EACH ASSOCIATE'S ARRAY AND CHECK FOR ERRORS. NEED TO DECIDE WHAT TO DO AFTER ERROR REPORTED.

//        list.forEach(a ->
//                {
//                    if (a.getEmployeeNbr().length() != 9) {
//                        log.error("Associate ID is in wrong format." + a.getEmployeeNbr());
//                        return;
//                    }
//                    if (a.getEffectiveDt()...){
//                    log.error("This error...");
//                    return;
//                }
//                    if (a.getWorkCtry() ...){
//                    log.error("This error");
//                    return;
//                }
//                    if (a.getFirstNm()...){
//                    log.error("This error");
//                    return;
//                }
//                    if (a.getLastNm()...){
//                    log.error("This error");
//                    return;
//                }
//                    if (a.getMiddleNm()...){
//                    log.error("This error");
//                    return;
//                }
//                }
//        );
    }
}
