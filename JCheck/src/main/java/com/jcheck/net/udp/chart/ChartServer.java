package com.jcheck.net.udp.chart;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 聊天室服务端
 */
public class ChartServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket=new DatagramSocket(10000);

        while (true){
            byte[] data=new byte[1024];
            DatagramPacket dp=new DatagramPacket(data,data.length);
            socket.receive(dp);
            System.out.println("接收到数据："+new String(dp.getData(),0,dp.getLength()));
        }
    }
}
