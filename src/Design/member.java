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
	// 클래스 내에서 사용될 멤버변수들을 선언하는 부분입니다..
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
	// 생성자 정의 부분이 되겠습니다.
	member()  {
		// 화면배치를 시작하게 되는 부분
		// p1~ p3까지 객체생성을 하면서 FlowLayout을 사용해서 화면배치
		p1 = new Panel(new FlowLayout(FlowLayout.CENTER));
		p2 = new Panel(new FlowLayout(FlowLayout.CENTER));
		p3 = new Panel(new FlowLayout(FlowLayout.CENTER));
		p4 = new Panel(new FlowLayout(FlowLayout.CENTER));

		//버튼 패널
		p9 = new Panel();
		p10 = new Panel();

		//북쪽 패널 회원가입
		pt = new Panel();
		//table 패널
		tp1 = new Panel();
		tp2 = new Panel();

		// label들은 각각의 이름을 가진 Label객체들이 생성
		la_name = new Label("이 름");
		la_id = new Label("I D");
		la_pass = new Label("비밀 번호");
		la_age = new Label("나이");
		la_title = new Label("회원 가입");
		
		// 각각 20이라는 길이를 가진채로 TextField 객체가 생성
		tf_name = new TextField(20);
		tf_id = new TextField(20);
		tf_pass = new TextField(20);
		tf_pass.setEchoChar('*');
		tf_age=new TextField(20);
		
		// 버튼 
		btn2 = new Button();
		btn2.setLabel("등록");
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
							System.out.println("이미 존재하는 아이디입니다");
							JFrame frm = new JFrame();
							JOptionPane.showMessageDialog(frm, "이미 존재하는 아이디 입니다", "경고", JOptionPane.WARNING_MESSAGE);
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
		btn3 = new Button("가입취소");
		btn3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Object obj = e.getSource();
				if (obj.equals(btn3)) {
					dispose();
				}
			}
		});
		// pt부분에 label2를 붙임
		pt.add(la_title);
		//각 라벨들을 왼쪽 패널에 넣어준다. 
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
		// 프레임의 사이즈 setting 부분이 되구요..
		setSize(400, 400);
		// 프레임을 보여줄지 안보이게 숨길지를 setting하는 부분
		// 프레임창 종료 메소드
	}
} 