package com.project.backend.general;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class AddDateUtils {

    public static LocalDate dateWithoutTime(Date date) {
        System.out.println("year, month, date"+date.getYear() + date.getMonth()+ date.getDate());
        LocalDate dateWithoutTime = LocalDate.of(date.getYear()+1900, date.getMonth()+1, date.getDate());
        return dateWithoutTime;
    }

}
