package com.homework.Feed.Training.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@NoArgsConstructor
public class Associates {
    String employeeNbr;
    String effectiveDt;
    String workCtry;
    String firstNm;
    String lastNm;
    String middleNm;
}
