package com.jcheck.net.tcp.simple2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);

        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        byte[] data = new byte[1024];
        int len = is.read(data);
        System.out.println("服务端收到信息："+ new String(data,0,len));

        OutputStream os = socket.getOutputStream();
        os.write("已收到信息".getBytes());
        ss.close();
    }
}
