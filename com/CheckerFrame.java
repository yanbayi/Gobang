package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * ������壬ѡ����
 * */
public class CheckerFrame extends JFrame {
	private static int qipann=0;
	private static int qizin=0;
	private static final long serialVersionUID = 4802277944291967336L;
	CheckerBoard p = null;//������
	Label L1;
	JButton L2;
	JButton L3;
	TextField text1;
	Button B1;
	  
	public CheckerFrame() {
		this.setSize(750, 600);
		ImageIcon background = new ImageIcon("img/bound.jpg");  //����ͼƬ
		JLabel label = new JLabel(background);  
		// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
		label.setBounds(0, 0, this.getWidth(), this.getHeight());  
		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
		JPanel imagePanel = (JPanel) this.getContentPane();  
		imagePanel.setOpaque(false);  
		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  

		p = new CheckerBoard();
		p.setBounds(0, 0, 576,576);
		this.setLocation(200, 100);	
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.add(p);
    	  
    	JMenuBar menuber = new JMenuBar();
    		JMenu jm1 = new JMenu("ѡ��");
    		JMenu jm2 = new JMenu("����");
    		JMenu jm3 = new JMenu("����");
    		JMenuItem jm1_1 = new JMenuItem("���¿�ʼ");
    		JMenuItem jm1_3 = new JMenuItem("�������");
    		JMenuItem jm1_4 = new JMenuItem("��Һ���");
    		JMenuItem jm1_2 = new JMenuItem("�˳���Ϸ");
    		JMenuItem jm2_1 = new JMenuItem("��������");
    		JMenuItem jm2_2 = new JMenuItem("��������");
    		JMenuItem jm2_3 = new JMenuItem("���˻�");
    		JMenuItem jm2_4 = new JMenuItem("�����˻�");
    		JMenuItem jm2_6 = new JMenuItem("�е��˻�");
    		JMenuItem jm2_5 = new JMenuItem("��������");
    		JMenuItem jm3_1 = new JMenuItem("��������");
    		
		jm1.add(jm1_1);
		jm1.add(jm1_2);
		jm1.add(jm1_3);
		jm1.add(jm1_4);
		jm2.add(jm2_1);
		jm2.add(jm2_2);
		jm2.add(jm2_3);
		jm2.add(jm2_6);
		jm2.add(jm2_4);
		jm2.add(jm2_5);
		jm3.add(jm3_1);
		menuber.add(jm1);
		menuber.add(jm2);
		menuber.add(jm3);
		
		this.setJMenuBar(menuber);
		this.addMouseListener(p);
		// �����鹹�ཨ������// �����鹹��      ѡ��˳�
		jm1_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		//ѡ��  ���¿�ʼ
		jm1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				init();
				p.canSetChess = 1;
				if(p.Inter==1&&p.serve==0)p.canSetChess = 0;
				p.step=0;
				repaint();
			}
		});
		//ѡ��  �������
		jm1_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				init();
				p.canSetChess = 1;
				p.step=0;
				p.Inter=0;
				repaint();
			}
		});
		//ѡ��    ��Һ���
		jm1_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				init();
				p.num[7][7]=2;
				p.canSetChess = 1;
				p.Inter=0;
				repaint();
			}
		});
		//����      ��������
		jm2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                  qipann++;
				String qipan_name = "qipan" + qipann%3 + ".jpg";
				p.qipan_name = qipan_name;
				repaint();
			}
		});
		//����   ��������
		jm2_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				qizin++;
				String qizi1_name = "c" + qizin%5 + ".png";
				String qizi2_name = "c" + (qizin + 1)%5 + ".png";
				p.qizi1_name = qizi1_name;
				p.qizi2_name = qizi2_name;
				repaint();
			}
		});
		//����  ���˻�
		jm2_3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				init();
				Computer com=new Computer();
				p.peo_peo=false;
				p.canSetChess = 1;
				p.step=0;
				com.easy();
				p.Inter=0;
				repaint();
			}
			
		});
		//����   �����˻�
		jm2_4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				init();
				Computer com=new Computer();
				p.peo_peo=false;
				p.canSetChess = 1;
				p.step=0;
				com.trouble();
				p.Inter=0;
				repaint();
			}
			
		});
		//����  �е��˻�
		jm2_6.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				init();
				Computer com=new Computer();
				p.peo_peo=false;
				p.canSetChess = 1;
				p.step=0;
				com.trouble1();
				p.Inter=0;
				repaint();
			}
			
		});
		//����     ��������
		jm2_5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				init();
			p.canSetChess = 1;
			p.peo_peo=true;
			p.count=0;
			p.Inter=0;
			repaint();
			}
		});
		//��������
		jm3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = "��������\n" + "1�����������;\n" + "2���γ�5��ͬɫ���Ӽ�ΪӮ;\n\n\n";
				JOptionPane.showMessageDialog(null, msg);

			}
		});
		//L2��ť����ť3
		this.setIconImage(Toolkit.getDefaultToolkit().createImage("img/c5.png"));
		this.setVisible(true);
	}
	//��ʼ������
	private void init() {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < p.row; i++) {
			for (int j = 0; j < p.col; j++) {
				p.num[i][j] = 0;
			}
		}
	}
}

