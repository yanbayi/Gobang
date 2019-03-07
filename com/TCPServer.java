package com;
     /*������*/
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class TCPServer {
             //����һ�����ϣ����ϴ�ſͷ��˵Ķ���
    ArrayList<Socket> arrayListSocket = new ArrayList<Socket>();
    TCPServer() {
        System.out.println("------����---------");
        ServerSocket ss;
        try {
            ss = new ServerSocket(30001);
            while (true) {                                                   
                                //�ȴ��ͷ������ӣ�һ���пͷ������ӷ����������ͷ��˶����ŵ�������
                Socket socket = ss.accept();
                arrayListSocket.add(socket);
                String ip = socket.getInetAddress().getHostAddress();
                System.out.println("ip: " + ip + " �����ˣ�");
                new Thread(new acceptClient(socket, ip)).start();   //�½�һ���ͷ��˵��߳�
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class acceptClient implements Runnable {
        Socket s;
        String ip;
        acceptClient(Socket s, String ip) {
            this.s = s;
            this.ip = ip;
        }
        @Override
        public void run() {
            try {
                // ֪ͨ�û�������Ϣ
                for (int i = 0; i < arrayListSocket.size(); i++) {
                    Socket client = arrayListSocket.get(i);
                    // ����PrintWriter�ǿ����Դ�ˢ�»��湤�������Լ�д�����Ĳ�����
                    PrintWriter pout = new PrintWriter(
                                 client.getOutputStream(), true);
                    pout.println(ip + "�����ˣ�����");
                }
                while (true) {
                    // ��ȡSocket��ȡ���е�����
                    BufferedReader bufIn = new BufferedReader(
                            new InputStreamReader(s.getInputStream()));
                    String line = bufIn.readLine();
                    // ����PrintWriter�ǿ����Դ�ˢ�»��湤�������Լ�д�����Ĳ�����
                    //ͨ��for����ѭ������������ݷ��͵�arrayListSocket���������пͷ���
                    for (int i = 0; i < arrayListSocket.size(); i++) {
                        Socket client = arrayListSocket.get(i);
                        PrintWriter pout = new PrintWriter(
                                client.getOutputStream(), true);
                        pout.println(line);
                    }
                }

            } catch (IOException e) {
                System.out.println("����:" + s.getInetAddress() + " ��Ϣʧ��");
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) throws Exception, IOException {
        //��������������
        new TCPServer();
    }
}