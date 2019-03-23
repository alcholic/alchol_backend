package com.hirim.sulgijang.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    /**
     * String 타입을 지정된 포맷의 LocalDate 타입으로 반환
     * @param date
     * @param date format
     * @return LocalDate
     * @exception Exception
     * */
    public LocalDate stringToLocalDate(String date, String format) throws Exception{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDate.parse(date, formatter);
    }
}
