package com.jcheck.main;

import com.jcheck.tools.ArraySort;
import com.jcheck.tools.DateUtil;

import java.io.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

public class Starter {
    public static void main(String[] args) throws ParseException, IOException {
        int[] array = {22, 13, 56, 98, 45};
        System.out.println("排序之前：" + Arrays.toString(array));
        ArraySort.sort(array);
        System.out.println("排序之后：" + Arrays.toString(array));

        String format = "yyyy-MM-dd HH:mm:ss";
        Date d = new Date();

        System.out.println(DateUtil.dateToString(d, format));

        String dateString = "2019-01-01 01:01:01";
        System.out.println(DateUtil.stringToDate(dateString, format));

        File file = new File("test.txt");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write("你好".getBytes());
        fos.close();

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("C:\\Users\\fangpeng\\Downloads\\day9\\01_字节缓冲流.mp4"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("ddd.mp4"));

        int by;
        byte[] bys = new byte[1024];
        while ((by = bis.read(bys)) != -1) {
            bos.write(bys);
        }
        bis.close();
        bos.close();

    }
}
