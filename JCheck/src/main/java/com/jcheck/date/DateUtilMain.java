package com.jcheck.date;

import java.text.ParseException;
import java.util.Date;

public class DateUtilMain {
    public static void main(String[] args) throws ParseException {
        String format = "yyyy-MM-dd HH:mm:ss";
        Date d = new Date();

        System.out.println(DateUtil.dateToString(d, format));

        String dateString = "2019-01-01 01:01:01";
        System.out.println(DateUtil.stringToDate(dateString, format));
    }
}
