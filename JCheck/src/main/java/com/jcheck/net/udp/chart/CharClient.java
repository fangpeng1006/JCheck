package com.jcheck.net.udp.chart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 聊天客户端
 */
public class CharClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket=new DatagramSocket();

        InetAddress ip = InetAddress.getByName("127.0.0.1");
        //从命令行接受需要发送的字符串
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String data;

        while ((data=br.readLine())!=null){
            if(data.equals("bye")){
                break;
            }
            DatagramPacket dp=new DatagramPacket(data.getBytes(),data.getBytes().length,ip,10000);
            socket.send(dp);
        }
        br.close();
        socket.close();
    }
}
