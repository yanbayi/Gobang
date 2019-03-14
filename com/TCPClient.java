package com;

import java.net.*;
/**
 * 客户端
 */
public class TCPClient{

    static Socket s;
    setMenu t;
    public TCPClient(){
    	 //连接服务器
        connetServer();
        //设计GUI界面
       t=new setMenu(s);
        //t.setBounds(0,0,200,200);
        //add(t);
        //启动接收数据线程
        new Thread(new recvMsg(s)).start();
    }
    /**
     * 连接服务器
     */
    private static void connetServer() {
        //创建客服端的Socket服务，指定目的主机和端口
        try {
            s = new Socket("127.0.0.1",30001);
        } catch (Exception e) {
            System.out.println("客服端连接服务器失败");
            e.printStackTrace();
        }               
    }
}