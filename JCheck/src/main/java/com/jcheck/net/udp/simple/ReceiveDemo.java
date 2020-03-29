package com.jcheck.net.udp.simple;
/**
 * UDP接受数据包
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveDemo {
    public static void main(String[] args) throws IOException {
        //创建接受socket
        DatagramSocket socket=new DatagramSocket(10000);

        byte[] data=new byte[1024];

        //创建接受的数据包
        DatagramPacket dp=new DatagramPacket(data,data.length);
        //接受数据
        socket.receive(dp);
        //打印数据
        System.out.println(new String(dp.getData(),0,dp.getLength()));
    }
}
