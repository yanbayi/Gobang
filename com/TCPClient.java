package com;

import java.net.*;
/**
 * �ͻ���
 */
public class TCPClient{

    static Socket s;
    setMenu t;
    public TCPClient(){
    	 //���ӷ�����
        connetServer();
        //���GUI����
       t=new setMenu(s);
        //t.setBounds(0,0,200,200);
        //add(t);
        //�������������߳�
        new Thread(new recvMsg(s)).start();
    }
    /**
     * ���ӷ�����
     */
    private static void connetServer() {
        //�����ͷ��˵�Socket����ָ��Ŀ�������Ͷ˿�
        try {
            s = new Socket("127.0.0.1",30001);
        } catch (Exception e) {
            System.out.println("�ͷ������ӷ�����ʧ��");
            e.printStackTrace();
        }               
    }
}