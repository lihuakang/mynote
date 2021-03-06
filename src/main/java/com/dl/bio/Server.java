package com.dl.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket();
        ss.bind(new InetSocketAddress("127.0.0.1",8888));
        while (true){
            Socket s=ss.accept();//阻塞
            //每个连接起一个线程
            new Thread(()->{
                handle(s);
            }).start();
        }
    }

    private static void handle(Socket s) {
        //单向的
        try {
            byte[] bytes=new byte[1024];
            int len=s.getInputStream().read(bytes);//阻塞
            System.out.println(new String(bytes,0,len));

            s.getOutputStream().write(bytes,0,len);//阻塞
            s.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
