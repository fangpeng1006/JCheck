package com.jcheck.io;

import java.io.*;

public class BufferedBytesRW {
    public static void main(String[] args) throws IOException {
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
