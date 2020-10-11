package com.dl.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {
    //定义属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final  int PORT=6667;

    //构造
    public GroupChatServer(){
        try {
            //得到选择器
            selector=Selector.open();
            //ServerSocketChannel
            listenChannel=ServerSocketChannel.open();
            //绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置非阻塞模型
            listenChannel.configureBlocking(false);
            //将该listenChannel注册到selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //监听
    public void listen(){
        try {

            while (true){
                int count  =selector.select();
                if (count>0){//有事件处理
                    Iterator<SelectionKey> iterator=selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        //取出selectionkey
                        SelectionKey key=iterator.next();

                        //监听accept
                        if (key.isAcceptable()){
                            SocketChannel sc=listenChannel.accept();
                            sc.configureBlocking(false);
                            //将sc注册到selector
                            sc.register(selector,SelectionKey.OP_READ);
                            //提示
                            System.out.println(sc.getRemoteAddress()+"上线");
                        }
                        if (key.isReadable()){//通道发送read事件，即通道是可读的状态
                            readData(key);
                        }
                        //当前key删除，防止重复处理
                        iterator.remove();
                    }
                }else {
                    System.out.println("等待。。。。。");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    private void readData(SelectionKey key) {
        //取到关联的channel
        SocketChannel channel=null;
        try {
            channel= (SocketChannel) key.channel();
            //创建buffer
            ByteBuffer buffer=ByteBuffer.allocate(1024);
            int count=channel.read(buffer);
            if (count>0){
                //把缓存区的数据转成字符串
                String msg=new String(buffer.array());
                //输出该消息
                System.out.println("from 客户端"+msg);
                //向其他客户端转发消息（去掉自己），专门写一个方法处理
                sendInfoOtherClients(msg,channel);
            }
        }catch (IOException e){
            try {
                System.out.println(channel.getRemoteAddress()+"离线了");
                //取消注册
                key.cancel();
                channel.close();
            }catch (IOException e2){
                e2.printStackTrace();
            }
        }
    }

    //转发消息给其他客户端（通道）
    private void sendInfoOtherClients(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器、转发消息中。。。。");
        //遍历所有注册到selector上的SocketChannel 并排除self自己
        for (SelectionKey key:selector.keys()){
            //通过key取出对应的SocketChannel
            Channel tartgetChannel =key.channel();
            //排除自己
            if (tartgetChannel instanceof SocketChannel && tartgetChannel!=self){
                //转型
                SocketChannel dest= (SocketChannel) tartgetChannel;
                //将msg存储到buffer
                ByteBuffer buffer=ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        //创建服务器对象
        GroupChatServer groupChatServer=new GroupChatServer();
        groupChatServer.listen();
    }
}
