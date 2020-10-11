package com.dl.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        //创建ServerSocketChannel ->ServerSocket
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        //得到一个selector对象
        Selector selector=Selector.open();
        //绑定一个端口6666，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //把serverSocketChannel 注册到selector关心事件 为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环等待客户端连接
        while (true){
            //我们在这里等待1秒，没有事件发生返回
            if(selector.select(1000)==0){
                System.out.println("服务器等待1秒，无连接");
                continue;
            }
            Set<SelectionKey> selectionKeys=selector.selectedKeys();
            Iterator<SelectionKey> keyIterator=selectionKeys.iterator();

            while (keyIterator.hasNext()){
                //获取selectionKey
                SelectionKey key=keyIterator.next();
                //根据key对应的通道发生的事件做相应处理
                if (key.isAcceptable()){ //如果是OP_ACCEPT 有新的客户端连接
                    //该客户端生成一个SocketChannel
                    SocketChannel socketChannel=serverSocketChannel.accept();
                    System.out.println("客户端连接成功 ， 生成一个socketChannel"+socketChannel.hashCode());
                    //将SocketChannel设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector，关注事件为OP_READ ，同时给socketChannel关联一个buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()){ //发生OP_READ
                    //通过key反向获得Channel
                    SocketChannel channel= (SocketChannel) key.channel();
                    //获取该channel关联的Buffer
                    ByteBuffer buffer= (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端"+new String(buffer.array()));
                }

                //手动从集合中移除当前的selectionKey，防止重复操作
                keyIterator.remove();
            }
        }
    }
}
