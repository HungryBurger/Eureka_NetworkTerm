package Design;
import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.*;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
public class member extends JFrame{
	// Ŭ���� ������ ���� ����������� �����ϴ� �κ��Դϴ�..
	Panel p1, p2, p3,p4, p9, p10, pt,tp1,tp2;
	Label la_name, la_id, la_pass,  la_addr,la_age,la_title;
	TextField tf_name, tf_id, tf_pass,tf_age;
	Button btn1, btn2, btn3;
	Choice job;
	Checkbox cb1, cb2;
	CheckboxGroup cbg;
	TextArea ta;
	private Label label;
	
	public static void main(String[] args) throws Exception {
		Login client = new Login();
		client.setVisible(true);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	// ������ ���� �κ��� �ǰڽ��ϴ�.
	member()  {
		// ȭ���ġ�� �����ϰ� �Ǵ� �κ�
		// p1~ p3���� ��ü������ �ϸ鼭 FlowLayout�� ����ؼ� ȭ���ġ
		p1 = new Panel(new FlowLayout(FlowLayout.CENTER));
		p2 = new Panel(new FlowLayout(FlowLayout.CENTER));
		p3 = new Panel(new FlowLayout(FlowLayout.CENTER));
		p4 = new Panel(new FlowLayout(FlowLayout.CENTER));

		//��ư �г�
		p9 = new Panel();
		p10 = new Panel();

		//���� �г� ȸ������
		pt = new Panel();
		//table �г�
		tp1 = new Panel();
		tp2 = new Panel();

		// label���� ������ �̸��� ���� Label��ü���� ����
		la_name = new Label("�� ��");
		la_id = new Label("I D");
		la_pass = new Label("��� ��ȣ");
		la_age = new Label("����");
		la_title = new Label("ȸ�� ����");
		
		// ���� 20�̶�� ���̸� ����ä�� TextField ��ü�� ����
		tf_name = new TextField(20);
		tf_id = new TextField(20);
		tf_pass = new TextField(20);
		tf_pass.setEchoChar('*');
		tf_age=new TextField(20);
		
		// ��ư 
		btn2 = new Button();
		btn2.setLabel("���");
		btn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if (obj.equals(btn2)) {
					String id=tf_id.getText().trim();
					String pw=tf_pass.getText().trim();
					String name=tf_name.getText().trim();
					String age=tf_age.getText().trim();
					System.out.println(id);
					System.out.println(pw);
					System.out.println(name);
					System.out.println(age);
					try {
						Login client = new Login();
						client.out.println("[member]");
						client.out.println(id);
						client.out.println(pw);
						client.out.println(name);
						client.out.println(age);
						int line = client.in.read();
						System.out.println(line);
						if(line==49)
						{
							new Login().setVisible(true);
							setVisible(false);
						}
						else
						{
							System.out.println("�̹� �����ϴ� ���̵��Դϴ�");
							JFrame frm = new JFrame();
							JOptionPane.showMessageDialog(frm, "�̹� �����ϴ� ���̵� �Դϴ�", "���", JOptionPane.WARNING_MESSAGE);
						}
						String remain="";
						while (!remain.equals("[END]")) 
								remain = client.in.readLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn3 = new Button("�������");
		btn3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if (obj.equals(btn3)) {
					dispose();
				}
			}
		});
		// pt�κп� label2�� ����
		pt.add(la_title);
		//�� �󺧵��� ���� �гο� �־��ش�. 
		//p1.add(la_name);
		p1.add(tf_name);
		//p2.add(la_id);
		p2.add(tf_id);
		//p3.add(la_pass);
		p3.add(tf_pass);
		p4.add(tf_age);
		tp1.setLayout(new GridLayout(8,1));
		tp1.add(la_name);
		tp1.add(la_id);
		tp1.add(la_pass);
		tp1.add(la_age);
		tp2.setLayout(new GridLayout(8,1));
		tp2.add(p1);
		tp2.add(p2);
		tp2.add(p3);
		tp2.add(p4);
		p9.add(btn2);
		p9.add(btn3);
		add("North",pt);
		add("West",tp1);
		add("Center",tp2);
		add("South",p9);
		// �������� ������ setting �κ��� �Ǳ���..
		setSize(400, 400);
		// �������� �������� �Ⱥ��̰� �������� setting�ϴ� �κ�
		// ������â ���� �޼ҵ�
	}
} 