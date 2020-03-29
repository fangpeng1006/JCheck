package com.jcheck.net.tcp.simple;

import sun.reflect.generics.scope.Scope;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * TCP 客户端示例
 */
public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket s=new Socket("127.0.0.1",10000);
        OutputStream os = s.getOutputStream();
        os.write("hello tcp 你好".getBytes());
        s.close();
    }
}
