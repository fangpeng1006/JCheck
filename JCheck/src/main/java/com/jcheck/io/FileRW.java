package com.jcheck.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileRW {
    public static void main(String[] args) throws IOException {
        File file = new File("test.txt");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write("你好".getBytes());
        fos.close();

    }
}
