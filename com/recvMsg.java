package com;

import javax.swing.*;
import java.io.*;
import java.net.*;
//������Ϣ������    Runnable�ӿ�
class recvMsg implements Runnable{
    private Socket s;
    private JTextArea messageArea;
    recvMsg(Socket s){
        this.s = s;
        this.messageArea = setMenu.messageArea;
    }
    @Override
    public void run() {
        //����һ��Socket��ȡ������ȡ�������˷��ص���Ϣ
                BufferedReader bufIn = null;
                System.out.println("-------rev----------");
                while(true){
                    try {
                        bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    } catch (IOException e1) {
                        System.out.println("������Ϣʧ��");
                        e1.printStackTrace();
                    }
                    try {
                        String recvMsg = bufIn.readLine();
                        messageArea.append(recvMsg+"\r\n");
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
    }
}
