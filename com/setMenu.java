package com;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
/**
* ����GUI�������  ����Ϣ��
*/
class setMenu extends JPanel{
    static JButton btn_send;
    static JButton btn_clear;
    static JButton btn_close;
    static JTextField txf_name;
    static JTextField txf_send;
    static JTextArea messageArea;
    Socket s;
    setMenu(Socket s){
        this.s = s;
        createMenu();
        someListener();
    }

    /**
     * ����¼���������
     **/
    private void someListener() {
        btn_send.addActionListener(new SendMsg3(s));
        btn_close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        txf_send.addKeyListener(new SendMsg3(s));
        btn_clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txf_send.setText("");
            }
        });
        messageArea.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                e.consume();// ���ε�������
            }
        });
    }

    private void createMenu() {
     this.setLayout(null);
    JLabel label_name = new JLabel("���֣�");
    label_name.setBounds(0,0, 50, 20);
    this.add(label_name);
    txf_name = new JTextField(10);
    txf_name.setBounds(50,0,80,20);
    this.add(txf_name);
    messageArea = new JTextArea();
    messageArea.setBounds(0,20,150, 120);
    this.add(messageArea);
    JLabel label_send = new JLabel("�������ݣ�");
    label_send.setBounds(0, 150, 70 , 20);
    this.add(label_send);
    txf_send = new JTextField(30);
    txf_send.setBounds(70, 150, 80, 20);
    this.add(txf_send);
    btn_send = new JButton("����");
    btn_clear = new JButton("���");
    btn_close = new JButton("�ر�");
    }
    
    
    class SendMsg3 implements ActionListener, KeyListener {

        Socket s;
        SendMsg3(Socket s){
            this.s =  s;
        }
        @Override
        public void keyTyped(KeyEvent e) {
        }
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == e.VK_ENTER) {
                sendMsg();
            }
        }
        public void keyReleased(KeyEvent e) {
        }
        public void actionPerformed(ActionEvent e) {
            sendMsg();

        }


        /**
         * ������Ϣ
         */
        private void sendMsg() {
            // �������Ϊ�� ������ʾ��Ϣ
            if(txf_name.getText().isEmpty()){
                new JOptionPane().showMessageDialog(null, "��һ��Ҫȡ������");
            }
            String line = txf_name.getText() + ": "+txf_send.getText();
            try {
                if (!line.isEmpty()) {
                    //����PrintWriter�ǿ����Դ�ˢ�»��湤�������Լ�д�����Ĳ�����
                    PrintWriter pout = new PrintWriter(s.getOutputStream(),true);
                    pout.println(line);
                    System.out.print("input:"+line);
                    txf_send.setText("");
                } else {
                    System.out.println("�������ݲ�Ϊ�գ�����������");
                    new JOptionPane().showMessageDialog(null, "�������ݲ�Ϊ�գ����������룡");
                }
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                System.out.println("����ʧ��");
                e1.printStackTrace();
            }
        }
    }
}