package com.jcheck.net.udp.simple;

import java.io.IOException;
import java.net.*;

/**
 *
 * 发送UDP数据报包
 */
public class SendDemo {

    public static void main(String[] args) throws IOException {
        //创建udp Socket
        DatagramSocket socket=new DatagramSocket();

        //构建数据包
        byte[] data="hello udp 你好".getBytes();
        //IP地址对象
        InetAddress ip=InetAddress.getByName("127.0.0.1");
        //数据报包对象
        DatagramPacket dp=new DatagramPacket(data,data.length,ip,10000);
        socket.send(dp);

        //关闭连接
        socket.close();
    }
}
