package com;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
/*
 * ������������
 * */
public class Server extends Thread{
	 private  static ServerSocket serverSock;
    static boolean connect=false;
    private static Socket socket=null;
    DataOutputStream out;
    DataInputStream in;
    public boolean setSrever(){
      	try{
      		serverSock=new ServerSocket(6666);
      	}catch(IOException e){
      		JOptionPane.showMessageDialog(null, "����������ʧ��");
      		return false;
      		}
      	JOptionPane.showMessageDialog(null, "�ɹ���������\n�ȴ����Ѽ��룡");
      	this.start();
      	return true;
      	}
    public void run(){
    	while(true){
    		try{
    			socket=serverSock.accept();
		}catch(Exception ee){
			
		};
			if(socket!=null){
				JOptionPane.showMessageDialog(null, "���ѳɹ����룡");
				break;
			}
    	}
    }
    void setChess(int i,int j)
    {
    	try{
    		OutputStream os=socket.getOutputStream();
			out=new DataOutputStream(os);
			String s="";
			s+=i;
    	out.writeUTF(s);
    	s="";
    	s+=j;
    	out.writeUTF(s);
    	}
    	catch(IOException ee){
			return ;
		}
    	return;
    }
    int getChess(){     
    	int a=0;
    	try{
    		    String s;
    			InputStream is=socket.getInputStream();
    			in=new DataInputStream(is);
	    	s=in.readUTF();
	    	if(s.length()>2)
	    		a=getChess();  
	    	else a=Integer.parseInt(s);
    	}
	    	catch(IOException ee){	
	    		JOptionPane.showMessageDialog(null,"�������뿪\n��Ű�ѵ��԰�");
	    		return -1;
    		}
    	return a;
    }
}
