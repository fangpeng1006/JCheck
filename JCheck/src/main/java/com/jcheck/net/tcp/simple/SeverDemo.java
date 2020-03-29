package com.jcheck.net.tcp.simple;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP服务端实例
 */
public class SeverDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss =new ServerSocket(10000);

        //接受客户端的连接
        Socket s = ss.accept();
        InputStream is = s.getInputStream();

        byte[] data = new byte[1024];

        int len=is.read(data);

        System.out.println(new String(data,0,len));
        ss.close();
    }
}
