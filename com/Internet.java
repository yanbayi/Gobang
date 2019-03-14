package com;
import java.io.*;
import java.net.*;
import javax.swing.*;
/*
 * ����  ����
 * */
public class Internet {
	    static   Socket sock;
	    static boolean connect=false;
	    DataOutputStream out;
	    DataInputStream in;
	    public boolean send(String s){    
	    		try{
	    			sock=new Socket(s,6666);
	    			OutputStream os=sock.getOutputStream();
	    			out=new DataOutputStream(os);
	    			InputStream is=sock.getInputStream();
	    			in=new DataInputStream(is);
	    			out.writeUTF(s);
	    			Thread th=new Thread();
	    			 th.start();
	    		}catch(IOException ee){
	    			return false;
	    		}
	    		connect=true;
	    		return true;
	    }
	   
	    void setChess(int i,int j){
	    	String s="";
	    	try{
	    		OutputStream os=sock.getOutputStream();
    			out=new DataOutputStream(os);  
    			s+=i;
	    	out.writeUTF(s);
	    	s="";
	    	s+=j;
	    	out.writeUTF(s);
	    	}
	    	catch(IOException ee){
    			return ;
    		}
	    }
	    int getChess(){
	    	int a=0;
	    	try{
	    		String s;
	    			InputStream is=sock.getInputStream();
	    			in=new DataInputStream(is);
		    	s=in.readUTF();
		    	a=Integer.parseInt(s);
		    	return a;
	    	}
		    	catch(IOException ee){
		    		JOptionPane.showMessageDialog(null,"�������뿪\nŰ�ѵ���������");
		    		return -1;
	    		}		    	
	    }
}

