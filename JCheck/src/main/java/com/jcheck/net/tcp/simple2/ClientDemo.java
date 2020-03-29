package com.jcheck.net.tcp.simple2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket s=new Socket("127.0.0.1",10000);

        OutputStream os = s.getOutputStream();
        os.write("hello".getBytes());

        InputStream is = s.getInputStream();
        byte[] data =new byte[1024];
        int len=is.read(data);
        System.out.println("客户端收到信息："+new String(data,0,len));
    }
}
